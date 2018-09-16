package jp.try0.wicket.toastr.core;

import java.io.Serializable;

/**
 * Toast options<br>
 *
 *
 * @author Ryo Tsunoda
 *
 */
public interface IToastOptions extends Serializable {

	public final static String DEFAULT_TOAST_CLASS = "toast";
	public final static String DEFAULT_CONTAINER_ID = "toast-container";
	public final static String DEFAULT_TITLE_CLASS = "toast-title";
	public final static String DEFAULT_MESSAGE_CLASS = "toast-message";
	public final static String DEFAULT_CLOSE_CLASS = "toast-close-button";
	public final static String DEFAULT_PROGRESS_CLASS = "toast-progress";



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

	public interface IAppearMethod extends Serializable {
	}

	public interface IDisappearMethod extends Serializable {
	}

	public static interface IEasing extends Serializable {
	}

	public static interface IIconClass extends Serializable {
	}

	public static interface IPositionClass extends Serializable {
	}

	/**
	 * Get closeButton
	 *
	 * @return
	 */
	public Boolean isEnableCloseButton();
	/**
	 * Get closeClass
	 *
	 * @return
	 */
	public String getCloseClass();
	/**
	 * Get closeDuration
	 *
	 * @return
	 */
	public Integer getCloseDureation();

	/**
	 * Get closeEasing
	 *
	 * @return
	 */
	public IEasing getCloseEasing();

	/**
	 * Get closeHtml
	 *
	 * @return
	 */
	public String getCloseHtml();
	/**
	 * Get closeMethod
	 *
	 * @return
	 */
	public IDisappearMethod getCloseMethod();
	/**
	 * Get closeOnHover
	 *
	 * @return
	 */
	public Boolean isCloseOnHover();

	/**
	 * Get containerId
	 *
	 * @return
	 */
	public String getContainerId();
	/**
	 * Get debug
	 *
	 * @return
	 */
	public Boolean isDebug();
	/**
	 * Get escapeHtml
	 *
	 * @return
	 */
	public Boolean needEscapeHtml();

	/**
	 * Get extendedTimeOut
	 *
	 * @return
	 */
	public Integer getExtendedTimeOut();
	/**
	 * Get hideDuration
	 *
	 * @return
	 */
	public Integer getHideDuration();
	/**
	 * Get hideEasing
	 *
	 * @return
	 */
	public IEasing getHideEasing();
	/**
	 * Get hideMethod
	 *
	 * @return
	 */
	public IDisappearMethod getHideMethod();
	/**
	 * Get iconClass
	 *
	 * @return
	 */
	public IIconClass getIconClass();
	/**
	 * Get messageClass
	 *
	 * @return
	 */
	public String getMessageClass();
	/**
	 * Get newestOnTop
	 *
	 * @return
	 */
	public Boolean isNewestOnTop();

	/**
	 * Get onclick
	 *
	 * @return
	 */
	public String getOnClickFunction();

	/**
	 * Get onCloseClick
	 *
	 * @return
	 */
	public String getOnCloseClickFunction();
	/**
	 * Get onHidden
	 *
	 * @return
	 */
	public String getOnHiddenFunction();
	/**
	 * Get onShown
	 *
	 * @return
	 */
	public String getOnShownFunction();

	/**
	 * Get positionClass
	 *
	 * @return
	 */
	public IPositionClass getPositionClass();
	/**
	 * Get preventDuplicates
	 *
	 * @return
	 */
	public Boolean needPreventDuplicates();
	/**
	 * Get progressBar
	 *
	 * @return
	 */
	public Boolean isEnableProgressBar();

	/**
	 * Get progressClass
	 *
	 * @return
	 */
	public String getProgressClass();
	/**
	 * Get rtl
	 *
	 * @return
	 */
	public Boolean isRightToLeft();
	/**
	 * Get showDuration
	 *
	 * @return
	 */
	public Integer getShowDuration();
	/**
	 * Get showEasing
	 *
	 * @return
	 */
	public IEasing getShowEasing();
	/**
	 * Get showMethod
	 *
	 * @return
	 */
	public IAppearMethod getShowMethod();

	/**
	 * Get tapToDismiss
	 *
	 * @return
	 */
	public Boolean isTapToDismiss();

	/**
	 * Get target
	 *
	 * @return
	 */
	public String getTarget();

	/**
	 * Get timeOut
	 *
	 * @return
	 */
	public Integer getTimeOut();
	/**
	 * Get titleClass
	 *
	 * @return
	 */
	public String getTitleClass();
	/**
	 * Get toastClass
	 *
	 * @return
	 */
	public String getToastClass();

	/**
	 * Gets Options as json string.
	 *
	 * @return
	 */
	public String toJsonString();

	/**
	 * Gets new overwritten options.<br>
	 * If the value of the argument's option is exists, overwrite the option value.
	 *
	 * @param options
	 * @return
	 */
	public IToastOptions overwrite(IToastOptions options);
}
