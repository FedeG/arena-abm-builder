package implementation;

import java.lang.reflect.Field;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.CheckBox;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import annotations.abm.Title;
import annotations.visualWidgets.FieldCheck;
import annotations.visualWidgets.FieldDate;
import annotations.visualWidgets.FieldList;
import annotations.visualWidgets.FieldRadioSelector;
import annotations.visualWidgets.FieldText;
import annotations.visualWidgets.FieldTime;

@SuppressWarnings("serial")
public abstract class ABMbuilder extends SimpleWindow<ObjectABM> {

	ObjectABM modelo;

	public ABMbuilder(WindowOwner parent, ObjectABM model) {
		super(parent, model);
		modelo = model;

	}

	protected void addButtons(Panel actionsPanel) {

		new Button(actionsPanel).setCaption("Nuevo");

		new Button(actionsPanel).setCaption("Editar");

		new Button(actionsPanel).setCaption("Eliminar");

	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {

		// Pone el titulo sacado de la annotation

		ObjectABM objetoDeDominio = this.getModelObject();

		Title titleAnnotation = objetoDeDominio.getClass()
				.getDeclaredAnnotation(Title.class);
		this.setTitle(titleAnnotation.title());

		createFormPanel(mainPanel);

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Class<? extends ObjectABM> claseDominio = this.getModelObject()
				.getClass();

		Table<? extends ObjectABM> tablaDeDominio = new Table<>(mainPanel,
				claseDominio);

		/*
		 * for (Method method : claseDominio.getMethods()) {
		 * 
		 * if (method.isAnnotationPresent(AddMethod.class)) { new
		 * Button(mainPanel).setCaption("Nuevo").onClick( ((Action)
		 * Exceptions.soften(() -> method.invoke(this, (Object[]) null)))); }
		 * 
		 * }
		 */
		
		for (Field campo : claseDominio.getDeclaredFields()) {
			try {
				System.out.println(claseDominio.getDeclaredField(campo.getName()));
				if (campo.isAnnotationPresent(FieldCheck.class)) {

					Panel subPanel = new Panel(mainPanel);
					subPanel.setLayout(new ColumnLayout(2));

					new Label(subPanel).setText(campo.getName());
					new CheckBox(subPanel).bindValueToProperty(campo.getName());

				} else if (campo.isAnnotationPresent(FieldDate.class)) {

					new Label(mainPanel).setText("Fecha");

				} else if (campo.isAnnotationPresent(FieldList.class)) {

					new Label(mainPanel).setText("Lista");

				} else if (campo.isAnnotationPresent(FieldRadioSelector.class)) {

					new Label(mainPanel).setText("Pick&Select");

				} else if (campo.isAnnotationPresent(FieldText.class)) {

					new Label(mainPanel).bindValueToProperty(campo.getName());

				} else if (campo.isAnnotationPresent(FieldTime.class)) {

					new Label(mainPanel).setText("Time");

				}
			} catch (NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			} 
		}
		

		/*for (Field campo : claseDominio.getFields()) {			
			if (campo.isAnnotationPresent(FieldCheck.class)) {

				Panel subPanel = new Panel(mainPanel);
				subPanel.setLayout(new ColumnLayout(2));

				new Label(subPanel).setText(campo.getName());
				new CheckBox(subPanel).bindValueToProperty(campo.getName());

			} else if (campo.isAnnotationPresent(FieldDate.class)) {

				new Label(mainPanel).setText("Fecha");

			} else if (campo.isAnnotationPresent(FieldList.class)) {

				new Label(mainPanel).setText("Lista");

			} else if (campo.isAnnotationPresent(FieldRadioSelector.class)) {

				new Label(mainPanel).setText("Pick&Select");

			} else if (campo.isAnnotationPresent(FieldText.class)) {

				new Label(mainPanel).bindValueToProperty(campo.getName());

			} else if (campo.isAnnotationPresent(FieldTime.class)) {

				new Label(mainPanel).setText("Time");

			}

		}*/
	}

	@Override
	protected void addActions(Panel mainPanel) {

	}
}
