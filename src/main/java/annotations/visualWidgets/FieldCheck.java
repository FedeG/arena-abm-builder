package annotations.visualWidgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldCheck {
	public String name() default ""; // Si se requiere un nombre
	public boolean modifiable() default true; // Si el campo se puede modificar	
}
