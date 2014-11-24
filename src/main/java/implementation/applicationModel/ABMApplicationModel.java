package implementation.applicationModel;

import implementation.FWObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.utils.Observable;

import annotations.abm.Title;

@Observable
public class ABMApplicationModel extends Entity {

	public List<FWObject> persistedElements = new ArrayList<FWObject>();
	public FWObject objetoSeleccionado;

	public Class<? extends FWObject> domainClass;
	public FWObject tableReferenceInstance;

	// Constructor
	public ABMApplicationModel(Class<? extends FWObject> domClass) {

		domainClass = domClass;

		tableReferenceInstance = newDomainInstance();
	}

	public boolean hasTitle() {
		return domainClass.isAnnotationPresent(Title.class);
	}

	public FWObject newDomainInstance() {
		FWObject newInstance = null;
		try {
			newInstance = domainClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {

			e.printStackTrace();
		}
		return newInstance;
	}

	public void persist(FWObject objToPersist) {
		persistedElements.add(objToPersist);
		refresh();
	}

	public void refresh() {
		List<FWObject> aux = new ArrayList<FWObject>();
		aux = persistedElements;
		persistedElements = new ArrayList<FWObject>();
		persistedElements = aux;

	}

	public void acceptEdit(FWObject existingInstance, FWObject cloneInstance) {
		persistedElements.remove(existingInstance);
		persistedElements.add(cloneInstance);
		objetoSeleccionado = cloneInstance;

	}

	public void delete(FWObject objetoABorrar) {
		List<FWObject> aux = new ArrayList<FWObject>();
		for (FWObject fwObject : persistedElements) {
			if (!(fwObject == objetoABorrar)) {
				aux.add(fwObject);
			}
		}
		persistedElements = aux;
		refresh();

	}

	public Method generateGetter(Field field, FWObject instance) {
		Method getter=null;
		try {
			getter= instance.getClass().getDeclaredMethod(
					"get"
							+ field.getName().substring(0, 1).toUpperCase()
							+ field.getName().substring(1,
									field.getName().length()), (Class<?>[]) null);
		} catch (NoSuchMethodException | SecurityException e) {

			e.printStackTrace();
		}
		return getter;
	}

	// /--------------------------------S&G-----------------------------------------------/

	public List<FWObject> getPersistedElements() {
		return persistedElements;
	}

	public void setPersistedElements(List<FWObject> persistedElements) {
		this.persistedElements = persistedElements;
	}

	public FWObject getObjetoSeleccionado() {
		return objetoSeleccionado;
	}

	public void setObjetoSeleccionado(FWObject objetoSeleccionado) {
		this.objetoSeleccionado = objetoSeleccionado;
	}

	public Class<? extends FWObject> getDomainClass() {
		return domainClass;
	}

	public void setDomainClass(Class<? extends FWObject> domainClass) {
		this.domainClass = domainClass;
	}

	public Object getTableReferenceInstance() {
		return tableReferenceInstance;
	}

	public void getTableReferenceInstance(FWObject modelObject) {
		this.tableReferenceInstance = modelObject;
	}

}
