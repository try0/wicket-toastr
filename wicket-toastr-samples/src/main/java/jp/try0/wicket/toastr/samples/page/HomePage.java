package jp.try0.wicket.toastr.samples.page;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.value.IValueMap;
import org.apache.wicket.util.value.ValueMap;

import com.google.common.base.Strings;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapExternalLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapRadioChoice;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.radio.BooleanRadioGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarExternalLink;
import jp.try0.wicket.toastr.core.Toast;
import jp.try0.wicket.toastr.core.Toast.ToastLevel;
import jp.try0.wicket.toastr.core.ToastOptions;
import jp.try0.wicket.toastr.core.ToastOptions.CloseMethod;
import jp.try0.wicket.toastr.core.ToastOptions.DisplayEasing;
import jp.try0.wicket.toastr.core.ToastOptions.HideMethod;
import jp.try0.wicket.toastr.core.ToastOptions.PositionClass;
import jp.try0.wicket.toastr.core.ToastOptions.ShowMethod;

/**
 * Home page
 *
 * @author Ryo Tsunoda
 *
 */
public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	/**
	 * Constractor
	 *
	 * @param parameters
	 */
	public HomePage(final PageParameters parameters) {
		super(parameters);

		success("Success feedback message");
		info("Information feedback message");
		warn("Warning feedback message");
		error("Error feedback message");

		add(new Navbar("navBar") {
			{
				// fluid();
				setBrandName(Model.of("Wicket Toastr Samples"));

				addComponents(NavbarComponents.transform(Navbar.ComponentPosition.RIGHT,
						new NavbarExternalLink(Model.of("https://github.com/try0/wicket-toastr")) {
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
			}
		});

		add(new BootstrapForm<Void>("form") {
			{
				// Toast positions
				IModel<PositionClass> toastPosition = new Model<PositionClass>(PositionClass.TOP_RIGHT);
				add(new BootstrapRadioChoice<PositionClass>("rdoPosition", toastPosition,
						Arrays.asList(PositionClass.values())));

				// Methods
				IModel<ShowMethod> showMethod = new Model<ShowMethod>(ShowMethod.FADE_ID);
				add(new BootstrapRadioChoice<ShowMethod>("rdoShowMethod", showMethod,
						Arrays.asList(ShowMethod.values())));

				IModel<HideMethod> hideMethod = new Model<HideMethod>(HideMethod.FADE_OUT);
				add(new BootstrapRadioChoice<HideMethod>("rdoHideMethod", hideMethod,
						Arrays.asList(HideMethod.values())));

				IModel<CloseMethod> closeMethod = new Model<CloseMethod>(CloseMethod.FADE_OUT);
				add(new BootstrapRadioChoice<CloseMethod>("rdoCloseMethod", closeMethod,
						Arrays.asList(CloseMethod.values())));

				// Easing
				IModel<DisplayEasing> showEasing = new Model<DisplayEasing>(DisplayEasing.SWING);
				add(new BootstrapRadioChoice<DisplayEasing>("rdoShowEasing", showEasing,
						Arrays.asList(DisplayEasing.values())));

				IModel<DisplayEasing> hideEasing = new Model<DisplayEasing>(DisplayEasing.SWING);
				add(new BootstrapRadioChoice<DisplayEasing>("rdoHideEasing", hideEasing,
						Arrays.asList(DisplayEasing.values())));

				IModel<DisplayEasing> closeEasing = new Model<DisplayEasing>(DisplayEasing.SWING);
				add(new BootstrapRadioChoice<DisplayEasing>("rdoCloseEasing", closeEasing,
						Arrays.asList(DisplayEasing.values())));

				// Progress Bar
				IModel<Boolean> progressBar = new Model<>(false);
				add(new BooleanRadioGroup("switchProgressBar", progressBar));

				// Right to left
				IModel<Boolean> rtl = new Model<>(false);
				add(new BooleanRadioGroup("switchRtl", rtl));

				// Callbacks
				IModel<Boolean> onShown = new Model<>(false);
				add(new BooleanRadioGroup("switchOnShown", onShown));

				IModel<Boolean> onHidden = new Model<>(false);
				add(new BooleanRadioGroup("switchOnHidden", onHidden));

				IModel<Boolean> onClick = new Model<>(false);
				add(new BooleanRadioGroup("switchOnClick", onClick));

				IModel<Boolean> onCloseClick = new Model<>(false);
				add(new BooleanRadioGroup("switchOnCloseClick", onCloseClick));

				// Display sequence
				IModel<Boolean> newestOnTop = new Model<>(false);
				add(new BooleanRadioGroup("switchNewestOnTop", newestOnTop));

				// Prevent duplicates
				IModel<Boolean> preventDuplicates = new Model<>(false);
				add(new BooleanRadioGroup("switchPreventDuplicates", preventDuplicates));

				// show duration
				IModel<Integer> showDuration = new Model<Integer>(1000);
				add(new NumberTextField<Integer>("txtShowDuration", showDuration));

				// hide duration
				IModel<Integer> hideDuration = new Model<Integer>(500);
				add(new NumberTextField<Integer>("txtHideDuration", hideDuration));

				// close duration
				IModel<Integer> closeDuration = new Model<Integer>(500);
				add(new NumberTextField<Integer>("txtCloseDuration", closeDuration));

				// Time out
				IModel<Integer> timeOut = new Model<Integer>(5000);
				add(new NumberTextField<Integer>("txtTimeOut", timeOut));

				// Extended time out
				IModel<Integer> extendedTimeOut = new Model<Integer>(1000);
				add(new NumberTextField<Integer>("txtExtendedTimeOut", extendedTimeOut));

				// Extended time out
				IModel<Boolean> escapeHtml = new Model<>(true);
				add(new BooleanRadioGroup("switchEscapeHtml", escapeHtml));

				// Toast levels
				IModel<ToastLevel> toastLevel = new Model<ToastLevel>(ToastLevel.INFO);
				List<ToastLevel> levels = Arrays.asList(ToastLevel.SUCCESS, ToastLevel.INFO, ToastLevel.WARNING,
						ToastLevel.ERROR);
				add(new BootstrapRadioChoice<ToastLevel>("rdoLevel", toastLevel, levels) {

					@Override
					protected IValueMap getAdditionalAttributesForLabel(int index, ToastLevel choice) {
						IValueMap vm = super.getAdditionalAttributes(index, choice);

						if (vm == null) {
							vm = new ValueMap();
						}
						String classNames = vm.getKey("class");

						switch (choice) {
						case ERROR:
							classNames += " text-danger";
							break;
						case INFO:
							classNames += " text-info";
							break;
						case SUCCESS:
							classNames += " text-success";
							break;
						case WARNING:
							classNames += " text-warning";
							break;

						}

						vm.put("class", classNames);

						return vm;
					}
				});

				// Toast title
				IModel<String> title = new Model<String>("");
				add(new TextField<>("txtTitle", title));

				// Toast Message
				IModel<String> message = new Model<String>("toast");
				add(new TextArea<String>("txtMessage", message) {
					{
						setRequired(true);
					}
				});

				// ToastOptions
				IModel<ToastOptions> options = () -> {
					ToastOptions opts = ToastOptions.create()
							.setPositionClass(toastPosition.getObject())
							.setShowMethod(showMethod.getObject())
							.setHideMethod(hideMethod.getObject())
							.setCloseMethod(closeMethod.getObject())
							.setShowEasing(showEasing.getObject())
							.setHideEasing(hideEasing.getObject())
							.setCloseEasing(closeEasing.getObject())
							.setShowDuration(showDuration.getObject())
							.setHideDuration(hideDuration.getObject())
							.setIsNewestOnTop(newestOnTop.getObject())
							.setIsEnableProgressBar(progressBar.getObject())
							.setIsRightToLeft(rtl.getObject())
							.setNeedEscapeHtml(escapeHtml.getObject())
							.setTimeOut(timeOut.getObject())
							.setExtendedTimeOut(extendedTimeOut.getObject())
							.setNeedPreventDuplicates(preventDuplicates.getObject())
							.setOnShownFunction(onShown.getObject() ? "function() {alert('fired onShown');}" : "false")
							.setOnHiddenFunction(onHidden.getObject() ? "function() {alert('fired onHidden');}" : "false")
							.setOnClickFunction(onClick.getObject() ? "function() {alert('fired onclick');}" : "false")
							.setIsEnableCloseButton(onCloseClick.getObject()).setOnCloseClickFunction(
									onCloseClick.getObject() ? "function() {alert('fired onCloseClick');}" : "false");
					return opts;
				};

				// Toast
				IModel<Toast> toast = () -> {
					return Toast.create(toastLevel.getObject(), message.getObject())
							.withTitle(!Strings.isNullOrEmpty(title.getObject()), title::getObject)
							.withToastOptions(options.getObject());
				};

				// Links
				add(new SubmitLink("linkShow") {

					@Override
					public void onSubmit() {
						super.onSubmit();
						toast.getObject().show(this);
					}

				});
				add(new AjaxSubmitLink("ajaxLinkShow") {

					@Override
					protected void onSubmit(AjaxRequestTarget target) {
						super.onSubmit(target);
						toast.getObject().show(target);
					};

				});
				add(new AjaxSubmitLink("ajaxLinkRemove") {

					@Override
					protected void onSubmit(AjaxRequestTarget target) {
						super.onSubmit(target);
						Toast.remove(target);
					};

				});
				add(new AjaxSubmitLink("ajaxLinkClear") {

					@Override
					protected void onSubmit(AjaxRequestTarget target) {
						super.onSubmit(target);
						Toast.clear(target);
					};

				});

			}
		});

	}
}
