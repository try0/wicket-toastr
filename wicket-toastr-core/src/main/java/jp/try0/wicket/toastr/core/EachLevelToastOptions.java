package jp.try0.wicket.toastr.core;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import jp.try0.wicket.toastr.core.Toast.ToastLevel;

/**
 * Toast options for each {@link ToastLevel}.
 *
 * @author Ryo Tsunoda
 *
 */
public class EachLevelToastOptions implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Options builder.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static class EachLevelToastOptionsBuilder {

		/**
		 * options for each levels
		 */
		Map<ToastLevel, ToastOption> options = new EnumMap<>(ToastLevel.class);

		/**
		 * Sets option of infomation toast.
		 *
		 * @param option option of information toast
		 * @return builder
		 */
		public EachLevelToastOptionsBuilder setInfoOption(ToastOption option) {
			options.put(ToastLevel.INFO, option);
			return this;
		}

		/**
		 * Sets option of success toast.
		 *
		 * @param option option of success toast
		 * @return builder
		 */
		public EachLevelToastOptionsBuilder setSuccessOption(ToastOption option) {
			options.put(ToastLevel.SUCCESS, option);
			return this;
		}

		/**
		 * Sets option of warning toast.
		 *
		 * @param option option of warn toast
		 * @return builder
		 */
		public EachLevelToastOptionsBuilder setWarnOption(ToastOption option) {
			options.put(ToastLevel.WARNING, option);
			return this;
		}

		/**
		 * Sets option of error toast.
		 *
		 * @param option option of error toast
		 * @return builder
		 */
		public EachLevelToastOptionsBuilder setErrorOption(ToastOption option) {
			options.put(ToastLevel.ERROR, option);
			return this;
		}

		/**
		 * Creates ToastOptions.
		 *
		 * @return the options for each levels
		 */
		public EachLevelToastOptions get() {
			return new EachLevelToastOptions(options);
		}
	}

	/**
	 * Creates options builder.
	 *
	 * @return builder
	 */
	public static EachLevelToastOptionsBuilder builder() {
		return new EachLevelToastOptionsBuilder();
	}

	/**
	 * options for each level
	 */
	private final Map<ToastLevel, ToastOption> options = new EnumMap<>(ToastLevel.class);

	/**
	 * Constructor
	 */
	public EachLevelToastOptions() {
	}

	/**
	 * Constrauctor
	 *
	 * @param options the options for each levels
	 */
	public EachLevelToastOptions(Map<ToastLevel, ToastOption> options) {
		options.forEach((key, val) -> {
			if (val == null) {
				return;
			}

			this.options.put(key, val);
		});
	}

	/**
	 * Gets toast option.
	 *
	 * @param level the toast level
	 * @return the toast option
	 */
	public Optional<ToastOption> get(ToastLevel level) {
		ToastOption option = options.get(level);
		return Optional.ofNullable(option);
	}

}
