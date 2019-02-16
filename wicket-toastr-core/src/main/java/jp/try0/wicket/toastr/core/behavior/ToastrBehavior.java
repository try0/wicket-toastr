package jp.try0.wicket.toastr.core.behavior;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import jp.try0.wicket.toastr.core.IToastOption;
import jp.try0.wicket.toastr.core.Toast;
import jp.try0.wicket.toastr.core.Toast.ToastLevel;
import jp.try0.wicket.toastr.core.ToastOption;
import jp.try0.wicket.toastr.core.EachLevelToastOptions;
import jp.try0.wicket.toastr.core.config.ToastrSetting;

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
	 * Combiner that combines messages for each toast level.<br>
	 * This class works when there are multiple messages for the level.
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

			@Override
			public String getPrefix() {
				return "";
			}

			@Override
			public String getSuffix() {
				return "";
			}
		};

		/**
		 * Default delimiter string.
		 */
		public static final String DEFAULT_SUFFIX = "<br>";

		// dummy to append suffix. this toast's level has no meaning.
		private static final Toast IDENTITY = Toast.success("");

		/**
		 * Prefix of each message
		 */
		private String prefix = "";

		/**
		 * Suffix of each message
		 */
		private String suffix = DEFAULT_SUFFIX;

		/**
		 * Constractor
		 */
		public ToastMessageCombiner() {
		}

		/**
		 * Gets prefix of each message.
		 *
		 * @return the prefix
		 */
		public String getPrefix() {
			return prefix;
		}

		/**
		 * Sets prefix of each message.
		 *
		 * @param prefix the prefix
		 */
		public void setPrefix(String prefix) {
			this.prefix = prefix;
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
		 * @param suffix the suffix
		 */
		public void setSuffix(String suffix) {
			this.suffix = suffix;
		}

		/**
		 * Combines messages for each toast level.
		 *
		 * @param toastStream the target
		 * @return the stream that combined messages for each toast level
		 */
		public Stream<IToast> combine(Stream<IToast> toastStream) {

			Map<ToastLevel, List<IToast>> groupByLevel = toastStream
					.collect(Collectors.groupingBy(IToast::getToastLevel));

			return groupByLevel.entrySet().stream()
					.filter(es -> !es.getValue().isEmpty())
					.map(es -> es.getValue())
					.map(toasts -> {
						return toasts.stream()
								.reduce(IDENTITY, (joined, t) -> {
									return combine(joined, t);
								});
					});
		}

		/**
		 * Combines toasts.
		 *
		 * @param combined the combined toast
		 * @param target the uncombined toast
		 * @return the toast that combine combined and target
		 */
		public IToast combine(IToast combined, IToast target) {

			// combine messages
			String decoratedMessage = decorateMessage(target.getMessage(), getPrefix(), getSuffix());
			String concatenatedMessage = combined.getMessage() + decoratedMessage;
			final Toast newToast = Toast.create(target.getToastLevel(), concatenatedMessage);

			// select toast title
			Optional<String> title = decideTitle(combined, target);
			title.ifPresent(val -> newToast.withTitle(val));

			// combine toast options
			Optional<ToastOption> option = decideToastOption(combined, target);
			option.ifPresent(val -> newToast.withToastOption(val));

			return newToast;
		}

		/**
		 * Decides toast option.
		 *
		 * @param combined the combined toast
		 * @param target the uncombined toast
		 * @return the option to apply
		 */
		protected Optional<ToastOption> decideToastOption(IToast combined, IToast target) {

			Optional<ToastOption> optCombinedOption = combined.getToastOption();
			Optional<ToastOption> optTargetOption = target.getToastOption();

			if (optTargetOption.isPresent()) {
				if (optCombinedOption.isPresent()) {

					ToastOption optionOfCombined = optCombinedOption.get();
					ToastOption optionOfTarget = optTargetOption.get();

					return Optional.of(optionOfCombined.overwrite(optionOfTarget));
				} else {
					return optTargetOption;
				}
			}

			return Optional.empty();
		}

		/**
		 * Decides toast title.
		 *
		 * @param combined the combined toast
		 * @param target the uncombined toast
		 * @return the title to display
		 */
		protected Optional<String> decideTitle(IToast combined, IToast target) {
			return target.getTitle();
		}

		/**
		 * Decorates message.
		 *
		 * @param message the toast message
		 * @param prefix the message prefix
		 * @param suffix the message suffix
		 * @return decorated message
		 */
		protected String decorateMessage(String message, String prefix, String suffix) {
			return prefix + message + suffix;
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
		private static final EmptyFeedbackMessagesModel INSTANCE = new EmptyFeedbackMessagesModel();

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
	private IFeedbackMessageFilter messageFilter;

	/**
	 * Message combiner
	 */
	private ToastMessageCombiner messageCombiner = ToastrSetting.get().getToastMessageCombiner();

	/**
	 * Constractor
	 */
	public ToastrBehavior() {
		super();
		ToastrSetting.get().getMessageFilter().ifPresent(filter -> this.messageFilter = filter);
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

		ToastrSetting.get().getGlobalOption().ifPresent(option -> {
			// toastr global option setting
			response.render(JavaScriptHeaderItem.forScript(getScriptForSettingOption(option), null));
		});

		ToastrSetting.get().getFontAwesomeSettings().ifPresent(faSettings -> {
			// toastr FontAwesome icons setting
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
	 * Sets filter to apply.
	 *
	 * @param messageFilter the message filter
	 */
	public void setMessageFilter(IFeedbackMessageFilter messageFilter) {
		this.messageFilter = Args.notNull(messageFilter, "messageFilter");
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
	 * Creates a {@link FeedbackMessagesModel}.
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

	/**
	 * Converts to stream.
	 *
	 * @param feedbackMessages the feedback messages
	 * @return the stream of toast that to display
	 */
	private Stream<IToast> toToastStream(List<FeedbackMessage> feedbackMessages) {
		return feedbackMessages.stream()
				.filter(fm -> ToastLevel.fromFeedbackMessageLevel(fm.getLevel()).isSupported())
				.map(fm -> {
					markRendered(fm);
					return fm;
				})
				.map(fm -> getToast(fm));
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
			Toast toast = Toast.create(level, feedbackMessage.getMessage().toString());

			return applyDefaultOption(toast);
		}
	}

	/**
	 * Applies default option to toast.
	 *
	 * @param toast the toast
	 * @return toast
	 */
	private Toast applyDefaultOption(Toast toast) {
		EachLevelToastOptions defaultOptions = ToastrSetting.get().getGlobalEachLevelOptions();
		defaultOptions.get(toast.getToastLevel()).ifPresent(option -> {
			toast.withToastOption(option);
		});
		return toast;
	}

	/**
	 * Gets script for setting toastr option.
	 *
	 * @param option the option to specify toast styles, behaviors...
	 * @return script for setting toastr option
	 */
	protected String getScriptForSettingOption(final IToastOption option) {
		return "toastr.options =" + option.toJsonString() + ";";
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
