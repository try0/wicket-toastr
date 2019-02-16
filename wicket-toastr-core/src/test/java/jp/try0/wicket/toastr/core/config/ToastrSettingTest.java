package jp.try0.wicket.toastr.core.config;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.Test;

import jp.try0.wicket.toastr.core.Toast.ToastLevel;
import jp.try0.wicket.toastr.core.ToastOption;
import jp.try0.wicket.toastr.core.ToastOption.CloseMethod;
import jp.try0.wicket.toastr.core.ToastOption.Easing;
import jp.try0.wicket.toastr.core.ToastOption.HideMethod;
import jp.try0.wicket.toastr.core.ToastOption.IconClass;
import jp.try0.wicket.toastr.core.ToastOption.PositionClass;
import jp.try0.wicket.toastr.core.ToastOption.ShowMethod;
import jp.try0.wicket.toastr.core.ToastOptions;
import jp.try0.wicket.toastr.core.behavior.ToastrBehavior;
import jp.try0.wicket.toastr.core.behavior.ToastrBehavior.ToastMessageCombiner;
import jp.try0.wicket.toastr.core.test.AbstractToastrTest;
import jp.try0.wicket.toastr.core.test.ToastrTestPage;

/**
 * {@link ToastrSetting} tests.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastrSettingTest extends AbstractToastrTest {

	@Test
	public void initializeSettings() {

		boolean autoAppend = true;
		ToastOption options = ToastOption.create();
		IFeedbackMessageFilter filter = msg -> true;
		Function<Optional<IFeedbackMessageFilter>, ToastrBehavior> factory = optFilter -> new ToastrBehavior();
		ToastMessageCombiner combier = new ToastMessageCombiner();
		ToastrFontAwesomeSetting fontSettings = new ToastrFontAwesomeSetting(
				new ToastrFontAwesomeIcons("", "", "", ""));

		ToastrSetting.createInitializer(getWebApplication())
				.setAutoAppendBehavior(autoAppend)
				.setGlobalOptions(options)
				.setMessageFilter(filter)
				.setToastrBehaviorFactory(factory)
				.setToastMessageCombiner(combier)
				.setFontAwesomeSettings(fontSettings)
				.initialize();

		ToastrSetting settings = ToastrSetting.get();

		assertEquals(settings.hasGlobalOptions(), true);
		assertTrue(settings.getGlobalOptions().isPresent());
		assertTrue(settings.hasMessageFilter());
		assertTrue(settings.getFontAwesomeSettings().isPresent());
		assertTrue(settings.getGlobalOptions().get() == options);
		assertTrue(settings.getMessageFilter().get() == filter);
		assertTrue(settings.getToastMessageCombiner() == combier);
		assertTrue(settings.getFontAwesomeSettings().get() == fontSettings);
	}

	@Test
	public void initializeSimpleSettings() {

		ToastrSetting.createInitializer(getWebApplication()).initialize();

		ToastrSetting settings = ToastrSetting.get();

		assertEquals(settings.hasGlobalOptions(), false);
		assertFalse(settings.getGlobalOptions().isPresent());
		assertTrue(settings.getMessageFilter().isPresent());
		assertEquals(settings.getMessageFilter().get(), IFeedbackMessageFilter.ALL);
		assertFalse(settings.getFontAwesomeSettings().isPresent());
	}

	@Test
	public void initializeSettingsTwice() {
		assertThrows(UnsupportedOperationException.class, () -> {
			ToastrSetting.createInitializer(getWebApplication()).initialize();
			ToastrSetting.createInitializer(getWebApplication()).initialize();
		});
	}

	@Test
	public void autoAppendBehavior() {

		ToastrSetting.createInitializer(getWebApplication())
				.setAutoAppendBehavior(true)
				.initialize();

		final WicketTester tester = getWicketTester();
		tester.startPage(ToastrTestPage.class);
		List<ToastrBehavior> behaviors = tester.getLastRenderedPage().getBehaviors(ToastrBehavior.class);
		assertTrue(behaviors.size() == 1);

	}

	@Test
	public void customBehaviorFactory() {

		final IModel<ToastrBehavior> behavior = Model.of();
		ToastrSetting.createInitializer(getWebApplication())
				.setAutoAppendBehavior(true)
				.setToastrBehaviorFactory(filter -> {
					behavior.setObject(new ToastrBehavior());
					return behavior.getObject();
				})
				.initialize();

		final WicketTester tester = getWicketTester();

		tester.startPage(ToastrTestPage.class);
		List<ToastrBehavior> behaviors = tester.getLastRenderedPage().getBehaviors(ToastrBehavior.class);
		assertTrue(behaviors.size() == 1);
		assertTrue(behaviors.contains(behavior.getObject()));
	}

	@Test
	public void useFontAwesomeIcons() {

		ToastrFontAwesomeSetting fontAwesomeSettings = new ToastrFontAwesomeSetting(
				new ToastrFontAwesomeIcons(
						"\\f058", // fa-check-circle
						"\\f05a", // fa-info-circle
						"\\f071", // fa-exclamation-triangle
						"\\f057" // fa-times-circle
				));
		ToastrSetting.createInitializer(getWebApplication())
				.setAutoAppendBehavior(true)
				.setFontAwesomeSettings(fontAwesomeSettings)
				.initialize();

		final WicketTester tester = getWicketTester();
		tester.startPage(ToastrTestPage.class);

		final String lastResponseString = tester.getLastResponseAsString();

		assertTrue(lastResponseString.contains(fontAwesomeSettings.getFontAwesomeCssLinkTag()));
		assertTrue(lastResponseString.contains(fontAwesomeSettings.getIcons().getStyleForAdaptIconContent()));

	}

	@Test
	public void setGlobalOptions() {
		ToastOption globalOptions = ToastOption.create()
				.setCloseClass("closeClass-global")
				.setCloseDureation(0)
				.setCloseEasing(Easing.LINEAR)
				.setCloseHtml("closeHtml-global")
				.setCloseMethod(CloseMethod.FADE_OUT)
				.setContainerId("global")
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
				.setMessageClass("messageClass-global")
				.setNeedEscapeHtml(false)
				.setNeedPreventDuplicates(false)
				.setOnClickFunction("onClickFunction-global")
				.setOnCloseClickFunction("onCloseClickFunction-global")
				.setOnHiddenFunction("onHiddenFunction-global")
				.setOnShownFunction("onShownFunction-global")
				.setPositionClass(PositionClass.BOTTOM_CENTER)
				.setProgressClass("progressClass-global")
				.setShowDuration(0)
				.setShowEasing(Easing.LINEAR)
				.setShowMethod(ShowMethod.FADE_IN)
				.setTarget("target-global")
				.setTimeOut(0)
				.setTitleClass("titleClass-global")
				.setToastClass("toastClass-global");
		ToastrSetting.createInitializer(getWebApplication())
				.setAutoAppendBehavior(true)
				.setGlobalOptions(globalOptions)
				.initialize();

		final WicketTester tester = getWicketTester();
		tester.startPage(ToastrTestPage.class);

		final String lastResponseString = tester.getLastResponseAsString();
		assertTrue(lastResponseString.contains(globalOptions.toJsonString()));

	}

	@Test
	public void setGlobalEachLevelOptions() {

		ToastOption info = new ToastOption();
		ToastOption success = new ToastOption();
		ToastOption warn = new ToastOption();
		ToastOption error = new ToastOption();

		ToastOptions options = new ToastOptions(new HashMap<ToastLevel, ToastOption>() {
			{
				put(ToastLevel.INFO, info);
				put(ToastLevel.SUCCESS, success);
				put(ToastLevel.WARNING, warn);
				put(ToastLevel.ERROR, error);
			}
		});

		ToastrSetting setting = ToastrSetting.createInitializer(getWebApplication())
				.setAutoAppendBehavior(true)
				.setGlobalEachLevelOptions(options)
				.initialize();

		assertTrue(setting.getGlobalEachLevelOptions().get(ToastLevel.INFO).isPresent());
		assertTrue(setting.getGlobalEachLevelOptions().get(ToastLevel.INFO).get() == info);
		assertTrue(setting.getGlobalEachLevelOptions().get(ToastLevel.SUCCESS).isPresent());
		assertTrue(setting.getGlobalEachLevelOptions().get(ToastLevel.SUCCESS).get() == success);
		assertTrue(setting.getGlobalEachLevelOptions().get(ToastLevel.WARNING).isPresent());
		assertTrue(setting.getGlobalEachLevelOptions().get(ToastLevel.WARNING).get() == warn);
		assertTrue(setting.getGlobalEachLevelOptions().get(ToastLevel.ERROR).isPresent());
		assertTrue(setting.getGlobalEachLevelOptions().get(ToastLevel.ERROR).get() == error);

	}
}
