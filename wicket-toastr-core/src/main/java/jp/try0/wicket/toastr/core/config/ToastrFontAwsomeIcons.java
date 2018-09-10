package jp.try0.wicket.toastr.core.config;

import java.io.Serializable;

import com.google.common.base.Strings;

import jp.try0.wicket.toastr.core.IToast;
import jp.try0.wicket.toastr.core.Toast.ToastLevel;
import jp.try0.wicket.toastr.core.ToastOptions;
import jp.try0.wicket.toastr.core.ToastOptions.IIconClass;
import jp.try0.wicket.toastr.core.ToastOptions.IconClass;

/**
 * Font Awsome Icon unicodes.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastrFontAwsomeIcons implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Get the style to adapt the Font Awsome icons.
	 *
	 * @param toast
	 * @param icons
	 * @return
	 */
	public static String getStyleForAdaptIconContent(IToast toast, ToastrFontAwsomeIcons icons) {
		String containerId = "";
		String iconClassName = "";
		if (toast.getToastOptions().isPresent()) {

			ToastOptions options = toast.getToastOptions().get();

			containerId = options.getContainerId();

			IIconClass iconClass = options.getIconClass();
			if (iconClass != null) {
				iconClassName = iconClass.toString();
			}

		}

		if (Strings.isNullOrEmpty(containerId)) {
			containerId = ToastOptions.DEFAULT_CONTAINER_ID;
		}

		if (Strings.isNullOrEmpty(iconClassName)) {
			switch (toast.getToastLevel()) {
			case ERROR:
				iconClassName = IconClass.ERROR.toString();
				break;
			case INFO:
				iconClassName = IconClass.INFO.toString();
				break;
			case SUCCESS:
				iconClassName = IconClass.SUCCESS.toString();
				break;
			case WARNING:
				iconClassName = IconClass.WARNING.toString();
				break;
			case UNDEFINED:
				break;
			default:
				break;
			}
		}

		StringBuilder sb = new StringBuilder();
		appendStyleForAdaptIconContent(sb, containerId, iconClassName, icons, toast.getToastLevel());
		return sb.toString();
	}

	/**
	 * Get the style to adapt the Font Awsome icons.
	 *
	 * @param toast
	 * @param icons
	 * @return
	 */
	public static String getStyleForAdaptIconContent(ToastrFontAwsomeIcons icons) {

		final StringBuilder sb = new StringBuilder();
		for (ToastLevel level : ToastLevel.values()) {
			String iconClassName;
			switch (level) {
			case ERROR:
				iconClassName = IconClass.ERROR.toString();
				break;
			case INFO:
				iconClassName = IconClass.INFO.toString();
				break;
			case SUCCESS:
				iconClassName = IconClass.SUCCESS.toString();
				break;
			case WARNING:
				iconClassName = IconClass.WARNING.toString();
				break;
			default:
				continue;
			}

			appendStyleForAdaptIconContent(sb, ToastOptions.DEFAULT_CONTAINER_ID, iconClassName, icons, level);
		}

		return sb.toString();
	}

	/**
	 * Append style to string builder.
	 *
	 * @param sb
	 * @param containerId
	 * @param iconClassName
	 * @param icons
	 * @param level
	 */
	private static void appendStyleForAdaptIconContent(StringBuilder sb, String containerId, String iconClassName, ToastrFontAwsomeIcons icons, ToastLevel level) {
		sb.append("#").append(containerId).append(">.").append(iconClassName).append(":before{content:\"")
		.append(icons.getContent(level)).append("\";}");
	}


	private String successIconUnicode;
	private String infoIconUnicode;
	private String warningIconUnicode;
	private String errorIconUnicode;

	/**
	 * Constractor
	 *
	 * @param successIconUnicode
	 * @param infoIconUnicode
	 * @param warningIconUnicode
	 * @param errorIconUnicode
	 */
	public ToastrFontAwsomeIcons(String successIconUnicode, String infoIconUnicode, String warningIconUnicode,
			String errorIconUnicode) {
		this.successIconUnicode = successIconUnicode;
		this.infoIconUnicode = infoIconUnicode;
		this.warningIconUnicode = warningIconUnicode;
		this.errorIconUnicode = errorIconUnicode;
	}

	/**
	 * Get content.
	 *
	 * @param level
	 * @return
	 */
	public String getContent(ToastLevel level) {
		switch (level) {
		case ERROR:
			return errorIconUnicode;
		case INFO:
			return infoIconUnicode;
		case SUCCESS:
			return successIconUnicode;
		case WARNING:
			return warningIconUnicode;
		default:
			throw new IllegalArgumentException("this level is not supported.");

		}
	}
}
