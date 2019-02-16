package jp.try0.wicket.toastr.core.config;

import java.util.function.Supplier;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.application.IComponentInstantiationListener;

import jp.try0.wicket.toastr.core.behavior.ToastrBehavior;

/**
 * Appends a {@link ToastrBehavior} to new {@link Page}.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastrBehaviorAutoAppender implements IComponentInstantiationListener {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onInstantiation(Component component) {
		if (component instanceof Page) {
			Supplier<ToastrBehavior> factory = ToastrSetting.get().getToastrBehaviorFactory();
			ToastrBehavior behavior = factory.get();
			component.add(behavior);
		}
	}

}
