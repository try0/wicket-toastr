package jp.try0.wicket.toastr.samples.panel;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.value.IValueMap;
import org.apache.wicket.util.value.ValueMap;

import com.google.common.base.Strings;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapRadioChoice;
import jp.try0.wicket.toastr.core.Toast;
import jp.try0.wicket.toastr.core.Toast.ToastLevel;

/**
 * The panel that creates basic toast.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastBasicPanel extends Panel {
	private static final long serialVersionUID = 1L;

	/**
	 * level
	 */
	private final IModel<ToastLevel> toastLevel = Model.of(ToastLevel.INFO);

	/**
	 * toast title
	 */
	private final IModel<String> title = Model.of("");

	/**
	 * toast message
	 */
	private final IModel<String> message = Model.of("toast");


	/**
	 * Constractor
	 *
	 * @param parameters
	 */
	public ToastBasicPanel(String id) {
		super(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitialize() {
		super.onInitialize();

		// Toast levels
		List<ToastLevel> levels =
				Arrays.asList(ToastLevel.SUCCESS, ToastLevel.INFO, ToastLevel.WARNING, ToastLevel.ERROR);
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
				default:
					break;
				}

				vm.put("class", classNames);

				return vm;
			}
		});

		// Toast title
		add(new TextField<>("txtTitle", title));

		// Toast Message
		add(new TextArea<String>("txtMessage", message).setRequired(true));
	}

	/**
	 * Gets toast.
	 *
	 * @return toast object
	 */
	public Toast getToast() {
		return Toast.create(toastLevel.getObject(), message.getObject())
				.withTitle(!Strings.isNullOrEmpty(title.getObject()), title::getObject);
	}
}
