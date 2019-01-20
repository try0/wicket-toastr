package jp.try0.wicket.toastr.samples.panel.filter;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.google.common.collect.Sets;

import jp.try0.wicket.toastr.core.Toast;
import jp.try0.wicket.toastr.core.Toast.ToastLevel;
import jp.try0.wicket.toastr.core.behavior.ToastrBehavior;
import jp.try0.wicket.toastr.samples.panel.ToastBasicPanel;

/**
 * Filter sample.
 *
 * @author Ryo Tsunoda
 *
 */
public class FilterSamplePanel extends Panel {
	private static final long serialVersionUID = 1L;

	/**
	 * Accepts levels
	 */
	private final Set<ToastLevel> accepts = Sets.newHashSet();

	/**
	 * Constractor
	 *
	 * @param parameters
	 */
	public FilterSamplePanel(String id) {
		super(id);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitialize() {
		super.onInitialize();

		final ToastrBehavior behavior = new ToastrBehavior();
		behavior.setMessageFilter(msg -> {
			ToastLevel lv = ToastLevel.fromFeedbackMessageLevel(msg.getLevel());
			return accepts.contains(lv);
		});
		add(behavior);

		final ToastBasicPanel pnlBasic;
		add(pnlBasic = new ToastBasicPanel("pnlBasic"));

		final List<ToastLevel> levels =
				Arrays.asList(ToastLevel.SUCCESS, ToastLevel.INFO, ToastLevel.WARNING, ToastLevel.ERROR);
		accepts.addAll(levels);
		add(new ListView<ToastLevel>("levels", levels) {

			@Override
			protected void populateItem(ListItem<ToastLevel> item) {
				IModel<Boolean> isAccept = new IModel<Boolean>() {
					@Override
					public Boolean getObject() {
						return accepts.contains(item.getModelObject());
					}

					@Override
					public void setObject(Boolean object) {
						if (object) {
							accepts.add(item.getModelObject());
						} else {
							accepts.remove(item.getModelObject());
						}
					}
				};
				item.add(new CheckBox("chkLevel", isAccept));
				item.add(new Label("labelLevel", item.getModelObject()));

			}
		});

		add(new SubmitLink("linkShow") {

			@Override
			public void onSubmit() {
				super.onSubmit();
				Toast toast = pnlBasic.getToast();
				toast.show(this);
			}

		});
	}
}
