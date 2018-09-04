package jp.try0.wicket.toastr.core.feedback;

import java.util.Optional;

import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackMessage;

import jp.try0.wicket.toastr.core.IToast;
import jp.try0.wicket.toastr.core.Toast;
import jp.try0.wicket.toastr.core.Toast.FeedbackMessageLevel;
import jp.try0.wicket.toastr.core.Toast.ToastLevel;
import jp.try0.wicket.toastr.core.ToastOptions;

/**
 * Toast message
 *
 * @author Ryo Tsunoda
 *
 */
public class FeedbackToast extends FeedbackMessage implements IToast
{
	/**
	 * Toast
	 */
	private Toast toast;

	/**
	 * Constractor
	 *
	 * @param reporter
	 * @param toast
	 */
	public FeedbackToast(Component reporter, Toast toast) {
		super(reporter, toast.getToastMessage(), FeedbackMessageLevel.fromToastLevel(toast.getToastLevel()).toInteger());
		this.toast = toast;
	}

	/**
	 * Gets toast.
	 *
	 * @return
	 */
	public Toast get() {
		return toast;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ToastLevel getToastLevel() {
		return toast.getToastLevel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getToastMessage() {
		return toast.getToastMessage();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<String> getToastTitle() {
		return toast.getToastTitle();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<ToastOptions> getToastOptions() {
		return toast.getToastOptions();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getScriptForDisplay() {
		return toast.getScriptForDisplay();
	}

}
