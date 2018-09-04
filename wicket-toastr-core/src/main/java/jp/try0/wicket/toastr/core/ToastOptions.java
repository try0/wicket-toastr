package jp.try0.wicket.toastr.core;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import org.apache.wicket.util.time.Duration;

/**
 * Toast options<br>
 *
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastOptions implements Serializable {
	private static final long serialVersionUID = 1L;

	public final static String DEFAULT_TOAST_CLASS = "toast";
	public final static String DEFAULT_CONTAINER_ID = "toast-container";
	public final static String DEFAULT_TITLE_CLASS = "toast-title";
	public final static String DEFAULT_MESSAGE_CLASS = "toast-message";
	public final static String DEFAULT_CLOSE_CLASS = "toast-close-button";
	public final static String DEFAULT_PROGRESS_CLASS = "toast-progress";

	/**
	 * Empty options.
	 */
	public final static ToastOptions EMPTY = new ToastOptions() {
		@Override
		public String toJsonString() {
			return "";
		}
	};

	/**
	 * Option keys
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static final class OptionKeys {
		public final static String CLOSE_BUTTON = "closeButton";
		public final static String CLOSE_CLASS = "closeClass";
		public final static String CLOSE_DURATION = "closeDuration";
		public final static String CLOSE_EASING = "closeEasing";
		public final static String CLOSE_HTML = "closeHtml";
		public final static String CLOSE_METHOD = "closeMethod";
		public final static String CLOSE_ON_HOVER = "closeOnHover";
		public final static String CONTAINER_ID = "containerId";
		public final static String DEBUG = "debug";
		public final static String ESCAPE_HTML = "escapeHtml";
		public final static String EXTENDED_TIME_OUT = "extendedTimeOut";
		public final static String HIDE_DURATION = "hideDuration";
		public final static String HIDE_EASING = "hideEasing";
		public final static String HIDE_METHOD = "hideMethod";
		public final static String ICON_CLASS = "iconClass";
		public final static String ICON_CLASSES = "iconClasses";
		public final static String MESSAGE_CLASS = "messageClass";
		public final static String NEWEST_ON_TOP = "newestOnTop";
		public final static String ONCLICK = "onclick";
		public final static String ON_CLOSE_CLICK = "onCloseClick";
		public final static String ON_HIDDEN = "onHidden";
		public final static String ON_SHOWN = "onShown";
		public final static String POSITION_CLASS = "positionClass";
		public final static String PREVENT_DUPLICATES = "preventDuplicates";
		public final static String PROGRESS_BAR = "progressBar";
		public final static String PROGRESS_CLASS = "progressClass";
		public final static String RTL = "rtl";
		public final static String SHOW_DURATION = "showDuration";
		public final static String SHOW_EASING = "showEasing";
		public final static String SHOW_METHOD = "showMethod";
		public final static String TAP_TO_DISMISS = "tapToDismiss";
		public final static String TARGET = "target";
		public final static String TIME_OUT = "timeOut";
		public final static String TITLE_CLASS = "titleClass";
		public final static String TOAST_CLASS = "toastClass";
	}

	/**
	 * Option name
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	private static @interface OptionName {
		String value();
		boolean squeezDoubleQuotes() default true;
	}

	/**
	 * Show method names.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static enum ShowMethod {
		SHOW("show"),
		FADE_ID("fadeIn"),
		SLIDE_DOWN("slideDown"),
		;

		String methodName;

		private ShowMethod(String methodName) {
			this.methodName = methodName;
		}

		@Override
		public String toString() {
			return methodName;
		}
	}

	/**
	 * Hide method names.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static enum HideMethod {
		HIDE("hide"),
		FADE_OUT("fadeOut"),
		SLIDE_UP("slideUp"),
		;

		String methodName;

		private HideMethod(String methodName) {
			this.methodName = methodName;
		}

		@Override
		public String toString() {
			return methodName;
		}
	}

	/**
	 * TODO
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static enum CloseMethod {}

	/**
	 * Easing types.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static enum DisplayEasing {
		SWING("swing"),
		LINEAR("linear"),
		EASE_OUT_BOUNCE("easeOutBounce"),
		EASE_IN_BACK("easeInBack"),
		;

		String easing;

		private DisplayEasing(String easing) {
			this.easing = easing;
		}

		@Override
		public String toString() {
			return easing;
		}
	}

	/**
	 * Toast position class names.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static enum PositionClass {
		TOP_RIGHT("toast-top-right"),
		BOTTOM_RIGHT("toast-bottom-right"),
		BOTTOM_LEFT("toast-bottom-left"),
		TOP_LEFT("toast-top-left"),
		TOP_FULL_WIDTH("toast-top-full-width"),
		BOTTOM_FULL_WIDTH("toast-bottom-full-width"),
		TOP_CENTER("toast-top-center"),
		BOTTOM_CENTER("toast-bottom-center"),
		;

		String positionClassName;

		PositionClass(String positionClassName) {
			this.positionClassName = positionClassName;
		}

		@Override
		public String toString() {
			return positionClassName;
		}

	}

	/**
	 * Toast icon class names.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static enum IconClass {
        ERROR("toast-error"),
        INFO("toast-info"),
        SUCCESS("toast-success"),
        WARNING("toast-warning")
		;

		String iconClassName;

		IconClass(String iconClassName) {
			this.iconClassName = iconClassName;
		}

		@Override
		public String toString() {
			return iconClassName;
		}

	}

	@OptionName(value = OptionKeys.CLOSE_ON_HOVER, squeezDoubleQuotes = false)
	public Boolean isCloseOnHover = null;

	@OptionName(value = OptionKeys.TAP_TO_DISMISS, squeezDoubleQuotes = false)
	public Boolean isTapToDismiss = null;

	@OptionName(value = OptionKeys.TOAST_CLASS)
	public String toastClass = null;

	@OptionName(value = OptionKeys.CONTAINER_ID)
	public String containerId = null;

	@OptionName(value = OptionKeys.SHOW_EASING)
	public DisplayEasing showEasing = null;

	@OptionName(value = OptionKeys.HIDE_EASING)
	public DisplayEasing hideEasing = null;

	@OptionName(value = OptionKeys.CLOSE_EASING)
	public DisplayEasing closeEasing = null;

	@OptionName(value = OptionKeys.CLOSE_BUTTON, squeezDoubleQuotes = false)
	private Boolean isEnableCloseButton = null;

	@OptionName(value = OptionKeys.DEBUG, squeezDoubleQuotes = false)
	private Boolean isDebug = null;

	@OptionName(value = OptionKeys.NEWEST_ON_TOP, squeezDoubleQuotes = false)
	private Boolean isNewestOnTop = null;

	@OptionName(value = OptionKeys.PROGRESS_BAR, squeezDoubleQuotes = false)
	private Boolean isEnableProgressBar = null;

	@OptionName(value = OptionKeys.ESCAPE_HTML, squeezDoubleQuotes = false)
	private Boolean needEscapeHtml = null;

	@OptionName(value = OptionKeys.POSITION_CLASS)
	public PositionClass positionClass = null;

	@OptionName(value = OptionKeys.PREVENT_DUPLICATES, squeezDoubleQuotes = false)
	private Boolean needPreventDuplicates = null;

	@OptionName(value = OptionKeys.ONCLICK, squeezDoubleQuotes = false)
	public String onClickFunction = null;

	@OptionName(value = OptionKeys.ON_SHOWN, squeezDoubleQuotes = false)
	public String onShownFunction = null;

	@OptionName(value = OptionKeys.ON_HIDDEN, squeezDoubleQuotes = false)
	public String onHiddenFunction = null;

	@OptionName(value = OptionKeys.ON_CLOSE_CLICK, squeezDoubleQuotes = false)
	public String onCloseClickFunction = null;

	@OptionName(value = OptionKeys.SHOW_DURATION)
	private Duration showDuration = null;

	@OptionName(value = OptionKeys.HIDE_DURATION)
	private Duration hideDuration = null;

	@OptionName(value = OptionKeys.TIME_OUT)
	private Integer timeOut = null;

	@OptionName(value = OptionKeys.EXTENDED_TIME_OUT)
	private Integer extendedTimeOut = null;

	@OptionName(value = OptionKeys.SHOW_METHOD)
	private ShowMethod showMethod = null;

	@OptionName(value = OptionKeys.HIDE_METHOD)
	private HideMethod hideMethod = null;

	@OptionName(value = OptionKeys.CLOSE_METHOD)
	public CloseMethod closeMethod = null;

	@OptionName(value = OptionKeys.RTL, squeezDoubleQuotes = false)
	public Boolean isRightToLeft = null;

	@OptionName(value = OptionKeys.CLOSE_HTML)
	public String closeHtml = null;


	/**
	 * Gets Options as json string.
	 *
	 * @return
	 */
	public String toJsonString() {
		StringBuilder sb = new StringBuilder("{");

		for (Field field : this.getClass().getDeclaredFields()) {

			OptionName optionName = field.getAnnotation(OptionName.class);
			if (optionName == null) {
				continue;
			}

			field.setAccessible(true);

			Object option;
			try {
				option = field.get(this);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}

			if (option != null) {
				sb.append("\"").append(optionName.value()).append("\":");
				if (optionName.squeezDoubleQuotes()) {
					sb.append("\"").append(option.toString()).append("\",");
				} else {
					sb.append(option.toString()).append(",");
				}
			}


		}
		sb.append("}");
		return sb.toString();
	}

}
