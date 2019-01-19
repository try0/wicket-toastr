package jp.try0.wicket.toastr.core.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

import jp.try0.wicket.toastr.core.resource.css.ToastrCssResourceReference;
import jp.try0.wicket.toastr.core.resource.js.ToastrJavaScriptResourceReference;

/**
 * Toastr resource behavior.<br>
 * This behavior provides the function to append toastr's resources.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastrResourcesBehavior extends Behavior {
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(Component component, IHeaderResponse response) {
		super.renderHead(component, response);

		// toastr css
		response.render(CssHeaderItem.forReference(ToastrCssResourceReference.getInstance()));
		// toastr js
		response.render(JavaScriptHeaderItem.forReference(ToastrJavaScriptResourceReference.getInstance()));

	}

}
