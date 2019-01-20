package jp.try0.wicket.toastr.samples.panel;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jp.try0.wicket.toastr.core.Toast;
import jp.try0.wicket.toastr.core.Toast.ToastLevel;
import jp.try0.wicket.toastr.samples.test.ToastrSamplesTest;

/**
 * {@link ToastBasicPanel} Test
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastBasicPanelTest extends ToastrSamplesTest {

	@Test
	public void render() {
		ToastBasicPanel panel = tester.startComponentInPage(ToastBasicPanel.class);
		Assertions.assertNotNull(panel);
	}


	@Test
	public void getDefaultToast() {
		ToastBasicPanel panel = tester.startComponentInPage(ToastBasicPanel.class);
		Assertions.assertNotNull(panel);

		Toast toast = panel.getToast();

		Assertions.assertEquals(toast.getToastLevel(), ToastLevel.INFO);
		Assertions.assertEquals(toast.getTitle(), Optional.empty());
		Assertions.assertEquals(toast.getMessage(), "toast");

	}
}
