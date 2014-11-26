package annotations.abm;

import java.util.regex.Pattern;

import implementation.FWObject;

@SuppressWarnings("serial")
public class Validator extends FWObject {

	private String redex;

	public Boolean validateUserName(String text){
		this.redex = "^[a-z0-9_-]{0,15}$";
		return executeValidator(text);
	}
	
	public Boolean validateNotNumbersUnlimited(String text){
		this.redex = "^[a-zA-Z]*$";
		return executeValidator(text);
	}
	
	public Boolean validateNotNumbersLimited(String text){
		this.redex = "^[a-zA-Z]{0,30}$";
		return executeValidator(text);
	}
	
	public Boolean validateNumbers(String text){
		this.redex = "^[0-9]*$";
		return executeValidator(text);
	}
	
	private Boolean executeValidator(String text) {
		return Pattern.compile(this.redex).matcher(text).matches();
	}

}
