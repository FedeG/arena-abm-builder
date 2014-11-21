package implementation.applicationModel;

import implementation.FWObject;

import java.util.HashSet;
import java.util.Set;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.utils.Observable;

import annotations.abm.Title;

@Observable
public class ABMApplicationModel extends Entity {

	public Set<FWObject> persistedElements = new HashSet<FWObject>();
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
		Set<FWObject> aux = new HashSet<FWObject>();
		aux = persistedElements;
		persistedElements = new HashSet<FWObject>();
		persistedElements = aux;

	}
	public void cancelEdit(FWObject existingInstance, FWObject cloneInstance) {
		persistedElements.remove(existingInstance);
		persistedElements.add(cloneInstance);
		objetoSeleccionado = cloneInstance;
		
	}

	public void delete() {
	 persistedElements.remove(objetoSeleccionado);
	 objetoSeleccionado=null;
	 refresh();
		
	}

	// /--------------------------------S&G-----------------------------------------------/

	public Set<FWObject> getPersistedElements() {
		return persistedElements;
	}

	public void setPersistedElements(Set<FWObject> persistedElements) {
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
