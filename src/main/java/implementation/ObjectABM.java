package implementation;

import java.util.LinkedHashSet;
import java.util.Set;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.utils.Observable;

import annotations.abm.Title;

@Observable
public class ObjectABM extends Entity {

	public Set<Object> persistedElements = new LinkedHashSet<Object>();
	public Object objetoSeleccionado;

	public Class<?> modelClass;
	public Object modelObject;

	// Constructor
	public ObjectABM(Class<?> model) throws InstantiationException,
			IllegalAccessException {
		try {

			modelClass = model;
			modelObject = modelClass.newInstance();

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		Object test;

	}
	public boolean hasTitle() {
		return modelClass.isAnnotationPresent(Title.class);
	}

	public Set<Object> getPersistedElements() {
		return persistedElements;
	}

	public void setPersistedElements(Set<Object> persistedElements) {
		this.persistedElements = persistedElements;
	}

	public Object getObjetoSeleccionado() {
		return objetoSeleccionado;
	}

	public void setObjetoSeleccionado(Object objetoSeleccionado) {
		this.objetoSeleccionado = objetoSeleccionado;
	}

	public Class<?> getModelClass() {
		return modelClass;
	}

	public void setModelClass(Class<?> modelClass) {
		this.modelClass = modelClass;
	}

	public Object getModelObject() {
		return modelObject;
	}

	public void setModelObject(Object modelObject) {
		this.modelObject = modelObject;
	}

	// /--------------------------------S&G-----------------------------------------------/

}
