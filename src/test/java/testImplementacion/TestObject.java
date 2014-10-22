package testImplementacion;

import annotations.abm.Title;
import annotations.visualWidgets.FieldCheck;
import implementation.ObjectABM;

@SuppressWarnings("serial")
@Title(title="Autos ABM")
public class TestObject extends ObjectABM {

	
	@FieldCheck
	public boolean descapotable;
	
	
	public boolean isDescapotable() {
		return descapotable;
	}

	public void setDescapotable(boolean descapotable) {
		this.descapotable = descapotable;
	}
	

}
