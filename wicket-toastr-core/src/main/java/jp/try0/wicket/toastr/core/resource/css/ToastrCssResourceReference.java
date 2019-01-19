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

	private static class ToastrCssResourceReferenceHolder {
		/**
		 * Reference of toastr css
		 */
		private static final ToastrCssResourceReference INSTANCE = new ToastrCssResourceReference();
	}

	/**
	 * Gets ToastrCssResourceReference instance.
	 *
	 * @return constant
	 */
	public static ToastrCssResourceReference getInstance() {
		return ToastrCssResourceReferenceHolder.INSTANCE;
	}

	/**
	 * Constractor
	 */
	public ToastrCssResourceReference() {
		super(ToastrCssResourceReference.class, "toastr.min.css");
	}

}
