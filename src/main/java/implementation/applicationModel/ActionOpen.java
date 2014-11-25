package implementation.applicationModel;

import implementation.FWObject;
import implementation.ui.MainWindow;

import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.Action;

public class ActionOpen implements Action {

	Class<? extends FWObject> clase;
	WindowOwner window;

	public ActionOpen(WindowOwner window, Class<? extends FWObject> clase) {
		this.clase = clase;
		this.window = window;
	}

	@Override
	public void execute() {
		new MainWindow(this.window, new ABMApplicationModel(this.clase)).open();
	}

	
}
