package jp.try0.wicket.toastr.samples.panel.options;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import jp.try0.wicket.toastr.samples.panel.combine.CombineSamplePanel;
import jp.try0.wicket.toastr.samples.test.ToastrSamplesTest;

/**
 * {@link OptionsSamplePanel} tests
 *
 * @author Ryo Tsunoda
 *
 */
public class OptionsSamplePanelTest extends ToastrSamplesTest {

	@Test
	public void render() {
		CombineSamplePanel panel = tester.startComponentInPage(CombineSamplePanel.class);

		assertNotNull(panel);

	}
}
