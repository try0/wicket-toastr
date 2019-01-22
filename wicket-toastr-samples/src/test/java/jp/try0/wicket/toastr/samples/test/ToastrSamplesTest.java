package jp.try0.wicket.toastr.samples.test;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.BeforeEach;

import jp.try0.wicket.toastr.samples.WicketApplication;

/**
 * Toastr Samples Test Base
 *
 * @author Ryo Tsunoda
 *
 */
public abstract class ToastrSamplesTest {

	protected WicketTester tester;

	@BeforeEach
	public void setUp() {
		tester = new WicketTester(new WicketApplication());
	}

}
