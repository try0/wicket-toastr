package jp.try0.wicket.toastr.core.feedback;

import java.io.Serializable;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;

import com.google.common.collect.Sets;

import jp.try0.wicket.toastr.core.IToast;
import jp.try0.wicket.toastr.core.Toast.ToastLevel;

/**
 * Filter for accepting feedback messages with toast level.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastLevelFeedbackMessageFilter implements IFeedbackMessageFilter {
	private static final long serialVersionUID = 1L;

	/**
	 * Create filter that accept less than argument's level.
	 *
	 * @param level
	 * @return
	 */
	public static ToastLevelFeedbackMessageFilter lessThan(ToastLevel level) {

		Set<ToastLevel> accepts = Sets.newHashSet();

		for (ToastLevel lv : ToastLevel.values()) {
			if (lv.lessThan(level)) {
				accepts.add(lv);
			}
		}

		return new ToastLevelFeedbackMessageFilter(accepts);
	}

	/**
	 * Create filter that accept less than argument's level or equals one.
	 *
	 * @param level
	 * @return
	 */
	public static ToastLevelFeedbackMessageFilter lessThanOrEqual(ToastLevel level) {

		Set<ToastLevel> accepts = Sets.newHashSet();

		for (ToastLevel lv : ToastLevel.values()) {
			if (lv.lessThan(level) || level == lv) {
				accepts.add(lv);
			}
		}

		return new ToastLevelFeedbackMessageFilter(accepts);
	}

	/**
	 * Create filter that accept greater than argument's level.
	 *
	 * @param level
	 * @return
	 */
	public static ToastLevelFeedbackMessageFilter greaterThan(ToastLevel level) {

		Set<ToastLevel> accepts = Sets.newHashSet();

		for (ToastLevel lv : ToastLevel.values()) {
			if (lv.greaterThan(level)) {
				accepts.add(lv);
			}
		}

		return new ToastLevelFeedbackMessageFilter(accepts);
	}

	/**
	 * Create filter that accept greater than argument's level or equals one.
	 *
	 * @param level
	 * @return
	 */
	public static ToastLevelFeedbackMessageFilter greaterThanOrEqual(ToastLevel level) {

		Set<ToastLevel> ignores = Sets.newHashSet();

		for (ToastLevel lv : ToastLevel.values()) {
			if (lv.greaterThan(level) || level == lv) {
				ignores.add(lv);
			}
		}

		return new ToastLevelFeedbackMessageFilter(ignores);
	}



	/**
	 * Accepts toast levels
	 */
	private EnumSet<ToastLevel> accepts;



	/**
	 * Constractor
	 *
	 * @param ignores
	 */
	public ToastLevelFeedbackMessageFilter(ToastLevel... accepts) {
		for (ToastLevel accept : accepts) {
			this.accepts.add(accept);
		}
	}

	/**
	 * Constractor
	 *
	 * @param ignores
	 */
	public ToastLevelFeedbackMessageFilter(Collection<ToastLevel> accepts) {
		this.accepts.addAll(accepts);
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean accept(FeedbackMessage message) {
		Serializable messageObject = message.getMessage();
		if (messageObject instanceof IToast) {
			IToast toast = (IToast) messageObject;
			return accepts.contains(toast.getToastLevel());
		}
		return false;
	}

}
