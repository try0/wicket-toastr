package jp.try0.wicket.toastr.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * Toast options.<br>
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

		/**
		 * Option key name.
		 *
		 * @return option key
		 */
		String value();

		/**
		 * Whether or not to squeeze with double quotes. Default is true.
		 *
		 * @return true if an option value needs squeeze with double quotes, otherwise false
		 */
		boolean squeezeWithDoubleQuotes() default true;

	}

	/**
	 * Show method names.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static enum ShowMethod implements IAppearAnimationMethod {
		SHOW("show"),
		FADE_IN("fadeIn"),
		SLIDE_DOWN("slideDown"),
		;

		/**
		 * Method name
		 */
		String methodName;

		/**
		 * Constractor
		 *
		 * @param methodName name of show method
		 */
		private ShowMethod(String methodName) {
			this.methodName = methodName;
		}

		/**
		 * Returns {@link ShowMethod#methodName}.
		 */
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
	public static enum HideMethod implements IDisappearAnimationMethod {
		HIDE("hide"),
		FADE_OUT("fadeOut"),
		SLIDE_UP("slideUp"),
		;

		/**
		 * Method name
		 */
		String methodName;

		/**
		 * Constractor
		 *
		 * @param methodName name of hide method
		 */
		private HideMethod(String methodName) {
			this.methodName = methodName;
		}

		/**
		 * Returns {@link HideMethod#methodName}.
		 */
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
	public static enum CloseMethod implements IDisappearAnimationMethod {
		HIDE("hide"),
		FADE_OUT("fadeOut"),
		SLIDE_UP("slideUp"),
		;

		/**
		 * Method name
		 */
		String methodName;

		/**
		 * Constractor
		 *
		 * @param methodName name of close method
		 */
		private CloseMethod(String methodName) {
			this.methodName = methodName;
		}

		/**
		 * Returns {@link CloseMethod#methodName}.
		 */
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

		/**
		 * Position class name
		 */
		String positionClassName;

		/**
		 * Constractor
		 *
		 * @param positionClassName the name of position class
		 */
		PositionClass(String positionClassName) {
			this.positionClassName = positionClassName;
		}

		/**
		 * Returns {@link PositionClass#positionClassName}.
		 */
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

		/**
		 * Icon class name
		 */
		String iconClassName;

		/**
		 * Constractor
		 *
		 * @param iconClassName the name of icon class
		 */
		IconClass(String iconClassName) {
			this.iconClassName = iconClassName;
		}

		/**
		 * Returns {@link IconClass#iconClassName}.
		 */
		@Override
		public String toString() {
			return iconClassName;
		}

	}

	/**
	 * Create new one.
	 *
	 * @return new {@link ToastOptions}
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
	private IDisappearAnimationMethod closeMethod = null;

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
	private IDisappearAnimationMethod hideMethod = null;

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
	private IAppearAnimationMethod showMethod = null;

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
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isEnableCloseButton() {
		return isEnableCloseButton;
	}

	/**
	 * Sets closeButton option value.
	 *
	 * @param isEnableCloseButton option value
	 * @return this
	 */
	public ToastOptions setIsEnableCloseButton(Boolean isEnableCloseButton) {
		this.isEnableCloseButton = isEnableCloseButton;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCloseClass() {
		return closeClass;
	}

	/**
	 * Sets closeClass option value.
	 *
	 * @param closeClass option value
	 * @return this
	 */
	public ToastOptions setCloseClass(String closeClass) {
		this.closeClass = closeClass;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getCloseDureation() {
		return closeDureation;
	}

	/**
	 * Sets closeDuration option value
	 *
	 * @param closeDureation option value
	 * @return this
	 */
	public ToastOptions setCloseDureation(Integer closeDureation) {
		this.closeDureation = closeDureation;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IEasing getCloseEasing() {
		return closeEasing;
	}

	/**
	 * Sets closeEasing option value.
	 *
	 * @param closeEasing option value
	 * @return this
	 */
	public ToastOptions setCloseEasing(IEasing closeEasing) {
		this.closeEasing = closeEasing;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCloseHtml() {
		return closeHtml;
	}

	/**
	 * Sets closeHtml option value.
	 *
	 * @param closeHtml option value
	 * @return this
	 */
	public ToastOptions setCloseHtml(String closeHtml) {
		this.closeHtml = closeHtml;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IDisappearAnimationMethod getCloseMethod() {
		return closeMethod;
	}

	/**
	 * Sets closeMethod option value.
	 *
	 * @param closeMethod option value
	 * @return this
	 */
	public ToastOptions setCloseMethod(IDisappearAnimationMethod closeMethod) {
		this.closeMethod = closeMethod;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isCloseOnHover() {
		return isCloseOnHover;
	}

	/**
	 * Sets closeOnHover option value.
	 *
	 * @param isCloseOnHover option value
	 * @return this
	 */
	public ToastOptions setIsCloseOnHover(Boolean isCloseOnHover) {
		this.isCloseOnHover = isCloseOnHover;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getContainerId() {
		return containerId;
	}

	/**
	 * Sets containerId option value.
	 *
	 * @param containerId option value
	 * @return this
	 */
	public ToastOptions setContainerId(String containerId) {
		this.containerId = containerId;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isDebug() {
		return isDebug;
	}

	/**
	 * Sets debug option value.
	 *
	 * @param isDebug
	 * @return
	 */
	public ToastOptions setIsDebug(Boolean isDebug) {
		this.isDebug = isDebug;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean needEscapeHtml() {
		return needEscapeHtml;
	}

	/**
	 * Sets escapeHtml option value.
	 *
	 * @param needEscapeHtml option value
	 * @return this
	 */
	public ToastOptions setNeedEscapeHtml(Boolean needEscapeHtml) {
		this.needEscapeHtml = needEscapeHtml;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getExtendedTimeOut() {
		return extendedTimeOut;
	}

	/**
	 * Sets extendedTimeOut option value.
	 *
	 * @param extendedTimeOut option value
	 * @return this
	 */
	public ToastOptions setExtendedTimeOut(Integer extendedTimeOut) {
		this.extendedTimeOut = extendedTimeOut;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getHideDuration() {
		return hideDuration;
	}

	/**
	 * Sets hideDuration option value.
	 *
	 * @param hideDuration option value
	 * @return this
	 */
	public ToastOptions setHideDuration(Integer hideDuration) {
		this.hideDuration = hideDuration;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IEasing getHideEasing() {
		return hideEasing;
	}

	/**
	 * Sets hideEasing option value
	 *
	 * @param hideEasing option value
	 * @return this
	 */
	public ToastOptions setHideEasing(IEasing hideEasing) {
		this.hideEasing = hideEasing;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IDisappearAnimationMethod getHideMethod() {
		return hideMethod;
	}

	/**
	 * Sets hideMethod option value.
	 *
	 * @param hideMethod option value
	 * @return this
	 */
	public ToastOptions setHideMethod(IDisappearAnimationMethod hideMethod) {
		this.hideMethod = hideMethod;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IIconClass getIconClass() {
		return iconClass;
	}

	/**
	 * Sets iconClass option value.
	 *
	 * @param iconClass option value
	 * @return this
	 */
	public ToastOptions setIconClass(IIconClass iconClass) {
		this.iconClass = iconClass;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessageClass() {
		return messageClass;
	}

	/**
	 * Sets messageClass option value.
	 *
	 * @param messageClass option value
	 * @return this
	 */
	public ToastOptions setMessageClass(String messageClass) {
		this.messageClass = messageClass;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isNewestOnTop() {
		return isNewestOnTop;
	}

	/**
	 * Sets newestOnTop option value.
	 *
	 * @param isNewestOnTop option value
	 * @return this
	 */
	public ToastOptions setIsNewestOnTop(Boolean isNewestOnTop) {
		this.isNewestOnTop = isNewestOnTop;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getOnClickFunction() {
		return onClickFunction;
	}

	/**
	 * Sets onclick option value.
	 *
	 * @param onClickFunction option value
	 * @return this
	 */
	public ToastOptions setOnClickFunction(String onClickFunction) {
		this.onClickFunction = onClickFunction;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getOnCloseClickFunction() {
		return onCloseClickFunction;
	}

	/**
	 * Sets onCloseClick option value.
	 *
	 * @param onCloseClickFunction option value
	 * @return this
	 */
	public ToastOptions setOnCloseClickFunction(String onCloseClickFunction) {
		this.onCloseClickFunction = onCloseClickFunction;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getOnHiddenFunction() {
		return onHiddenFunction;
	}

	/**
	 * Sets onHidden option value.
	 *
	 * @param onHiddenFunction option value
	 * @return this
	 */
	public ToastOptions setOnHiddenFunction(String onHiddenFunction) {
		this.onHiddenFunction = onHiddenFunction;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getOnShownFunction() {
		return onShownFunction;
	}

	/**
	 * Sets onShown option value.
	 *
	 * @param onShownFunction option value
	 * @return this
	 */
	public ToastOptions setOnShownFunction(String onShownFunction) {
		this.onShownFunction = onShownFunction;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPositionClass getPositionClass() {
		return positionClass;
	}

	/**
	 * Sets positionClass option value.
	 *
	 * @param positionClass option value
	 * @return this
	 */
	public ToastOptions setPositionClass(IPositionClass positionClass) {
		this.positionClass = positionClass;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean needPreventDuplicates() {
		return needPreventDuplicates;
	}

	/**
	 * Sets preventDuplicates option value.
	 *
	 * @param needPreventDuplicates option value
	 * @return this
	 */
	public ToastOptions setNeedPreventDuplicates(Boolean needPreventDuplicates) {
		this.needPreventDuplicates = needPreventDuplicates;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isEnableProgressBar() {
		return isEnableProgressBar;
	}

	/**
	 * Sets progressBar option value.
	 *
	 * @param isEnableProgressBar option value
	 * @return this
	 */
	public ToastOptions setIsEnableProgressBar(Boolean isEnableProgressBar) {
		this.isEnableProgressBar = isEnableProgressBar;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getProgressClass() {
		return progressClass;
	}

	/**
	 * Sets progressClass option value.
	 *
	 * @param progressClass option value
	 * @return this
	 */
	public ToastOptions setProgressClass(String progressClass) {
		this.progressClass = progressClass;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isRightToLeft() {
		return isRightToLeft;
	}

	/**
	 * Sets rtl option value.
	 *
	 * @param isRightToLeft option value
	 * @return this
	 */
	public ToastOptions setIsRightToLeft(Boolean isRightToLeft) {
		this.isRightToLeft = isRightToLeft;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getShowDuration() {
		return showDuration;
	}

	/**
	 * Sets showDuration option value.
	 *
	 * @param showDuration option value
	 * @return this
	 */
	public ToastOptions setShowDuration(Integer showDuration) {
		this.showDuration = showDuration;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IEasing getShowEasing() {
		return showEasing;
	}

	/**
	 * Sets showEasing option value.
	 *
	 * @param showEasing option value
	 * @return this
	 */
	public ToastOptions setShowEasing(IEasing showEasing) {
		this.showEasing = showEasing;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IAppearAnimationMethod getShowMethod() {
		return showMethod;
	}

	/**
	 * Sets showMethod option value.
	 *
	 * @param showMethod option value
	 * @return this
	 */
	public ToastOptions setShowMethod(IAppearAnimationMethod showMethod) {
		this.showMethod = showMethod;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isTapToDismiss() {
		return isTapToDismiss;
	}

	/**
	 * Sets tapToDismiss option value.
	 *
	 * @param isTapToDismiss  option value
	 * @return this
	 */
	public ToastOptions setIsTapToDismiss(Boolean isTapToDismiss) {
		this.isTapToDismiss = isTapToDismiss;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTarget() {
		return target;
	}

	/**
	 * Sets target option value.
	 *
	 * @param target option value
	 * @return this
	 */
	public ToastOptions setTarget(String target) {
		this.target = target;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getTimeOut() {
		return timeOut;
	}

	/**
	 * Sets timeOut option value.
	 *
	 * @param timeOut option value
	 * @return this
	 */
	public ToastOptions setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTitleClass() {
		return titleClass;
	}

	/**
	 * Sets titleClass  option value.
	 *
	 * @param titleClass option value
	 * @return this
	 */
	public ToastOptions setTitleClass(String titleClass) {
		this.titleClass = titleClass;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getToastClass() {
		return toastClass;
	}

	/**
	 * Sets toastClass option value.
	 *
	 * @param toastClass option value
	 * @return this
	 */
	public ToastOptions setToastClass(String toastClass) {
		this.toastClass = toastClass;
		return this;
	}

	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
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
