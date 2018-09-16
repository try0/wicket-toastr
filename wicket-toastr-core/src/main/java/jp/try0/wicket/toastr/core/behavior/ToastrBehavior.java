package jp.try0.wicket.toastr.core.behavior;

import java.util.Collections;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.FeedbackMessagesModel;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

import jp.try0.wicket.toastr.core.IToast;
import jp.try0.wicket.toastr.core.IToastOptions;
import jp.try0.wicket.toastr.core.Toast;
import jp.try0.wicket.toastr.core.Toast.ToastLevel;
import jp.try0.wicket.toastr.core.config.ToastrFontAwsomeIcons;
import jp.try0.wicket.toastr.core.config.ToastrSettings;
import jp.try0.wicket.toastr.core.resource.css.ToastrFontAwsomeCssResourceReference;

/**
 * Toastr behavior
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastrBehavior extends ToastrResourcesBehavior {
	private static final long serialVersionUID = 1L;

	/**
	 * Model that returns empty list of {@link FeedbackMessage}
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	private static final class EmptyFeedbackMessagesModel implements IModel<List<FeedbackMessage>> {
		private static final long serialVersionUID = 1L;

		/**
		 * Instance of EmptyFeedbackMessagesModel
		 */
		private final static EmptyFeedbackMessagesModel INSTANCE = new EmptyFeedbackMessagesModel();

		/**
		 * noop
		 */
		@Override
		public final void detach() {
			// noop
		}

		/**
		 * Always return results of {@link Collections#emptyList()}
		 */
		@Override
		public final List<FeedbackMessage> getObject() {
			return Collections.emptyList();
		}

		/**
		 * Always throws {@link UnsupportedOperationException}
		 */
		@Override
		public final void setObject(List<FeedbackMessage> object) {
			throw new UnsupportedOperationException(EmptyFeedbackMessagesModel.class.getName() + " is readonly.");
		}

	}

	/**
	 * Model of feedback messages
	 */
	private IModel<List<FeedbackMessage>> feedbackMessagesModel = EmptyFeedbackMessagesModel.INSTANCE;

	/**
	 * Message filter
	 */
	private IFeedbackMessageFilter messageFilter = IFeedbackMessageFilter.ALL;

	/**
	 * Constractor
	 */
	public ToastrBehavior() {
		super();
	}

	/**
	 * Constractor
	 *
	 * @param messageFilter
	 */
	public ToastrBehavior(IFeedbackMessageFilter messageFilter) {
		super();
		this.messageFilter = Args.notNull(messageFilter, "messageFilter");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void bind(Component component) {
		super.bind(component);

		feedbackMessagesModel = newFeedbackMessagesModel(component, messageFilter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void unbind(Component component) {
		super.unbind(component);

		feedbackMessagesModel = EmptyFeedbackMessagesModel.INSTANCE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void detach(Component component) {
		super.detach(component);

		// clear cache messages
		feedbackMessagesModel.detach();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onEvent(Component component, IEvent<?> event) {
		super.onEvent(component, event);

		Object payload = event.getPayload();
		if (payload instanceof AjaxRequestTarget) {
			// even when recieve an ajax request, show toast

			if (!feedbackMessagesModel.getObject().isEmpty()) {
				String script = getScriptForDisplay();
				if (!script.isEmpty()) {
					AjaxRequestTarget target = (AjaxRequestTarget) payload;

					target.appendJavaScript(script);
				}

				// clear cache messages
				feedbackMessagesModel.detach();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(Component component, IHeaderResponse response) {
		super.renderHead(component, response);

		ToastrSettings.getGlobalOptions().ifPresent(options -> {
			// toastr option setting
			response.render(JavaScriptHeaderItem.forScript(getScriptForSettingOptions(options), null));
		});

		if (!feedbackMessagesModel.getObject().isEmpty()) {
			// notification script
			response.render(JavaScriptHeaderItem.forScript(getScriptForDisplay(), null));

			ToastrSettings.getFontAwsomeIcons().ifPresent(icons -> {
				response.render(CssHeaderItem.forReference(ToastrFontAwsomeCssResourceReference.INSTANCE, null));
				response.render(CssHeaderItem
						.forCSS(ToastrFontAwsomeIcons.getStyleForAdaptIconContent(icons), null));
			});

		}

		// clear cache messages
		feedbackMessagesModel.detach();
	}

	/**
	 * Creates a {@link FeedbackMessagesModel}
	 *
	 * @param component
	 * @param messageFilter
	 * @return {@link FeedbackMessagesModel}
	 */
	protected IModel<List<FeedbackMessage>> newFeedbackMessagesModel(Component component,
			IFeedbackMessageFilter messageFilter) {
		return new FeedbackMessagesModel(component.getPage(), messageFilter);
	}

	/**
	 * Gets script for display toast with consider level of feedback messages.
	 *
	 * @return script for display toast
	 */
	protected String getScriptForDisplay() {

		List<FeedbackMessage> feedbackMessages = feedbackMessagesModel.getObject();

		if (feedbackMessages.isEmpty()) {
			return "";
		}

		StringBuilder scriptReady = new StringBuilder("$(function(){");
		for (FeedbackMessage feedbackMessage : feedbackMessages) {

			ToastLevel toastLevel = ToastLevel.fromFeedbackMessageLevel(feedbackMessage.getLevel());

			if (!toastLevel.isSupported()) {
				continue;
			}

			IToast toast = getToast(feedbackMessage);
			// create script
			final String scriptForDisplay = getScriptForDisplay(toast);
			scriptReady.append(scriptForDisplay);

			// mark rendered
			markRendered(feedbackMessage);

		}
		scriptReady.append("});");
		return scriptReady.toString();
	}

	/**
	 * Gets a toast.
	 *
	 * @param feedbackMessage
	 * @param toastLevel
	 * @return
	 */
	protected IToast getToast(FeedbackMessage feedbackMessage) {
		if (feedbackMessage.getMessage() instanceof IToast) {
			// use feedback message
			return (IToast) feedbackMessage.getMessage();
		} else {
			// create new one
			ToastLevel level = ToastLevel.fromFeedbackMessageLevel(feedbackMessage.getLevel());
			return Toast.create(level, feedbackMessage.getMessage().toString());
		}
	}

	/**
	 * Gets script for set toastr options.
	 *
	 * @return
	 */
	protected String getScriptForSettingOptions(IToastOptions options) {
		return "toastr.options =" + options.toJsonString() + ";";
	}

	/**
	 * Gets script for display toast.
	 *
	 * @param toast
	 * @return
	 */
	protected String getScriptForDisplay(IToast toast) {
		return toast.getScriptForDisplay().toString();
	}

	/**
	 * Sets whether already rendered.
	 */
	protected void markRendered(FeedbackMessage feedbackMessage) {
		// mark rendered
		feedbackMessage.markRendered();
	}

}
