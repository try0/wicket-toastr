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
	 * Option value and config.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	private static @interface ToastOption {

		String value();

		boolean squeezDoubleQuotes() default true;

	}

	public interface IAppearMethod extends Serializable {
	}

	public interface IDisappearMethod extends Serializable {
	}

	/**
	 * Show method names.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static enum ShowMethod implements IAppearMethod {
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
	public static enum HideMethod implements IDisappearMethod {
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
	 * Close method names.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static enum CloseMethod implements IDisappearMethod {
		HIDE("hide"),
		FADE_OUT("fadeOut"),
		SLIDE_UP("slideUp"),
		;

		String methodName;

		private CloseMethod(String methodName) {
			this.methodName = methodName;
		}

		@Override
		public String toString() {
			return methodName;
		}
	}

	/**
	 * Easing interface.
	 *
	 * @author ryoMac
	 *
	 */
	public static interface IEasing extends Serializable {
	}

	/**
	 * Easing types.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static enum DisplayEasing implements IEasing {
		SWING("swing"),
		LINEAR("linear"),
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

	public static interface IIconClass {
	}

	/**
	 * Toast icon class names.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static enum IconClass implements IIconClass {
		ERROR("toast-error"), INFO("toast-info"), SUCCESS("toast-success"), WARNING("toast-warning");

		String iconClassName;

		IconClass(String iconClassName) {
			this.iconClassName = iconClassName;
		}

		@Override
		public String toString() {
			return iconClassName;
		}

	}

	/**
	 * Create new one.
	 *
	 * @return
	 */
	public static ToastOptions create() {
		return new ToastOptions();
	}

	/**
	 * Constractor
	 */
	public ToastOptions() {

	}

	/**
	 * closeButton
	 */
	@ToastOption(value = OptionKeys.CLOSE_BUTTON, squeezDoubleQuotes = false)
	private Boolean isEnableCloseButton = null;

	/**
	 * closeClass
	 */
	@ToastOption(value = OptionKeys.CLOSE_CLASS)
	private String closeClass = null;

	/**
	 * closeDuration
	 */
	@ToastOption(value = OptionKeys.CLOSE_DURATION)
	private Duration closeDureation = null;

	/**
	 * closeEasing
	 */
	@ToastOption(value = OptionKeys.CLOSE_EASING)
	private IEasing closeEasing = null;

	/**
	 * closeHtml
	 */
	@ToastOption(value = OptionKeys.CLOSE_HTML)
	private String closeHtml = null;

	/**
	 * closeMethod
	 */
	@ToastOption(value = OptionKeys.CLOSE_METHOD)
	private IDisappearMethod closeMethod = null;

	/**
	 * closeOnHover
	 */
	@ToastOption(value = OptionKeys.CLOSE_ON_HOVER, squeezDoubleQuotes = false)
	private Boolean isCloseOnHover = null;

	/**
	 * containerId
	 */
	@ToastOption(value = OptionKeys.CONTAINER_ID)
	private String containerId = null;

	/**
	 * debug
	 */
	@ToastOption(value = OptionKeys.DEBUG, squeezDoubleQuotes = false)
	private Boolean isDebug = null;

	/**
	 * escapeHtml
	 */
	@ToastOption(value = OptionKeys.ESCAPE_HTML, squeezDoubleQuotes = false)
	private Boolean needEscapeHtml = null;

	/**
	 * extendedTimeOut
	 */
	@ToastOption(value = OptionKeys.EXTENDED_TIME_OUT)
	private Integer extendedTimeOut = null;

	/**
	 * hideDuration
	 */
	@ToastOption(value = OptionKeys.HIDE_DURATION)
	private Duration hideDuration = null;

	/**
	 * hideEasing
	 */
	@ToastOption(value = OptionKeys.HIDE_EASING)
	private IEasing hideEasing = null;

	/**
	 * hideMethod
	 */
	@ToastOption(value = OptionKeys.HIDE_METHOD)
	private IDisappearMethod hideMethod = null;

	/**
	 * iconClass
	 */
	@ToastOption(value = OptionKeys.ICON_CLASS)
	private IIconClass iconClass = null;

//	/**
//	 * iconClasses
//	 */
//	@ToastOption(value = OptionKeys.ICON_CLASSES)
//	private Map<String, IIconClass> iconClasses = null;

	/**
	 * messageClass
	 */
	@ToastOption(value = OptionKeys.MESSAGE_CLASS)
	private String messageClass = null;

	/**
	 * newestOnTop
	 */
	@ToastOption(value = OptionKeys.NEWEST_ON_TOP, squeezDoubleQuotes = false)
	private Boolean isNewestOnTop = null;

	/**
	 * onclick
	 */
	@ToastOption(value = OptionKeys.ONCLICK, squeezDoubleQuotes = false)
	private String onClickFunction = null;

	/**
	 * onCloseClick
	 */
	@ToastOption(value = OptionKeys.ON_CLOSE_CLICK, squeezDoubleQuotes = false)
	private String onCloseClickFunction = null;

	/**
	 * onHidden
	 */
	@ToastOption(value = OptionKeys.ON_HIDDEN, squeezDoubleQuotes = false)
	private String onHiddenFunction = null;

	/**
	 * onShown
	 */
	@ToastOption(value = OptionKeys.ON_SHOWN, squeezDoubleQuotes = false)
	private String onShownFunction = null;

	/**
	 * positionClass
	 */
	@ToastOption(value = OptionKeys.POSITION_CLASS)
	private PositionClass positionClass = null;

	/**
	 * preventDuplicates
	 */
	@ToastOption(value = OptionKeys.PREVENT_DUPLICATES, squeezDoubleQuotes = false)
	private Boolean needPreventDuplicates = null;

	/**
	 * progressBar
	 */
	@ToastOption(value = OptionKeys.PROGRESS_BAR, squeezDoubleQuotes = false)
	private Boolean isEnableProgressBar = null;

	/**
	 * progressClass
	 */
	@ToastOption(value = OptionKeys.PROGRESS_CLASS)
	private String progressClass = null;

	/**
	 * rtl
	 */
	@ToastOption(value = OptionKeys.RTL, squeezDoubleQuotes = false)
	private Boolean isRightToLeft = null;

	/**
	 * showDuration
	 */
	@ToastOption(value = OptionKeys.SHOW_DURATION)
	private Duration showDuration = null;

	/**
	 * showEasing
	 */
	@ToastOption(value = OptionKeys.SHOW_EASING)
	private IEasing showEasing = null;

	/**
	 * showMethod
	 */
	@ToastOption(value = OptionKeys.SHOW_METHOD)
	private ShowMethod showMethod = null;

	/**
	 * tapToDismiss
	 */
	@ToastOption(value = OptionKeys.TAP_TO_DISMISS, squeezDoubleQuotes = false)
	private Boolean isTapToDismiss = null;

	/**
	 * target
	 */
	@ToastOption(value = OptionKeys.TARGET)
	private String target = null;

	/**
	 * timeOut
	 */
	@ToastOption(value = OptionKeys.TIME_OUT)
	private Integer timeOut = null;

	/**
	 * titleClass
	 */
	@ToastOption(value = OptionKeys.TITLE_CLASS)
	private String titleClass = null;

	/**
	 * toastClass
	 */
	@ToastOption(value = OptionKeys.TOAST_CLASS)
	private String toastClass = null;

	/**
	 * Get closeButton
	 *
	 * @return
	 */
	public Boolean isEnableCloseButton() {
		return isEnableCloseButton;
	}

	/**
	 * Set closeButton
	 *
	 * @param isEnableCloseButton
	 * @return
	 */
	public ToastOptions setIsEnableCloseButton(Boolean isEnableCloseButton) {
		this.isEnableCloseButton = isEnableCloseButton;
		return this;
	}

	/**
	 * Get closeClass
	 *
	 * @return
	 */
	public String getCloseClass() {
		return closeClass;
	}

	/**
	 * Set closeClass
	 *
	 * @param closeClass
	 * @return
	 */
	public ToastOptions setCloseClass(String closeClass) {
		this.closeClass = closeClass;
		return this;
	}

	/**
	 * Get closeDuration
	 *
	 * @return
	 */
	public Duration getCloseDureation() {
		return closeDureation;
	}

	/**
	 * Set closeDuration
	 *
	 * @param closeDureation
	 * @return
	 */
	public ToastOptions setCloseDureation(Duration closeDureation) {
		this.closeDureation = closeDureation;
		return this;
	}

	/**
	 * Get closeEasing
	 *
	 * @return
	 */
	public IEasing getCloseEasing() {
		return closeEasing;
	}

	/**
	 * Set closeEasing
	 *
	 * @param closeEasing
	 * @return
	 */
	public ToastOptions setCloseEasing(IEasing closeEasing) {
		this.closeEasing = closeEasing;
		return this;
	}

	/**
	 * Get closeHtml
	 *
	 * @return
	 */
	public String getCloseHtml() {
		return closeHtml;
	}

	/**
	 * Set closeHtml
	 *
	 * @param closeHtml
	 * @return
	 */
	public ToastOptions setCloseHtml(String closeHtml) {
		this.closeHtml = closeHtml;
		return this;
	}

	/**
	 * Get closeMethod
	 *
	 * @return
	 */
	public IDisappearMethod getCloseMethod() {
		return closeMethod;
	}

	/**
	 * Set closeMethod
	 *
	 * @param closeMethod
	 * @return
	 */
	public ToastOptions setCloseMethod(IDisappearMethod closeMethod) {
		this.closeMethod = closeMethod;
		return this;
	}

	/**
	 * Get closeOnHover
	 *
	 * @return
	 */
	public Boolean isCloseOnHover() {
		return isCloseOnHover;
	}

	/**
	 * Set closeOnHover
	 *
	 * @param isCloseOnHover
	 * @return
	 */
	public ToastOptions setIsCloseOnHover(Boolean isCloseOnHover) {
		this.isCloseOnHover = isCloseOnHover;
		return this;
	}

	/**
	 * Get containerId
	 *
	 * @return
	 */
	public String getContainerId() {
		return containerId;
	}

	/**
	 * Set containerId
	 *
	 * @param containerId
	 * @return
	 */
	public ToastOptions setContainerId(String containerId) {
		this.containerId = containerId;
		return this;
	}

	/**
	 * Get debug
	 *
	 * @return
	 */
	public Boolean isDebug() {
		return isDebug;
	}

	/**
	 * Set debug
	 *
	 * @param isDebug
	 * @return
	 */
	public ToastOptions setIsDebug(Boolean isDebug) {
		this.isDebug = isDebug;
		return this;
	}

	/**
	 * Get escapeHtml
	 *
	 * @return
	 */
	public Boolean needEscapeHtml() {
		return needEscapeHtml;
	}

	/**
	 * Set escapeHtml
	 *
	 * @param needEscapeHtml
	 * @return
	 */
	public ToastOptions setNeedEscapeHtml(Boolean needEscapeHtml) {
		this.needEscapeHtml = needEscapeHtml;
		return this;
	}

	/**
	 * Get extendedTimeOut
	 *
	 * @return
	 */
	public Integer getExtendedTimeOut() {
		return extendedTimeOut;
	}

	/**
	 * Set extendedTimeOut
	 *
	 * @param extendedTimeOut
	 * @return
	 */
	public ToastOptions setExtendedTimeOut(Integer extendedTimeOut) {
		this.extendedTimeOut = extendedTimeOut;
		return this;
	}

	/**
	 * Get hideDuration
	 *
	 * @return
	 */
	public Duration getHideDuration() {
		return hideDuration;
	}

	/**
	 * Set hideDuration
	 *
	 * @param hideDuration
	 * @return
	 */
	public ToastOptions setHideDuration(Duration hideDuration) {
		this.hideDuration = hideDuration;
		return this;
	}

	/**
	 * Get hideEasing
	 *
	 * @return
	 */
	public IEasing getHideEasing() {
		return hideEasing;
	}

	/**
	 * Set hideEasing
	 *
	 * @param hideEasing
	 * @return
	 */
	public ToastOptions setHideEasing(IEasing hideEasing) {
		this.hideEasing = hideEasing;
		return this;
	}

	/**
	 * Get hideMethod
	 *
	 * @return
	 */
	public IDisappearMethod getHideMethod() {
		return hideMethod;
	}

	/**
	 * Set hideMethod
	 *
	 * @param hideMethod
	 * @return
	 */
	public ToastOptions setHideMethod(IDisappearMethod hideMethod) {
		this.hideMethod = hideMethod;
		return this;
	}

	/**
	 * Get iconClass
	 *
	 * @return
	 */
	public IIconClass getIconClass() {
		return iconClass;
	}

	/**
	 * Set iconClass
	 *
	 * @param iconClass
	 * @return
	 */
	public ToastOptions setIconClass(IIconClass iconClass) {
		this.iconClass = iconClass;
		return this;
	}

	/**
	 * Get messageClass
	 *
	 * @return
	 */
	public String getMessageClass() {
		return messageClass;
	}

	/**
	 * Set messageClass
	 *
	 * @param messageClass
	 * @return
	 */
	public ToastOptions setMessageClass(String messageClass) {
		this.messageClass = messageClass;
		return this;
	}

	/**
	 * Get newestOnTop
	 *
	 * @return
	 */
	public Boolean isNewestOnTop() {
		return isNewestOnTop;
	}

	/**
	 * Set newestOnTop
	 *
	 * @param isNewestOnTop
	 * @return
	 */
	public ToastOptions setIsNewestOnTop(Boolean isNewestOnTop) {
		this.isNewestOnTop = isNewestOnTop;
		return this;
	}

	/**
	 * Get onclick
	 *
	 * @return
	 */
	public String getOnClickFunction() {
		return onClickFunction;
	}

	/**
	 * Set onclick
	 *
	 * @param onClickFunction
	 * @return
	 */
	public ToastOptions setOnClickFunction(String onClickFunction) {
		this.onClickFunction = onClickFunction;
		return this;
	}

	/**
	 * Get onCloseClick
	 *
	 * @return
	 */
	public String getOnCloseClickFunction() {
		return onCloseClickFunction;
	}

	/**
	 * Set onCloseClick
	 *
	 * @param onCloseClickFunction
	 * @return
	 */
	public ToastOptions setOnCloseClickFunction(String onCloseClickFunction) {
		this.onCloseClickFunction = onCloseClickFunction;
		return this;
	}

	/**
	 * Get onHidden
	 *
	 * @return
	 */
	public String getOnHiddenFunction() {
		return onHiddenFunction;
	}

	/**
	 * Set onHidden
	 *
	 * @param onHiddenFunction
	 * @return
	 */
	public ToastOptions setOnHiddenFunction(String onHiddenFunction) {
		this.onHiddenFunction = onHiddenFunction;
		return this;
	}

	/**
	 * Get onShown
	 *
	 * @return
	 */
	public String getOnShownFunction() {
		return onShownFunction;
	}

	/**
	 * Set onShown
	 *
	 * @param onShownFunction
	 * @return
	 */
	public ToastOptions setOnShownFunction(String onShownFunction) {
		this.onShownFunction = onShownFunction;
		return this;
	}

	/**
	 * Get positionClass
	 *
	 * @return
	 */
	public PositionClass getPositionClass() {
		return positionClass;
	}

	/**
	 * Set positionClass
	 *
	 * @param positionClass
	 * @return
	 */
	public ToastOptions setPositionClass(PositionClass positionClass) {
		this.positionClass = positionClass;
		return this;
	}

	/**
	 * Get preventDuplicates
	 *
	 * @return
	 */
	public Boolean needPreventDuplicates() {
		return needPreventDuplicates;
	}

	/**
	 * Set preventDuplicates
	 *
	 * @param needPreventDuplicates
	 * @return
	 */
	public ToastOptions setNeedPreventDuplicates(Boolean needPreventDuplicates) {
		this.needPreventDuplicates = needPreventDuplicates;
		return this;
	}

	/**
	 * Get progressBar
	 *
	 * @return
	 */
	public Boolean isEnableProgressBar() {
		return isEnableProgressBar;
	}

	/**
	 * Set progressBar
	 *
	 * @param isEnableProgressBar
	 * @return
	 */
	public ToastOptions setIsEnableProgressBar(Boolean isEnableProgressBar) {
		this.isEnableProgressBar = isEnableProgressBar;
		return this;
	}

	/**
	 * Get progressClass
	 *
	 * @return
	 */
	public String getProgressClass() {
		return progressClass;
	}

	/**
	 * Set progressClass
	 *
	 * @param progressClass
	 * @return
	 */
	public ToastOptions setProgressClass(String progressClass) {
		this.progressClass = progressClass;
		return this;
	}

	/**
	 * Get rtl
	 *
	 * @return
	 */
	public Boolean isRightToLeft() {
		return isRightToLeft;
	}

	/**
	 * Set rtl
	 *
	 * @param isRightToLeft
	 * @return
	 */
	public ToastOptions setIsRightToLeft(Boolean isRightToLeft) {
		this.isRightToLeft = isRightToLeft;
		return this;
	}

	/**
	 * Get showDuration
	 *
	 * @return
	 */
	public Duration getShowDuration() {
		return showDuration;
	}

	/**
	 * Set showDuration
	 *
	 * @param showDuration
	 * @return
	 */
	public ToastOptions setShowDuration(Duration showDuration) {
		this.showDuration = showDuration;
		return this;
	}

	/**
	 * Get showEasing
	 *
	 * @return
	 */
	public IEasing getShowEasing() {
		return showEasing;
	}

	/**
	 * Set showEasing
	 *
	 * @param showEasing
	 * @return
	 */
	public ToastOptions setShowEasing(IEasing showEasing) {
		this.showEasing = showEasing;
		return this;
	}

	/**
	 * Get showMethod
	 *
	 * @return
	 */
	public ShowMethod getShowMethod() {
		return showMethod;
	}

	/**
	 * Set showMethod
	 *
	 * @param showMethod
	 * @return
	 */
	public ToastOptions setShowMethod(ShowMethod showMethod) {
		this.showMethod = showMethod;
		return this;
	}

	/**
	 * Get tapToDismiss
	 *
	 * @return
	 */
	public Boolean isTapToDismiss() {
		return isTapToDismiss;
	}

	/**
	 * Set tapToDismiss
	 *
	 * @param isTapToDismiss
	 * @return
	 */
	public ToastOptions setIsTapToDismiss(Boolean isTapToDismiss) {
		this.isTapToDismiss = isTapToDismiss;
		return this;
	}

	/**
	 * Get target
	 *
	 * @return
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * Set target
	 *
	 * @param target
	 * @return
	 */
	public ToastOptions setTarget(String target) {
		this.target = target;
		return this;
	}

	/**
	 * Get timeOut
	 *
	 * @return
	 */
	public Integer getTimeOut() {
		return timeOut;
	}

	/**
	 * Set timeOut
	 *
	 * @param timeOut
	 * @return
	 */
	public ToastOptions setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
		return this;
	}

	/**
	 * Get titleClass
	 *
	 * @return
	 */
	public String getTitleClass() {
		return titleClass;
	}

	/**
	 * Set titleClass
	 *
	 * @param titleClass
	 * @return
	 */
	public ToastOptions setTitleClass(String titleClass) {
		this.titleClass = titleClass;
		return this;
	}

	/**
	 * Get toastClass
	 *
	 * @return
	 */
	public String getToastClass() {
		return toastClass;
	}

	/**
	 * Set toastClass
	 *
	 * @param toastClass
	 * @return
	 */
	public ToastOptions setToastClass(String toastClass) {
		this.toastClass = toastClass;
		return this;
	}

	/**
	 * Gets Options as json string.
	 *
	 * @return
	 */
	public String toJsonString() {
		StringBuilder sb = new StringBuilder("{");

		for (Field field : this.getClass().getDeclaredFields()) {

			ToastOption option = field.getAnnotation(ToastOption.class);
			if (option == null) {
				continue;
			}

			field.setAccessible(true);

			Object optionValue;
			try {
				optionValue = field.get(this);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}

			if (optionValue != null) {

				sb.append("\"").append(option.value()).append("\":");
				if (option.squeezDoubleQuotes()) {
					sb.append("\"").append(optionValue.toString()).append("\",");
				} else {
					sb.append(optionValue.toString()).append(",");
				}
			}

		}
		sb.append("}");
		return sb.toString();
	}

}
