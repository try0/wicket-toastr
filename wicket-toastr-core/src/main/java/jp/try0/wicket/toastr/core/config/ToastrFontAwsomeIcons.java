package jp.try0.wicket.toastr.core.config;

import java.io.Serializable;

import jp.try0.wicket.toastr.core.Toast.ToastLevel;
import jp.try0.wicket.toastr.core.ToastOptions;
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
	 * @param icons
	 * @return
	 */
	public static String getStyleForAdaptIconContent(ToastrFontAwsomeIcons icons) {

		final String containerId;

		if (ToastrSettings.hasGlobalOptions()) {
			String tmpContainerId = ToastrSettings.getGlobalOptions().get().getContainerId();
			if (com.google.common.base.Strings.isNullOrEmpty(tmpContainerId)) {
				containerId = ToastOptions.DEFAULT_CONTAINER_ID;
			} else {
				containerId = tmpContainerId;
			}

		} else {
			containerId = ToastOptions.DEFAULT_CONTAINER_ID;
		}

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


			sb.append("#").append(containerId).append(">.").append(iconClassName).append(":not(.rtl):before{content:\"")
			.append(icons.getContent(level)).append("\";}");

			sb.append("#").append(containerId).append(">.").append(iconClassName).append(".rtl:after{content:\"")
			.append(icons.getContent(level)).append("\";}");
		}

		return sb.toString();
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
