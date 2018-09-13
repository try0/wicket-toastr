package jp.try0.wicket.toastr.core.feedback;

import java.io.Serializable;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

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

		Set<ToastLevel> accepts = Sets.newHashSet();

		for (ToastLevel lv : ToastLevel.values()) {
			if (lv.greaterThan(level) || level == lv) {
				accepts.add(lv);
			}
		}

		return new ToastLevelFeedbackMessageFilter(accepts);
	}

	/**
	 * Create filter that accept argument's levels.
	 *
	 * @param level
	 * @return
	 */
	public static ToastLevelFeedbackMessageFilter accepts(ToastLevel ...accepts) {
		return new ToastLevelFeedbackMessageFilter(accepts);
	}

	/**
	 * Create filter that ignore argument's levels.
	 *
	 * @param level
	 * @return
	 */
	public static ToastLevelFeedbackMessageFilter ignores(ToastLevel ...ignores) {

		Set<ToastLevel> accepts = EnumSet.allOf(ToastLevel.class).stream()
				.filter(lv -> {
					for (ToastLevel ignore : ignores) {
						if (lv == ignore) {
							return false;
						}
					}
					return true;
				})
				.collect(Collectors.toSet());

		return new ToastLevelFeedbackMessageFilter(accepts);
	}


	/**
	 * Accepts toast levels
	 */
	private final EnumSet<ToastLevel> accepts = EnumSet.noneOf(ToastLevel.class);



	/**
	 * Constractor
	 *
	 * @param accepts
	 */
	public ToastLevelFeedbackMessageFilter(ToastLevel... accepts) {
		for (ToastLevel level : accepts) {
			this.accepts.add(level);
		}
	}

	/**
	 * Constractor
	 *
	 * @param accepts
	 */
	public ToastLevelFeedbackMessageFilter(Collection<ToastLevel> accepts) {
		for (ToastLevel level : accepts) {
			this.accepts.add(level);
		}
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
