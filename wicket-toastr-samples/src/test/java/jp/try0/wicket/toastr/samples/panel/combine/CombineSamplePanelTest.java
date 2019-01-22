package jp.try0.wicket.toastr.samples.panel.combine;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import jp.try0.wicket.toastr.samples.test.ToastrSamplesTest;

/**
 * {@link CombineSamplePanel} tests
 *
 * @author Ryo Tsunoda
 *
 */
public class CombineSamplePanelTest extends ToastrSamplesTest {

	@Test
	public void render() {
		CombineSamplePanel panel = tester.startComponentInPage(CombineSamplePanel.class);

		assertNotNull(panel);

	}
}
