package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PrimitiveAnnotations {
	boolean validator() default true; // Si es valido o no, mediante un validador
	boolean required() default true; // Si el campo es requerido si o si
	boolean modifiable() default true; // Si el campo se puede modificar
	boolean inTable() default true; // Si se muestra en la tabla de datos
	boolean canFilter() default true; // Si se puede filtrar por este campo 
}
