package jp.try0.wicket.toastr.samples;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

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
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

		getMarkupSettings().setStripWicketTags(true);

		// toastr configuration
		ToastrSettings.setUp(this, true);

		Bootstrap.install(this);
	}
}
