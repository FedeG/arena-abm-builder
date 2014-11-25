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
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.WindowOwner;

import annotations.visualWidgets.FieldCheck;
import annotations.visualWidgets.FieldSelector;
import annotations.visualWidgets.FieldText;

public abstract class BaseWindow extends TransactionalDialog<FWObject> {

	FWObject existingInstance;
	FWObject instance;
	ABMApplicationModel appModel;
	String title = "";
	Boolean editable = false;
	Boolean editable_all = false;

	public BaseWindow(WindowOwner owner,
			ABMApplicationModel abmApplicationModel, FWObject clonedInstance) {
		super(owner, clonedInstance);
		appModel = abmApplicationModel;
		existingInstance = appModel.objetoSeleccionado;
		instance = clonedInstance;

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {

		this.setTitle(this.title);

		try {
			Field[] fields = existingInstance.getClass().getDeclaredFields();
			for (Field field : fields) {

				Panel panel = new Panel(mainPanel)
						.setLayout(new ColumnLayout(2));

				if (field.isAnnotationPresent(FieldCheck.class)) {

					FieldCheck annotation = field
							.getAnnotation(FieldCheck.class);

					if (annotation.name() == null || annotation.name() == "") {
						new Label(panel).setText(field.getName());
					} else {
						new Label(panel).setText(annotation.name());
					}

					if ((field.getAnnotation(FieldCheck.class).modifiable() && this.editable) || this.editable_all) {
						new CheckBox(panel)
								.bindValueToProperty(field.getName());

					} else {

						Method getter = appModel.generateGetter(field,
								existingInstance);

						Boolean valor = (Boolean) getter.invoke(
								existingInstance, (Object[]) null);
						new Label(panel).setText((valor) ? "Si" : "No");

					}

				}
				if (field.isAnnotationPresent(FieldText.class)) {

					FieldText annotation = field.getAnnotation(FieldText.class);

					if (annotation.name() == null || annotation.name() == "") {
						new Label(panel).setText(field.getName());
					} else {
						new Label(panel).setText(annotation.name());
					}

					if ((field.getAnnotation(FieldText.class).modifiable() && this.editable) || this.editable_all) {
						new TextBox(panel).bindValueToProperty(field.getName());

					} else {
						Method getter = appModel.generateGetter(field,
								existingInstance);
						new Label(panel).setText((String) getter.invoke(
								existingInstance, (Object[]) null));

					}
				}
				if (field.isAnnotationPresent(FieldSelector.class)) {

					FieldSelector annotation = field
							.getAnnotation(FieldSelector.class);

					new Label(panel).setText(annotation.name());

					if ((field.getAnnotation(FieldSelector.class).modifiable() && this.editable) || this.editable_all) {
						Selector<String> selector = new Selector<String>(panel);
						selector.bindItemsToProperty(annotation.choices());
						selector.bindValueToProperty(field.getName());

					} else {
						Method getter = appModel.generateGetter(field,
								existingInstance);
						new Label(panel).setText((String) getter.invoke(
								existingInstance, (Object[]) null));

					}

				}

			}
		} catch (IllegalArgumentException | IllegalAccessException
				| InvocationTargetException | SecurityException e) {

			e.printStackTrace();
		}
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
