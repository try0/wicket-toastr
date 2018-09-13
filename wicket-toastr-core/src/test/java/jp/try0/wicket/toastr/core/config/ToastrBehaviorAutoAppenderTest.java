package jp.try0.wicket.toastr.core.config;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.wicket.Application;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.Test;

import jp.try0.wicket.toastr.core.behavior.ToastrBehavior;
import jp.try0.wicket.toastr.core.test.AbstractToastrTest;

/**
 * {@link ToastrBehaviorAutoAppender} tests.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastrBehaviorAutoAppenderTest extends AbstractToastrTest {

	/**
	 * {@link ToastrBehaviorAutoAppender} test.
	 */
	@Test
	public void appendToastrBehavior() {
		final Application app = getWebApplication();
		app.getComponentInstantiationListeners().add(new ToastrBehaviorAutoAppender());

		final WicketTester tester = getWicketTester();

		final WebMarkupContainer container = new WebMarkupContainer("dummy") {

			@Override
			public String getMarkupId() {
				return "dummy";
			}

			@Override
			public Markup getAssociatedMarkup() {
				return Markup.of("<div wicket:id=\"dummy\"></div>");
			}

			@Override
			protected void onComponentTag(ComponentTag tag) {
				tag.setName("div");

				super.onComponentTag(tag);
			}
		};

		tester.startComponentInPage(container);

		tester.assertBehavior(tester.getLastRenderedPage().getPageRelativePath(), ToastrBehavior.class);

		assertEquals(container.getBehaviors(ToastrBehavior.class).size(), 0, "must be added only to page");
		assertEquals(tester.getLastRenderedPage().getBehaviors(ToastrBehavior.class).size(), 1,
				"must be added only once to page");

		tester.startPage(tester.getLastRenderedPage());

		assertEquals(container.getBehaviors(ToastrBehavior.class).size(), 0, "must be added only to page");
		assertEquals(tester.getLastRenderedPage().getBehaviors(ToastrBehavior.class).size(), 1,
				"must be added only once to page");
	}

}
