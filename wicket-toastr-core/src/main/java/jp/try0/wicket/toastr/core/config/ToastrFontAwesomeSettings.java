package jp.try0.wicket.toastr.core.config;

import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.lang.Args;

import jp.try0.wicket.toastr.core.resource.css.TweakFontAwesomeIconCssResourceReference;

/**
 * Settings for using Font Awesome icons.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastrFontAwesomeSettings {

	private static final String FONT_AWESOME_5_FREE = "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.3.1/css/all.css\" integrity=\"sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU\" crossorigin=\"anonymous\">";

	/**
	 * Font Awesome css reference.
	 */
	private final String linkTag;
	/**
	 * Font Awesome icons
	 */
	private final ToastrFontAwesomeIcons icons;

	/**
	 * Css reference that tweak icon style
	 */
	private final ResourceReference tweakCssReference;

	/**
	 * Constractor
	 *
	 * @param icons Font Awesome icon
	 */
	public ToastrFontAwesomeSettings(ToastrFontAwesomeIcons icons) {
		this(icons, FONT_AWESOME_5_FREE, TweakFontAwesomeIconCssResourceReference.INSTANCE);
	}

	/**
	 * Constractor
	 *
	 * @param icons Font Awesome icons
	 * @param linkTag Font Awesome css link tag
	 * @param tweakCssReference Tweak icon style resource reference
	 */
	public ToastrFontAwesomeSettings(ToastrFontAwesomeIcons icons, String linkTag,
			ResourceReference tweakCssReference) {
		this.icons = Args.notNull(icons, "icons");
		this.linkTag = Args.notNull(linkTag, "linkTag");
		this.tweakCssReference = Args.notNull(tweakCssReference, "tweakCssReference");
	}

	/**
	 * Gets icons.
	 *
	 * @return icon unicodes
	 */
	public ToastrFontAwesomeIcons getIcons() {
		return icons;
	}

	/**
	 * Gets Font Awesome css link tag.
	 *
	 * @return the string of css link tag
	 */
	public String getFontAwesomeCssLinkTag() {
		return linkTag;
	}

	/**
	 * Gets tweak icon style.
	 *
	 * @return tweak icon style reference
	 */
	public ResourceReference getTweakCssReference() {
		return tweakCssReference;
	}

}
