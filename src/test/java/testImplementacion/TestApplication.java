package testImplementacion;

import implementation.MainWindow;
import implementation.ObjectABM;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

public class TestApplication extends Application {

	@Override
	protected Window<?> createMainWindow() {
		try {
			return new MainWindow(this,new ObjectABM(TestObject.class));
		} catch (InstantiationException | IllegalAccessException e) {
			
			e.printStackTrace();
		}
		return null; 
		
	}

	public static void main(String[] args) {
		new TestApplication().start();
	}
}
