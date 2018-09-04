package jp.try0.wicket.toastr.core.resource.css;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * Reference of toastr css
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastrCssResourceReference extends CssResourceReference {
	private static final long serialVersionUID = 1L;

	/**
	 * Reference of toastr css
	 */
	public static final ToastrCssResourceReference INSTANCE =
			new ToastrCssResourceReference();

	/**
	 * Constractor
	 */
	public ToastrCssResourceReference() {
		super(ToastrCssResourceReference.class, "toastr.min.css");
	}

}
