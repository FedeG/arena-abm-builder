package implementation.ui;

import java.lang.reflect.Field;

import implementation.FWObject;
import implementation.applicationModel.ABMApplicationModel;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.CheckBox;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import annotations.visualWidgets.FieldCheck;
import annotations.visualWidgets.FieldText;

public class AddWindow extends Dialog<FWObject> {

	FWObject newInstance;
	ABMApplicationModel appModel;

	public AddWindow(WindowOwner owner, ABMApplicationModel applicationModel,
			FWObject nwInstance) {
		super(owner, nwInstance);
		newInstance = nwInstance;
		appModel = applicationModel;
		
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {

		
		this.setTitle("Agregar objeto nuevo");
		
		Field[] fields = newInstance.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(FieldCheck.class)) {
				Panel panel = new Panel(mainPanel)
						.setLayout(new ColumnLayout(2));
				new Label(panel).setText(field.getName());
				new CheckBox(panel).bindValueToProperty(field.getName());

			}
			if (field.isAnnotationPresent(FieldText.class)) {
				Panel panel = new Panel(mainPanel)
						.setLayout(new ColumnLayout(2));
				new Label(panel).setText(field.getName());
				new TextBox(panel).bindValueToProperty(field.getName());

			}
		}
	}

	@Override
	protected void executeTask() {
		appModel.persist(newInstance);
		super.executeTask();
	}

	@Override
	protected void addActions(Panel action) {
		Panel panelDeBotones = new Panel(action).setLayout(new ColumnLayout(2));
		new Button(panelDeBotones).setCaption("Aceptar").onClick(
				new MessageSend(this, "accept"));
		new Button(panelDeBotones).setCaption("Cancelar").onClick(
				new MessageSend(this, "cancel"));

	}

}