package annotations.visualWidgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldText {
	public String name() default ""; // Si se requiere un nombre
	public String validator() default ""; //Nombre del método validador
	public boolean required() default true; // Si el campo es requerido si o si
	public boolean modifiable() default true; // Si el campo se puede modificar	
	public boolean canFilter() default true; // Si se puede filtrar por este campo

}
