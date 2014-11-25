package implementation.ui;

import implementation.FWObject;
import implementation.applicationModel.ABMApplicationModel;

import org.uqbar.arena.windows.WindowOwner;

public class AddWindow extends BaseWindow {

	public AddWindow(WindowOwner owner, ABMApplicationModel applicationModel,
			FWObject nwInstance) {
		super(owner, applicationModel, nwInstance);
		this.title = "Agregar objeto nuevo";
		this.editable_all = true;
		this.existingInstance = this.instance;
	}

	@Override
	protected void executeTask() {
		appModel.persist(instance);
		super.executeTask();
	}

}
