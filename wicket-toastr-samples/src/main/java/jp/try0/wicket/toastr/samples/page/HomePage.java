package jp.try0.wicket.toastr.samples.page;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapExternalLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarExternalLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BackgroundColorBehavior.Color;
import jp.try0.wicket.toastr.samples.panel.combine.CombineSamplePanel;
import jp.try0.wicket.toastr.samples.panel.filter.FilterSamplePanel;
import jp.try0.wicket.toastr.samples.panel.options.OptionsSamplePanel;

/**
 * Home page
 *
 * @author Ryo Tsunoda
 *
 */
public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	private static final String GIT_HUB_PROJECT_ROOT = "https://github.com/try0/wicket-toastr";

	private static final String SAMPLE_PANEL_ID = "samplePanel";

	/**
	 * Tab
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	static enum Tab {
		/**
		 * Options Tab
		 */
		OPTIONS("Options", OptionsSamplePanel.class),
		/**
		 * Combine Tab
		 */
		COMBINE("Combine", CombineSamplePanel.class),
		/**
		 * Filter Tab
		 */
		FILTER("Filter", FilterSamplePanel.class),;

		String tabName;
		Class<? extends Panel> clazz;

		Tab(String tabName, Class<? extends Panel> clazz) {
			this.tabName = tabName;
			this.clazz = clazz;
		}
	}

	/**
	 * Selected tab
	 */
	private Tab selected = Tab.OPTIONS;

	/**
	 * Form
	 */
	private Form<?> form;

	/**
	 * the link that navigate to source code
	 */
	private ExternalLink linkToSource;

	/**
	 *  the model that create url to github source code
	 */
	private IModel<String> modelToSource = () -> {

		String packageOfSample = Arrays.stream(selected.clazz.getPackage().getName().split("\\."))
				.collect(Collectors.joining("/"));

		return GIT_HUB_PROJECT_ROOT
				+ "/tree/master/wicket-toastr-samples/src/main/java"
				+ "/" + packageOfSample;
	};

	/**
	 * Constractor
	 */
	public HomePage() {
	}

	/**
	 * Constractor
	 *
	 * @param parameters
	 */
	public HomePage(final PageParameters parameters) {
		super(parameters);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitialize() {
		super.onInitialize();

		info("Information feedback message");
		success("Success feedback message");
		warn("Warning feedback message");
		error("Error feedback message");

		// Navigation
		final Navbar navbar = new Navbar("navBar");
		add(navbar);
		navbar.setInverted(true);
		navbar.setBackgroundColor(Color.Dark);
		navbar.setBrandName(Model.of("Wicket Toastr Samples"));
		// add dummy
		navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT,
				new NavbarExternalLink(Model.of("#")).setVisible(false)));

		navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.RIGHT,
				new NavbarExternalLink(Model.of(GIT_HUB_PROJECT_ROOT)) {
					{
						setLabel(Model.of("<i class=\"fab fa-github\" style=\"font-size:1.5em;\"></i>"));
						setTarget(BootstrapExternalLink.Target.blank);
					}

					@Override
					protected Component newLabel(String markupId) {
						Component lbl = super.newLabel(markupId);
						lbl.setEscapeModelStrings(false);
						return lbl;
					};
				}));

		// Main form
		form = new BootstrapForm<Void>("form") {

			@Override
			protected void onInitialize() {
				super.onInitialize();

				add(new ListView<Tab>("tabs", Arrays.asList(Tab.values())) {

					@Override
					protected void populateItem(ListItem<Tab> item) {

						item.add(new AjaxLink<Void>("navLink") {
							{
								add(new Label("tabName", item.getModelObject().tabName));

								if (item.getModelObject() == selected) {
									add(new AttributeAppender("class", " active "));
								}
							}

							@Override
							public void onClick(AjaxRequestTarget target) {

								Panel switchTargetPanel = null;
								switch (item.getModelObject()) {
								case COMBINE:
									switchTargetPanel = new CombineSamplePanel(SAMPLE_PANEL_ID);
									break;
								case FILTER:
									switchTargetPanel = new FilterSamplePanel(SAMPLE_PANEL_ID);
									break;
								case OPTIONS:
									switchTargetPanel = new OptionsSamplePanel(SAMPLE_PANEL_ID);
									break;
								default:
									return;
								}

								selected = item.getModelObject();

								switchTargetPanel.setOutputMarkupId(true);
								form.addOrReplace(switchTargetPanel);
								target.add(form);
								target.add(linkToSource);
								target.appendJavaScript("$('[data-toggle=\"tooltip\"]').tooltip();");
							}

						});

					}
				});

				add(new OptionsSamplePanel(SAMPLE_PANEL_ID).setOutputMarkupId(true));

			}
		};
		add(form);

		linkToSource = new ExternalLink("toSource", modelToSource);
		linkToSource.setOutputMarkupId(true);
		add(linkToSource);
	}
}
