package jp.try0.wicket.toastr.core.test;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.danekja.java.util.function.serializable.SerializableConsumer;

/**
 * ToastrTestPage
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastrTestPage extends WebPage {
	private static final long serialVersionUID = 1L;

	@FunctionalInterface
	public static interface SerializableAction extends Serializable {
		public void invoke();
	}

	private AjaxLink<Void> ajaxLink;
	private Link<Void> link;

	private SerializableConsumer<AjaxRequestTarget> onClickAjaxLink = t -> {};
	private SerializableAction onClickLink = () -> {};

	/**
	 * Constractor
	 */
	public ToastrTestPage() {
		super();
	}

	/**
	 * Constractor
	 *
	 * @param model
	 */
	public ToastrTestPage(final IModel<?> model) {
		super(model);
	}

	/**
	 * Constractor
	 *
	 * @param parameters
	 */
	public ToastrTestPage(final PageParameters parameters) {
		super(parameters);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(link = new Link<Void>("link") {

			@Override
			public void onClick() {
				onClickLink();
			}
		});
		add(ajaxLink = new AjaxLink<Void>("ajaxLink") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				onClickAjaxLink(target);
			}
		});
	}



	/**
	 * On click ajax link.
	 *
	 * @return onClickAjaxLink
	 */
	protected void onClickAjaxLink(AjaxRequestTarget target) {
		onClickAjaxLink.accept(target);
	}

	/**
	 * On click link.
	 *
	 * @return onClickLink
	 */
	protected void onClickLink() {
		onClickLink.invoke();
	}

	/**
	 * Set on click ajax link handler.
	 *
	 * @param onClickAjaxLink
	 */
	public void setOnClickAjaxLink(SerializableConsumer<AjaxRequestTarget> onClickAjaxLink) {
		this.onClickAjaxLink = onClickAjaxLink;
	}

	/**
	 * Set on click link handler.
	 *
	 * @param onClickLink
	 */
	public void setOnClickLink(SerializableAction onClickLink) {
		this.onClickLink = onClickLink;
	}

	/**
	 * Get the ajax link.
	 *
	 * @return ajaxLink
	 */
	public AjaxLink<Void> getAjaxLink() {
		return ajaxLink;
	}

	/**
	 * Get the link.
	 * @return link
	 */
	public Link<Void> getLink() {
		return link;
	}

}
