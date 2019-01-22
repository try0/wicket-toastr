package jp.try0.wicket.toastr.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jp.try0.wicket.toastr.core.IToastOptions.OptionKeys;
import jp.try0.wicket.toastr.core.ToastOptions.CloseMethod;
import jp.try0.wicket.toastr.core.ToastOptions.Easing;
import jp.try0.wicket.toastr.core.ToastOptions.HideMethod;
import jp.try0.wicket.toastr.core.ToastOptions.IconClass;
import jp.try0.wicket.toastr.core.ToastOptions.PositionClass;
import jp.try0.wicket.toastr.core.ToastOptions.ShowMethod;

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

	/**
	 * {@link ToastOptions#overwrite(IToastOptions)}
	 */
	@Test
	public void overwriteAllOptions() {

		ToastOptions baseOptions = ToastOptions.create()
				.setCloseClass("closeClass-base")
				.setCloseDureation(0)
				.setCloseEasing(Easing.LINEAR)
				.setCloseHtml("closeHtml-base")
				.setCloseMethod(CloseMethod.FADE_OUT)
				.setContainerId("base")
				.setExtendedTimeOut(0)
				.setHideDuration(0)
				.setHideEasing(Easing.LINEAR)
				.setHideMethod(HideMethod.FADE_OUT)
				.setIconClass(IconClass.ERROR)
				.setIsCloseOnHover(false)
				.setIsDebug(false)
				.setIsEnableCloseButton(false)
				.setIsEnableProgressBar(false)
				.setIsNewestOnTop(false)
				.setIsRightToLeft(false)
				.setIsTapToDismiss(false)
				.setMessageClass("messageClass-base")
				.setNeedEscapeHtml(false)
				.setNeedPreventDuplicates(false)
				.setOnClickFunction("onClickFunction-base")
				.setOnCloseClickFunction("onCloseClickFunction-base")
				.setOnHiddenFunction("onHiddenFunction-base")
				.setOnShownFunction("onShownFunction-base")
				.setPositionClass(PositionClass.BOTTOM_CENTER)
				.setProgressClass("progressClass-base")
				.setShowDuration(0)
				.setShowEasing(Easing.LINEAR)
				.setShowMethod(ShowMethod.FADE_IN)
				.setTarget("target-base")
				.setTimeOut(0)
				.setTitleClass("titleClass-base")
				.setToastClass("toastClass-base");

		{
			ToastOptions overwriteOptions = ToastOptions.create()
					.setCloseClass("closeClass-new")
					.setCloseDureation(1)
					.setCloseEasing(Easing.SWING)
					.setCloseHtml("closeHtml-new")
					.setCloseMethod(CloseMethod.HIDE)
					.setContainerId("new")
					.setExtendedTimeOut(1)
					.setHideDuration(1)
					.setHideEasing(Easing.SWING)
					.setHideMethod(HideMethod.HIDE)
					.setIconClass(IconClass.INFO)
					.setIsCloseOnHover(true)
					.setIsDebug(true)
					.setIsEnableCloseButton(true)
					.setIsEnableProgressBar(true)
					.setIsNewestOnTop(true)
					.setIsRightToLeft(true)
					.setIsTapToDismiss(true)
					.setMessageClass("messageClass-new")
					.setNeedEscapeHtml(true)
					.setNeedPreventDuplicates(true)
					.setOnClickFunction("onClickFunction-new")
					.setOnCloseClickFunction("onCloseClickFunction-new")
					.setOnHiddenFunction("onHiddenFunction-new")
					.setOnShownFunction("onShownFunction-new")
					.setPositionClass(PositionClass.BOTTOM_FULL_WIDTH)
					.setProgressClass("progressClass-new")
					.setShowDuration(1)
					.setShowEasing(Easing.SWING)
					.setShowMethod(ShowMethod.SHOW)
					.setTarget("target-new")
					.setTimeOut(1)
					.setTitleClass("titleClass-new")
					.setToastClass("toastClass-new");

			ToastOptions newOptions = baseOptions.overwrite(overwriteOptions);

			assertEquals(newOptions.getCloseClass(), overwriteOptions.getCloseClass());
			assertEquals(newOptions.getCloseDureation(), overwriteOptions.getCloseDureation());
			assertEquals(newOptions.getCloseEasing(), overwriteOptions.getCloseEasing());
			assertEquals(newOptions.getCloseHtml(), overwriteOptions.getCloseHtml());
			assertEquals(newOptions.getCloseMethod(), overwriteOptions.getCloseMethod());
			assertEquals(newOptions.getContainerId(), overwriteOptions.getContainerId());
			assertEquals(newOptions.getExtendedTimeOut(), overwriteOptions.getExtendedTimeOut());
			assertEquals(newOptions.getHideDuration(), overwriteOptions.getHideDuration());
			assertEquals(newOptions.getHideEasing(), overwriteOptions.getHideEasing());
			assertEquals(newOptions.getHideMethod(), overwriteOptions.getHideMethod());
			assertEquals(newOptions.getIconClass(), overwriteOptions.getIconClass());
			assertEquals(newOptions.isCloseOnHover(), overwriteOptions.isCloseOnHover());
			assertEquals(newOptions.isDebug(), overwriteOptions.isDebug());
			assertEquals(newOptions.isEnableCloseButton(), overwriteOptions.isEnableCloseButton());
			assertEquals(newOptions.isEnableProgressBar(), overwriteOptions.isEnableProgressBar());
			assertEquals(newOptions.isNewestOnTop(), overwriteOptions.isNewestOnTop());
			assertEquals(newOptions.isRightToLeft(), overwriteOptions.isRightToLeft());
			assertEquals(newOptions.isTapToDismiss(), overwriteOptions.isTapToDismiss());
			assertEquals(newOptions.getMessageClass(), overwriteOptions.getMessageClass());
			assertEquals(newOptions.needEscapeHtml(), overwriteOptions.needEscapeHtml());
			assertEquals(newOptions.needPreventDuplicates(), overwriteOptions.needPreventDuplicates());
			assertEquals(newOptions.getOnClickFunction(), overwriteOptions.getOnClickFunction());
			assertEquals(newOptions.getOnCloseClickFunction(), overwriteOptions.getOnCloseClickFunction());
			assertEquals(newOptions.getOnHiddenFunction(), overwriteOptions.getOnHiddenFunction());
			assertEquals(newOptions.getOnShownFunction(), overwriteOptions.getOnShownFunction());
			assertEquals(newOptions.getPositionClass(), overwriteOptions.getPositionClass());
			assertEquals(newOptions.getProgressClass(), overwriteOptions.getProgressClass());
			assertEquals(newOptions.getShowDuration(), overwriteOptions.getShowDuration());
			assertEquals(newOptions.getShowEasing(), overwriteOptions.getShowEasing());
			assertEquals(newOptions.getShowMethod(), overwriteOptions.getShowMethod());
			assertEquals(newOptions.getTarget(), overwriteOptions.getTarget());
			assertEquals(newOptions.getTimeOut(), overwriteOptions.getTimeOut());
			assertEquals(newOptions.getTitleClass(), overwriteOptions.getTitleClass());
			assertEquals(newOptions.getToastClass(), overwriteOptions.getToastClass());

		}

		{
			ToastOptions overwriteNullOptions = ToastOptions.create();

			ToastOptions newOptions = baseOptions.overwrite(overwriteNullOptions);

			assertEquals(newOptions.getCloseClass(), baseOptions.getCloseClass());
			assertEquals(newOptions.getCloseDureation(), baseOptions.getCloseDureation());
			assertEquals(newOptions.getCloseEasing(), baseOptions.getCloseEasing());
			assertEquals(newOptions.getCloseHtml(), baseOptions.getCloseHtml());
			assertEquals(newOptions.getCloseMethod(), baseOptions.getCloseMethod());
			assertEquals(newOptions.getContainerId(), baseOptions.getContainerId());
			assertEquals(newOptions.getExtendedTimeOut(), baseOptions.getExtendedTimeOut());
			assertEquals(newOptions.getHideDuration(), baseOptions.getHideDuration());
			assertEquals(newOptions.getHideEasing(), baseOptions.getHideEasing());
			assertEquals(newOptions.getHideMethod(), baseOptions.getHideMethod());
			assertEquals(newOptions.getIconClass(), baseOptions.getIconClass());
			assertEquals(newOptions.isCloseOnHover(), baseOptions.isCloseOnHover());
			assertEquals(newOptions.isDebug(), baseOptions.isDebug());
			assertEquals(newOptions.isEnableCloseButton(), baseOptions.isEnableCloseButton());
			assertEquals(newOptions.isEnableProgressBar(), baseOptions.isEnableProgressBar());
			assertEquals(newOptions.isNewestOnTop(), baseOptions.isNewestOnTop());
			assertEquals(newOptions.isRightToLeft(), baseOptions.isRightToLeft());
			assertEquals(newOptions.isTapToDismiss(), baseOptions.isTapToDismiss());
			assertEquals(newOptions.getMessageClass(), baseOptions.getMessageClass());
			assertEquals(newOptions.needEscapeHtml(), baseOptions.needEscapeHtml());
			assertEquals(newOptions.needPreventDuplicates(), baseOptions.needPreventDuplicates());
			assertEquals(newOptions.getOnClickFunction(), baseOptions.getOnClickFunction());
			assertEquals(newOptions.getOnCloseClickFunction(), baseOptions.getOnCloseClickFunction());
			assertEquals(newOptions.getOnHiddenFunction(), baseOptions.getOnHiddenFunction());
			assertEquals(newOptions.getOnShownFunction(), baseOptions.getOnShownFunction());
			assertEquals(newOptions.getPositionClass(), baseOptions.getPositionClass());
			assertEquals(newOptions.getProgressClass(), baseOptions.getProgressClass());
			assertEquals(newOptions.getShowDuration(), baseOptions.getShowDuration());
			assertEquals(newOptions.getShowEasing(), baseOptions.getShowEasing());
			assertEquals(newOptions.getShowMethod(), baseOptions.getShowMethod());
			assertEquals(newOptions.getTarget(), baseOptions.getTarget());
			assertEquals(newOptions.getTimeOut(), baseOptions.getTimeOut());
			assertEquals(newOptions.getTitleClass(), baseOptions.getTitleClass());
			assertEquals(newOptions.getToastClass(), baseOptions.getToastClass());
		}
	}
}
