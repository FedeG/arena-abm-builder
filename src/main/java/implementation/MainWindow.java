package implementation;

import java.lang.reflect.Field;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import annotations.abm.Title;

public class MainWindow extends SimpleWindow<ObjectABM> {

	private Class<?> domainClass;

	public MainWindow(WindowOwner parent, ObjectABM model) {
		super(parent, model);
	}

	@Override
	protected void addActions(Panel actionsPanel) {

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {

		ObjectABM modelObject = getModelObject();
		setDomainClass(modelObject.modelClass);

		String nombreDeLaVentana;

		if (modelObject.hasTitle()) {
			nombreDeLaVentana = getDomainClass().getAnnotation(Title.class).title();
		} else {
			nombreDeLaVentana = "Untitled";
		}
		setTitle(nombreDeLaVentana);
		
		Panel tableBotons = new Panel(mainPanel);
		tableBotons.setLayout(new HorizontalLayout());
		
		crearTabla(tableBotons);

		Panel botons = new Panel(tableBotons);
		botons.setLayout(new VerticalLayout());
		
		Button agregar = new Button(botons);
		agregar.setCaption("Agregar");
		agregar.onClick(new MessageSend(this, "agregar")).setWidth(150);
		
		Button eliminar = new Button(botons);
		eliminar.setCaption("Eliminar");
		eliminar.onClick(new MessageSend(this, "eliminar")).setWidth(150);
		
		Button modificar = new Button(botons);
		modificar.setCaption("Modificar");
		modificar.onClick(new MessageSend(this, "modificar")).setWidth(150);
		
		Button ver = new Button(botons);
		ver.setCaption("Ver");
		ver.onClick(new MessageSend(this, "ver")).setWidth(150);
		
		NotNullObservable elementSelected = new NotNullObservable("objetoSeleccionado");
		eliminar.bindEnabled(elementSelected);
		modificar.bindEnabled(elementSelected);
		ver.bindEnabled(elementSelected);

	}

	protected void crearTabla(Panel mainPanel) {
		
		Table<?> tablaDePersistencia = new Table<>(mainPanel, domainClass);
		tablaDePersistencia.bindValueToProperty("objetoSeleccionado");
		// tablaDePersistencia.bindItemsToProperty("persistedElements");
		
		Field[] domainFields = getDomainClass().getDeclaredFields();
		for (Field field : domainFields) {
			new Column<>(tablaDePersistencia).setTitle(field.getName())
					.bindContentsToProperty("modelObject." + field.getName());

		}
	}
	
	public void agregar() throws InstantiationException, IllegalAccessException {
		this.getModelObject().persistedElements.add(getDomainClass().newInstance());
	}

	public Class<?> getDomainClass() {
		return domainClass;
	}

	public void setDomainClass(Class<?> domainClass) {
		this.domainClass = domainClass;
	}
}
