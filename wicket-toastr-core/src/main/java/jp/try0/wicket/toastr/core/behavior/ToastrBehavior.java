package jp.try0.wicket.toastr.core.behavior;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import jp.try0.wicket.toastr.core.ToastOptions;
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
	 * Combiner that combines messages for each toast level.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static class ToastMessageCombiner implements Serializable {
		private static final long serialVersionUID = 1L;

		/**
		 * Void action.
		 */
		public static final ToastMessageCombiner VOID_COMBINER = new ToastMessageCombiner() {
			private static final long serialVersionUID = 1L;

			@Override
			public Stream<IToast> combine(Stream<IToast> toastStream) {
				return toastStream;
			}
		};

		/**
		 * Default delimiter string.
		 */
		public static final String DEFAULT_DELIMITER = "<br>";

		/**
		 * Suffix of each message
		 */
		private String suffix = "";

		/**
		 * Delimiter
		 */
		private String delimiter = DEFAULT_DELIMITER;


		/**
		 * Constractor
		 */
		public ToastMessageCombiner() {
		}

		/**
		 * Gets suffix of each message.
		 *
		 * @return the suffix
		 */
		public String getSuffix() {
			return suffix;
		}

		/**
		 * Sets suffix of each message.
		 *
		 * @param suffix
		 */
		public void setSuffix(String suffix) {
			this.suffix = suffix;
		}

		/**
		 * Gets delimiter.
		 *
		 * @return the delimiter
		 */
		public String getDelimiter() {
			return delimiter;
		}

		/**
		 * Sets delimiter.
		 *
		 * @param delimiter
		 */
		public void setDelimiter(String delimiter) {
			this.delimiter = delimiter;
		}

		/**
		 * Combines messages for each toast level.
		 *
		 * @param toastStream the target
		 * @return the stream that combined messages for each toast level
		 */
		public Stream<IToast> combine(Stream<IToast> toastStream) {

			Map<ToastLevel, List<IToast>> groupByLevel =
					toastStream.collect(Collectors.groupingBy(IToast::getToastLevel));

			return groupByLevel.entrySet().stream()
					.map(es -> es.getValue().stream()
							.reduce((joined, t) -> {
								String concatMessage = getSuffix() + joined.getMessage() + getDelimiter() + getSuffix() + t.getMessage();
								Toast newToast = Toast.create(joined.getToastLevel(), concatMessage);

								// combine toast options
								if (t.getToastOptions().isPresent()) {
									if (joined.getToastOptions().isPresent()) {
										ToastOptions overwritten = joined.getToastOptions().get().overwrite(t.getToastOptions().get());
										newToast.withToastOptions(overwritten);
									} else {
										newToast.withToastOptions(t.getToastOptions().get());
									}
								}
								return newToast;
							}))
					.map(optToast -> optToast.get());
		}

	}

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
	 * Message combiner
	 */
	private ToastMessageCombiner messageCombiner = ToastMessageCombiner.VOID_COMBINER;



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
	 * Sets combiner that combines messages for each toast level.
	 *
	 * @param messageCombiner the message combiner
	 */
	public void setMessageCombiner(ToastMessageCombiner messageCombiner) {
		this.messageCombiner = Args.notNull(messageCombiner, "messageCombiner");
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

		Stream<IToast> toastStream = messageCombiner.combine(toToastStream(feedbackMessages));

		final StringBuilder scripts = new StringBuilder();
		toastStream.forEachOrdered(toast -> {
			// create script
			scripts.append(getScriptForDisplay(toast));
		});
		return scripts.toString();
	}

	private Stream<IToast> toToastStream(List<FeedbackMessage> feedbackMessages) {
		return feedbackMessages.stream()
				.filter(fm -> ToastLevel.fromFeedbackMessageLevel(fm.getLevel()).isSupported())
				.peek(fm -> markRendered(fm))
				.map(fm ->  getToast(fm));
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
