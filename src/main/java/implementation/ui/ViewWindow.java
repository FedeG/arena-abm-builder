package implementation.ui;

import implementation.FWObject;
import implementation.applicationModel.ABMApplicationModel;

import org.uqbar.arena.windows.WindowOwner;

@SuppressWarnings("serial")
public class ViewWindow extends BaseWindow {

	public ViewWindow(WindowOwner owner,
			ABMApplicationModel abmApplicationModel, FWObject clonedInstance) {
		super(owner, abmApplicationModel, clonedInstance);
		this.title = "Ver objeto existente";
	}

}