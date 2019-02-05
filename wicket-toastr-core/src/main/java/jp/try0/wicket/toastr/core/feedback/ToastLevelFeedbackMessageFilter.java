package jp.try0.wicket.toastr.core.feedback;

import java.io.Serializable;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;

import jp.try0.wicket.toastr.core.IToast;
import jp.try0.wicket.toastr.core.Toast.ToastLevel;

/**
 * Filter to accepts feedback messages with toast level.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastLevelFeedbackMessageFilter implements IFeedbackMessageFilter {
	private static final long serialVersionUID = 1L;

	/**
	 * Creates filter that accepts less than argument's level.
	 *
	 * @param level the level that compare to each levels
	 * @return the filter that accepts less than argument's level
	 */
	public static ToastLevelFeedbackMessageFilter lessThan(ToastLevel level) {

		Set<ToastLevel> accepts = new HashSet<>();

		for (ToastLevel lv : ToastLevel.values()) {
			if (lv.lessThan(level)) {
				accepts.add(lv);
			}
		}

		return new ToastLevelFeedbackMessageFilter(accepts);
	}

	/**
	 * Creates filter that accepts less than argument's level or equals one.
	 *
	 * @param level the level that compare to each levels
	 * @return the filter that accepts less than or equals argument's level
	 */
	public static ToastLevelFeedbackMessageFilter lessThanOrEqual(ToastLevel level) {

		Set<ToastLevel> accepts = new HashSet<>();

		for (ToastLevel lv : ToastLevel.values()) {
			if (lv.lessThan(level) || level == lv) {
				accepts.add(lv);
			}
		}

		return new ToastLevelFeedbackMessageFilter(accepts);
	}

	/**
	 * Creates filter that accepts greater than argument's level.
	 *
	 * @param level the level that compare to each levels
	 * @return the filter that accepts greater than argument's level
	 */
	public static ToastLevelFeedbackMessageFilter greaterThan(ToastLevel level) {

		Set<ToastLevel> accepts = new HashSet<>();

		for (ToastLevel lv : ToastLevel.values()) {
			if (lv.greaterThan(level)) {
				accepts.add(lv);
			}
		}

		return new ToastLevelFeedbackMessageFilter(accepts);
	}

	/**
	 * Creates filter that accepts greater than argument's level or equals one.
	 *
	 * @param level the level that compares to each levels
	 * @return the filter that accepts greater than or equals argument's level
	 */
	public static ToastLevelFeedbackMessageFilter greaterThanOrEqual(ToastLevel level) {

		Set<ToastLevel> accepts = new HashSet<>();

		for (ToastLevel lv : ToastLevel.values()) {
			if (lv.greaterThan(level) || level == lv) {
				accepts.add(lv);
			}
		}

		return new ToastLevelFeedbackMessageFilter(accepts);
	}

	/**
	 * Creates filter that accepts argument's levels.
	 *
	 * @param accepts the levels to accepts
	 * @return the filter that accepts argument's level
	 */
	public static ToastLevelFeedbackMessageFilter accepts(ToastLevel ...accepts) {
		return new ToastLevelFeedbackMessageFilter(accepts);
	}

	/**
	 * Creates filter that ignores argument's levels.
	 *
	 * @param ignores the levels to ignores
	 * @return the filter that ignores argument's level
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
	 * Levels to accepts
	 */
	private final EnumSet<ToastLevel> accepts = EnumSet.noneOf(ToastLevel.class);



	/**
	 * Constractor
	 *
	 * @param accepts the levels to accepts
	 */
	public ToastLevelFeedbackMessageFilter(ToastLevel... accepts) {
		for (ToastLevel level : accepts) {
			this.accepts.add(level);
		}
	}

	/**
	 * Constractor
	 *
	 * @param accepts the levels to accepts
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
		} else {
			return accepts.contains(ToastLevel.fromFeedbackMessageLevel(message.getLevel()));
		}
	}

}
