package jp.try0.wicket.toastr.samples.panel.options;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jp.try0.wicket.toastr.samples.panel.combine.CombineSamplePanel;
import jp.try0.wicket.toastr.samples.test.ToastrSamplesTest;

/**
 * {@link OptionsSamplePanel} Test
 *
 * @author Ryo Tsunoda
 *
 */
public class OptionsSamplePanelTest extends ToastrSamplesTest {

	@Test
	public void render() {
		CombineSamplePanel panel = tester.startComponentInPage(CombineSamplePanel.class);

		Assertions.assertNotNull(panel);

	}
}
