package jp.try0.wicket.toastr.core.test;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * ToastrTestPage
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastrTestPage extends WebPage {
	private static final long serialVersionUID = 1L;

	public ToastrTestPage() {
		super();
	}

	public ToastrTestPage(final IModel<?> model) {
		super(model);
	}

	public ToastrTestPage(final PageParameters parameters) {
		super(parameters);
	}

}
