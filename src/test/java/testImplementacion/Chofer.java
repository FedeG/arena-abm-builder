package testImplementacion;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Pattern;

import implementation.FWObject;
import annotations.abm.Title;
import annotations.visualWidgets.FieldCheck;
import annotations.visualWidgets.FieldSelector;
import annotations.visualWidgets.FieldText;

@SuppressWarnings("serial")
@Title(title = "Chofer ABM", addMethod="add", editMethod="edit", removeMethod="remove")
public class Chofer extends FWObject {

	@FieldText(name = "Nombre del chofer", validator="validateName")
	private String nombre;

	@FieldText(name = "Domicilio del chofer", required=true)
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

	public boolean validateName(String name){
		String NAME_PATTERN = "^[a-zA-Z]{0,20}$";
		return Pattern.compile(NAME_PATTERN).matcher(name).matches();
	}

	@Override
	public String toString() {
		String texto = this.nombre + ", con domicilio en " + this.domicilio + " (provincia: " + this.provincia + ") y ";
		if (this.tieneAuto) return texto + "tiene auto";
		else return texto + " no tiene auto";
	}

	public void add(Chofer chofer){
		this.loggeo("Se agrego -> " + chofer.toString());
	}

	public void edit(Chofer chofer){
		this.loggeo("Se modifico -> " + chofer.toString());
	}
	
	public void remove(Chofer chofer){
		this.loggeo("Se elimino -> " + chofer.toString());
	}

	public void loggeo(String texto){
		 Logger logger = Logger.getLogger("ChoferLog");  
		 FileHandler fh;  

		try {
		fh = new FileHandler("/tmp/choferes.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			logger.info(texto);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
