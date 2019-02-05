package jp.try0.wicket.toastr.core.config;

import java.io.Serializable;
import java.util.Optional;

import jp.try0.wicket.toastr.core.IToastOptions;
import jp.try0.wicket.toastr.core.Toast.ToastLevel;
import jp.try0.wicket.toastr.core.ToastOptions;
import jp.try0.wicket.toastr.core.ToastOptions.IconClass;

/**
 * Font Awsome icon unicodes.<br>
 * Holds the escaped icon unicode for each level.
 * <pre>
 * new ToastrFontAwesomeIcons(
 *     "\\f058", // fa-check-circle
 *     "\\f05a", // fa-info-circle
 *     "\\f071", // fa-exclamation-triangle
 *     "\\f057"  // fa-times-circle
 * )
 * </pre>
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastrFontAwesomeIcons implements Serializable {
	private static final long serialVersionUID = 1L;

	private String successIconUnicode;
	private String infoIconUnicode;
	private String warningIconUnicode;
	private String errorIconUnicode;

	/**
	 * Constractor
	 *
	 * @param successIconUnicode the escaped icon unicode
	 * @param infoIconUnicode the escaped icon unicode
	 * @param warningIconUnicode the escaped icon unicode
	 * @param errorIconUnicode the escaped icon unicode
	 */
	public ToastrFontAwesomeIcons(String successIconUnicode, String infoIconUnicode, String warningIconUnicode,
			String errorIconUnicode) {
		this.successIconUnicode = successIconUnicode;
		this.infoIconUnicode = infoIconUnicode;
		this.warningIconUnicode = warningIconUnicode;
		this.errorIconUnicode = errorIconUnicode;
	}

	/**
	 * Gets the escaped unicode for apply to the content of the pseudo-element.
	 *
	 * @param level the toast level
	 * @return the content of the pseudo-element
	 */
	public String getEscapedUnicode(ToastLevel level) {
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

	/**
	 * Gets the style to adapt the Font Awesome icons.
	 *
	 * @return the style for displaying icons
	 */
	public String getStyleForAdaptIconContent() {

		Optional<IToastOptions> globalOptions = ToastrSettings.get().getGlobalOptions();
		final String containerId;
		if (globalOptions.isPresent()) {
			String tmpContainerId = globalOptions.get().getContainerId();
			if (tmpContainerId == null || tmpContainerId.isEmpty()) {
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
					.append(getEscapedUnicode(level)).append("\";}");

			sb.append("#").append(containerId).append(">.").append(iconClassName).append(".rtl:after{content:\"")
					.append(getEscapedUnicode(level)).append("\";}");
		}

		return sb.toString();
	}
}
