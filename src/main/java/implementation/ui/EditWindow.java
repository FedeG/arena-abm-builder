package implementation.ui;

import implementation.FWObject;
import implementation.applicationModel.ABMApplicationModel;

import org.uqbar.arena.windows.WindowOwner;

public class EditWindow extends BaseWindow {
	
	public EditWindow(WindowOwner owner,
			ABMApplicationModel abmApplicationModel, FWObject clonedInstance) {
		super(owner, abmApplicationModel, clonedInstance);
		this.title = "Editar objeto existente";
		this.editable = true;
	}
	
	@Override
	public void accept() {
		appModel.acceptEdit(existingInstance, instance);
		super.accept();
	}
	
}
