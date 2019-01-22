package jp.try0.wicket.toastr.samples.panel;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import jp.try0.wicket.toastr.core.Toast;
import jp.try0.wicket.toastr.core.Toast.ToastLevel;
import jp.try0.wicket.toastr.samples.test.ToastrSamplesTest;

/**
 * {@link ToastBasicPanel} tests
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastBasicPanelTest extends ToastrSamplesTest {

	@Test
	public void render() {
		ToastBasicPanel panel = tester.startComponentInPage(ToastBasicPanel.class);
		assertNotNull(panel);
	}


	@Test
	public void getDefaultToast() {
		ToastBasicPanel panel = tester.startComponentInPage(ToastBasicPanel.class);
		assertNotNull(panel);

		Toast toast = panel.getToast();

		assertEquals(toast.getToastLevel(), ToastLevel.INFO);
		assertEquals(toast.getTitle(), Optional.empty());
		assertEquals(toast.getMessage(), "toast");

	}
}
