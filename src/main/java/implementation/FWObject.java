package implementation;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.utils.Observable;

@Observable
public class FWObject extends Entity implements Cloneable {

	@Override
	public Object clone() throws CloneNotSupportedException {

		return super.clone();
	}
}
