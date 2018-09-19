package jp.try0.wicket.toastr.core.behavior;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.FeedbackMessagesModel;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.Response;
import org.apache.wicket.util.lang.Args;

import jp.try0.wicket.toastr.core.IToast;
import jp.try0.wicket.toastr.core.IToastOptions;
import jp.try0.wicket.toastr.core.Toast;
import jp.try0.wicket.toastr.core.Toast.ToastLevel;
import jp.try0.wicket.toastr.core.config.ToastrSettings;

/**
 * Toastr behavior.<br>
 * This Behavior provides the function to create Toasts from FeedbackMessages.
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
		 * Always returns result of {@link Collections#emptyList()}
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
	 * Model of {@link FeedbackMessage}s
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
	 * @param messageFilter The filter to apply
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

		ToastrSettings.get().getGlobalOptions().ifPresent(options -> {
			// toastr global options setting
			response.render(JavaScriptHeaderItem.forScript(getScriptForSettingOptions(options), null));
		});

		ToastrSettings.get().getFontAwesomeSettings().ifPresent(faSettings -> {
			response.render(new HeaderItem() {
				private static final long serialVersionUID = 1L;

				@Override
				public void render(Response response) {
					response.write(faSettings.getFontAwesomeCssLinkTag());
				}

				@Override
				public Iterable<?> getRenderTokens() {
					return Arrays.asList(faSettings.getFontAwesomeCssLinkTag());
				}
			});
			response.render(CssHeaderItem.forReference(faSettings.getTweakCssReference(), null));
			response.render(CssHeaderItem
					.forCSS(faSettings.getIcons().getStyleForAdaptIconContent(), null));
		});

		if (!feedbackMessagesModel.getObject().isEmpty()) {
			// notification script
			response.render(OnDomReadyHeaderItem.forScript(getScriptForDisplay()));
		}

		// clear cache messages
		feedbackMessagesModel.detach();
	}

	/**
	 * Creates a {@link FeedbackMessage}s model.
	 *
	 * @param pageResolvingComponent The component for retrieving page instance
	 * @param messageFilter The filter to apply
	 * @return The model of {@link FeedbackMessage}s that applied filter
	 */
	protected FeedbackMessagesModel newFeedbackMessagesModel(final Component pageResolvingComponent,
			final IFeedbackMessageFilter messageFilter) {
		return new FeedbackMessagesModel(pageResolvingComponent.getPage(), messageFilter);
	}

	/**
	 * Gets script for displaying toasts with consider level of feedback messages.
	 *
	 * @return script for displaying toasts
	 */
	protected String getScriptForDisplay() {

		List<FeedbackMessage> feedbackMessages = feedbackMessagesModel.getObject();

		if (feedbackMessages.isEmpty()) {
			return "";
		}

		StringBuilder scripts = new StringBuilder();
		for (FeedbackMessage feedbackMessage : feedbackMessages) {

			ToastLevel toastLevel = ToastLevel.fromFeedbackMessageLevel(feedbackMessage.getLevel());

			if (!toastLevel.isSupported()) {
				continue;
			}

			IToast toast = getToast(feedbackMessage);
			// create script
			final String scriptForDisplay = getScriptForDisplay(toast);
			scripts.append(scriptForDisplay);

			// mark rendered
			markRendered(feedbackMessage);

		}
		return scripts.toString();
	}

	/**
	 * Gets a toast that created from {@link FeedbackMessage}.
	 *
	 * @param feedbackMessage The material for creates a toast
	 * @return The toast object
	 */
	protected IToast getToast(final FeedbackMessage feedbackMessage) {
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
	 * Gets script for setting toastr options.
	 *
	 * @param options the option to specify toast styles, behaviors...
	 * @return script for setting toastr options
	 */
	protected String getScriptForSettingOptions(final IToastOptions options) {
		return "toastr.options =" + options.toJsonString() + ";";
	}

	/**
	 * Gets script for displaying toast.
	 *
	 * @param toast the target to display
	 * @return script for displaying toast
	 */
	protected String getScriptForDisplay(final IToast toast) {
		return toast.getScriptForDisplay().toString();
	}

	/**
	 * Marks argument's message as rendered.
	 *
	 * @param feedbackMessage the target to mark as already rendered
	 */
	protected void markRendered(final FeedbackMessage feedbackMessage) {
		feedbackMessage.markRendered();
	}

}
