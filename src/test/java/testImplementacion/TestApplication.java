package testImplementacion;

import java.util.ArrayList;

import implementation.FWObject;
import implementation.applicationModel.MultiABMApplicationModel;
import implementation.ui.MultiMainWindow;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

public class TestApplication extends Application {
	
	@Override
	protected Window<?> createMainWindow() {
		ArrayList<Class<? extends FWObject>> lista = new ArrayList<Class<? extends FWObject>>();
		lista.add(Auto.class);
		lista.add(Chofer.class);
		return new MultiMainWindow(this, new MultiABMApplicationModel(lista));
	}

	public static void main(String[] args) {
		new TestApplication().start();
	}
}
