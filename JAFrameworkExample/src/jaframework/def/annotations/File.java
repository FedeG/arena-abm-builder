package jaframework.def.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface File
{
	String name();
	String alias() default "";
	char fieldSeparator() default ',';
	String newLineSeparator() default "\r\n";
	Index index() default @Index(key="",alias="");
	Index[] indexes() default{};
	
	
}
