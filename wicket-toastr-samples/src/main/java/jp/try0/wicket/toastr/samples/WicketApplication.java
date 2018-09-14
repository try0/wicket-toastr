package jp.try0.wicket.toastr.samples;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.RequestCycleSettings.RenderStrategy;

import de.agilecoders.wicket.core.Bootstrap;
import jp.try0.wicket.toastr.core.config.ToastrSettings;
import jp.try0.wicket.toastr.samples.page.HomePage;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 *
 * @see jp.try0.wicket.toastr.samples.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init()
	{
		super.init();

		getMarkupSettings().setStripWicketTags(true);
		getRequestCycleSettings().setRenderStrategy(RenderStrategy.ONE_PASS_RENDER);

		// toastr configuration
		ToastrSettings.createBuilder(this)
		.setAutoAppendBehavior(true)
//		.setFontAwsomeIcons(new ToastrFontAwsomeIcons(
//				"\\f058", // fa-check-circle
//				"\\f05a", // fa-info-circle
//				"\\f071", // fa-exclamation-triangle
//				"\\f00d"  // fa-times
//		))
		.initializeSettings();

		Bootstrap.install(this);
	}

	@Override
	public RuntimeConfigurationType getConfigurationType() {
		return RuntimeConfigurationType.DEPLOYMENT;
	}
}
