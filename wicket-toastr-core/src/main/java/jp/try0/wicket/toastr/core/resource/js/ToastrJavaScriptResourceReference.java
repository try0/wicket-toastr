package jp.try0.wicket.toastr.core.resource.js;

import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.resource.JQueryResourceReference;

/**
 * Reference of toastr javascript
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastrJavaScriptResourceReference extends JavaScriptResourceReference {
	private static final long serialVersionUID = 1L;

	/**
	 * Reference of toastr javascript
	 */
	private static final ToastrJavaScriptResourceReference INSTANCE = new ToastrJavaScriptResourceReference();

	/**
	 * Gets ToastrJavaScriptResourceReference instance.
	 *
	 * @return constant
	 */
	public static ToastrJavaScriptResourceReference getInstance() {
		return INSTANCE;
	}

	/**
	 * Constractor
	 */
	public ToastrJavaScriptResourceReference() {
		super(ToastrJavaScriptResourceReference.class, "toastr.min.js");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<HeaderItem> getDependencies() {
		List<HeaderItem> dependencies = super.getDependencies();

		// JQuery
		final ResourceReference jQueryResourceReference;
		if (Application.exists()) {
			jQueryResourceReference = Application.get().getJavaScriptLibrarySettings().getJQueryReference();
		} else {
			jQueryResourceReference = JQueryResourceReference.getV2();
		}

		HeaderItem item = JavaScriptHeaderItem.forReference(jQueryResourceReference);
		dependencies.add(item);

		return dependencies;
	}
}
