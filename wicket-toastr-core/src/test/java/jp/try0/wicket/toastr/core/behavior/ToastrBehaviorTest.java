package jp.try0.wicket.toastr.core.behavior;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;

import jp.try0.wicket.toastr.core.Toast;
import jp.try0.wicket.toastr.core.Toast.ToastLevel;
import jp.try0.wicket.toastr.core.ToastOptions;
import jp.try0.wicket.toastr.core.ToastOptions.CloseMethod;
import jp.try0.wicket.toastr.core.ToastOptions.Easing;
import jp.try0.wicket.toastr.core.ToastOptions.HideMethod;
import jp.try0.wicket.toastr.core.ToastOptions.IconClass;
import jp.try0.wicket.toastr.core.ToastOptions.PositionClass;
import jp.try0.wicket.toastr.core.ToastOptions.ShowMethod;
import jp.try0.wicket.toastr.core.behavior.ToastrBehavior.ToastMessageCombiner;
import jp.try0.wicket.toastr.core.config.ToastrSettings;
import jp.try0.wicket.toastr.core.test.AbstractToastrTest;
import jp.try0.wicket.toastr.core.test.ToastrTestPage;

/**
 * {@link ToastrBehavior} tests.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastrBehaviorTest extends AbstractToastrTest {

	@Test
	public void appendScripts() {
		final WicketTester tester = getWicketTester();

		Page page = new ToastrTestPage() {
			{
				add(new ToastrBehavior());
			}
		};
		page.error("error");

		tester.startPage(page);

		final String lastResponseString = tester.getLastResponseAsString();
		assertTrue(lastResponseString.contains("toastr.error(\"error\","));

	}

	@Test
	public void appendScriptsOnAjaxRequest() {
		final WicketTester tester = getWicketTester();
		ToastrTestPage page = new ToastrTestPage() {
			{
				add(new ToastrBehavior());
			}

			@Override
			protected void onClickAjaxLink(AjaxRequestTarget target) {
				info("info");
			}
		};

		tester.startPage(page);
		{
			String lastResponseString = tester.getLastResponseAsString();
			assertTrue(!lastResponseString.contains("toastr.info(\"info\","));
		}

		tester.clickLink(page.getAjaxLink());
		{
			String lastResponseString = tester.getLastResponseAsString();
			assertTrue(lastResponseString.contains("toastr.info(\"info\","));
		}

	}

	@Test
	public void appendScriptsUseSession() {
		final WicketTester tester = getWicketTester();

		Page page = new ToastrTestPage() {
			{
				add(new ToastrBehavior());
			}
		};
		Session.get().error("error");

		tester.startPage(page);

		final String lastResponseString = tester.getLastResponseAsString();
		assertTrue(lastResponseString.contains("toastr.error(\"error\","));

	}

	@Test
	public void appendScriptsUseSessionOnAjaxRequest() {
		final WicketTester tester = getWicketTester();
		ToastrTestPage page = new ToastrTestPage() {
			{
				add(new ToastrBehavior());
			}

			@Override
			protected void onClickAjaxLink(AjaxRequestTarget target) {
				Session.get().info("info");
			}
		};

		tester.startPage(page);
		{
			String lastResponseString = tester.getLastResponseAsString();
			assertTrue(!lastResponseString.contains("toastr.info(\"info\","));
		}

		tester.clickLink(page.getAjaxLink());
		{
			String lastResponseString = tester.getLastResponseAsString();
			assertTrue(lastResponseString.contains("toastr.info(\"info\","));
		}

	}

	@ParameterizedTest
	@EnumSource(value = ToastLevel.class, mode = Mode.EXCLUDE, names = { "UNDEFINED" })
	public void useFilter(ToastLevel accept) {
		final WicketTester tester = getWicketTester();

		Page page = new ToastrTestPage() {
			{
				add(new ToastrBehavior(msg -> ToastLevel.fromFeedbackMessageLevel(msg.getLevel()) == accept));
			}
		};
		page.info(ToastLevel.INFO.getLevelString());
		page.success(ToastLevel.SUCCESS.getLevelString());
		page.warn(ToastLevel.WARNING.getLevelString());
		page.error(ToastLevel.ERROR.getLevelString());

		tester.startPage(page);

		final String lastResponseString = tester.getLastResponseAsString();

		for (ToastLevel lv : Arrays.asList(ToastLevel.INFO, ToastLevel.SUCCESS, ToastLevel.WARNING, ToastLevel.ERROR)) {
			if (lv == accept) {
				assertTrue(lastResponseString
						.contains("toastr." + lv.getLevelString() + "(\"" + lv.getLevelString() + "\","));
			} else {
				assertFalse(lastResponseString
						.contains("toastr." + lv.getLevelString() + "(\"" + lv.getLevelString() + "\","));
			}
		}

	}

	@Test
	public void combineToasts() {
		final WicketTester tester = getWicketTester();

		final String prefix = "TEST_P";
		final String suffix = "TEST_S";
		Page page = new ToastrTestPage() {
			{
				ToastrBehavior behavior = new ToastrBehavior();

				ToastMessageCombiner combiner = new ToastMessageCombiner();
				combiner.setPrefix(prefix);
				combiner.setSuffix(suffix);

				behavior.setMessageCombiner(combiner);
				add(behavior);
			}
		};

		page.fatal("msg1");
		page.fatal("msg2");
		page.fatal("msg3");

		tester.startPage(page);

		final String lastResponseString = tester.getLastResponseAsString();

		final String message = prefix + "msg1" + suffix
				+ prefix + "msg2" + suffix
				+ prefix + "msg3" + suffix;
		assertTrue(lastResponseString.contains("toastr.error(\"" + message + "\","));
	}

	@Test
	public void combineToastsThatHasOptions() {
		final WicketTester tester = getWicketTester();

		Page page = new ToastrTestPage() {
			{
				ToastrBehavior behavior = new ToastrBehavior();

				ToastMessageCombiner combiner = new ToastMessageCombiner();
				behavior.setMessageCombiner(combiner);
				add(behavior);
			}
		};

		final ToastOptions opt1 = ToastOptions.create().setCloseClass("cls");
		Toast.success("msg1")
				.withToastOptions(opt1)
				.show(page);
		final ToastOptions opt2 = ToastOptions.create().setCloseDureation(1000);
		Toast.success("msg2")
				.withToastOptions(opt2)
				.show(page);

		tester.startPage(page);

		final String lastResponseString = tester.getLastResponseAsString().replaceAll(" ", "");

		final String message = "msg1" + ToastMessageCombiner.DEFAULT_SUFFIX
				+ "msg2" + ToastMessageCombiner.DEFAULT_SUFFIX;
		final String options = opt1.overwrite(opt2).toJsonString();
		assertTrue(lastResponseString.contains("toastr.success(\"" + message + "\",\"\"," + options));
	}

	@Test
	public void outputToastOptions() {

		ToastrSettings.createInitializer(getWebApplication())
				.setAutoAppendBehavior(true)
				.initialize();

		final WicketTester tester = getWicketTester();
		ToastrTestPage page = new ToastrTestPage() {
			{
				add(new ToastrBehavior());
			}
		};
		ToastOptions options = ToastOptions.create()
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

		Toast.success("toastOptions")
				.withToastOptions(options)
				.show(page);

		tester.startPage(page);

		final String lastResponseString = tester.getLastResponseAsString();
		assertTrue(lastResponseString.contains("toastr.success(\"toastOptions\", \"\", " + options.toJsonString()));
	}
}
