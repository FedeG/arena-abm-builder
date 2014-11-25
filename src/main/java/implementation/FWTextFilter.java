package implementation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.uqbar.arena.widgets.TextFilter;
import org.uqbar.arena.widgets.TextInputEvent;

public class FWTextFilter implements TextFilter {

	Method validator;
	FWObject instance;

	public FWTextFilter(Method validator, FWObject instance) {
		this.validator = validator;
		this.instance = instance;
	}

	@Override
	public boolean accept(TextInputEvent arg0) {
		try {
			return (boolean) this.validator.invoke(this.instance, arg0.getPotentialTextResult());
		} catch (SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			return false;
		}
	}

}
