package jp.try0.wicket.toastr.core.test;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
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
			}

			@Override
			public Class<? extends Page> getHomePage() {
				return AbstractToastrTest.this.getHomePage();
			}

		};
	}

	/**
	 * Get mock home page.
	 *
	 * @return
	 */
	protected Class<? extends Page> getHomePage() {
		return Page.class;
	}

}
