package testImplementacion;

import implementation.FWObject;
import annotations.abm.Title;
import annotations.visualWidgets.FieldCheck;
import annotations.visualWidgets.FieldSelector;
import annotations.visualWidgets.FieldText;

@Title(title = "Chofer ABM")
public class Chofer extends FWObject {

	@FieldText(modifiable = true, name = "Nombre del chofer")
	private String nombre;

	@FieldText(modifiable = true, name = "Domicilio del chofer")
	private String domicilio;
	
	@FieldCheck(modifiable = false, name = "Tiene auto")
	private boolean tieneAuto;

	@FieldSelector(choices = "provincias", modifiable = false, name = "Provincias")
	public String provincia;
	String[] provincias = { "Buenos Aires", "Cordoba", "La Rioja", "Chaco",
			"Catamarca" };

	public String[] getProvincias() {
		return provincias;
	}

	public void setProvincias() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public boolean getTieneAuto() {
		return tieneAuto;
	}

	public void setTieneAuto(boolean tieneAuto) {
		this.tieneAuto = tieneAuto;
	}

}
