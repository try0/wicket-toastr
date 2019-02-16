package jp.try0.wicket.toastr.core.config;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import org.apache.wicket.Application;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.util.lang.Args;

import jp.try0.wicket.toastr.core.IToastOption;
import jp.try0.wicket.toastr.core.ToastOption;
import jp.try0.wicket.toastr.core.ToastOptions;
import jp.try0.wicket.toastr.core.behavior.ToastrBehavior;
import jp.try0.wicket.toastr.core.behavior.ToastrBehavior.ToastMessageCombiner;

/**
 * Toastr settings. This class has configs for using toastr.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastrSetting {

	/**
	 * Settings builder.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static class ToastrSettingInitializer {

		/**
		 * Create builder.
		 *
		 * @param application the current application
		 * @return initializer
		 */
		public static ToastrSettingInitializer create(final Application application) {
			return new ToastrSettingInitializer(application);
		}

		/**
		 * Application
		 */
		private final Application application;

		/**
		 * Global option
		 */
		private ToastOption globalOption = null;

		/**
		 * Global options for each toast levels
		 */
		private ToastOptions globalEachLevelOptions = new ToastOptions();

		/**
		 * Message Filter
		 */
		private IFeedbackMessageFilter filter = IFeedbackMessageFilter.ALL;

		/**
		 * Need append behavior to new pages
		 */
		private boolean needAutoAppendToastrBehavior = false;

		/**
		 * {@link ToastrBehavior} factory
		 */
		private Function<Optional<IFeedbackMessageFilter>, ToastrBehavior> toastrBehaviorFactory = DEFAULT_TOASTR_BEHAVIOR_FACTORY;

		/**
		 * Message combiner
		 */
		private ToastMessageCombiner toastMessageCombiner = ToastMessageCombiner.VOID_COMBINER;

		/**
		 * Font Awesome icon settings
		 */
		private ToastrFontAwesomeSetting fontAwesomeSettings = null;

		/**
		 * Constractor
		 *
		 * @param application the current application
		 */
		public ToastrSettingInitializer(Application application) {
			this.application = Args.notNull(application, "application");
		}

		/**
		 * Sets toastr global option.
		 *
		 * @param globalOption global option
		 * @return this
		 */
		public ToastrSettingInitializer setGlobalOption(ToastOption globalOption) {
			this.globalOption = globalOption;
			return this;
		}

		/**
		 * Sets toastr global options for each levels.
		 *
		 * @param globalOption global options
		 * @return this
		 */
		public ToastrSettingInitializer setGlobalEachLevelOptions(ToastOptions globalEachLevelOptions) {
			this.globalEachLevelOptions = globalEachLevelOptions;
			return this;
		}

		/**
		 * Sets filter.
		 *
		 * @param filter the filter to apply
		 * @return this
		 */
		public ToastrSettingInitializer setMessageFilter(IFeedbackMessageFilter filter) {
			this.filter = filter;
			return this;
		}

		/**
		 * Sets need auto append {@link ToastrBehavior} to new {@link Page}.
		 *
		 * @param needAutoAppendToastrBehavior whether or not to append {@link ToastrBehavior} to new page
		 * @return this
		 */
		public ToastrSettingInitializer setAutoAppendBehavior(boolean needAutoAppendToastrBehavior) {
			this.needAutoAppendToastrBehavior = needAutoAppendToastrBehavior;
			return this;
		}

		/**
		 * Sets {@link ToastrBehavior} factory.
		 *
		 * @param toastrBehaviorFactory factory of {@link ToastrBehavior}
		 * @return this
		 */
		public ToastrSettingInitializer setToastrBehaviorFactory(
				Function<Optional<IFeedbackMessageFilter>, ToastrBehavior> toastrBehaviorFactory) {
			this.toastrBehaviorFactory = toastrBehaviorFactory;
			return this;
		}

		/**
		 * Sets {@link ToastMessageCombiner}.
		 *
		 * @param toastMessageCombiner combiner that combines messages for each toast level
		 */
		public ToastrSettingInitializer setToastMessageCombiner(ToastMessageCombiner toastMessageCombiner) {
			this.toastMessageCombiner = toastMessageCombiner;
			return this;
		}

		/**
		 * Set Font Awesome icons settings.
		 *
		 * @param fontAwesomeSettings setting for icons
		 * @return this
		 */
		public ToastrSettingInitializer setFontAwesomeSettings(ToastrFontAwesomeSetting fontAwesomeSettings) {
			this.fontAwesomeSettings = fontAwesomeSettings;
			return this;
		}

		/**
		 * Initialize toastr settings.
		 */
		public ToastrSetting initialize() {

			if (application.getMetaData(META_DATA_KEY) != null) {
				throw new UnsupportedOperationException(
						"The setting has already been initialized. ToastrSettings#initialize can only be called once.");
			}

			if (needAutoAppendToastrBehavior) {
				application.getComponentInstantiationListeners().add(new ToastrBehaviorAutoAppender());
			}

			ToastrSetting settings = new ToastrSetting(this);

			application.setMetaData(META_DATA_KEY, settings);

			return settings;
		}

	}

	/**
	 * Key of {@link ToastrSetting} instance.
	 */
	private static final MetaDataKey<ToastrSetting> META_DATA_KEY = new MetaDataKey<ToastrSetting>() {
	};

	/**
	 * Default {@link ToastrBehavior} factory.
	 */
	private static final Function<Optional<IFeedbackMessageFilter>, ToastrBehavior> DEFAULT_TOASTR_BEHAVIOR_FACTORY = filter -> {
		if (filter.isPresent()) {
			return new ToastrBehavior(filter.get());
		} else {
			return new ToastrBehavior();
		}
	};

	/**
	 * Creates settings builder.
	 *
	 * @param application the current application
	 * @return initializer
	 */
	public static ToastrSettingInitializer createInitializer(final Application application) {
		return ToastrSettingInitializer.create(application);
	}

	/**
	 * Sets up default values.
	 *
	 * @return toastr settings
	 */
	private static ToastrSetting initialize() {

		if (!Application.exists()) {
			throw new UnsupportedOperationException("Application is not exisits.");
		}

		final Application application = Application.get();
		boolean needAutoAppendToastrBehavior = false;

		return ToastrSetting.createInitializer(application)
				.setAutoAppendBehavior(needAutoAppendToastrBehavior)
				.initialize();
	}

	/**
	 * Gets instance of toastr settings.<br>
	 * If settings has already been initialized, returns it, otherwise first initialize setting and returns it.
	 *
	 * @return toastr settings
	 */
	public static ToastrSetting get() {
		if (!Application.exists()) {
			throw new IllegalStateException("There is no active application.");
		}

		Application application = Application.get();
		ToastrSetting settings = application.getMetaData(META_DATA_KEY);

		if (settings != null) {
			return settings;
		}

		return ToastrSetting.initialize();
	}

	/**
	 * Global option
	 */
	private final Optional<IToastOption> globalOption;

	/**
	 * Global options for each toast levels
	 */
	private final ToastOptions globalEachLevelOptions;

	/**
	 * Message Filter
	 */
	private final Optional<IFeedbackMessageFilter> filter;

	/**
	 * Font Awesome icon settings
	 */
	private final Optional<ToastrFontAwesomeSetting> fontAwesomeSettings;

	/**
	 * {@link ToastrBehavior} factory
	 */
	private final Function<Optional<IFeedbackMessageFilter>, ToastrBehavior> toastrBehaviorFactory;

	/**
	 * Message combiner
	 */
	private final ToastMessageCombiner toastMessageCombiner;

	/**
	 * Constractor
	 */
	private ToastrSetting() {
		this.globalOption = Optional.empty();
		this.globalEachLevelOptions = new ToastOptions();
		this.filter = Optional.empty();
		this.toastrBehaviorFactory = DEFAULT_TOASTR_BEHAVIOR_FACTORY;
		this.toastMessageCombiner = ToastMessageCombiner.VOID_COMBINER;
		this.fontAwesomeSettings = Optional.empty();
	}

	/**
	 * Constractor
	 * @param initializer the initializer for set default values
	 */
	private ToastrSetting(ToastrSettingInitializer initializer) {
		this.globalOption = Optional.ofNullable(initializer.globalOption);
		this.globalEachLevelOptions = Args.notNull(initializer.globalEachLevelOptions, "globalEachLevelOptions");
		this.filter = Optional.ofNullable(initializer.filter);
		this.toastrBehaviorFactory = Args.notNull(initializer.toastrBehaviorFactory, "toastrBehaviorFactory");
		this.toastMessageCombiner = Args.notNull(initializer.toastMessageCombiner, "toastMessageCombiner");
		this.fontAwesomeSettings = Optional.ofNullable(initializer.fontAwesomeSettings);
	}

	/**
	 * Gets default toastr option.
	 *
	 * @return toastr option
	 */
	public Optional<IToastOption> getGlobalOption() {
		return globalOption;
	}

	/**
	 * Gets default toastr options for each levels.
	 *
	 * @return toastr options
	 */
	public ToastOptions getGlobalEachLevelOptions() {
		return globalEachLevelOptions;
	}

	/**
	 * Gets whether has global options.
	 *
	 * @return true if settings has global options, otherwise false
	 */
	public boolean hasGlobalOptions() {
		return globalOption.isPresent();
	}

	/**
	 * Gets filter.
	 *
	 * @return the filter to apply
	 */
	public Optional<IFeedbackMessageFilter> getMessageFilter() {
		return filter;
	}

	/**
	 * Gets whether has feedback message filter.
	 *
	 * @return true if settings has filter, otherwise false
	 */
	public boolean hasMessageFilter() {
		return filter.isPresent();
	}

	/**
	 * Gets icon settings.
	 *
	 * @return {@link Optional} with icon settings
	 */
	public Optional<ToastrFontAwesomeSetting> getFontAwesomeSettings() {
		return fontAwesomeSettings;
	}

	/**
	 * Gets {@link ToastrBehavior} factory.
	 *
	 * @return the {@link ToastrBehavior} factory
	 */
	public Supplier<ToastrBehavior> getToastrBehaviorFactory() {
		return () -> {
			ToastrBehavior behavior = toastrBehaviorFactory.apply(getMessageFilter());
			behavior.setMessageCombiner(toastMessageCombiner);
			return behavior;
		};
	}

	/**
	 * Gets message cobiner.
	 *
	 * @return the combiner that combines messages for each toast level
	 */
	public ToastMessageCombiner getToastMessageCombiner() {
		return toastMessageCombiner;
	}

}
