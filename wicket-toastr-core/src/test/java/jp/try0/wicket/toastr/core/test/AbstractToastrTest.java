package jp.try0.wicket.toastr.core.test;

import org.apache.wicket.Page;
import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 * Test base class.
 *
 * @author Ryo Tsunoda
 *
 */
public abstract class AbstractToastrTest {

	public static class ToastrTestPage extends WebPage {

		public ToastrTestPage() {
			super();
		}

		public ToastrTestPage(final IModel<?> model) {
			super(model);
		}

		public ToastrTestPage(final PageParameters parameters) {
			super(parameters);
		}

        @Override
        public Markup getAssociatedMarkup() {
            return Markup.of("<!DOCTYPE html><html></html>");
        }
	}

	private WebApplication application;
	private WicketTester tester;

	@BeforeEach
	public void setUp() {
		application = newWebApplication();
		tester = new WicketTester(application);
	}


	@AfterEach
	public final void after() throws Exception {
		if (tester != null) {
			tester.destroy();
		}
	}

	protected WebApplication getWebApplication() {
		return application;
	}

	protected WicketTester getWicketTester() {
		return tester;
	}

	/**
	 * Create mock application.
	 *
	 * @return
	 */
	protected WebApplication newWebApplication() {
		return new WebApplication() {

			@Override
			protected void init() {
				super.init();
				onInitializeApplication(this);
			}

			@Override
			public Class<? extends Page> getHomePage() {
				return AbstractToastrTest.this.getHomePage();
			}

		};
	}

	protected void onInitializeApplication(final WebApplication application) {
		// noop
	}

	/**
	 * Get mock home page.
	 *
	 * @return
	 */
	protected Class<? extends Page> getHomePage() {
		return ToastrTestPage.class;
	}

}
