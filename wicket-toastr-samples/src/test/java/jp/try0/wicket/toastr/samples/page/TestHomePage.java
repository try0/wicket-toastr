package jp.try0.wicket.toastr.samples.page;

import org.junit.jupiter.api.Test;

import jp.try0.wicket.toastr.samples.panel.ToastBasicPanel;
import jp.try0.wicket.toastr.samples.panel.combine.CombineSamplePanel;
import jp.try0.wicket.toastr.samples.panel.filter.FilterSamplePanel;
import jp.try0.wicket.toastr.samples.panel.options.OptionsSamplePanel;
import jp.try0.wicket.toastr.samples.test.ToastrSamplesTest;

/**
 * {@link HomePage} tests
 *
 * @author Ryo Tsunoda
 *
 */
public class TestHomePage extends ToastrSamplesTest {

	@Test
	public void homepageRendersSuccessfully() {
		//start and render the test page
		tester.startPage(HomePage.class);

		//assert rendered page class
		tester.assertRenderedPage(HomePage.class);
	}

	@Test
	public void initialPanel() {

		//start and render the test page
		tester.startPage(HomePage.class);

		//assert rendered page class
		tester.assertRenderedPage(HomePage.class);

		// initial panel is OptionsSamplePanel
		tester.assertComponent("form:samplePanel", OptionsSamplePanel.class);
	}

	@Test
	public void selectTab() {

		tester.startPage(HomePage.class);
		tester.assertComponent("form:samplePanel:pnlBasic", ToastBasicPanel.class);

		tester.clickLink("form:tabs:0:navLink", true);
		tester.assertComponent("form:samplePanel", OptionsSamplePanel.class);

		tester.clickLink("form:tabs:1:navLink", true);
		tester.assertComponent("form:samplePanel", CombineSamplePanel.class);

		tester.clickLink("form:tabs:2:navLink", true);
		tester.assertComponent("form:samplePanel", FilterSamplePanel.class);

	}
}
