package jp.try0.wicket.toastr.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.map.HashedMap;

import jp.try0.wicket.toastr.core.Toast.ToastLevel;

/**
 * Toast options for each {@link ToastLevel}.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastOptions implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Options builder.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static class ToastOptionsBuilder {

		/**
		 * options for each levels
		 */
		Map<ToastLevel, ToastOption> options = new HashMap<>();

		/**
		 * Sets option of infomation toast.
		 *
		 * @param option option of information toast
		 * @return builder
		 */
		public ToastOptionsBuilder setInfoOption(ToastOption option) {
			options.put(ToastLevel.INFO, option);
			return this;
		}

		/**
		 * Sets option of success toast.
		 *
		 * @param option option of success toast
		 * @return builder
		 */
		public ToastOptionsBuilder setSuccessOption(ToastOption option) {
			options.put(ToastLevel.SUCCESS, option);
			return this;
		}

		/**
		 * Sets option of warning toast.
		 *
		 * @param option option of warn toast
		 * @return builder
		 */
		public ToastOptionsBuilder setWarnOption(ToastOption option) {
			options.put(ToastLevel.WARNING, option);
			return this;
		}

		/**
		 * Sets option of error toast.
		 *
		 * @param option option of error toast
		 * @return builder
		 */
		public ToastOptionsBuilder setErrorOption(ToastOption option) {
			options.put(ToastLevel.ERROR, option);
			return this;
		}

		/**
		 * Creates ToastOptions.
		 *
		 * @return the options for each levels
		 */
		public ToastOptions get() {
			return new ToastOptions(options);
		}
	}

	/**
	 * Creates options builder.
	 *
	 * @return builder
	 */
	public static ToastOptionsBuilder builder() {
		return new ToastOptionsBuilder();
	}

	/**
	 * options for each level
	 */
	private final Map<ToastLevel, Optional<ToastOption>> options = new HashedMap<>();

	/**
	 * Constructor
	 */
	public ToastOptions() {
	}

	/**
	 * Constrauctor
	 *
	 * @param options the options for each levels
	 */
	public ToastOptions(Map<ToastLevel, ToastOption> options) {
		options.forEach((key, val) -> {
			this.options.put(key, Optional.ofNullable(val));
		});
	}

	/**
	 * Gets toast option.
	 *
	 * @param level the toast level
	 * @return the toast option
	 */
	public Optional<ToastOption> get(ToastLevel level) {
		return options.computeIfAbsent(level, key -> Optional.empty());
	}

}
