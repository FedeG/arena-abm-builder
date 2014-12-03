package annotations.visualWidgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldSelector {
	public String name() default ""; // Si se requiere un nombre	
	public String choices(); //Nombre del método que le da sus opciones 
	public boolean required() default true; // Si el campo es requerido si o si
	public boolean modifiable() default true; // Si el campo se puede modificar	
}