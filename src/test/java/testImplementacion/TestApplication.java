package testImplementacion;

import implementation.ABMbuilder;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

public class TestApplication extends Application {

	@SuppressWarnings("serial")
	@Override
	protected Window<?> createMainWindow() {
		return new ABMbuilder(this, new TestObject()) {
		};
	}

	public static void main(String[] args) {
		new TestApplication().start();
	}
}
