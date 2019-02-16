package jp.try0.wicket.toastr.core;

import java.io.Serializable;
import java.util.Optional;

import jp.try0.wicket.toastr.core.Toast.ToastLevel;

/**
 * Toast interface.
 *
 * @author Ryo Tsunoda
 *
 */
public interface IToast extends Serializable {

	/**
	 * Gets toast level.
	 *
	 * @return the toast level
	 */
	public ToastLevel getToastLevel();

	/**
	 * Gets toast message.
	 *
	 * @return the toast message
	 */
	public String getMessage();

	/**
	 * Gets toast title.
	 *
	 * @return the toast title
	 */
	public default Optional<String> getTitle() {
		return Optional.empty();
	}

	/**
	 * Gets toast options that override global options.
	 *
	 * @return toast option
	 */
	public default Optional<ToastOption> getToastOption() {
		return Optional.empty();
	}

	/**
	 * Gets script for display toast.
	 *
	 * @return the script for display toast
	 */
	public CharSequence getScriptForDisplay();

}
