package jp.try0.wicket.toastr.samples.panel.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jp.try0.wicket.toastr.samples.panel.combine.CombineSamplePanel;
import jp.try0.wicket.toastr.samples.test.ToastrSamplesTest;

/**
 * {@link FilterSamplePanel} Test
 *
 * @author Ryo Tsunoda
 *
 */
public class FilterSamplePanelTest extends ToastrSamplesTest {

	@Test
	public void render() {
		CombineSamplePanel panel = tester.startComponentInPage(CombineSamplePanel.class);

		Assertions.assertNotNull(panel);

	}
}
