package annotations.abm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Title {
	String title() default "ABM";
	String addMethod() default "";
	String editMethod() default "";
	String removeMethod() default "";
}
