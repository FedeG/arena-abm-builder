package implementation;

import java.lang.reflect.Field;

import org.eclipse.ui.internal.handlers.WizardHandler.New;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import annotations.abm.Title;

public class MainWindow extends SimpleWindow<ObjectABM> {

	Class<?> domainClass;

	public MainWindow(WindowOwner parent, ObjectABM model) {
		super(parent, model);
	}

	@Override
	protected void addActions(Panel actionsPanel) {

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {

		ObjectABM modelObject = getModelObject();
		domainClass = modelObject.modelClass;

		String nombreDeLaVentana;

		if (modelObject.hasTitle()) {
			nombreDeLaVentana = domainClass.getAnnotation(Title.class).title();
		} else {
			nombreDeLaVentana = "Untitled";
		}
		setTitle(nombreDeLaVentana);

		crearTabla(mainPanel);
	}

	protected void crearTabla(Panel mainPanel) {

		Table<?> tablaDePersistencia = new Table<>(mainPanel, domainClass);

		Field[] domainFields = domainClass.getDeclaredFields();
		for (Field field : domainFields) {
			new Column<>(tablaDePersistencia).setTitle(field.getName())
					.bindContentsToProperty("modelObject." + field.getName());

		}
	}
}
