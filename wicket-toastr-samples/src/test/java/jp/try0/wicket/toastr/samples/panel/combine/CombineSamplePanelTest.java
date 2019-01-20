package jp.try0.wicket.toastr.samples.panel.combine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jp.try0.wicket.toastr.samples.test.ToastrSamplesTest;

/**
 * {@link CombineSamplePanel} Test
 *
 * @author Ryo Tsunoda
 *
 */
public class CombineSamplePanelTest extends ToastrSamplesTest {

	@Test
	public void render() {
		CombineSamplePanel panel = tester.startComponentInPage(CombineSamplePanel.class);

		Assertions.assertNotNull(panel);

	}
}
