package jp.try0.wicket.toastr.core.feedback;

 import static org.junit.jupiter.api.Assertions.*;

import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;

import jp.try0.wicket.toastr.core.Toast;
import jp.try0.wicket.toastr.core.Toast.FeedbackMessageLevel;
import jp.try0.wicket.toastr.core.Toast.ToastLevel;
import jp.try0.wicket.toastr.core.test.AbstractToastrTest;


/**
 * {@link ToastLevelFeedbackMessageFilter} Tests.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastLevelFeedbackMessageFilterTest extends AbstractToastrTest {

	/**
	 * {@see ToastLevelFeedbackMessageFilter#lessThan(ToastLevel)
	 *
	 * @param level
	 */
	@ParameterizedTest
	@EnumSource(value=ToastLevel.class, mode = Mode.EXCLUDE, names= {"UNDEFINED"})
	public void lessThan(ToastLevel level) {
		ToastLevelFeedbackMessageFilter filter = ToastLevelFeedbackMessageFilter.lessThan(level);

		Component dummy = new WebMarkupContainer("dummy");

		Integer feedbackMessageLevel = FeedbackMessageLevel.fromToastLevel(level).toInteger();
		FeedbackMessage argLevelMessage = new FeedbackMessage(dummy, Toast.create(level, "dummy"), feedbackMessageLevel);
		assertFalse(filter.accept(argLevelMessage), "Argument's level is must not contains for accepts level");


		Toast successToast = Toast.success("dummy");
		FeedbackMessage successMessage = new FeedbackMessage(dummy, successToast, FeedbackMessage.SUCCESS);
		assertEquals(filter.accept(successMessage), successToast.getToastLevel().lessThan(level));


		Toast infoToast = Toast.info("dummy");
		FeedbackMessage infoMessage = new FeedbackMessage(dummy, infoToast, FeedbackMessage.INFO);
		assertEquals(filter.accept(infoMessage), infoToast.getToastLevel().lessThan(level));


		Toast warnToast = Toast.warn("dummy");
		FeedbackMessage warnMessage = new FeedbackMessage(dummy, warnToast, FeedbackMessage.WARNING);
		assertEquals(filter.accept(warnMessage), warnToast.getToastLevel().lessThan(level));


		Toast errorToast = Toast.error("dummy");
		FeedbackMessage errorMessage = new FeedbackMessage(dummy, errorToast, FeedbackMessage.ERROR);
		assertEquals(filter.accept(errorMessage), errorToast.getToastLevel().lessThan(level));

	}

}
