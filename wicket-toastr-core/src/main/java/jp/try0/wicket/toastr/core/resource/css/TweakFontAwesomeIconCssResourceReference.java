package jp.try0.wicket.toastr.core.resource.css;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * Reference of tweak css for using FontAwesome icons.<br>
 *
 * @author Ryo Tsunoda
 *
 */
public class TweakFontAwesomeIconCssResourceReference extends CssResourceReference {
	private static final long serialVersionUID = 1L;

	/**
	 * Reference of toastr css
	 */
	public static final TweakFontAwesomeIconCssResourceReference INSTANCE =
			new TweakFontAwesomeIconCssResourceReference();

	/**
	 * Constractor
	 */
	public TweakFontAwesomeIconCssResourceReference() {
		super(TweakFontAwesomeIconCssResourceReference.class, "toastr.fontawesome.tweak.css");
	}

}
