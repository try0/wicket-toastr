package jp.try0.wicket.toastr.core;

import java.util.Optional;

import jp.try0.wicket.toastr.core.Toast.ToastLevel;

/**
 * Toast interface
 *
 * @author Ryo Tsunoda
 *
 */
public interface IToast {

	/**
	 * Gets toast level.
	 *
	 * @return
	 */
	public ToastLevel getToastLevel();

	/**
	 * Gets toast message.
	 *
	 * @return
	 */
	public String getToastMessage();

	/**
	 * Gets toast title.
	 *
	 * @return
	 */
	public default Optional<String> getToastTitle() {
		return Optional.empty();
	}

	/**
	 * Gets toast options that override global options.
	 *
	 * @return
	 */
	public default Optional<ToastOptions> getToastOptions() {
		return Optional.empty();
	}

	/**
	 * Gets script for display toast.
	 *
	 * @return
	 */
	public CharSequence getScriptForDisplay();

}
