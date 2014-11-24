package implementation.ui;

import implementation.FWObject;
import implementation.applicationModel.ABMApplicationModel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
			ABMApplicationModel abmApplicationModel, FWObject clonedInstance)
			throws CloneNotSupportedException {
		super(owner, clonedInstance);
		appModel = abmApplicationModel;
		existingInstance = appModel.objetoSeleccionado;
		cloneInstance = clonedInstance;

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {

		this.setTitle("Agregar objeto nuevo");

		try {
			Field[] fields = existingInstance.getClass().getDeclaredFields();
			for (Field field : fields) {

				Panel panel = new Panel(mainPanel)
						.setLayout(new ColumnLayout(2));

				if (field.isAnnotationPresent(FieldCheck.class)) {

					FieldCheck annotation = field
							.getAnnotation(FieldCheck.class);

					if (annotation.name() == "") {
						new Label(panel).setText(annotation.name());
					} else {
						new Label(panel).setText(field.getName());
					}

					if (field.getAnnotation(FieldCheck.class).modifiable()) {
						new CheckBox(panel)
								.bindValueToProperty(field.getName());

					} else {

						Method getter = appModel.generateGetter(field,
								existingInstance);

						Boolean valor = (Boolean) getter.invoke(
								existingInstance, (Object[])null);
						new Label(panel).setText((valor) ? "Si" : "No");

					}

				}
				if (field.isAnnotationPresent(FieldText.class)) {

					FieldText annotation = field.getAnnotation(FieldText.class);

					if (annotation.name() == "") {
						new Label(panel).setText(annotation.name());
					} else {
						new Label(panel).setText(field.getName());
					}

					if (field.getAnnotation(FieldText.class).modifiable()) {
						new TextBox(panel).bindValueToProperty(field.getName());

					} else {
						Method getter = appModel.generateGetter(field,
								existingInstance);
						new Label(panel).setText((String) getter.invoke(
								existingInstance, (Object[])null));

					}
				}

			}
		} catch (IllegalArgumentException | IllegalAccessException
				| InvocationTargetException | SecurityException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void accept() {
		appModel.acceptEdit(existingInstance, cloneInstance);
		super.accept();
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
