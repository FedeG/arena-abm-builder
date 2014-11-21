package testImplementacion;

import implementation.applicationModel.ABMApplicationModel;
import implementation.ui.MainWindow;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

public class TestApplication extends Application {

	@Override
	protected Window<?> createMainWindow() {

		return new MainWindow(this, new ABMApplicationModel(TestObject.class));

	}

	public static void main(String[] args) {
		new TestApplication().start();
	}
}
