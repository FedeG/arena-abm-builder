package testImplementacion;

import annotations.abm.Title;
import annotations.visualWidgets.FieldCheck;

@Title(title = "Autos ABM")
public class TestObject {
	
	@FieldCheck
	public boolean tienePatente=true;
	public String nombre="pepito";

	public boolean isTienePatente() {
		return tienePatente;
	}

	public void setTienePatente(boolean tienePatente) {
		this.tienePatente = tienePatente;
	}

}
