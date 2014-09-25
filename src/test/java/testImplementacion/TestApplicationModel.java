package testImplementacion;

import implementation.ABMapplicationModel;
import annotations.visualWidgets.FieldList;
import annotations.visualWidgets.FieldText;

@SuppressWarnings("serial")
public class TestApplicationModel extends ABMapplicationModel{

	@FieldText()
	public String nombre;
	
	@FieldText()
	private String apellido;
	
	@FieldList()
	private Integer nota;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}


}
