package implementation;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

@SuppressWarnings("serial")
public abstract class ABMbuilder extends SimpleWindow<ABMapplicationModel> {

	
	public ABMbuilder(WindowOwner parent, ABMapplicationModel model, String title) {
		super(parent, model);
		model.setWindowTitle(title);
	}

	@Override
	protected void addActions(Panel actionsPanel) {

		new Button(actionsPanel).setCaption("Nuevo");
		// .onClick(() -> this.nuevo());

		new Button(actionsPanel).setCaption("Editar");
		// .onClick(() -> editar());

		new Button(actionsPanel).setCaption("Eliminar");
		// .onClick(() -> this.eliminar());

	}

	/*
	 * private void eliminar() { this.getModelObject().eliminar(); }
	 * 
	 * private void nuevo() { this.getModelObject().nuevo(); }
	 * 
	 * private void editar() { this.getModelObject().editar();
	 * 
	 * }
	 */
	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle((this.getModelObject()).getWindowTitle());

	}
}
