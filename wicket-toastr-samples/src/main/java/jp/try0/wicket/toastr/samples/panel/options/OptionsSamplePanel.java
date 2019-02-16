package jp.try0.wicket.toastr.samples.panel.options;

import java.util.Arrays;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.FeedbackMessagesModel;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.danekja.java.misc.serializable.SerializableComparator;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapRadioChoice;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.radio.BooleanRadioChoiceRenderer;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.radio.BooleanRadioGroup;
import jp.try0.wicket.toastr.core.Toast;
import jp.try0.wicket.toastr.core.ToastOption;
import jp.try0.wicket.toastr.core.ToastOption.CloseMethod;
import jp.try0.wicket.toastr.core.ToastOption.Easing;
import jp.try0.wicket.toastr.core.ToastOption.HideMethod;
import jp.try0.wicket.toastr.core.ToastOption.PositionClass;
import jp.try0.wicket.toastr.core.ToastOption.ShowMethod;
import jp.try0.wicket.toastr.core.behavior.ToastrBehavior;
import jp.try0.wicket.toastr.samples.panel.ToastBasicPanel;

/**
 * Options sample.
 *
 * @author Ryo Tsunoda
 *
 */
public class OptionsSamplePanel extends Panel {
	private static final long serialVersionUID = 1L;

	/**
	 * Constractor
	 *
	 * @param parameters
	 */
	public OptionsSamplePanel(String id) {
		super(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new ToastrBehavior() {
			@Override
			protected FeedbackMessagesModel newFeedbackMessagesModel(Component pageResolver, IFeedbackMessageFilter filter) {
				FeedbackMessagesModel mdl = super.newFeedbackMessagesModel(pageResolver, filter);
				SerializableComparator<FeedbackMessage> comparator =
						(m1, m2) -> Integer.compare(m1.getLevel(), m2.getLevel());
				mdl.setSortingComparator(comparator);
				return mdl;
			}
		});

		// Toast positions
		IModel<PositionClass> toastPosition = new Model<>(PositionClass.TOP_RIGHT);
		add(new BootstrapRadioChoice<PositionClass>("rdoPosition", toastPosition,
				Arrays.asList(PositionClass.values())));

		// Methods
		IModel<ShowMethod> showMethod = new Model<>(ShowMethod.FADE_IN);
		add(new BootstrapRadioChoice<ShowMethod>("rdoShowMethod", showMethod,
				Arrays.asList(ShowMethod.values())));

		IModel<HideMethod> hideMethod = new Model<>(HideMethod.FADE_OUT);
		add(new BootstrapRadioChoice<HideMethod>("rdoHideMethod", hideMethod,
				Arrays.asList(HideMethod.values())));

		IModel<CloseMethod> closeMethod = new Model<>(CloseMethod.FADE_OUT);
		add(new BootstrapRadioChoice<CloseMethod>("rdoCloseMethod", closeMethod,
				Arrays.asList(CloseMethod.values())));

		// Easing
		IModel<Easing> showEasing = new Model<>(Easing.SWING);
		add(new BootstrapRadioChoice<Easing>("rdoShowEasing", showEasing,
				Arrays.asList(Easing.values())));

		IModel<Easing> hideEasing = new Model<>(Easing.SWING);
		add(new BootstrapRadioChoice<Easing>("rdoHideEasing", hideEasing,
				Arrays.asList(Easing.values())));

		IModel<Easing> closeEasing = new Model<>(Easing.SWING);
		add(new BootstrapRadioChoice<Easing>("rdoCloseEasing", closeEasing,
				Arrays.asList(Easing.values())));

		// Progress Bar
		IModel<Boolean> progressBar = new Model<>(false);
		add(newBooleanRadioGroup("switchProgressBar", progressBar));

		// Right to left
		IModel<Boolean> rtl = new Model<>(false);
		add(newBooleanRadioGroup("switchRtl", rtl));

		// Callbacks
		IModel<Boolean> onShown = new Model<>(false);
		add(newBooleanRadioGroup("switchOnShown", onShown));

		IModel<Boolean> onHidden = new Model<>(false);
		add(newBooleanRadioGroup("switchOnHidden", onHidden));

		IModel<Boolean> onClick = new Model<>(false);
		add(newBooleanRadioGroup("switchOnClick", onClick));

		IModel<Boolean> onCloseClick = new Model<>(false);
		add(newBooleanRadioGroup("switchOnCloseClick", onCloseClick));

		// Display sequence
		IModel<Boolean> newestOnTop = new Model<>(false);
		add(newBooleanRadioGroup("switchNewestOnTop", newestOnTop));

		// Prevent duplicates
		IModel<Boolean> preventDuplicates = new Model<>(false);
		add(newBooleanRadioGroup("switchPreventDuplicates", preventDuplicates));

		// show duration
		IModel<Integer> showDuration = new Model<>(1000);
		add(new NumberTextField<Integer>("txtShowDuration", showDuration));

		// hide duration
		IModel<Integer> hideDuration = new Model<>(500);
		add(new NumberTextField<Integer>("txtHideDuration", hideDuration));

		// close duration
		IModel<Integer> closeDuration = new Model<>(500);
		add(new NumberTextField<Integer>("txtCloseDuration", closeDuration));

		// Time out
		IModel<Integer> timeOut = new Model<>(5000);
		add(new NumberTextField<Integer>("txtTimeOut", timeOut));

		// Extended time out
		IModel<Integer> extendedTimeOut = new Model<>(1000);
		add(new NumberTextField<Integer>("txtExtendedTimeOut", extendedTimeOut));

		// Extended time out
		IModel<Boolean> escapeHtml = new Model<>(true);
		add(newBooleanRadioGroup("switchEscapeHtml", escapeHtml));

		// ToastOptions
		IModel<ToastOption> options = () -> {
			ToastOption opts = ToastOption.create()
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
					.setOnHiddenFunction(
							onHidden.getObject() ? "function() {alert('fired onHidden');}" : "false")
					.setOnClickFunction(onClick.getObject() ? "function() {alert('fired onclick');}" : "false")
					.setIsEnableCloseButton(onCloseClick.getObject()).setOnCloseClickFunction(
							onCloseClick.getObject() ? "function() {alert('fired onCloseClick');}" : "false");
			return opts;
		};

		final ToastBasicPanel pnlBasic;
		add(pnlBasic = new ToastBasicPanel("pnlBasic"));
		// Toast
		IModel<Toast> toast = () -> {
			return pnlBasic.getToast()
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

	/**
	 * Creates radio group
	 *
	 * @param id wicket:id
	 * @param model selected state model
	 * @return BooleanRadioGroup
	 */
	private BooleanRadioGroup newBooleanRadioGroup(String id, IModel<Boolean> model) {
		BooleanRadioGroup radio = new BooleanRadioGroup(id, model);
		radio.setChoiceRenderer(new BooleanRadioChoiceRenderer(Type.Secondary, radio));
		return radio;
	}
}
