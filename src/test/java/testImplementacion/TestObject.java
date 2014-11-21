package testImplementacion;

import implementation.FWObject;
import annotations.abm.Title;
import annotations.visualWidgets.FieldCheck;
import annotations.visualWidgets.FieldText;

@Title(title = "Autos ABM")
public class TestObject extends FWObject {

	@FieldCheck
	public boolean tienePatente;

	@FieldText
	private String nombre;

	public boolean isTienePatente() {
		return tienePatente;
	}

	public void setTienePatente(boolean tienePatente) {
		this.tienePatente = tienePatente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
