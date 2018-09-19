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
 * Toastr settings. This class has configs for using toastr.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastrSettings {

	/**
	 * Settings builder.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static class ToastrSettingsInitializer {

		/**
		 * Create builder.
		 *
		 * @param application the current application
		 * @return initializer
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
		 * Need append behavior to new pages
		 */
		private boolean needAutoAppendToastrBehavior;

		/**
		 * {@link ToastrBehavior} factory
		 */
		private Supplier<ToastrBehavior> toastrBehaviorFactory = DEFAULT_TOASTR_BEHAVIOR_FACTORY;

		/**
		 * Font Awesome icon settings
		 */
		private ToastrFontAwesomeSettings fontAwesomeSettings;

		/**
		 * Constractor
		 *
		 * @param application the current application
		 */
		public ToastrSettingsInitializer(Application application) {
			this.application = Args.notNull(application, "application");
		}

		/**
		 * Sets toastr global options.
		 *
		 * @param globalOptions global options
		 * @return this
		 */
		public ToastrSettingsInitializer setGlobalOptions(ToastOptions globalOptions) {
			this.globalOptions = globalOptions;
			return this;
		}

		/**
		 * Sets filter.
		 *
		 * @param filter the filter to apply
		 * @return this
		 */
		public ToastrSettingsInitializer setMessageFilter(IFeedbackMessageFilter filter) {
			this.filter = filter;
			return this;
		}

		/**
		 * Sets need auto append {@link ToastrBehavior} to new {@link Page}.
		 *
		 * @param needAutoAppendToastrBehavior whether or not to append {@link ToastrBehavior} to new page
		 * @return this
		 */
		public ToastrSettingsInitializer setAutoAppendBehavior(boolean needAutoAppendToastrBehavior) {
			this.needAutoAppendToastrBehavior = needAutoAppendToastrBehavior;
			return this;
		}

		/**
		 * Sets {@link ToastrBehavior} factory.
		 *
		 * @param toastrBehaviorFactory factory of {@link ToastrBehavior}
		 * @return this
		 */
		public ToastrSettingsInitializer setToastrBehaviorFactory(Supplier<ToastrBehavior> toastrBehaviorFactory) {
			this.toastrBehaviorFactory = toastrBehaviorFactory;
			return this;
		}

		/**
		 * Set Font Awesome icons settings.
		 *
		 * @param fontAwesomeSettings setting for icons
		 * @return this
		 */
		public ToastrSettingsInitializer setFontAwesomeSettings(ToastrFontAwesomeSettings fontAwesomeSettings) {
			this.fontAwesomeSettings = fontAwesomeSettings;
			return this;
		}

		/**
		 * Initialize toastr settings.
		 */
		public void initialize() {
			ToastrSettings.initialize(application, needAutoAppendToastrBehavior, globalOptions, filter,
					toastrBehaviorFactory, fontAwesomeSettings);
		}

	}

	/**
	 * Key of {@link ToastrSettings} instance.
	 */
	private final static MetaDataKey<ToastrSettings> META_DATA_KEY = new MetaDataKey<ToastrSettings>() {
	};

	/**
	 * Default {@link ToastrBehavior} factory.
	 */
	private final static Supplier<ToastrBehavior> DEFAULT_TOASTR_BEHAVIOR_FACTORY = () -> {
		if (ToastrSettings.get().getMessageFilter().isPresent()) {
			return new ToastrBehavior(ToastrSettings.get().getMessageFilter().get());
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
	public static ToastrSettingsInitializer createInitializer(final Application application) {
		return ToastrSettingsInitializer.create(application);
	}

	/**
	 * Sets up default values.
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
	 * Sets up default values.
	 *
	 * @param application the current application
	 * @param needAutoAppendToastrBehavior whether or not to append {@link ToastrBehavior} to new page
	 */
	public static ToastrSettings initialize(final Application application, boolean needAutoAppendToastrBehavior) {
		return initialize(application, needAutoAppendToastrBehavior, null, null, DEFAULT_TOASTR_BEHAVIOR_FACTORY, null);
	}

	/**
	 * Set up default values.
	 *
	 * @param application
	 * @param needAutoAppendToastrBehavior whether or not to append {@link ToastrBehavior} to new page
	 * @param globalOptions toastr global options
	 * @return toastr settings
	 */
	public static ToastrSettings initialize(final Application application, boolean needAutoAppendToastrBehavior,
			ToastOptions globalOptions) {
		return initialize(application, needAutoAppendToastrBehavior, globalOptions, null,
				DEFAULT_TOASTR_BEHAVIOR_FACTORY, null);
	}

	/**
	 * Set up default values.
	 *
	 * @param application the current application
	 * @param needAutoAppendToastrBehavior whether or not to append {@link ToastrBehavior} to new page
	 * @param globalOptions toastr global options
	 * @param filter the filter to apply
	 * @param toastrBehaviorFactory the factory of {@link ToastrBehavior}
	 * @param fontAwesomeSettings Font Awesome icons settings
	 * @return toastr settings
	 */
	public static ToastrSettings initialize(final Application application, boolean needAutoAppendToastrBehavior,
			ToastOptions globalOptions, IFeedbackMessageFilter filter, Supplier<ToastrBehavior> toastrBehaviorFactory,
			ToastrFontAwesomeSettings fontAwesomeSettings) {

		if (application.getMetaData(META_DATA_KEY) != null) {
			throw new UnsupportedOperationException(
					"The setting has already been initialized. ToastrSettings#initialize can only be called once.");
		}

		if (needAutoAppendToastrBehavior) {
			application.getComponentInstantiationListeners().add(new ToastrBehaviorAutoAppender());
		}

		ToastrSettings settings = new ToastrSettings(globalOptions, filter, toastrBehaviorFactory, fontAwesomeSettings);

		application.setMetaData(META_DATA_KEY, settings);

		return settings;
	}

	/**
	 * Gets instance of toastr settings.<br>
	 * If settings has already been initialized, returns it, otherwise first initialize setting and returns it.
	 *
	 * @return toastr settings
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
	 * Font Awesome icon settings
	 */
	private final Optional<ToastrFontAwesomeSettings> fontAwesomeSettings;

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
		this.fontAwesomeSettings = Optional.empty();
	}

	/**
	 * Constractor
	 *
	 * @param globalOptions toastr global options
	 * @param filter the filter to apply
	 * @param toastrBehaviorFactory the factory of {@link ToastrBehavior}
	 * @param fontAwsomeSettings Font Awesome icons settings
	 */
	private ToastrSettings(ToastOptions globalOptions, IFeedbackMessageFilter filter,
			Supplier<ToastrBehavior> toastrBehaviorFactory,
			ToastrFontAwesomeSettings fontAwesomeSettings) {
		this.globalOptions = Optional.ofNullable(globalOptions);
		this.filter = Optional.ofNullable(filter);
		this.toastrBehaviorFactory = Args.notNull(toastrBehaviorFactory, "toastrBehaviorFactory");
		this.fontAwesomeSettings = Optional.ofNullable(fontAwesomeSettings);
	}

	/**
	 * Gets default toastr options.
	 *
	 * @return toastr options
	 */
	public Optional<IToastOptions> getGlobalOptions() {
		return globalOptions;
	}

	/**
	 * Gets whether has global options.
	 *
	 * @return true if settings has global options, otherwise false
	 */
	public boolean hasGlobalOptions() {
		return globalOptions.isPresent();
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
	public Optional<ToastrFontAwesomeSettings> getFontAwesomeSettings() {
		return fontAwesomeSettings;
	}

	/**
	 * Gets {@link ToastrBehavior} factory.
	 *
	 * @return the {@link ToastrBehavior} factory
	 */
	public Supplier<ToastrBehavior> getToastrBehaviorFactory() {
		return toastrBehaviorFactory;
	}

}
