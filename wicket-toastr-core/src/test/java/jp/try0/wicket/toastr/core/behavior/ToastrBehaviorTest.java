package jp.try0.wicket.toastr.core.behavior;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.Test;

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

}
