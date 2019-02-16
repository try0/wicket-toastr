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
public class ToastOption implements IToastOption {
	private static final long serialVersionUID = 1L;

	/**
	 * Option name and config.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	private static @interface ToastOptionValue {

		/**
		 * Option key name.
		 *
		 * @return option key
		 */
		String value();

		/**
		 * Whether or not to squeeze with double quotes. Default is true.
		 *
		 * @return true if an option value needs squeeze with double quotes, otherwise
		 *         false
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
		/**
		 * show
		 */
		SHOW("show"),
		/**
		 * fadeIn
		 */
		FADE_IN("fadeIn"),
		/**
		 * slideDown
		 */
		SLIDE_DOWN("slideDown"),;

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
		/**
		 * hide
		 */
		HIDE("hide"),
		/**
		 * fadeOut
		 */
		FADE_OUT("fadeOut"),
		/**
		 * slideUp
		 */
		SLIDE_UP("slideUp"),;

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
		/**
		 * hide
		 */
		HIDE("hide"),
		/**
		 * fadeOut
		 */
		FADE_OUT("fadeOut"),
		/**
		 * slideUp
		 */
		SLIDE_UP("slideUp"),;

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
		/**
		 * swing
		 */
		SWING("swing"),
		/**
		 * linear
		 */
		LINEAR("linear"),;

		/**
		 * easing type
		 */
		String easing;

		/**
		 * Constractor
		 *
		 * @param easing
		 */
		private Easing(String easing) {
			this.easing = easing;
		}

		/**
		 * Returns easing type string.
		 */
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
		/**
		 * toast-top-right
		 */
		TOP_RIGHT("toast-top-right"),
		/**
		 * toast-bottom-right
		 */
		BOTTOM_RIGHT("toast-bottom-right"),
		/**
		 * toast-bottom-left
		 */
		BOTTOM_LEFT("toast-bottom-left"),
		/**
		 * toast-top-left
		 */
		TOP_LEFT("toast-top-left"),
		/**
		 * toast-top-full-width
		 */
		TOP_FULL_WIDTH("toast-top-full-width"),
		/**
		 * toast-bottom-full-width
		 */
		BOTTOM_FULL_WIDTH("toast-bottom-full-width"),
		/**
		 * toast-top-center
		 */
		TOP_CENTER("toast-top-center"),
		/**
		 * toast-bottom-center
		 */
		BOTTOM_CENTER("toast-bottom-center"),;

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
		/**
		 * toast-error
		 */
		ERROR("toast-error"),
		/**
		 * toast-info
		 */
		INFO("toast-info"),
		/**
		 * toast-success
		 */
		SUCCESS("toast-success"),
		/**
		 * toast-warning
		 */
		WARNING("toast-warning");

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
	 * @return new {@link ToastOption}
	 */
	public static ToastOption create() {
		return new ToastOption();
	}

	/**
	 * Constractor
	 */
	public ToastOption() {

	}

	/**
	 * closeButton
	 */
	@ToastOptionValue(value = OptionKeys.CLOSE_BUTTON, squeezeWithDoubleQuotes = false)
	private Boolean isEnableCloseButton = null;

	/**
	 * closeClass
	 */
	@ToastOptionValue(value = OptionKeys.CLOSE_CLASS)
	private String closeClass = null;

	/**
	 * closeDuration
	 */
	@ToastOptionValue(value = OptionKeys.CLOSE_DURATION, squeezeWithDoubleQuotes = false)
	private Integer closeDureation = null;

	/**
	 * closeEasing
	 */
	@ToastOptionValue(value = OptionKeys.CLOSE_EASING)
	private IEasing closeEasing = null;

	/**
	 * closeHtml
	 */
	@ToastOptionValue(value = OptionKeys.CLOSE_HTML)
	private String closeHtml = null;

	/**
	 * closeMethod
	 */
	@ToastOptionValue(value = OptionKeys.CLOSE_METHOD)
	private IDisappearAnimationMethod closeMethod = null;

	/**
	 * closeOnHover
	 */
	@ToastOptionValue(value = OptionKeys.CLOSE_ON_HOVER, squeezeWithDoubleQuotes = false)
	private Boolean isCloseOnHover = null;

	/**
	 * containerId
	 */
	@ToastOptionValue(value = OptionKeys.CONTAINER_ID)
	private String containerId = null;

	/**
	 * debug
	 */
	@ToastOptionValue(value = OptionKeys.DEBUG, squeezeWithDoubleQuotes = false)
	private Boolean isDebug = null;

	/**
	 * escapeHtml
	 */
	@ToastOptionValue(value = OptionKeys.ESCAPE_HTML, squeezeWithDoubleQuotes = false)
	private Boolean needEscapeHtml = null;

	/**
	 * extendedTimeOut
	 */
	@ToastOptionValue(value = OptionKeys.EXTENDED_TIME_OUT)
	private Integer extendedTimeOut = null;

	/**
	 * hideDuration
	 */
	@ToastOptionValue(value = OptionKeys.HIDE_DURATION, squeezeWithDoubleQuotes = false)
	private Integer hideDuration = null;

	/**
	 * hideEasing
	 */
	@ToastOptionValue(value = OptionKeys.HIDE_EASING)
	private IEasing hideEasing = null;

	/**
	 * hideMethod
	 */
	@ToastOptionValue(value = OptionKeys.HIDE_METHOD)
	private IDisappearAnimationMethod hideMethod = null;

	/**
	 * iconClass
	 */
	@ToastOptionValue(value = OptionKeys.ICON_CLASS)
	private IIconClass iconClass = null;

	// /**
	// * iconClasses
	// */
	// @ToastOption(value = OptionKeys.ICON_CLASSES)
	// private Map<String, IIconClass> iconClasses = null;

	/**
	 * messageClass
	 */
	@ToastOptionValue(value = OptionKeys.MESSAGE_CLASS)
	private String messageClass = null;

	/**
	 * newestOnTop
	 */
	@ToastOptionValue(value = OptionKeys.NEWEST_ON_TOP, squeezeWithDoubleQuotes = false)
	private Boolean isNewestOnTop = null;

	/**
	 * onclick
	 */
	@ToastOptionValue(value = OptionKeys.ONCLICK, squeezeWithDoubleQuotes = false)
	private String onClickFunction = null;

	/**
	 * onCloseClick
	 */
	@ToastOptionValue(value = OptionKeys.ON_CLOSE_CLICK, squeezeWithDoubleQuotes = false)
	private String onCloseClickFunction = null;

	/**
	 * onHidden
	 */
	@ToastOptionValue(value = OptionKeys.ON_HIDDEN, squeezeWithDoubleQuotes = false)
	private String onHiddenFunction = null;

	/**
	 * onShown
	 */
	@ToastOptionValue(value = OptionKeys.ON_SHOWN, squeezeWithDoubleQuotes = false)
	private String onShownFunction = null;

	/**
	 * positionClass
	 */
	@ToastOptionValue(value = OptionKeys.POSITION_CLASS)
	private IPositionClass positionClass = null;

	/**
	 * preventDuplicates
	 */
	@ToastOptionValue(value = OptionKeys.PREVENT_DUPLICATES, squeezeWithDoubleQuotes = false)
	private Boolean needPreventDuplicates = null;

	/**
	 * progressBar
	 */
	@ToastOptionValue(value = OptionKeys.PROGRESS_BAR, squeezeWithDoubleQuotes = false)
	private Boolean isEnableProgressBar = null;

	/**
	 * progressClass
	 */
	@ToastOptionValue(value = OptionKeys.PROGRESS_CLASS)
	private String progressClass = null;

	/**
	 * rtl
	 */
	@ToastOptionValue(value = OptionKeys.RTL, squeezeWithDoubleQuotes = false)
	private Boolean isRightToLeft = null;

	/**
	 * showDuration
	 */
	@ToastOptionValue(value = OptionKeys.SHOW_DURATION, squeezeWithDoubleQuotes = false)
	private Integer showDuration = null;

	/**
	 * showEasing
	 */
	@ToastOptionValue(value = OptionKeys.SHOW_EASING)
	private IEasing showEasing = null;

	/**
	 * showMethod
	 */
	@ToastOptionValue(value = OptionKeys.SHOW_METHOD)
	private IAppearAnimationMethod showMethod = null;

	/**
	 * tapToDismiss
	 */
	@ToastOptionValue(value = OptionKeys.TAP_TO_DISMISS, squeezeWithDoubleQuotes = false)
	private Boolean isTapToDismiss = null;

	/**
	 * target
	 */
	@ToastOptionValue(value = OptionKeys.TARGET)
	private String target = null;

	/**
	 * timeOut
	 */
	@ToastOptionValue(value = OptionKeys.TIME_OUT)
	private Integer timeOut = null;

	/**
	 * titleClass
	 */
	@ToastOptionValue(value = OptionKeys.TITLE_CLASS)
	private String titleClass = null;

	/**
	 * toastClass
	 */
	@ToastOptionValue(value = OptionKeys.TOAST_CLASS)
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
	public ToastOption setIsEnableCloseButton(Boolean isEnableCloseButton) {
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
	public ToastOption setCloseClass(String closeClass) {
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
	public ToastOption setCloseDureation(Integer closeDureation) {
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
	public ToastOption setCloseEasing(IEasing closeEasing) {
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
	public ToastOption setCloseHtml(String closeHtml) {
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
	public ToastOption setCloseMethod(IDisappearAnimationMethod closeMethod) {
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
	public ToastOption setIsCloseOnHover(Boolean isCloseOnHover) {
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
	public ToastOption setContainerId(String containerId) {
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
	 * @param isDebug option value
	 * @return this
	 */
	public ToastOption setIsDebug(Boolean isDebug) {
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
	public ToastOption setNeedEscapeHtml(Boolean needEscapeHtml) {
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
	public ToastOption setExtendedTimeOut(Integer extendedTimeOut) {
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
	public ToastOption setHideDuration(Integer hideDuration) {
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
	public ToastOption setHideEasing(IEasing hideEasing) {
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
	public ToastOption setHideMethod(IDisappearAnimationMethod hideMethod) {
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
	public ToastOption setIconClass(IIconClass iconClass) {
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
	public ToastOption setMessageClass(String messageClass) {
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
	public ToastOption setIsNewestOnTop(Boolean isNewestOnTop) {
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
	public ToastOption setOnClickFunction(String onClickFunction) {
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
	public ToastOption setOnCloseClickFunction(String onCloseClickFunction) {
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
	public ToastOption setOnHiddenFunction(String onHiddenFunction) {
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
	public ToastOption setOnShownFunction(String onShownFunction) {
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
	public ToastOption setPositionClass(IPositionClass positionClass) {
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
	public ToastOption setNeedPreventDuplicates(Boolean needPreventDuplicates) {
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
	public ToastOption setIsEnableProgressBar(Boolean isEnableProgressBar) {
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
	public ToastOption setProgressClass(String progressClass) {
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
	public ToastOption setIsRightToLeft(Boolean isRightToLeft) {
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
	public ToastOption setShowDuration(Integer showDuration) {
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
	public ToastOption setShowEasing(IEasing showEasing) {
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
	public ToastOption setShowMethod(IAppearAnimationMethod showMethod) {
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
	 * @param isTapToDismiss option value
	 * @return this
	 */
	public ToastOption setIsTapToDismiss(Boolean isTapToDismiss) {
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
	public ToastOption setTarget(String target) {
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
	public ToastOption setTimeOut(Integer timeOut) {
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
	 * Sets titleClass option value.
	 *
	 * @param titleClass option value
	 * @return this
	 */
	public ToastOption setTitleClass(String titleClass) {
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
	public ToastOption setToastClass(String toastClass) {
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

			ToastOptionValue option = field.getAnnotation(ToastOptionValue.class);
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
	public ToastOption overwrite(IToastOption options) {

		ToastOption newOptions = ToastOption.create();

		if (options.isEnableCloseButton() == null) {
			newOptions.setIsEnableCloseButton(this.isEnableCloseButton);
		} else {
			newOptions.setIsEnableCloseButton(options.isEnableCloseButton());
		}

		if (options.getCloseClass() == null) {
			newOptions.setCloseClass(this.closeClass);
		} else {
			newOptions.setCloseClass(options.getCloseClass());
		}

		if (options.getCloseDureation() == null) {
			newOptions.setCloseDureation(this.closeDureation);
		} else {
			newOptions.setCloseDureation(options.getCloseDureation());
		}

		if (options.getCloseEasing() == null) {
			newOptions.setCloseEasing(this.closeEasing);
		} else {
			newOptions.setCloseEasing(options.getCloseEasing());
		}

		if (options.getCloseHtml() == null) {
			newOptions.setCloseHtml(this.closeHtml);
		} else {
			newOptions.setCloseHtml(options.getCloseHtml());
		}

		if (options.getCloseMethod() == null) {
			newOptions.setCloseMethod(this.closeMethod);
		} else {
			newOptions.setCloseMethod(options.getCloseMethod());
		}

		if (options.isCloseOnHover() == null) {
			newOptions.setIsCloseOnHover(this.isCloseOnHover);
		} else {
			newOptions.setIsCloseOnHover(options.isCloseOnHover());
		}

		if (options.getContainerId() == null) {
			newOptions.setContainerId(this.containerId);
		} else {
			newOptions.setContainerId(options.getContainerId());
		}

		if (options.isDebug() == null) {
			newOptions.setIsDebug(this.isDebug);
		} else {
			newOptions.setIsDebug(options.isDebug());
		}

		if (options.needEscapeHtml() == null) {
			newOptions.setNeedEscapeHtml(this.needEscapeHtml);
		} else {
			newOptions.setNeedEscapeHtml(options.needEscapeHtml());
		}

		if (options.getExtendedTimeOut() == null) {
			newOptions.setExtendedTimeOut(this.extendedTimeOut);
		} else {
			newOptions.setExtendedTimeOut(options.getExtendedTimeOut());
		}

		if (options.getHideDuration() == null) {
			newOptions.setHideDuration(this.hideDuration);
		} else {
			newOptions.setHideDuration(options.getHideDuration());
		}

		if (options.getHideEasing() == null) {
			newOptions.setHideEasing(this.hideEasing);
		} else {
			newOptions.setHideEasing(options.getHideEasing());
		}

		if (options.getHideMethod() == null) {
			newOptions.setHideMethod(this.hideMethod);
		} else {
			newOptions.setHideMethod(options.getHideMethod());
		}

		if (options.getIconClass() == null) {
			newOptions.setIconClass(this.iconClass);
		} else {
			newOptions.setIconClass(options.getIconClass());
		}

		if (options.getMessageClass() == null) {
			newOptions.setMessageClass(this.messageClass);
		} else {
			newOptions.setMessageClass(options.getMessageClass());
		}

		if (options.isNewestOnTop() == null) {
			newOptions.setIsNewestOnTop(this.isNewestOnTop);
		} else {
			newOptions.setIsNewestOnTop(options.isNewestOnTop());
		}

		if (options.getOnClickFunction() == null) {
			newOptions.setOnClickFunction(this.onClickFunction);
		} else {
			newOptions.setOnClickFunction(options.getOnClickFunction());
		}

		if (options.getOnCloseClickFunction() == null) {
			newOptions.setOnCloseClickFunction(this.onCloseClickFunction);
		} else {
			newOptions.setOnCloseClickFunction(options.getOnCloseClickFunction());
		}

		if (options.getOnHiddenFunction() == null) {
			newOptions.setOnHiddenFunction(this.onHiddenFunction);
		} else {
			newOptions.setOnHiddenFunction(options.getOnHiddenFunction());
		}

		if (options.getOnShownFunction() == null) {
			newOptions.setOnShownFunction(this.onShownFunction);
		} else {
			newOptions.setOnShownFunction(options.getOnShownFunction());
		}

		if (options.getPositionClass() == null) {
			newOptions.setPositionClass(this.positionClass);
		} else {
			newOptions.setPositionClass(options.getPositionClass());
		}

		if (options.needPreventDuplicates() == null) {
			newOptions.setNeedPreventDuplicates(this.needPreventDuplicates);
		} else {
			newOptions.setNeedPreventDuplicates(options.needPreventDuplicates());
		}

		if (options.isEnableProgressBar() == null) {
			newOptions.setIsEnableProgressBar(this.isEnableProgressBar);
		} else {
			newOptions.setIsEnableProgressBar(options.isEnableProgressBar());
		}

		if (options.getProgressClass() == null) {
			newOptions.setProgressClass(this.progressClass);
		} else {
			newOptions.setProgressClass(options.getProgressClass());
		}

		if (options.isRightToLeft() == null) {
			newOptions.setIsRightToLeft(this.isRightToLeft);
		} else {
			newOptions.setIsRightToLeft(options.isRightToLeft());
		}

		if (options.getShowDuration() == null) {
			newOptions.setShowDuration(this.showDuration);
		} else {
			newOptions.setShowDuration(options.getShowDuration());
		}

		if (options.getShowEasing() == null) {
			newOptions.setShowEasing(this.showEasing);
		} else {
			newOptions.setShowEasing(options.getShowEasing());
		}

		if (options.getShowMethod() == null) {
			newOptions.setShowMethod(this.showMethod);
		} else {
			newOptions.setShowMethod(options.getShowMethod());
		}

		if (options.isTapToDismiss() == null) {
			newOptions.setIsTapToDismiss(this.isTapToDismiss);
		} else {
			newOptions.setIsTapToDismiss(options.isTapToDismiss());
		}

		if (options.getTarget() == null) {
			newOptions.setTarget(this.target);
		} else {
			newOptions.setTarget(options.getTarget());
		}

		if (options.getTimeOut() == null) {
			newOptions.setTimeOut(this.timeOut);
		} else {
			newOptions.setTimeOut(options.getTimeOut());
		}

		if (options.getTitleClass() == null) {
			newOptions.setTitleClass(this.titleClass);
		} else {
			newOptions.setTitleClass(options.getTitleClass());
		}

		if (options.getToastClass() == null) {
			newOptions.setToastClass(this.toastClass);
		} else {
			newOptions.setToastClass(options.getToastClass());
		}

		return newOptions;
	}

}
