package implementation.ui;

import implementation.FWObject;
import implementation.applicationModel.ABMApplicationModel;

import java.lang.reflect.Field;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.CheckBox;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.WindowOwner;

import annotations.visualWidgets.FieldCheck;
import annotations.visualWidgets.FieldText;

public class EditWindow extends TransactionalDialog<FWObject> {

	FWObject existingInstance;
	FWObject cloneInstance;
	ABMApplicationModel appModel;

	public EditWindow(WindowOwner owner,
			ABMApplicationModel abmApplicationModel, FWObject exstingInstance) {
		super(owner, exstingInstance);
		existingInstance = exstingInstance;
		appModel = abmApplicationModel;
		try {
			cloneInstance = (FWObject) exstingInstance.clone();
		} catch (CloneNotSupportedException e) {

		}

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {

		this.setTitle("Agregar objeto nuevo");

		Field[] fields = existingInstance.getClass().getDeclaredFields();
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
	public void cancel() {
		appModel.cancelEdit(existingInstance, cloneInstance);
		super.cancel();
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
