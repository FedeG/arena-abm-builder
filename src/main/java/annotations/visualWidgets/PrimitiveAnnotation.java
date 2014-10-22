package annotations.visualWidgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PrimitiveAnnotation {
	public String name() default ""; // Si se requiere un nombre
	public boolean validator() default true; // Si es valido o no mediante un validador
	public boolean required() default true; // Si el campo es requerido si o si
	public boolean modifiable() default true; // Si el campo se puede modificar
	public boolean inTable() default true; // Si se muestra en la tabla de datos
	public boolean canFilter() default true; // Si se puede filtrar por este campo
}
