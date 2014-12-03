package testImplementacion;

import implementation.applicationModel.ABMApplicationModel;
import implementation.ui.MainWindow;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

public class TestSimpleApplication extends Application {
	
	@Override
	protected Window<?> createMainWindow() {
		return new MainWindow(this, new ABMApplicationModel(Auto.class));
	}

	public static void main(String[] args) {
		new TestApplication().start();
	}
}
