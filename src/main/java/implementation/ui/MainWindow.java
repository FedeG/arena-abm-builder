package implementation.ui;

import implementation.FWObject;
import implementation.applicationModel.ABMApplicationModel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import annotations.abm.Title;
import annotations.visualWidgets.FieldCheck;
import annotations.visualWidgets.FieldText;

@SuppressWarnings("serial")
public class MainWindow extends TransactionalDialog<ABMApplicationModel> {

	private Class<? extends FWObject> domainClass;

	public MainWindow(WindowOwner parent,
			ABMApplicationModel abmApplicationModel) {
		super(parent, abmApplicationModel);
	}

	@Override
	protected void addActions(Panel actionsPanel) {

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {

		domainClass = getModelObject().getDomainClass();

		String nombreDeLaVentana;

		if (getModelObject().hasTitle()) {
			nombreDeLaVentana = getDomainClass().getAnnotation(Title.class)
					.title();
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

		NotNullObservable elementSelected = new NotNullObservable(
				"objetoSeleccionado");
		eliminar.bindEnabled(elementSelected);
		modificar.bindEnabled(elementSelected);
		ver.bindEnabled(elementSelected);

	}

	protected void crearTabla(Panel mainPanel) {

		Table<? extends FWObject> tablaDePersistencia = new Table<>(mainPanel,domainClass);
		tablaDePersistencia.setHeigth(120).bindValueToProperty("objetoSeleccionado");
		tablaDePersistencia.bindItemsToProperty("persistedElements");
		
		this.getInitialTableData();
		
		Field[] domainFields = getDomainClass().getDeclaredFields();
		for (Field field : domainFields) {

			try {
				if (field.isAnnotationPresent(FieldCheck.class)) {

					FieldCheck annotation = field
							.getAnnotation(FieldCheck.class);

					if (annotation.name() == null || annotation.name() == "") {

						new Column<>(tablaDePersistencia)
								.setTitle(field.getName())
								.bindContentsToProperty(field.getName())
								.setTitle(field.getName());
					} else {
						new Column<>(tablaDePersistencia)
								.setTitle(field.getName())
								.bindContentsToProperty(field.getName())
								.setTitle(annotation.name());
					}

				}
				if (field.isAnnotationPresent(FieldText.class)) {

					FieldText annotation = field.getAnnotation(FieldText.class);

					if (annotation.name() == null || annotation.name() == "") {

						new Column<>(tablaDePersistencia)
								.setTitle(field.getName())
								.bindContentsToProperty(field.getName())
								.setTitle(field.getName());

					} else {
						new Column<>(tablaDePersistencia)
								.setTitle(field.getName())
								.bindContentsToProperty(field.getName())
								.setTitle(annotation.name());
					}

				}

			} catch (IllegalArgumentException | SecurityException e) {

				e.printStackTrace();
			}

		}
	}

	@SuppressWarnings("unchecked")
	private void getInitialTableData() {
		FWObject instance;
		try {
			instance = (FWObject) getDomainClass().newInstance();
			String nameInitialMethod = instance.getClass().getAnnotation(Title.class).getInitialMethod();
			if (!nameInitialMethod.equalsIgnoreCase("")){
				Method edit = instance.getClass().getMethod(nameInitialMethod);
				getModelObject().persistedElements = (List<FWObject>) edit.invoke(instance);
			}
		} catch (NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void eliminar() {
		FWObject instance = getModelObject().objetoSeleccionado;
		this.execMethodOfInstance(instance.getClass().getAnnotation(Title.class).removeMethod(), instance);
		getModelObject().delete(getModelObject().objetoSeleccionado);
	}
	
	public void execMethodOfInstance(String methodName, FWObject instance){
		if (!methodName.equalsIgnoreCase(""))
			try {
				Method edit = instance.getClass().getMethod(methodName, instance.getClass());
				edit.invoke(instance, instance);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
	}


	public void modificar() throws InstantiationException,
			IllegalAccessException, CloneNotSupportedException {
		modificarObjeto();
	}

	public void ver() throws InstantiationException, IllegalAccessException,
			CloneNotSupportedException {
		verObjeto();
	}

	public void agregar() throws InstantiationException, IllegalAccessException {
		agregarObjeto();
	}

	public void agregarObjeto() {
		this.openDialog(new AddWindow(this, getModelObject(), getModelObject()
				.newDomainInstance()));
	}

	public void modificarObjeto() throws CloneNotSupportedException {
		this.openDialog(new EditWindow(this, getModelObject(),
				(FWObject) getModelObject().objetoSeleccionado.clone()));
	}

	public void verObjeto() throws CloneNotSupportedException {
		this.openDialog(new ViewWindow(this, getModelObject(),
				(FWObject) getModelObject().objetoSeleccionado.clone()));
	}

	protected void openDialog(Dialog<?> dialog) {
		dialog.onAccept(new MessageSend(this.getModelObject(), "refresh"));
		dialog.onCancel(new MessageSend(this.getModelObject(), "refresh"));
		dialog.open();
	}

	public Class<?> getDomainClass() {
		return domainClass;
	}

	public void setDomainClass(Class<? extends FWObject> domainClass) {
		this.domainClass = domainClass;
	}
}
