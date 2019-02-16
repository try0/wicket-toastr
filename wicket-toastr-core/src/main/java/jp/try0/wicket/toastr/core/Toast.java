package jp.try0.wicket.toastr.core;

import java.util.Optional;

import org.apache.wicket.Component;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.util.lang.Args;
import org.danekja.java.util.function.serializable.SerializableSupplier;

import jp.try0.wicket.toastr.core.behavior.ToastrBehavior;

/**
 * Toast
 *
 * @author Ryo Tsunoda
 *
 */
public class Toast implements IToast {
	private static final long serialVersionUID = 1L;

	/**
	 * Toast levels
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static enum ToastLevel {
		/**
		 * Undefined level
		 */
		UNDEFINED("undefined", FeedbackMessage.UNDEFINED, false),

		/**
		 * Success level
		 */
		SUCCESS("success", FeedbackMessage.SUCCESS, true),

		/**
		 * Information level
		 */
		INFO("info", FeedbackMessage.INFO, true),

		/**
		 * Warning level
		 */
		WARNING("warning", FeedbackMessage.WARNING, true),

		/**
		 * Error level
		 */
		ERROR("error", FeedbackMessage.ERROR, true),;

		/**
		 * Feedback message level
		 */
		int feedbackMessageLevel;

		/**
		 * Level string. Except for {@link ToastLevel#UNDEFINED}, it's same as toastr's method name that display messages.
		 */
		String level;

		/**
		 * Whether toast can be displayed.
		 */
		boolean isSupported;

		/**
		 * Constractor
		 *
		 * @param level level string
		 * @param isSupported Whether it is toastr's method name to display the message
		 */
		ToastLevel(String level, int feedbackMessageLevel, boolean isSupported) {
			this.feedbackMessageLevel = feedbackMessageLevel;
			this.level = level;
			this.isSupported = isSupported;
		}

		/**
		 * Gets level as string. Except for {@link ToastLevel#UNDEFINED}, it's same as toastr's method name that display messages.
		 *
		 * @return a level string
		 */
		public String getLevelString() {
			return this.level;
		}

		/**
		 * Gets whether toast can be displayed.
		 *
		 * @return true if toast can be displayed, otherwise false
		 */
		public boolean isSupported() {
			return this.isSupported;
		}

		/**
		 * Whether is less than argument's level.
		 *
		 * @param level the level to be compared to this level
		 * @return true if this level is less than argument's level, otherwise false
		 */
		public boolean lessThan(ToastLevel level) {
			return this.feedbackMessageLevel < level.feedbackMessageLevel;
		}

		/**
		 * Whether is greater than argument's level.
		 *
		 * @param level the level to be compared to this level
		 * @return true if this level is greater than argument's level, otherwise false
		 */
		public boolean greaterThan(ToastLevel level) {
			return this.feedbackMessageLevel > level.feedbackMessageLevel;
		}

		/**
		 * Convert feedback message level to toast level.
		 *
		 * @param levelOfFeedbackMessage
		 *            the feedback message's level
		 * @return the toast level
		 */
		public static ToastLevel fromFeedbackMessageLevel(int levelOfFeedbackMessage) {

			for (FeedbackMessageLevel messageLevel : FeedbackMessageLevel.values()) {
				if (messageLevel.toInteger() == levelOfFeedbackMessage) {
					return fromFeedbackMessageLevel(messageLevel);
				}
			}

			return ToastLevel.UNDEFINED;
		}

		/**
		 * Converts feedback message level to toast level.
		 *
		 * @param level
		 *            the feedback message's level
		 * @return the toast level
		 */
		public static ToastLevel fromFeedbackMessageLevel(FeedbackMessageLevel level) {

			switch (level) {
			case DEBUG:
			case ERROR:
			case FATAL:
				return ToastLevel.ERROR;
			case INFO:
				return ToastLevel.INFO;
			case SUCCESS:
				return ToastLevel.SUCCESS;
			case WARNING:
				return ToastLevel.WARNING;
			default:
				return ToastLevel.UNDEFINED;
			}

		}
	}

	/**
	 * Enum of FeedbackMessage's level constants.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public enum FeedbackMessageLevel {
		/**
		 * Undefined level
		 */
		UNDEFINED(FeedbackMessage.UNDEFINED),

		/**
		 * Debug level
		 */
		DEBUG(FeedbackMessage.DEBUG),

		/**
		 * Info level
		 */
		INFO(FeedbackMessage.INFO),

		/**
		 * Success level
		 */
		SUCCESS(FeedbackMessage.SUCCESS),

		/**
		 * Warning level
		 */
		WARNING(FeedbackMessage.WARNING),

		/**
		 * Error level
		 */
		ERROR(FeedbackMessage.ERROR),

		/**
		 * Fatal level
		 */
		FATAL(FeedbackMessage.FATAL),;

		/**
		 * {@link FeedbackMessage}'s constant of message level.
		 */
		int level;

		/**
		 * Constractor
		 *
		 * @param level
		 */
		private FeedbackMessageLevel(int level) {
			this.level = level;
		}

		/**
		 * Gets message level as int.
		 *
		 * @return the {@link FeedbackMessage}'s level int value
		 */
		public int toInteger() {
			return level;
		}

		/**
		 * Converts toast level to feedback message level.
		 *
		 * @param toastLevel the toast level
		 * @return the feedback message's level
		 */
		public static FeedbackMessageLevel fromToastLevel(ToastLevel toastLevel) {

			switch (toastLevel) {
			case ERROR:
				return FeedbackMessageLevel.ERROR;
			case INFO:
				return FeedbackMessageLevel.INFO;
			case SUCCESS:
				return FeedbackMessageLevel.SUCCESS;
			case WARNING:
				return FeedbackMessageLevel.WARNING;
			default:
				return FeedbackMessageLevel.UNDEFINED;
			}

		}
	}

	/**
	 * Creates a toast.
	 *
	 * @param level the toast level
	 * @param message the string displayed on toast
	 * @return a toast
	 */
	public static Toast create(ToastLevel level, String message) {
		return new Toast(level, message);
	}

	/**
	 * Creates a success toast.
	 *
	 * @param message the string displayed on toast
	 * @return a success level toast
	 */
	public static Toast success(String message) {
		return new Toast(ToastLevel.SUCCESS, message);
	}

	/**
	 * Creates an information toast.
	 *
	 * @param message the string displayed on toast
	 * @return an information level toast
	 */
	public static Toast info(String message) {
		return new Toast(ToastLevel.INFO, message);
	}

	/**
	 * Creates a warning toast.
	 *
	 * @param message the string displayed on toast
	 * @return a warning level toast
	 */
	public static Toast warn(String message) {
		return new Toast(ToastLevel.WARNING, message);
	}

	/**
	 * Creates a error toast.
	 *
	 * @param message the string displayed on toast
	 * @return a error level toast
	 */
	public static Toast error(String message) {
		return new Toast(ToastLevel.ERROR, message);
	}

	/**
	 * Remove current toasts without using animation.
	 *
	 * @param response the response object
	 */
	public static void remove(final IHeaderResponse response) {
		response.render(JavaScriptHeaderItem.forScript("toastr.remove();", null));
	}

	/**
	 * Remove current toasts without using animation.
	 *
	 * @param target the request target
	 */
	public static void remove(final IPartialPageRequestHandler target) {
		target.appendJavaScript("toastr.remove();");
	}

	/**
	 * Remove current toasts using animation.
	 *
	 * @param response the response object
	 */
	public static void clear(final IHeaderResponse response) {
		response.render(JavaScriptHeaderItem.forScript("toastr.clear();", null));
	}

	/**
	 * Remove current toasts using animation.
	 *
	 * @param target the request target
	 */
	public static void clear(final IPartialPageRequestHandler target) {
		target.appendJavaScript("toastr.clear();");
	}

	/**
	 * Toast level
	 */
	private ToastLevel level;

	/**
	 * Toast message
	 */
	private String message;

	/**
	 * Toast title
	 */
	private String title;

	/**
	 * Option for override global option
	 */
	private ToastOption option;

	/**
	 * Constractor
	 *
	 * @param level the toast level
	 * @param message the string displayed on toast
	 */
	public Toast(ToastLevel level, String message) {
		this(level, message, null);
	}

	/**
	 * Constractor
	 *
	 * @param level the toast level
	 * @param message the string displayed on toast
	 * @param title the string displayed on toast
	 */
	public Toast(ToastLevel level, String message, String title) {
		this(level, message, title, null);
	}

	/**
	 * Constractor
	 *
	 * @param level the toast level
	 * @param message the string displayed on toast
	 * @param title the string displayed on toast
	 * @param option option for override global option
	 */
	public Toast(ToastLevel level, String message, String title, ToastOption option) {
		if (!level.isSupported()) {
			throw new IllegalArgumentException("This level is unsupported.");
		}

		this.level = Args.notNull(level, "level");
		this.message = Args.notNull(message, "message");
		this.title = title;
		this.option = option;

	}

	/**
	 * Sets title.
	 *
	 * @param title the string displayed on toast
	 * @return this
	 */
	public Toast withTitle(String title) {
		this.title = Args.notNull(title, "title");
		return this;
	}

	/**
	 * Sets title if the condition is true.
	 *
	 * @param canSet the condition that the title can be set
	 * @param titleFactory factory of title string
	 * @return this
	 */
	public Toast withTitle(boolean canSet, SerializableSupplier<String> titleFactory) {
		if (canSet) {
			withTitle(titleFactory.get());
		}
		return this;
	}

	/**
	 * Gets title.
	 *
	 * @return an {@code Optional} with a title string
	 */
	@Override
	public Optional<String> getTitle() {
		return Optional.ofNullable(title);
	}

	/**
	 * Sets override option.
	 *
	 * @param option the option to override global option
	 * @return this
	 */
	public Toast withToastOption(ToastOption option) {
		this.option = option;
		return this;
	}

	/**
	 * Sets override option if the condition is true.
	 *
	 * @param canSet the condition that the option can be set
	 * @param optionFactory factory of {@link ToastOption}
	 * @return this
	 */
	public Toast withToastOption(boolean canSet, SerializableSupplier<ToastOption> optionFactory) {
		if (canSet) {
			this.option = optionFactory.get();
		}
		return this;
	}

	/**
	 * Gets option.
	 *
	 * @return an {@code Optional} with a toast option
	 */
	@Override
	public Optional<ToastOption> getToastOption() {
		return Optional.ofNullable(option);
	}

	/**
	 * Gets toast level.
	 *
	 * @return the toast level
	 */
	@Override
	public ToastLevel getToastLevel() {
		return level;
	}

	/**
	 * Gets message.
	 *
	 * @return the toast message
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * Replaces \r\n and \r to &lt;br&gt; tag.
	 *
	 * @param value the target that to replace newline code with a br tag
	 * @return a string that replaced a newline code with a br tag
	 */
	private static String replaceNewlineCodeToTag(String value) {
		return value.replaceAll("\r\n", "\n").replaceAll("\r", "\n").replaceAll("\n", "<br/>");
	}

	/**
	 * Creates script for displaying toast with consider level.
	 *
	 * @return the script for displaying toast
	 */
	@Override
	public String getScriptForDisplay() {
		final StringBuilder script = new StringBuilder();
		script.append("toastr.").append(level.getLevelString()).append("(\"").append(replaceNewlineCodeToTag(message))
				.append("\"");

		// sets title
		Optional<String> title = getTitle();
		if (title.isPresent()) {
			script.append(", \"").append(replaceNewlineCodeToTag(title.get())).append("\"");
		} else {
			script.append(", \"\"");
		}

		// sets override options
		getToastOption().ifPresent(opt -> {
			script.append(", ").append(opt.toJsonString());
		});

		script.append(");");

		return script.toString();
	}

	/**
	 * Shows toast.
	 *
	 * @param target the request target
	 */
	public void show(final IPartialPageRequestHandler target) {
		target.appendJavaScript(getScriptForDisplay());
	}

	/**
	 * Shows toast.
	 *
	 * @param responce responce object
	 */
	public void show(final IHeaderResponse responce) {
		responce.render(JavaScriptHeaderItem.forScript(getScriptForDisplay(), null));
	}

	/**
	 * Shows toast.<br>
	 * This method need to add {@link ToastrBehavior} to any of component in page.
	 *
	 * @param component the reporting component
	 */
	public void show(final Component component) {
		switch (level) {
		case ERROR:
			component.error(this);
			break;
		case INFO:
			component.info(this);
			break;
		case SUCCESS:
			component.success(this);
			break;
		case WARNING:
			component.warn(this);
			break;
		default:
			throw new IllegalArgumentException("This level is unsupported.");

		}
	}

}
