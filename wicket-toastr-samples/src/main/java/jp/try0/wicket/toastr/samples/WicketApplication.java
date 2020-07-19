package jp.try0.wicket.toastr.samples;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.RequestCycleSettings.RenderStrategy;

import de.agilecoders.wicket.core.Bootstrap;
import jp.try0.wicket.toastr.samples.page.HomePage;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 *
 * @see jp.try0.wicket.toastr.samples.page.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init() {
		super.init();

		getMarkupSettings().setStripWicketTags(true);
		getRequestCycleSettings().setRenderStrategy(RenderStrategy.ONE_PASS_RENDER);
		getCspSettings().blocking().disabled();

		getApplicationSettings().setFeedbackMessageCleanupFilter(msg -> true);


		// toastr configuration
//		ToastrSettings.createInitializer(this)
//				.setAutoAppendBehavior(true)
//				.setFontAwesomeSettings(new ToastrFontAwesomeSettings(
//						new ToastrFontAwesomeIcons(
//								"\\f058", // fa-check-circle
//								"\\f05a", // fa-info-circle
//								"\\f071", // fa-exclamation-triangle
//								"\\f057"  // fa-times-circle
//				)))
//				.setMessageFilter(new ToastLevelFeedbackMessageFilter(
//						ToastLevel.SUCCESS, ToastLevel.INFO, ToastLevel.WARNING))
//				.setToastrBehaviorFactory(filter -> {
//					ToastrBehavior behavior = new ToastrBehavior();
//
//					behavior.setMessageFilter(filter.get());
//					ToastMessageCombiner combiner = new ToastMessageCombiner();
//					combiner.setPrefix("・");
//					behavior.setMessageCombiner(combiner);
//
//					return behavior;
//				})
//				.initialize();

		Bootstrap.install(this);
	}

	@Override
	public RuntimeConfigurationType getConfigurationType() {
		return RuntimeConfigurationType.DEPLOYMENT;
	}
}
