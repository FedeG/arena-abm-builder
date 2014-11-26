package implementation.ui;

import implementation.FWObject;
import implementation.applicationModel.ABMApplicationModel;

import org.uqbar.arena.windows.WindowOwner;

import annotations.abm.Title;

@SuppressWarnings("serial")
public class EditWindow extends BaseWindow {

	public EditWindow(WindowOwner owner,
			ABMApplicationModel abmApplicationModel, FWObject clonedInstance) {
		super(owner, abmApplicationModel, clonedInstance);
		this.title = "Editar objeto existente";
		this.editable = true;
	}

	@Override
	public void accept() {
		this.execMethodOfInstance(instance.getClass().getAnnotation(Title.class).editMethod());
		appModel.acceptEdit(existingInstance, instance);
		super.accept();
	}

}
