package jp.try0.wicket.toastr.core;

import java.io.Serializable;
import java.util.Optional;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.util.lang.Args;

import jp.try0.wicket.toastr.core.behavior.ToastrBehavior;
import jp.try0.wicket.toastr.core.feedback.FeedbackToast;

/**
 * Toast
 *
 * @author Ryo Tsunoda
 *
 */
public class Toast implements IToast, Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Toast levels
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public enum ToastLevel {
		/**
		 * Undefined level
		 */
		UNDEFINED("undefined", false),

		/**
		 * Success level
		 */
		SUCCESS("success", true),

		/**
		 * Information level
		 */
		INFO("info", true),

		/**
		 * Warning level
		 */
		WARNING("warning", true),

		/**
		 * Error level
		 */
		ERROR("error", true),
		;

		/**
		 * Short level string. Same as toastr's method name that display messages.
		 */
		String level;

		/**
		 * Whether toast can be displayed.
		 */
		boolean isSupported;


		/**
		 * Constractor
		 *
		 * @param level
		 * @param isSupported
		 */
		ToastLevel(String level, boolean isSupported) {
			this.level = level;
			this.isSupported = isSupported;
		}

		/**
		 * Gets level string. Same as toastr's method name that display messages.
		 */
		public String getLevelString() {
			return this.level;
		}

		/**
		 * Gets whether toast can be displayed.
		 */
		public boolean isSupported() {
			return this.isSupported;
		}

		/**
		 * Convert feedback message level to toast level.
		 *
		 * @param levelOfFeedbackMessage feedback message level
		 * @return toast level
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
		 * @param level feedback message level
		 * @return toast level
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
		FATAL(FeedbackMessage.FATAL),
		;

		/**
		 * FeedbackMessage's constant of message level.
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
		 * @return
		 */
		public int toInteger() {
			return level;
		}

		/**
		 * Converts toast level to feedback message level.
		 *
		 * @param level
		 * @return
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
	 * @param level
	 * @param message
	 * @return
	 */
	public static Toast create(ToastLevel level, String message) {
		return new Toast(level, message);
	}

	/**
	 * Creates a success toast.
	 *
	 * @param message
	 * @return
	 */
	public static Toast success(String message) {
		return new Toast(ToastLevel.SUCCESS, message);
	}

	/**
	 * Creates a info toast.
	 *
	 * @param message
	 * @return
	 */
	public static Toast info(String message) {
		return new Toast(ToastLevel.INFO, message);
	}

	/**
	 * Creates a warning toast.
	 *
	 * @param message
	 * @return
	 */
	public static Toast warn(String message) {
		return new Toast(ToastLevel.WARNING, message);
	}

	/**
	 * Creates a error toast.
	 *
	 * @param message
	 * @return
	 */
	public static Toast error(String message) {
		return new Toast(ToastLevel.ERROR, message);
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
	private Optional<String> title;

	/**
	 * Options for override global options
	 */
	private Optional<ToastOptions> options;


	/**
	 * Constractor
	 *
	 * @param level
	 * @param message
	 */
	public Toast(ToastLevel level, String message) {
		this(level, message, null);
	}

	/**
	 * Constractor
	 *
	 * @param level
	 * @param message
	 * @param title
	 */
	public Toast(ToastLevel level, String message, String title) {
		this(level, message , title, null);
	}

	/**
	 * Constractor
	 *
	 * @param level
	 * @param message
	 * @param title
	 * @param options
	 */
	public Toast(ToastLevel level, String message, String title, ToastOptions options) {
		this.level = Args.notNull(level, "level");
		this.message = Args.notNull(message, "message");
		this.title = Optional.ofNullable(title);
		this.options = Optional.ofNullable(options);

		if (!level.isSupported()) {
			throw new IllegalArgumentException("This level is unsupported.");
		}
	}

	/**
	 * Sets title.
	 *
	 * @param title
	 * @return
	 */
	public Toast setToastTitle(String title) {
		this.title = Optional.ofNullable(title);
		return this;
	}

	/**
	 * Gets title.
	 *
	 * @return
	 */
	@Override
	public Optional<String> getToastTitle() {
		return title;
	}

	/**
	 * Sets override options.
	 *
	 * @param options
	 * @return
	 */
	public Toast setToastOptions(ToastOptions options) {
		this.options = Optional.ofNullable(options);
		return this;
	}

	/**
	 * Gets options.
	 *
	 * @return
	 */
	@Override
	public Optional<ToastOptions> getToastOptions() {
		return this.options;
	}

	/**
	 * Gets toast level.
	 *
	 * @return
	 */
	@Override
	public ToastLevel getToastLevel() {
		return level;
	}

	/**
	 * Gets message.
	 *
	 * @return
	 */
	@Override
	public String getToastMessage() {
		return message;
	}


	/**
	 * Creates script for display toast with consider level of feedback messages.
	 *
	 * @return
	 */
	@Override
	public String getScriptForDisplay() {
		final StringBuilder script = new StringBuilder();
		script.append("toastr.")
		.append(level.getLevelString())
		.append("(\"")
		.append(message)
		.append("\"");

		// title is optional
		if (title.isPresent()) {
			script.append(", \"").append(title.get()).append("\"");
		} else {
			script.append(", \"\"");
		}
		// override option is optional
		options.ifPresent(opt -> script.append(", ").append(opt.toJsonString()));

		script.append(");");

		return script.toString();
	}

	/**
	 * Show toast.
	 *
	 * @param target
	 */
	public void show(AjaxRequestTarget target) {
		StringBuilder script = new StringBuilder("$(function(){")
		.append(getScriptForDisplay())
		.append("});");

		target.appendJavaScript(script);
	}

	/**
	 * Show toast.
	 *
	 * @param responce
	 */
	public void show(IHeaderResponse responce) {
		StringBuilder script = new StringBuilder("$(function(){")
		.append(getScriptForDisplay())
		.append("});");

		responce.render(JavaScriptHeaderItem.forScript(script, null));
	}

	/**
	 * Show toast.<br>
	 * This method need to add {@link ToastrBehavior} to any of component in page.
	 *
	 * @param component
	 */
	public void show(Component component) {
		switch (level) {
		case ERROR:
			component.error(toFeedbackToast(component));
			break;
		case INFO:
			component.info(toFeedbackToast(component));
			break;
		case SUCCESS:
			component.success(toFeedbackToast(component));
			break;
		case UNDEFINED:
			break;
		case WARNING:
			component.warn(toFeedbackToast(component));
			break;
		default:
			break;

		}
	}

	/**
	 * Converts to FeedbackToast.
	 *
	 * @param component
	 * @return
	 */
	public FeedbackToast toFeedbackToast(Component component) {
		return new FeedbackToast(component, this);
	}

}
