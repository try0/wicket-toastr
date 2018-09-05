package jp.try0.wicket.toastr.core.config;

import java.util.Optional;

import org.apache.wicket.Application;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.util.lang.Args;

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
	 * @author Ryo Tsunoda
	 *
	 */
	public static class SettingBuilder {

		/**
		 * Application
		 */
		private final Application application;

		/**
		 * Global options
		 */
		private ToastOptions globalOptions;

		/**
		 * Need append behavior to pages.
		 */
		private boolean needAutoAppendToastrBehavior;


		/**
		 * Constractor
		 *
		 * @param application
		 */
		public SettingBuilder(Application application) {
			this.application = Args.notNull(application, "application");;
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
		 * Initialize toastr settings.
		 *
		 * @param defaultOptions
		 */
		public void setUp() {
			ToastrSettings.setUp(application, needAutoAppendToastrBehavior, globalOptions);
		}

	}

	/**
	 * Key of whether has already been initialized settings
	 */
	private final static MetaDataKey<ToastrSettings> INITIALIZED_KEY = new MetaDataKey<ToastrSettings>() {};

	/**
	 * Default settings
	 */
	private final static ToastrSettings DEFAULT = new ToastrSettings();

	/**
	 * Instance of {@link ToastrSettings}.
	 */
	private static ToastrSettings instance = DEFAULT;


	/**
	 * Set up defaults values.
	 *
	 * @param application
	 * @param needAutoAppendToastrBehavior
	 */
	public static void setUp(final Application application, boolean needAutoAppendToastrBehavior) {
		setUp(application, needAutoAppendToastrBehavior, null);
	}

	/**
	 * Set up defaults values.
	 *
	 * @param application
	 * @param globalOptions
	 * @param needAutoAppendToastrBehavior
	 */
	public static void setUp(final Application application, boolean needAutoAppendToastrBehavior, ToastOptions globalOptions) {

		if (application.getMetaData(INITIALIZED_KEY) == DEFAULT) {
			throw new UnsupportedOperationException("The setting has already been initialized. ToastrSettings # setUp can only be called once.");
		}

		if (needAutoAppendToastrBehavior) {
			application.getComponentInstantiationListeners().add(new ToastrBehaviorAutoAppender());
		}

		ToastrSettings settings = new ToastrSettings();

		settings.globalOptions = Optional.ofNullable(globalOptions);

		ToastrSettings.instance = settings;
		application.setMetaData(INITIALIZED_KEY, DEFAULT);

	}

	/**
	 * Gets default toastr options.
	 *
	 * @return
	 */
	public static Optional<ToastOptions> getGlobalOptions() {
		return instance.globalOptions;
	}

	/**
	 * Gets wether has global options.
	 *
	 * @return
	 */
	public static boolean hasGlobalOptions() {
		return instance.globalOptions.isPresent();
	}


	/**
	 * Global options
	 */
	private Optional<ToastOptions> globalOptions = Optional.empty();


	/**
	 * Constractor
	 */
	private ToastrSettings() {
	}




}
