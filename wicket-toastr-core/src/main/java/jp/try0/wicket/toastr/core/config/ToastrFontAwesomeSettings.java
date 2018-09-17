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

	private final static String FONT_AWESOME_5_FREE = "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.3.1/css/all.css\" integrity=\"sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU\" crossorigin=\"anonymous\">";

	/**
	 * Font Awesome css reference.
	 */
	private final String linkTag;
	/**
	 * Font Awesome icons
	 */
	private final ToastrFontAwesomeIcons icons;

	/**
	 * Tweak icon style resource reference
	 */
	private final ResourceReference tweakCssResourceReference;

	/**
	 * Constractor
	 *
	 * @param icons Font Awesome icons
	 */
	public ToastrFontAwesomeSettings(ToastrFontAwesomeIcons icons) {
		this(icons, FONT_AWESOME_5_FREE, TweakFontAwesomeIconCssResourceReference.INSTANCE);
	}

	/**
	 * Constractor
	 *
	 * @param icons Font Awesome icons
	 * @param linkTag
	 * @param tweakCssResourceReference Tweak icon style resource reference
	 */
	public ToastrFontAwesomeSettings(ToastrFontAwesomeIcons icons, String linkTag,
			ResourceReference tweakCssResourceReference) {
		this.icons = Args.notNull(icons, "icons");
		this.linkTag = Args.notNull(linkTag, "linkTag");
		this.tweakCssResourceReference = Args.notNull(tweakCssResourceReference, "tweakCssResourceReference");
	}

	/**
	 * Gets icons.
	 *
	 * @return
	 */
	public ToastrFontAwesomeIcons getIcons() {
		return icons;
	}

	/**
	 * Gets Font Awesome css link tag.
	 *
	 * @return
	 */
	public String getFontAwesomeCssLinkTag() {
		return linkTag;
	}

	/**
	 * Gets tweak icon style.
	 *
	 * @return
	 */
	public ResourceReference getTweakCssResourceReference() {
		return tweakCssResourceReference;
	}

}
