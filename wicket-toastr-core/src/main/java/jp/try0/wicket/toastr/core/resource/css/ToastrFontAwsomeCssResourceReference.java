package jp.try0.wicket.toastr.core.resource.css;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * Reference of toastr css
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastrFontAwsomeCssResourceReference extends CssResourceReference {
	private static final long serialVersionUID = 1L;

	/**
	 * Reference of toastr css
	 */
	public static final ToastrFontAwsomeCssResourceReference INSTANCE =
			new ToastrFontAwsomeCssResourceReference();

	/**
	 * Constractor
	 */
	public ToastrFontAwsomeCssResourceReference() {
		super(ToastrFontAwsomeCssResourceReference.class, "toastr.fontawsome.adapt.css");
	}

}
