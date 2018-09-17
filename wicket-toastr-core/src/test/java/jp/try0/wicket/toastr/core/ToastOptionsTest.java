package jp.try0.wicket.toastr.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jp.try0.wicket.toastr.core.IToastOptions.OptionKeys;
import jp.try0.wicket.toastr.core.ToastOptions.CloseMethod;
import jp.try0.wicket.toastr.core.ToastOptions.PositionClass;

/**
 * {@link ToastOptions} tests.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastOptionsTest {

	@Test
	public void squeezeWithDoubleQuotes() {
		ToastOptions options = ToastOptions.create();
		options.setCloseClass("closeClass");
		options.setPositionClass(PositionClass.BOTTOM_CENTER);

		String optionObject = options.toJsonString().replaceAll(" ", "");

		assertTrue(optionObject.contains("\"" + OptionKeys.CLOSE_CLASS + "\":\"closeClass\","));
		assertTrue(optionObject
				.contains("\"" + OptionKeys.POSITION_CLASS + "\":\"" + PositionClass.BOTTOM_CENTER.toString() + "\","));

	}

	@Test
	public void unsqueezeWithDoubleQuotes() {
		ToastOptions options = ToastOptions.create();
		final String onCloseClick = "function(){alert('test');}";
		options.setOnCloseClickFunction(onCloseClick);

		String optionObject = options.toJsonString().replaceAll(" ", "");

		assertTrue(optionObject.contains("\"" + OptionKeys.ON_CLOSE_CLICK + "\":" + onCloseClick + ","));
	}

	/**
	 * {@link ToastOptions#overwrite(IToastOptions)}
	 */
	@Test
	public void createOverwriteOptions() {

		{
			ToastOptions base = ToastOptions.create();

			ToastOptions overwrite = ToastOptions.create();
			overwrite.setContainerId("overwriteId");
			overwrite.setCloseDureation(1000);
			overwrite.setCloseMethod(CloseMethod.FADE_OUT);

			ToastOptions overwritten = base.overwrite(overwrite);

			assertEquals(overwritten.getContainerId(), "overwriteId");
			assertTrue(overwritten.getCloseDureation() == 1000);
			assertEquals(overwritten.getCloseMethod(), CloseMethod.FADE_OUT);
		}

		{
			ToastOptions base = ToastOptions.create();
			base.setContainerId("baseId");
			base.setCloseDureation(10);

			ToastOptions overwrite = ToastOptions.create();
			overwrite.setContainerId("overwriteId");
			overwrite.setCloseDureation(1000);
			overwrite.setCloseMethod(CloseMethod.FADE_OUT);

			ToastOptions overwritten = base.overwrite(overwrite);

			assertEquals(overwritten.getContainerId(), "overwriteId");
			assertTrue(overwritten.getCloseDureation() == 1000);
			assertEquals(overwritten.getCloseMethod(), CloseMethod.FADE_OUT);
		}

		{
			ToastOptions base = ToastOptions.create();
			base.setContainerId("baseId");
			base.setCloseDureation(10);

			ToastOptions overwrite = ToastOptions.create();
			overwrite.setCloseMethod(CloseMethod.FADE_OUT);

			ToastOptions overwritten = base.overwrite(overwrite);

			assertEquals(overwritten.getContainerId(), "baseId");
			assertTrue(overwritten.getCloseDureation() == 10);
			assertEquals(overwritten.getCloseMethod(), CloseMethod.FADE_OUT);
		}

	}

}
