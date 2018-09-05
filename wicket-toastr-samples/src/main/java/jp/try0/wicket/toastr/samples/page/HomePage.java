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
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import jp.try0.wicket.toastr.core.Toast;
import jp.try0.wicket.toastr.core.Toast.ToastLevel;
import jp.try0.wicket.toastr.core.ToastOptions;
import jp.try0.wicket.toastr.core.ToastOptions.PositionClass;

/**
 * Home page
 *
 * @author ryoMac
 *
 */
public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

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


				// Links
				add(new SubmitLink("link") {

					@Override
					public void onSubmit() {
						super.onSubmit();

						ToastOptions options = new ToastOptions();
						options.positionClass = toastPosition.getObject();

						Toast toast = Toast.create(toastLevel.getObject(), message.getObject());
						if (!Strings.isNullOrEmpty(title.getObject())) {
							toast.setToastTitle(title.getObject());
						}
						toast.setToastOptions(options)
						.show(this);

					}

				});
				add(new AjaxSubmitLink("ajaxLink") {

					@Override
					protected void onSubmit(AjaxRequestTarget target) {
						super.onSubmit(target);
						ToastOptions options = new ToastOptions();
						options.positionClass = toastPosition.getObject();

						Toast toast = Toast.create(toastLevel.getObject(), message.getObject());
						if (!Strings.isNullOrEmpty(title.getObject())) {
							toast.setToastTitle(title.getObject());
						}
						toast.setToastOptions(options)
						.show(target);
					};

				});

			}
		});

	}
}
