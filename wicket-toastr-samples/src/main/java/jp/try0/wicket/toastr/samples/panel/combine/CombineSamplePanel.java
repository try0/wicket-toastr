package jp.try0.wicket.toastr.samples.panel.combine;

import java.util.stream.Stream;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import jp.try0.wicket.toastr.core.IToast;
import jp.try0.wicket.toastr.core.Toast;
import jp.try0.wicket.toastr.core.ToastOptions;
import jp.try0.wicket.toastr.core.behavior.ToastrBehavior;
import jp.try0.wicket.toastr.core.behavior.ToastrBehavior.ToastMessageCombiner;
import jp.try0.wicket.toastr.samples.panel.ToastBasicPanel;

/**
 * Combine sample.
 *
 * @author Ryo Tsunoda
 *
 */
public class CombineSamplePanel extends Panel {
	private static final long serialVersionUID = 1L;

	/**
	 * Constractor
	 *
	 * @param parameters
	 */
	public CombineSamplePanel(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		final ToastBasicPanel pnlBasic;
		add(pnlBasic = new ToastBasicPanel("pnlBasic"));

		IModel<Boolean> needCombine = new Model<>(false);
		add(new CheckBox("chkCombine", needCombine));

		add(new SubmitLink("linkShowx3") {

			@Override
			public void onSubmit() {
				super.onSubmit();
				Toast toast = pnlBasic.getToast();
				ToastOptions options = new ToastOptions();
				options.setNeedEscapeHtml(false);
				toast.withToastOptions(options);

				toast.show(this);
				toast.show(this);
				toast.show(this);
			}

		});

		ToastMessageCombiner combiner = new ToastMessageCombiner() {
			@Override
			public Stream<IToast> combine(Stream<IToast> toastStream) {
				if (needCombine.getObject()) {
					return super.combine(toastStream);
				}

				return toastStream;
			}
		};
		combiner.setPrefix("・");

		final ToastrBehavior behavior = new ToastrBehavior();
		behavior.setMessageCombiner(combiner);
		add(behavior);
	}
}
