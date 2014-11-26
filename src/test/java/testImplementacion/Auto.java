package testImplementacion;

import java.util.ArrayList;
import java.util.regex.Pattern;

import implementation.FWObject;
import annotations.abm.Title;
import annotations.visualWidgets.FieldCheck;
import annotations.visualWidgets.FieldSelector;
import annotations.visualWidgets.FieldText;

@SuppressWarnings("serial")
@Title(title = "Autos ABM")
public class Auto extends FWObject {

	@FieldText(name = "Nombre del automotor")
	private String nombre;

	@FieldText(name = "Patente del automotor", required=true, validator="validarPatente")
	private String patente;
	
	@FieldCheck(name = "Tiene patente")
	public boolean tienePatente;

	@FieldSelector(choices = "provincias", modifiable = false, name = "Provincias")
	public String provincia;
	String[] provincias = { "Buenos Aires", "Cordoba", "La Rioja", "Chaco",
			"Catamarca" };

	public String[] getProvincias() {
		return provincias;
	}

	public void setProvincias() {

	}

	public boolean getTienePatente() {
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

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public ArrayList<String> textos = new ArrayList<String>();
	
	public boolean validarPatente(String patente){
		String PATENTE_PATTERN = "^[a-zA-Z]{0,3}[0-9]{0,3}$";
		textos.add(patente);
		return Pattern.compile(PATENTE_PATTERN).matcher(patente).matches();
	}
}
