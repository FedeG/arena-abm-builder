package implementation;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.utils.Observable;

@Observable
@SuppressWarnings("serial")
public abstract class ABMapplicationModel extends Entity {

	private String windowTitle;
	
	//Acciones por default, las cuales se pueden overridear.
		
	public String getWindowTitle() {
		return windowTitle;
	}

	public void setWindowTitle(String windowTitle) {
		this.windowTitle = windowTitle;
	}

	public void editar() {

	}

	public void eliminar() {

	}

	public void nuevo() {

	}

}
