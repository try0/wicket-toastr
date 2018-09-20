package jp.try0.wicket.toastr.core.feedback;

import java.io.Serializable;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;

import jp.try0.wicket.toastr.core.IToast;

/**
 * A filter that ignores the instance implemented the {@link IToast} interface.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastIgnoreFilter implements IFeedbackMessageFilter {
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean accept(FeedbackMessage message) {
		Serializable messageObject = message.getMessage();
		return !(messageObject instanceof IToast);
	}

}
