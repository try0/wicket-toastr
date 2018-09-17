package jp.try0.wicket.toastr.core.config;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.Test;

import jp.try0.wicket.toastr.core.behavior.ToastrBehavior;
import jp.try0.wicket.toastr.core.test.AbstractToastrTest;
import jp.try0.wicket.toastr.core.test.ToastrTestPage;

/**
 * {@link ToastrSettings} tests.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastrSettingsTest extends AbstractToastrTest {

	@Test
	public void autoAppendBehavior() {

		ToastrSettings.createInitializer(getWebApplication())
				.setAutoAppendBehavior(true)
				.initialize();

		final WicketTester tester = getWicketTester();
		tester.startPage(ToastrTestPage.class);
		List<ToastrBehavior> behaviors = tester.getLastRenderedPage().getBehaviors(ToastrBehavior.class);
		assertTrue(behaviors.size() == 1);

	}

	@Test
	public void customBehaviorFactory() {

		final ToastrBehavior behavior = new ToastrBehavior();
		ToastrSettings.createInitializer(getWebApplication())
				.setAutoAppendBehavior(true)
				.setToastrBehaviorFactory(() -> {
					return behavior;
				})
				.initialize();

		final WicketTester tester = getWicketTester();

		tester.startPage(ToastrTestPage.class);
		List<ToastrBehavior> behaviors = tester.getLastRenderedPage().getBehaviors(ToastrBehavior.class);
		assertTrue(behaviors.size() == 1);
		assertTrue(behaviors.contains(behavior));
	}

	@Test
	public void useFontAwesomeIcons() {

		ToastrFontAwesomeSettings fontAwesomeSettings = new ToastrFontAwesomeSettings(
				new ToastrFontAwesomeIcons(
						"\\f058", // fa-check-circle
						"\\f05a", // fa-info-circle
						"\\f071", // fa-exclamation-triangle
						"\\f057"  // fa-times-circle
		));
		ToastrSettings.createInitializer(getWebApplication())
				.setAutoAppendBehavior(true)
				.setFontAwesomeSettings(fontAwesomeSettings)
				.initialize();

		final WicketTester tester = getWicketTester();
		tester.startPage(ToastrTestPage.class);

		final String lastResponseString = tester.getLastResponseAsString();

		assertTrue(lastResponseString.contains(fontAwesomeSettings.getFontAwesomeCssLinkTag()));
		assertTrue(lastResponseString.contains(fontAwesomeSettings.getIcons().getStyleForAdaptIconContent()));

	}

}
