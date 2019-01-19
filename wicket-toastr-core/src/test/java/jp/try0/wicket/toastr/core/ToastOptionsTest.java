package jp.try0.wicket.toastr.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
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

			Assertions.assertEquals(newOptions.getCloseClass(), overwriteOptions.getCloseClass());
			Assertions.assertEquals(newOptions.getCloseDureation(), overwriteOptions.getCloseDureation());
			Assertions.assertEquals(newOptions.getCloseEasing(), overwriteOptions.getCloseEasing());
			Assertions.assertEquals(newOptions.getCloseHtml(), overwriteOptions.getCloseHtml());
			Assertions.assertEquals(newOptions.getCloseMethod(), overwriteOptions.getCloseMethod());
			Assertions.assertEquals(newOptions.getContainerId(), overwriteOptions.getContainerId());
			Assertions.assertEquals(newOptions.getExtendedTimeOut(), overwriteOptions.getExtendedTimeOut());
			Assertions.assertEquals(newOptions.getHideDuration(), overwriteOptions.getHideDuration());
			Assertions.assertEquals(newOptions.getHideEasing(), overwriteOptions.getHideEasing());
			Assertions.assertEquals(newOptions.getHideMethod(), overwriteOptions.getHideMethod());
			Assertions.assertEquals(newOptions.getIconClass(), overwriteOptions.getIconClass());
			Assertions.assertEquals(newOptions.isCloseOnHover(), overwriteOptions.isCloseOnHover());
			Assertions.assertEquals(newOptions.isDebug(), overwriteOptions.isDebug());
			Assertions.assertEquals(newOptions.isEnableCloseButton(), overwriteOptions.isEnableCloseButton());
			Assertions.assertEquals(newOptions.isEnableProgressBar(), overwriteOptions.isEnableProgressBar());
			Assertions.assertEquals(newOptions.isNewestOnTop(), overwriteOptions.isNewestOnTop());
			Assertions.assertEquals(newOptions.isRightToLeft(), overwriteOptions.isRightToLeft());
			Assertions.assertEquals(newOptions.isTapToDismiss(), overwriteOptions.isTapToDismiss());
			Assertions.assertEquals(newOptions.getMessageClass(), overwriteOptions.getMessageClass());
			Assertions.assertEquals(newOptions.needEscapeHtml(), overwriteOptions.needEscapeHtml());
			Assertions.assertEquals(newOptions.needPreventDuplicates(), overwriteOptions.needPreventDuplicates());
			Assertions.assertEquals(newOptions.getOnClickFunction(), overwriteOptions.getOnClickFunction());
			Assertions.assertEquals(newOptions.getOnCloseClickFunction(), overwriteOptions.getOnCloseClickFunction());
			Assertions.assertEquals(newOptions.getOnHiddenFunction(), overwriteOptions.getOnHiddenFunction());
			Assertions.assertEquals(newOptions.getOnShownFunction(), overwriteOptions.getOnShownFunction());
			Assertions.assertEquals(newOptions.getPositionClass(), overwriteOptions.getPositionClass());
			Assertions.assertEquals(newOptions.getProgressClass(), overwriteOptions.getProgressClass());
			Assertions.assertEquals(newOptions.getShowDuration(), overwriteOptions.getShowDuration());
			Assertions.assertEquals(newOptions.getShowEasing(), overwriteOptions.getShowEasing());
			Assertions.assertEquals(newOptions.getShowMethod(), overwriteOptions.getShowMethod());
			Assertions.assertEquals(newOptions.getTarget(), overwriteOptions.getTarget());
			Assertions.assertEquals(newOptions.getTimeOut(), overwriteOptions.getTimeOut());
			Assertions.assertEquals(newOptions.getTitleClass(), overwriteOptions.getTitleClass());
			Assertions.assertEquals(newOptions.getToastClass(), overwriteOptions.getToastClass());

		}

		{
			ToastOptions overwriteNullOptions = ToastOptions.create();

			ToastOptions newOptions = baseOptions.overwrite(overwriteNullOptions);

			Assertions.assertEquals(newOptions.getCloseClass(), baseOptions.getCloseClass());
			Assertions.assertEquals(newOptions.getCloseDureation(), baseOptions.getCloseDureation());
			Assertions.assertEquals(newOptions.getCloseEasing(), baseOptions.getCloseEasing());
			Assertions.assertEquals(newOptions.getCloseHtml(), baseOptions.getCloseHtml());
			Assertions.assertEquals(newOptions.getCloseMethod(), baseOptions.getCloseMethod());
			Assertions.assertEquals(newOptions.getContainerId(), baseOptions.getContainerId());
			Assertions.assertEquals(newOptions.getExtendedTimeOut(), baseOptions.getExtendedTimeOut());
			Assertions.assertEquals(newOptions.getHideDuration(), baseOptions.getHideDuration());
			Assertions.assertEquals(newOptions.getHideEasing(), baseOptions.getHideEasing());
			Assertions.assertEquals(newOptions.getHideMethod(), baseOptions.getHideMethod());
			Assertions.assertEquals(newOptions.getIconClass(), baseOptions.getIconClass());
			Assertions.assertEquals(newOptions.isCloseOnHover(), baseOptions.isCloseOnHover());
			Assertions.assertEquals(newOptions.isDebug(), baseOptions.isDebug());
			Assertions.assertEquals(newOptions.isEnableCloseButton(), baseOptions.isEnableCloseButton());
			Assertions.assertEquals(newOptions.isEnableProgressBar(), baseOptions.isEnableProgressBar());
			Assertions.assertEquals(newOptions.isNewestOnTop(), baseOptions.isNewestOnTop());
			Assertions.assertEquals(newOptions.isRightToLeft(), baseOptions.isRightToLeft());
			Assertions.assertEquals(newOptions.isTapToDismiss(), baseOptions.isTapToDismiss());
			Assertions.assertEquals(newOptions.getMessageClass(), baseOptions.getMessageClass());
			Assertions.assertEquals(newOptions.needEscapeHtml(), baseOptions.needEscapeHtml());
			Assertions.assertEquals(newOptions.needPreventDuplicates(), baseOptions.needPreventDuplicates());
			Assertions.assertEquals(newOptions.getOnClickFunction(), baseOptions.getOnClickFunction());
			Assertions.assertEquals(newOptions.getOnCloseClickFunction(), baseOptions.getOnCloseClickFunction());
			Assertions.assertEquals(newOptions.getOnHiddenFunction(), baseOptions.getOnHiddenFunction());
			Assertions.assertEquals(newOptions.getOnShownFunction(), baseOptions.getOnShownFunction());
			Assertions.assertEquals(newOptions.getPositionClass(), baseOptions.getPositionClass());
			Assertions.assertEquals(newOptions.getProgressClass(), baseOptions.getProgressClass());
			Assertions.assertEquals(newOptions.getShowDuration(), baseOptions.getShowDuration());
			Assertions.assertEquals(newOptions.getShowEasing(), baseOptions.getShowEasing());
			Assertions.assertEquals(newOptions.getShowMethod(), baseOptions.getShowMethod());
			Assertions.assertEquals(newOptions.getTarget(), baseOptions.getTarget());
			Assertions.assertEquals(newOptions.getTimeOut(), baseOptions.getTimeOut());
			Assertions.assertEquals(newOptions.getTitleClass(), baseOptions.getTitleClass());
			Assertions.assertEquals(newOptions.getToastClass(), baseOptions.getToastClass());
		}
	}
}
