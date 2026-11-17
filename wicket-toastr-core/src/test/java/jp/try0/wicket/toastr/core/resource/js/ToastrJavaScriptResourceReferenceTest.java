package jp.try0.wicket.toastr.core.resource.js;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.apache.wicket.Page;
import org.apache.wicket.ThreadContext;
import org.apache.wicket.ajax.WicketAjaxResourceReference;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.resource.JQueryResourceReference;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.Test;

import jp.try0.wicket.toastr.core.behavior.ToastrBehavior;
import jp.try0.wicket.toastr.core.test.AbstractToastrTest;
import jp.try0.wicket.toastr.core.test.ToastrTestPage;

/**
 * {@link ToastrJavaScriptResourceReference} tests.<br>
 * toastr requires jQuery, so the reference must always declare jQuery as a dependency.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastrJavaScriptResourceReferenceTest extends AbstractToastrTest {

	private static Optional<ResourceReference> findJQueryDependency(ToastrJavaScriptResourceReference reference) {
		List<HeaderItem> dependencies = reference.getDependencies();
		return dependencies.stream()
				.filter(JavaScriptReferenceHeaderItem.class::isInstance)
				.map(JavaScriptReferenceHeaderItem.class::cast)
				.map(JavaScriptReferenceHeaderItem::getReference)
				.filter(JQueryResourceReference.class::isInstance)
				.findFirst();
	}

	@Test
	public void dependsOnApplicationJQueryByDefault() {
		ResourceReference jQuery = findJQueryDependency(new ToastrJavaScriptResourceReference()).orElseThrow();

		assertSame(getWebApplication().getJavaScriptLibrarySettings().getJQueryReference(), jQuery);
		// Wicket 11 default
		assertSame(JQueryResourceReference.getV4(), jQuery);
	}

	@Test
	public void dependsOnConfiguredJQuery() {
		getWebApplication().getJavaScriptLibrarySettings().setJQueryReference(JQueryResourceReference.getV3());

		ResourceReference jQuery = findJQueryDependency(new ToastrJavaScriptResourceReference()).orElseThrow();

		assertSame(JQueryResourceReference.getV3(), jQuery);
	}

	@Test
	public void dependsOnJQuery4WithoutApplication() {
		ThreadContext context = ThreadContext.detach();
		try {
			ResourceReference jQuery = findJQueryDependency(new ToastrJavaScriptResourceReference()).orElseThrow();

			assertSame(JQueryResourceReference.getV4(), jQuery);
		} finally {
			ThreadContext.restore(context);
		}
	}

	@Test
	public void rendersJQueryBeforeToastrEvenIfWicketAjaxDoesNotUseJQuery() {
		// Wicket 11 can run its Ajax without jQuery. toastr still needs it.
		getWebApplication().getJavaScriptLibrarySettings()
				.setWicketAjaxReference(WicketAjaxResourceReference.get());

		final WicketTester tester = getWicketTester();
		Page page = new ToastrTestPage() {
			{
				add(new ToastrBehavior());
			}
		};
		page.info("info");
		tester.startPage(page);

		final String response = tester.getLastResponseAsString();
		assertFalse(response.contains("wicket-ajax-jquery"), "Wicket Ajax should not use jQuery in this test");

		// resource urls are like ".../toastr.min-ver-1234567890.js"
		final int jQueryIndex = response.indexOf("JQueryResourceReference/jquery/jquery-4.0.0");
		final int toastrIndex = response.indexOf("ToastrJavaScriptResourceReference/toastr.min");
		assertTrue(jQueryIndex >= 0, "jQuery is not rendered");
		assertTrue(toastrIndex >= 0, "toastr.js is not rendered");
		assertTrue(jQueryIndex < toastrIndex, "jQuery must be rendered before toastr.js");
		assertTrue(response.contains("toastr.info(\"info\","));
	}
}
