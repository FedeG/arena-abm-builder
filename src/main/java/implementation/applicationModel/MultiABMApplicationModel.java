package implementation.applicationModel;

import implementation.FWObject;

import java.util.List;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.utils.Observable;

@Observable
public class MultiABMApplicationModel extends Entity {

	public List<Class<? extends FWObject>> domainClasses;

	public MultiABMApplicationModel(List<Class<? extends FWObject>> domClasses) {
		domainClasses = domClasses;
	}

}
