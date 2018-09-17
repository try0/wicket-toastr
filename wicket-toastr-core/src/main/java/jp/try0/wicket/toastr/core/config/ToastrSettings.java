package jp.try0.wicket.toastr.core.config;

import java.util.Optional;
import java.util.function.Supplier;

import org.apache.wicket.Application;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.util.lang.Args;

import jp.try0.wicket.toastr.core.IToastOptions;
import jp.try0.wicket.toastr.core.ToastOptions;
import jp.try0.wicket.toastr.core.behavior.ToastrBehavior;

/**
 * Toastr settings
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastrSettings {

	/**
	 * Builder
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static class ToastrSettingsInitializer {

		/**
		 * Create builder.
		 *
		 * @param application
		 * @return
		 */
		public static ToastrSettingsInitializer create(final Application application) {
			return new ToastrSettingsInitializer(application);
		}

		/**
		 * Application
		 */
		private final Application application;

		/**
		 * Global options
		 */
		private ToastOptions globalOptions;

		/**
		 * Message Filter
		 */
		private IFeedbackMessageFilter filter;

		/**
		 * Need append behavior to pages
		 */
		private boolean needAutoAppendToastrBehavior;

		/**
		 * {@link ToastrBehavior} factory
		 */
		private Supplier<ToastrBehavior> toastrBehaviorFactory = DEFAULT_TOASTR_BEHAVIOR_FACTORY;

		/**
		 * Font Awesome icon settings
		 */
		private ToastrFontAwesomeSettings fontAwsomeSettings;

		/**
		 * Constractor
		 *
		 * @param application
		 */
		public ToastrSettingsInitializer(Application application) {
			this.application = Args.notNull(application, "application");
		}

		/**
		 * Sets toastr global options.
		 *
		 * @param globalOptions
		 * @return
		 */
		public ToastrSettingsInitializer setGlobalOptions(ToastOptions globalOptions) {
			this.globalOptions = globalOptions;
			return this;
		}

		/**
		 * Set filter.
		 *
		 * @param icons
		 * @return
		 */
		public ToastrSettingsInitializer setMessageFilter(IFeedbackMessageFilter filter) {
			this.filter = filter;
			return this;
		}

		/**
		 * Sets need auto append {@link ToastrBehavior} to new {@link Page}.
		 *
		 * @param needAutoAppendToastrBehavior
		 * @return
		 */
		public ToastrSettingsInitializer setAutoAppendBehavior(boolean needAutoAppendToastrBehavior) {
			this.needAutoAppendToastrBehavior = needAutoAppendToastrBehavior;
			return this;
		}

		/**
		 * Sets {@link ToastrBehavior} factory.
		 *
		 * @param toastrBehaviorFactory
		 * @return
		 */
		public ToastrSettingsInitializer setToastrBehaviorFactory(Supplier<ToastrBehavior> toastrBehaviorFactory) {
			this.toastrBehaviorFactory = toastrBehaviorFactory;
			return this;
		}

		/**
		 * Set icons.
		 *
		 * @param icons
		 * @return
		 */
		public ToastrSettingsInitializer setFontAwesomeSettings(ToastrFontAwesomeSettings fontAwsomeSettings) {
			this.fontAwsomeSettings = fontAwsomeSettings;
			return this;
		}

		/**
		 * Initialize toastr settings.
		 *
		 * @param defaultOptions
		 */
		public void initialize() {
			ToastrSettings.initialize(application, needAutoAppendToastrBehavior, globalOptions, filter,
					toastrBehaviorFactory, fontAwsomeSettings);
		}

	}

	/**
	 * Key of {@link ToastrSettings} instance.
	 */
	private final static MetaDataKey<ToastrSettings> META_DATA_KEY = new MetaDataKey<ToastrSettings>() {
	};

	/**
	 * Default toastr behavior factory.
	 */
	private final static Supplier<ToastrBehavior> DEFAULT_TOASTR_BEHAVIOR_FACTORY = () -> {
		if (ToastrSettings.get().getMessageFilter().isPresent()) {
			return new ToastrBehavior(ToastrSettings.get().getMessageFilter().get());
		} else {
			return new ToastrBehavior();
		}
	};

	/**
	 * Create setting builder.
	 *
	 * @param application
	 * @return
	 */
	public static ToastrSettingsInitializer createInitializer(final Application application) {
		return ToastrSettingsInitializer.create(application);
	}

	/**
	 * Set up default values.
	 */
	public static ToastrSettings initialize() {

		if (!Application.exists()) {
			throw new UnsupportedOperationException("Application is not exisits.");
		}

		final Application application = Application.get();
		boolean needAutoAppendToastrBehavior = true;
		return initialize(application, needAutoAppendToastrBehavior, null, null, DEFAULT_TOASTR_BEHAVIOR_FACTORY, null);
	}

	/**
	 * Set up default values.
	 *
	 * @param application
	 * @param needAutoAppendToastrBehavior
	 */
	public static ToastrSettings initialize(final Application application, boolean needAutoAppendToastrBehavior) {
		return initialize(application, needAutoAppendToastrBehavior, null, null, DEFAULT_TOASTR_BEHAVIOR_FACTORY, null);
	}

	/**
	 * Set up default values.
	 *
	 * @param application
	 * @param needAutoAppendToastrBehavior
	 * @param globalOptions
	 * @return
	 */
	public static ToastrSettings initialize(final Application application, boolean needAutoAppendToastrBehavior,
			ToastOptions globalOptions) {
		return initialize(application, needAutoAppendToastrBehavior, globalOptions, null,
				DEFAULT_TOASTR_BEHAVIOR_FACTORY, null);
	}

	/**
	 * Set up default values.
	 *
	 * @param application
	 * @param globalOptions
	 * @param needAutoAppendToastrBehavior
	 */
	public static ToastrSettings initialize(final Application application, boolean needAutoAppendToastrBehavior,
			ToastOptions globalOptions, IFeedbackMessageFilter filter, Supplier<ToastrBehavior> toastrBehaviorFactory,
			ToastrFontAwesomeSettings fontAwsomeSettings) {

		if (application.getMetaData(META_DATA_KEY) != null) {
			throw new UnsupportedOperationException(
					"The setting has already been initialized. ToastrSettings#initialize can only be called once.");
		}

		if (needAutoAppendToastrBehavior) {
			application.getComponentInstantiationListeners().add(new ToastrBehaviorAutoAppender());
		}

		ToastrSettings settings = new ToastrSettings(globalOptions, filter, toastrBehaviorFactory, fontAwsomeSettings);

		application.setMetaData(META_DATA_KEY, settings);

		return settings;
	}

	/**
	 * Get TastrSettings.
	 *
	 * @return
	 */
	public static ToastrSettings get() {
		if (!Application.exists()) {
			throw new IllegalStateException("There is no active application.");
		}

		Application application = Application.get();
		ToastrSettings settings = application.getMetaData(META_DATA_KEY);

		if (settings != null) {
			return settings;
		}

		return ToastrSettings.initialize();
	}

	/**
	 * Global options
	 */
	private final Optional<IToastOptions> globalOptions;

	/**
	 * Message Filter
	 */
	private final Optional<IFeedbackMessageFilter> filter;

	/**
	 * Font Awsome Icons
	 */
	private final Optional<ToastrFontAwesomeSettings> fontAwsomeSettings;

	/**
	 * {@link ToastrBehavior} factory
	 */
	private final Supplier<ToastrBehavior> toastrBehaviorFactory;

	/**
	 * Constractor
	 */
	private ToastrSettings() {
		this.globalOptions = Optional.empty();
		this.filter = Optional.empty();
		this.toastrBehaviorFactory = DEFAULT_TOASTR_BEHAVIOR_FACTORY;
		this.fontAwsomeSettings = Optional.empty();
	}

	/**
	 * Constractor
	 *
	 * @param globalOptions
	 * @param filter
	 * @param toastrBehaviorFactory
	 * @param fontAwsomeSettings
	 */
	private ToastrSettings(ToastOptions globalOptions, IFeedbackMessageFilter filter,
			Supplier<ToastrBehavior> toastrBehaviorFactory,
			ToastrFontAwesomeSettings fontAwsomeSettings) {
		this.globalOptions = Optional.ofNullable(globalOptions);
		this.filter = Optional.ofNullable(filter);
		this.toastrBehaviorFactory = Args.notNull(toastrBehaviorFactory, "toastrBehaviorFactory");
		this.fontAwsomeSettings = Optional.ofNullable(fontAwsomeSettings);

	}

	/**
	 * Gets default toastr options.
	 *
	 * @return
	 */
	public Optional<IToastOptions> getGlobalOptions() {
		return globalOptions;
	}

	/**
	 * Gets wether has global options.
	 *
	 * @return
	 */
	public boolean hasGlobalOptions() {
		return globalOptions.isPresent();
	}

	/**
	 * Gets filter.
	 *
	 * @return
	 */
	public Optional<IFeedbackMessageFilter> getMessageFilter() {
		return filter;
	}

	/**
	 * Gets wether has feedback message filter.
	 *
	 * @return
	 */
	public boolean hasMessageFilter() {
		return filter.isPresent();
	}

	/**
	 * Gets icon settings.
	 *
	 * @return
	 */
	public Optional<ToastrFontAwesomeSettings> getFontAwsomeSettings() {
		return fontAwsomeSettings;
	}

	/**
	 * Gets {@link ToastrBehavior} factory.
	 *
	 * @return
	 */
	public Supplier<ToastrBehavior> getToastrBehaviorFactory() {
		return toastrBehaviorFactory;
	}

}
