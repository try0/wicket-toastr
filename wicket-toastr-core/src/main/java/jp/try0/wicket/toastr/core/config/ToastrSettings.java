package jp.try0.wicket.toastr.core.config;

import java.util.Optional;

import org.apache.wicket.Application;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
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
	public static class SettingBuilder {

		/**
		 * Create builder.
		 *
		 * @param application
		 * @return
		 */
		public static SettingBuilder create(final Application application) {
			return new SettingBuilder(application);
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
		 * Need append behavior to pages
		 */
		private boolean needAutoAppendToastrBehavior;

		/**
		 * Font Awsome Icons
		 */
		private ToastrFontAwsomeIcons icons;

		/**
		 * Constractor
		 *
		 * @param application
		 */
		public SettingBuilder(Application application) {
			this.application = Args.notNull(application, "application");
			;
		}

		/**
		 * Sets toastr global options.
		 *
		 * @param globalOptions
		 * @return
		 */
		public SettingBuilder setGlobalOptions(ToastOptions globalOptions) {
			this.globalOptions = globalOptions;
			return this;
		}

		/**
		 * Sets need auto append {@link ToastrBehavior} to new {@link Page}.
		 *
		 * @param needAutoAppendToastrBehavior
		 * @return
		 */
		public SettingBuilder setAutoAppendBehavior(boolean needAutoAppendToastrBehavior) {
			this.needAutoAppendToastrBehavior = needAutoAppendToastrBehavior;
			return this;
		}

		/**
		 * Set icons.
		 *
		 * @param icons
		 * @return
		 */
		public SettingBuilder setFontAwsomeIcons(ToastrFontAwsomeIcons icons) {
			this.icons = icons;
			return this;
		}

		/**
		 * Initialize toastr settings.
		 *
		 * @param defaultOptions
		 */
		public void initializeSettings() {
			ToastrSettings.initialize(application, needAutoAppendToastrBehavior, globalOptions, icons);
		}

	}

	/**
	 * Key of {@link ToastrSettings} instance.
	 */
	private final static MetaDataKey<ToastrSettings> META_DATA_KEY = new MetaDataKey<ToastrSettings>() {
	};

	/**
	 * Create setting builder.
	 *
	 * @param application
	 * @return
	 */
	public static SettingBuilder createBuilder(final Application application) {
		return SettingBuilder.create(application);
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
		return initialize(application, needAutoAppendToastrBehavior, null, null);
	}

	/**
	 * Set up default values.
	 *
	 * @param application
	 * @param needAutoAppendToastrBehavior
	 */
	public static ToastrSettings initialize(final Application application, boolean needAutoAppendToastrBehavior) {
		return initialize(application, needAutoAppendToastrBehavior, null, null);
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
		return initialize(application, needAutoAppendToastrBehavior, globalOptions, null);
	}

	/**
	 * Set up default values.
	 *
	 * @param application
	 * @param globalOptions
	 * @param needAutoAppendToastrBehavior
	 */
	public static ToastrSettings initialize(final Application application, boolean needAutoAppendToastrBehavior,
			ToastOptions globalOptions, ToastrFontAwsomeIcons icons) {

		if (application.getMetaData(META_DATA_KEY) != null) {
			throw new UnsupportedOperationException(
					"The setting has already been initialized. ToastrSettings#initialize can only be called once.");
		}

		if (needAutoAppendToastrBehavior) {
			application.getComponentInstantiationListeners().add(new ToastrBehaviorAutoAppender());
		}

		ToastrSettings settings = new ToastrSettings();

		settings.globalOptions = Optional.ofNullable(globalOptions);
		settings.fontAwsomeIcons = Optional.ofNullable(icons);

		application.setMetaData(META_DATA_KEY, settings);

		return settings;
	}

	/**
	 * Get TastrSettings.
	 *
	 * @return
	 */
	public static ToastrSettings getInstance() {
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
	 * Gets default toastr options.
	 *
	 * @return
	 */
	public static Optional<IToastOptions> getGlobalOptions() {
		return getInstance().globalOptions;
	}

	/**
	 * Gets wether has global options.
	 *
	 * @return
	 */
	public static boolean hasGlobalOptions() {
		return getInstance().globalOptions.isPresent();
	}

	/**
	 * Get icons.
	 *
	 * @return
	 */
	public static Optional<ToastrFontAwsomeIcons> getFontAwsomeIcons() {
		return getInstance().fontAwsomeIcons;
	}

	/**
	 * Global options
	 */
	private Optional<IToastOptions> globalOptions = Optional.empty();

	/**
	 * Font Awsome Icons
	 */
	private Optional<ToastrFontAwsomeIcons> fontAwsomeIcons = Optional.empty();

	/**
	 * Constractor
	 */
	private ToastrSettings() {
	}

}
