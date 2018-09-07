package jp.try0.wicket.toastr.samples.page;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.common.base.Strings;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapRadioChoice;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.radio.BooleanRadioGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import jp.try0.wicket.toastr.core.Toast;
import jp.try0.wicket.toastr.core.Toast.ToastLevel;
import jp.try0.wicket.toastr.core.ToastOptions;
import jp.try0.wicket.toastr.core.ToastOptions.PositionClass;

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
//				fluid();
				setBrandName(Model.of("Wicket Toastr Samples"));
			}
		});


		add(new BootstrapForm<Void>("form") {
			{
				// Toast levels
				IModel<ToastLevel> toastLevel = new Model<ToastLevel>(ToastLevel.INFO);
				List<ToastLevel> levels =
						Arrays.asList(ToastLevel.SUCCESS, ToastLevel.INFO, ToastLevel.WARNING, ToastLevel.ERROR);
				add(new BootstrapRadioChoice<ToastLevel>("rdoLevel", toastLevel, levels));

				// Toast positions
				IModel<PositionClass> toastPosition = new Model<PositionClass>(PositionClass.TOP_RIGHT);
				add(new BootstrapRadioChoice<PositionClass>("rdoPosition", toastPosition, Arrays.asList(PositionClass.values())));

				// Callbacks
				IModel<Boolean> onShown = new Model<>(false);
				add(new BooleanRadioGroup("switchOnShown", onShown));

				IModel<Boolean> onHidden = new Model<>(false);
				add(new BooleanRadioGroup("switchOnHidden", onHidden));

				IModel<Boolean> onClick = new Model<>(false);
				add(new BooleanRadioGroup("switchOnClick", onClick));

				IModel<Boolean> onCloseClick = new Model<>(false);
				add(new BooleanRadioGroup("switchOnCloseClick", onCloseClick));

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
							.setOnShownFunction(onShown.getObject() ? "function() {alert('fired onShown');}" : "false")
							.setOnHiddenFunction(onHidden.getObject() ? "function() {alert('fired onHidden');}" : "false")
							.setOnClickFunction(onClick.getObject() ? "function() {alert('fired onclick');}" : "false")
							.setOnCloseClickFunction(onCloseClick.getObject() ? "function() {alert('fired onCloseClick');}" : "false");
					return 	opts;
				};

				// Toast
				IModel<Toast> toast = () -> {
					return Toast.create(toastLevel.getObject(), message.getObject())
							.withToastTitle(!Strings.isNullOrEmpty(title.getObject()), title::getObject)
							.withToastOptions(options.getObject());
				};

				// Links
				add(new SubmitLink("link") {

					@Override
					public void onSubmit() {
						super.onSubmit();
						toast.getObject().show(this);
					}

				});
				add(new AjaxSubmitLink("ajaxLink") {

					@Override
					protected void onSubmit(AjaxRequestTarget target) {
						super.onSubmit(target);
						toast.getObject().show(target);
					};

				});

			}
		});

	}
}
