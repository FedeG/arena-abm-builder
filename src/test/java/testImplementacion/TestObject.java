package testImplementacion;

import annotations.abm.Title;
import annotations.visualWidgets.FieldCheck;
import annotations.visualWidgets.FieldText;

@Title(title = "Autos ABM")
public class TestObject {
	
	public TestObject() {
		this.setTienePatente(true);
		this.setNombre("default");
	}
	
	public TestObject(boolean tienePatente, String nombre) {
		this.setTienePatente(tienePatente);
		this.setNombre(nombre);
	}
	
	@FieldCheck
	public boolean tienePatente=true;
	
	@FieldText
	private String nombre="pepito";

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
