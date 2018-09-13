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
	 * {@link ToastLevelFeedbackMessageFilter#lessThan(ToastLevel)} test.
	 *
	 * @param level
	 */
	@ParameterizedTest
	@EnumSource(value = ToastLevel.class, mode = Mode.EXCLUDE, names = { "UNDEFINED" })
	public void lessThan(ToastLevel level) {
		ToastLevelFeedbackMessageFilter filter = ToastLevelFeedbackMessageFilter.lessThan(level);

		Component dummy = new WebMarkupContainer("dummy");

		Integer feedbackMessageLevel = FeedbackMessageLevel.fromToastLevel(level).toInteger();
		FeedbackMessage argLevelMessage = new FeedbackMessage(dummy, Toast.create(level, "dummy"),
				feedbackMessageLevel);
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

	/**
	 * {@link ToastLevelFeedbackMessageFilter#lessThanOrEqual(ToastLevel)} test.
	 *
	 * @param level
	 */
	@ParameterizedTest
	@EnumSource(value = ToastLevel.class, mode = Mode.EXCLUDE, names = { "UNDEFINED" })
	public void lessThanOrEqual(ToastLevel level) {
		ToastLevelFeedbackMessageFilter filter = ToastLevelFeedbackMessageFilter.lessThanOrEqual(level);

		Component dummy = new WebMarkupContainer("dummy");

		Integer feedbackMessageLevel = FeedbackMessageLevel.fromToastLevel(level).toInteger();
		FeedbackMessage argLevelMessage = new FeedbackMessage(dummy, Toast.create(level, "dummy"),
				feedbackMessageLevel);
		assertTrue(filter.accept(argLevelMessage), "Argument's level is must contains for accepts level");

		Toast successToast = Toast.success("dummy");
		FeedbackMessage successMessage = new FeedbackMessage(dummy, successToast, FeedbackMessage.SUCCESS);
		assertEquals(filter.accept(successMessage),
				successToast.getToastLevel().lessThan(level) || successToast.getToastLevel().equals(level));

		Toast infoToast = Toast.info("dummy");
		FeedbackMessage infoMessage = new FeedbackMessage(dummy, infoToast, FeedbackMessage.INFO);
		assertEquals(filter.accept(infoMessage),
				infoToast.getToastLevel().lessThan(level) || infoToast.getToastLevel().equals(level));

		Toast warnToast = Toast.warn("dummy");
		FeedbackMessage warnMessage = new FeedbackMessage(dummy, warnToast, FeedbackMessage.WARNING);
		assertEquals(filter.accept(warnMessage),
				warnToast.getToastLevel().lessThan(level) || warnToast.getToastLevel().equals(level));

		Toast errorToast = Toast.error("dummy");
		FeedbackMessage errorMessage = new FeedbackMessage(dummy, errorToast, FeedbackMessage.ERROR);
		assertEquals(filter.accept(errorMessage),
				errorToast.getToastLevel().lessThan(level) || errorToast.getToastLevel().equals(level));

	}

	/**
	 * {@link ToastLevelFeedbackMessageFilter#greaterThan(ToastLevel)} test.
	 *
	 * @param level
	 */
	@ParameterizedTest
	@EnumSource(value = ToastLevel.class, mode = Mode.EXCLUDE, names = { "UNDEFINED" })
	public void greaterThan(ToastLevel level) {
		ToastLevelFeedbackMessageFilter filter = ToastLevelFeedbackMessageFilter.greaterThan(level);

		Component dummy = new WebMarkupContainer("dummy");

		Integer feedbackMessageLevel = FeedbackMessageLevel.fromToastLevel(level).toInteger();
		FeedbackMessage argLevelMessage = new FeedbackMessage(dummy, Toast.create(level, "dummy"),
				feedbackMessageLevel);
		assertFalse(filter.accept(argLevelMessage), "Argument's level is not must contains for accepts level");

		Toast successToast = Toast.success("dummy");
		FeedbackMessage successMessage = new FeedbackMessage(dummy, successToast, FeedbackMessage.SUCCESS);
		assertEquals(filter.accept(successMessage), successToast.getToastLevel().greaterThan(level));

		Toast infoToast = Toast.info("dummy");
		FeedbackMessage infoMessage = new FeedbackMessage(dummy, infoToast, FeedbackMessage.INFO);
		assertEquals(filter.accept(infoMessage), infoToast.getToastLevel().greaterThan(level));

		Toast warnToast = Toast.warn("dummy");
		FeedbackMessage warnMessage = new FeedbackMessage(dummy, warnToast, FeedbackMessage.WARNING);
		assertEquals(filter.accept(warnMessage), warnToast.getToastLevel().greaterThan(level));

		Toast errorToast = Toast.error("dummy");
		FeedbackMessage errorMessage = new FeedbackMessage(dummy, errorToast, FeedbackMessage.ERROR);
		assertEquals(filter.accept(errorMessage), errorToast.getToastLevel().greaterThan(level));

	}

	/**
	 * {@link ToastLevelFeedbackMessageFilter#greaterThanOrEqual(ToastLevel)} test.
	 *
	 * @param level
	 */
	@ParameterizedTest
	@EnumSource(value = ToastLevel.class, mode = Mode.EXCLUDE, names = { "UNDEFINED" })
	public void greaterThanOrEqual(ToastLevel level) {
		ToastLevelFeedbackMessageFilter filter = ToastLevelFeedbackMessageFilter.greaterThanOrEqual(level);

		Component dummy = new WebMarkupContainer("dummy");

		Integer feedbackMessageLevel = FeedbackMessageLevel.fromToastLevel(level).toInteger();
		FeedbackMessage argLevelMessage = new FeedbackMessage(dummy, Toast.create(level, "dummy"),
				feedbackMessageLevel);
		assertTrue(filter.accept(argLevelMessage), "Argument's level is must contains for accepts level");

		Toast successToast = Toast.success("dummy");
		FeedbackMessage successMessage = new FeedbackMessage(dummy, successToast, FeedbackMessage.SUCCESS);
		assertEquals(filter.accept(successMessage),
				successToast.getToastLevel().greaterThan(level) || successToast.getToastLevel().equals(level));

		Toast infoToast = Toast.info("dummy");
		FeedbackMessage infoMessage = new FeedbackMessage(dummy, infoToast, FeedbackMessage.INFO);
		assertEquals(filter.accept(infoMessage),
				infoToast.getToastLevel().greaterThan(level) || infoToast.getToastLevel().equals(level));

		Toast warnToast = Toast.warn("dummy");
		FeedbackMessage warnMessage = new FeedbackMessage(dummy, warnToast, FeedbackMessage.WARNING);
		assertEquals(filter.accept(warnMessage),
				warnToast.getToastLevel().greaterThan(level) || warnToast.getToastLevel().equals(level));

		Toast errorToast = Toast.error("dummy");
		FeedbackMessage errorMessage = new FeedbackMessage(dummy, errorToast, FeedbackMessage.ERROR);
		assertEquals(filter.accept(errorMessage),
				errorToast.getToastLevel().greaterThan(level) || errorToast.getToastLevel().equals(level));

	}


}
