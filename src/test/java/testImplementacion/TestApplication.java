package testImplementacion;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

public class TestApplication extends Application{

	@SuppressWarnings("serial")
	@Override
	protected Window<?> createMainWindow() {
		return new TestABM(this,new TestApplicationModel()) {
		};
	}

	public static void main(String[] args) {
		new TestApplication().start();
	}
}
