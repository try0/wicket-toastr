package jp.try0.wicket.toastr.core;

import java.io.Serializable;

/**
 * Toast options interface.
 *
 *
 * @author Ryo Tsunoda
 *
 */
public interface IToastOption extends Serializable {

	public static final String DEFAULT_TOAST_CLASS = "toast";
	public static final String DEFAULT_CONTAINER_ID = "toast-container";
	public static final String DEFAULT_TITLE_CLASS = "toast-title";
	public static final String DEFAULT_MESSAGE_CLASS = "toast-message";
	public static final String DEFAULT_CLOSE_CLASS = "toast-close-button";
	public static final String DEFAULT_PROGRESS_CLASS = "toast-progress";

	/**
	 * Option keys
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static final class OptionKeys {
		public static final String CLOSE_BUTTON = "closeButton";
		public static final String CLOSE_CLASS = "closeClass";
		public static final String CLOSE_DURATION = "closeDuration";
		public static final String CLOSE_EASING = "closeEasing";
		public static final String CLOSE_HTML = "closeHtml";
		public static final String CLOSE_METHOD = "closeMethod";
		public static final String CLOSE_ON_HOVER = "closeOnHover";
		public static final String CONTAINER_ID = "containerId";
		public static final String DEBUG = "debug";
		public static final String ESCAPE_HTML = "escapeHtml";
		public static final String EXTENDED_TIME_OUT = "extendedTimeOut";
		public static final String HIDE_DURATION = "hideDuration";
		public static final String HIDE_EASING = "hideEasing";
		public static final String HIDE_METHOD = "hideMethod";
		public static final String ICON_CLASS = "iconClass";
		public static final String ICON_CLASSES = "iconClasses";
		public static final String MESSAGE_CLASS = "messageClass";
		public static final String NEWEST_ON_TOP = "newestOnTop";
		public static final String ONCLICK = "onclick";
		public static final String ON_CLOSE_CLICK = "onCloseClick";
		public static final String ON_HIDDEN = "onHidden";
		public static final String ON_SHOWN = "onShown";
		public static final String POSITION_CLASS = "positionClass";
		public static final String PREVENT_DUPLICATES = "preventDuplicates";
		public static final String PROGRESS_BAR = "progressBar";
		public static final String PROGRESS_CLASS = "progressClass";
		public static final String RTL = "rtl";
		public static final String SHOW_DURATION = "showDuration";
		public static final String SHOW_EASING = "showEasing";
		public static final String SHOW_METHOD = "showMethod";
		public static final String TAP_TO_DISMISS = "tapToDismiss";
		public static final String TARGET = "target";
		public static final String TIME_OUT = "timeOut";
		public static final String TITLE_CLASS = "titleClass";
		public static final String TOAST_CLASS = "toastClass";

		private OptionKeys() {
			throw new IllegalStateException("There is no need create the instance.");
		}
	}

	/**
	 * Animation method when displaying toasts.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static interface IAppearAnimationMethod extends Serializable {
	}

	/**
	 * Animation method when hiding toasts.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static interface IDisappearAnimationMethod extends Serializable {
	}

	/**
	 * Animation easing.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static interface IEasing extends Serializable {
	}

	/**
	 * Icon css class.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static interface IIconClass extends Serializable {
	}

	/**
	 * Toast position css class.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static interface IPositionClass extends Serializable {
	}

	/**
	 * Gets closeButton.
	 *
	 * @return closeButton option value
	 */
	public Boolean isEnableCloseButton();

	/**
	 * Gets closeClass.
	 *
	 * @return closeClass option value
	 */
	public String getCloseClass();

	/**
	 * Gets closeDuration.
	 *
	 * @return closeDuration option value
	 */
	public Integer getCloseDureation();

	/**
	 * Gets closeEasing.
	 *
	 * @return closeEasing option value
	 */
	public IEasing getCloseEasing();

	/**
	 * Gets closeHtml.
	 *
	 * @return closeHtml option value
	 */
	public String getCloseHtml();

	/**
	 * Gets closeMethod.
	 *
	 * @return closeMethod option value
	 */
	public IDisappearAnimationMethod getCloseMethod();

	/**
	 * Gets closeOnHover.
	 *
	 * @return closeOnHover option value
	 */
	public Boolean isCloseOnHover();

	/**
	 * Gets containerId.
	 *
	 * @return containerId option value
	 */
	public String getContainerId();

	/**
	 * Gets debug.
	 *
	 * @return debug option value
	 */
	public Boolean isDebug();

	/**
	 * Gets escapeHtml.
	 *
	 * @return escapeHtml option value
	 */
	public Boolean needEscapeHtml();

	/**
	 * Gets extendedTimeOut.
	 *
	 * @return extendedTimeOut option value
	 */
	public Integer getExtendedTimeOut();

	/**
	 * Gets hideDuration.
	 *
	 * @return hideDuration option value
	 */
	public Integer getHideDuration();

	/**
	 * Gets hideEasing.
	 *
	 * @return hideEasing option value
	 */
	public IEasing getHideEasing();

	/**
	 * Gets hideMethod.
	 *
	 * @return hideMethod option value
	 */
	public IDisappearAnimationMethod getHideMethod();

	/**
	 * Gets iconClass.
	 *
	 * @return iconClass option value
	 */
	public IIconClass getIconClass();

	/**
	 * Gets messageClass.
	 *
	 * @return messageClass option value
	 */
	public String getMessageClass();

	/**
	 * Gets newestOnTop.
	 *
	 * @return newestOnTop option value
	 */
	public Boolean isNewestOnTop();

	/**
	 * Gets onclick.
	 *
	 * @return onclick option value
	 */
	public String getOnClickFunction();

	/**
	 * Gets onCloseClick.
	 *
	 * @return onCloseClick option value
	 */
	public String getOnCloseClickFunction();

	/**
	 * Gets onHidden.
	 *
	 * @return onHidden option value
	 */
	public String getOnHiddenFunction();

	/**
	 * Gets onShown.
	 *
	 * @return onShown option value
	 */
	public String getOnShownFunction();

	/**
	 * Gets positionClass.
	 *
	 * @return positionClass option value
	 */
	public IPositionClass getPositionClass();

	/**
	 * Gets preventDuplicates.
	 *
	 * @return preventDuplicates option value
	 */
	public Boolean needPreventDuplicates();

	/**
	 * Gets progressBar.
	 *
	 * @return progressBar option value
	 */
	public Boolean isEnableProgressBar();

	/**
	 * Gets progressClass.
	 *
	 * @return progressClass option value
	 */
	public String getProgressClass();

	/**
	 * Gets rtl.
	 *
	 * @return rtl option value
	 */
	public Boolean isRightToLeft();

	/**
	 * Gets showDuration.
	 *
	 * @return showDuration option value
	 */
	public Integer getShowDuration();

	/**
	 * Gets showEasing.
	 *
	 * @return showEasing option value
	 */
	public IEasing getShowEasing();

	/**
	 * Gets showMethod.
	 *
	 * @return showMethod option value
	 */
	public IAppearAnimationMethod getShowMethod();

	/**
	 * Gets tapToDismiss.
	 *
	 * @return tapToDismiss option value
	 */
	public Boolean isTapToDismiss();

	/**
	 * Gets target.
	 *
	 * @return target option value
	 */
	public String getTarget();

	/**
	 * Gets timeOut.
	 *
	 * @return timeOut option value
	 */
	public Integer getTimeOut();

	/**
	 * Gets titleClass.
	 *
	 * @return titleClass option value
	 */
	public String getTitleClass();

	/**
	 * Gets toastClass.
	 *
	 * @return toastClass option value
	 */
	public String getToastClass();

	/**
	 * Gets options as json string.
	 *
	 * @return json string
	 */
	public String toJsonString();

	/**
	 * Gets new overwritten options.<br>
	 * If the value of the argument's option is exists, overwrite the option value.
	 *
	 * @param option overwrite options
	 * @return overwritten option
	 */
	public IToastOption overwrite(IToastOption option);

}
