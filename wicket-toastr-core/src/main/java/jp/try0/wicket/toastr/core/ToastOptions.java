package jp.try0.wicket.toastr.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * Toast options<br>
 *
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastOptions implements IToastOptions {
	private static final long serialVersionUID = 1L;

	/**
	 * Option name and config.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	private static @interface ToastOption {

		String value();

		boolean squeezeWithDoubleQuotes() default true;

	}

	/**
	 * Show method names.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static enum ShowMethod implements IAppearMethod {
		SHOW("show"),
		FADE_IN("fadeIn"),
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
	 * Easing types.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static enum Easing implements IEasing {
		SWING("swing"),
		LINEAR("linear"),
		;

		String easing;

		private Easing(String easing) {
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
	public static enum PositionClass implements IPositionClass {
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
	@ToastOption(value = OptionKeys.CLOSE_BUTTON, squeezeWithDoubleQuotes = false)
	private Boolean isEnableCloseButton = null;

	/**
	 * closeClass
	 */
	@ToastOption(value = OptionKeys.CLOSE_CLASS)
	private String closeClass = null;

	/**
	 * closeDuration
	 */
	@ToastOption(value = OptionKeys.CLOSE_DURATION, squeezeWithDoubleQuotes = false)
	private Integer closeDureation = null;

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
	@ToastOption(value = OptionKeys.CLOSE_ON_HOVER, squeezeWithDoubleQuotes = false)
	private Boolean isCloseOnHover = null;

	/**
	 * containerId
	 */
	@ToastOption(value = OptionKeys.CONTAINER_ID)
	private String containerId = null;

	/**
	 * debug
	 */
	@ToastOption(value = OptionKeys.DEBUG, squeezeWithDoubleQuotes = false)
	private Boolean isDebug = null;

	/**
	 * escapeHtml
	 */
	@ToastOption(value = OptionKeys.ESCAPE_HTML, squeezeWithDoubleQuotes = false)
	private Boolean needEscapeHtml = null;

	/**
	 * extendedTimeOut
	 */
	@ToastOption(value = OptionKeys.EXTENDED_TIME_OUT)
	private Integer extendedTimeOut = null;

	/**
	 * hideDuration
	 */
	@ToastOption(value = OptionKeys.HIDE_DURATION, squeezeWithDoubleQuotes = false)
	private Integer hideDuration = null;

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
	@ToastOption(value = OptionKeys.NEWEST_ON_TOP, squeezeWithDoubleQuotes = false)
	private Boolean isNewestOnTop = null;

	/**
	 * onclick
	 */
	@ToastOption(value = OptionKeys.ONCLICK, squeezeWithDoubleQuotes = false)
	private String onClickFunction = null;

	/**
	 * onCloseClick
	 */
	@ToastOption(value = OptionKeys.ON_CLOSE_CLICK, squeezeWithDoubleQuotes = false)
	private String onCloseClickFunction = null;

	/**
	 * onHidden
	 */
	@ToastOption(value = OptionKeys.ON_HIDDEN, squeezeWithDoubleQuotes = false)
	private String onHiddenFunction = null;

	/**
	 * onShown
	 */
	@ToastOption(value = OptionKeys.ON_SHOWN, squeezeWithDoubleQuotes = false)
	private String onShownFunction = null;

	/**
	 * positionClass
	 */
	@ToastOption(value = OptionKeys.POSITION_CLASS)
	private IPositionClass positionClass = null;

	/**
	 * preventDuplicates
	 */
	@ToastOption(value = OptionKeys.PREVENT_DUPLICATES, squeezeWithDoubleQuotes = false)
	private Boolean needPreventDuplicates = null;

	/**
	 * progressBar
	 */
	@ToastOption(value = OptionKeys.PROGRESS_BAR, squeezeWithDoubleQuotes = false)
	private Boolean isEnableProgressBar = null;

	/**
	 * progressClass
	 */
	@ToastOption(value = OptionKeys.PROGRESS_CLASS)
	private String progressClass = null;

	/**
	 * rtl
	 */
	@ToastOption(value = OptionKeys.RTL, squeezeWithDoubleQuotes = false)
	private Boolean isRightToLeft = null;

	/**
	 * showDuration
	 */
	@ToastOption(value = OptionKeys.SHOW_DURATION, squeezeWithDoubleQuotes = false)
	private Integer showDuration = null;

	/**
	 * showEasing
	 */
	@ToastOption(value = OptionKeys.SHOW_EASING)
	private IEasing showEasing = null;

	/**
	 * showMethod
	 */
	@ToastOption(value = OptionKeys.SHOW_METHOD)
	private IAppearMethod showMethod = null;

	/**
	 * tapToDismiss
	 */
	@ToastOption(value = OptionKeys.TAP_TO_DISMISS, squeezeWithDoubleQuotes = false)
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
	@Override
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
	@Override
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
	@Override
	public Integer getCloseDureation() {
		return closeDureation;
	}

	/**
	 * Set closeDuration
	 *
	 * @param closeDureation
	 * @return
	 */
	public ToastOptions setCloseDureation(Integer closeDureation) {
		this.closeDureation = closeDureation;
		return this;
	}

	/**
	 * Get closeEasing
	 *
	 * @return
	 */
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
	public Integer getHideDuration() {
		return hideDuration;
	}

	/**
	 * Set hideDuration
	 *
	 * @param hideDuration
	 * @return
	 */
	public ToastOptions setHideDuration(Integer hideDuration) {
		this.hideDuration = hideDuration;
		return this;
	}

	/**
	 * Get hideEasing
	 *
	 * @return
	 */
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
	public IPositionClass getPositionClass() {
		return positionClass;
	}

	/**
	 * Set positionClass
	 *
	 * @param positionClass
	 * @return
	 */
	public ToastOptions setPositionClass(IPositionClass positionClass) {
		this.positionClass = positionClass;
		return this;
	}

	/**
	 * Get preventDuplicates
	 *
	 * @return
	 */
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
	public Integer getShowDuration() {
		return showDuration;
	}

	/**
	 * Set showDuration
	 *
	 * @param showDuration
	 * @return
	 */
	public ToastOptions setShowDuration(Integer showDuration) {
		this.showDuration = showDuration;
		return this;
	}

	/**
	 * Get showEasing
	 *
	 * @return
	 */
	@Override
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
	@Override
	public IAppearMethod getShowMethod() {
		return showMethod;
	}

	/**
	 * Set showMethod
	 *
	 * @param showMethod
	 * @return
	 */
	public ToastOptions setShowMethod(IAppearMethod showMethod) {
		this.showMethod = showMethod;
		return this;
	}

	/**
	 * Get tapToDismiss
	 *
	 * @return
	 */
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
				if (option.squeezeWithDoubleQuotes()) {
					sb.append("\"").append(optionValue.toString()).append("\",");
				} else {
					sb.append(optionValue.toString()).append(",");
				}
			}

		}
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Gets new overwritten options.<br>
	 * If the value of the argument's option is exists, overwrite the option value.
	 *
	 * @param options
	 * @return
	 */
	@Override
	public ToastOptions overwrite(IToastOptions options) {

		return ToastOptions.create()
		.setIsEnableCloseButton(options.isEnableCloseButton() == null ? this.isEnableCloseButton : options.isEnableCloseButton())
		.setCloseClass(options. getCloseClass() == null ? this.closeClass : options.getCloseClass())
		.setCloseDureation(options. getCloseDureation() == null ? this.closeDureation : options.getCloseDureation())
		.setCloseEasing(options. getCloseEasing() == null ? this.closeEasing : options.getCloseEasing())
		.setCloseHtml(options. getCloseHtml() == null ? this.closeHtml : options.getCloseHtml())
		.setCloseMethod(options. getCloseMethod() == null ? this.closeMethod : options.getCloseMethod())
		.setIsCloseOnHover(options. isCloseOnHover() == null ? this.isCloseOnHover : options.isCloseOnHover())
		.setContainerId(options. getContainerId() == null ? this.containerId : options.getContainerId())
		.setIsDebug(options. isDebug() == null ? this.isDebug : options.isDebug())
		.setNeedEscapeHtml(options. needEscapeHtml() == null ? this.needEscapeHtml:options.needEscapeHtml())
		.setExtendedTimeOut(options. getExtendedTimeOut() == null ? this.extendedTimeOut:options.getExtendedTimeOut())
		.setHideDuration(options. getHideDuration() == null ? this.hideDuration:options.getHideDuration())
		.setHideEasing(options. getHideEasing() == null ? this.hideEasing:options.getHideEasing())
		.setHideMethod(options. getHideMethod() == null ? this.hideMethod:options.getHideMethod())
		.setIconClass(options. getIconClass() == null ? this.iconClass:options.getIconClass())
		.setMessageClass(options. getMessageClass() == null ? this.messageClass:options.getMessageClass())
		.setIsNewestOnTop(options. isNewestOnTop() == null ? this.isNewestOnTop:options.isNewestOnTop())
		.setOnClickFunction(options. getOnClickFunction() == null ? this.onClickFunction:options.getOnClickFunction())
		.setOnCloseClickFunction(options. getOnCloseClickFunction() == null ? this.onCloseClickFunction:options.getOnCloseClickFunction())
		.setOnHiddenFunction(options. getOnHiddenFunction() == null ? this.onHiddenFunction:options.getOnHiddenFunction())
		.setOnShownFunction(options. getOnShownFunction() == null ? this.onShownFunction:options.getOnShownFunction())
		.setPositionClass(options. getPositionClass() == null ? this.positionClass:options.getPositionClass())
		.setNeedPreventDuplicates(options. needPreventDuplicates() == null ? this.needPreventDuplicates:options.needPreventDuplicates())
		.setIsEnableProgressBar(options. isEnableProgressBar() == null ? this.isEnableProgressBar:options.isEnableProgressBar())
		.setProgressClass(options. getProgressClass() == null ? this.progressClass:options.getProgressClass())
		.setIsRightToLeft(options. isRightToLeft() == null ? this.isRightToLeft:options.isRightToLeft())
		.setShowDuration(options. getShowDuration() == null ? this.showDuration:options.getShowDuration())
		.setShowEasing(options. getShowEasing() == null ? this.showEasing:options.getShowEasing())
		.setShowMethod(options. getShowMethod() == null ? this.showMethod:options.getShowMethod())
		.setIsTapToDismiss(options. isTapToDismiss() == null ? this.isTapToDismiss:options.isTapToDismiss())
		.setTarget(options. getTarget() == null ? this.target:options.getTarget())
		.setTimeOut(options. getTimeOut() == null ? this.timeOut:options.getTimeOut())
		.setTitleClass(options. getTitleClass() == null ? this.titleClass:options.getTitleClass())
		.setToastClass(options. getToastClass() == null ? this.toastClass:options.getToastClass());
	}

}
