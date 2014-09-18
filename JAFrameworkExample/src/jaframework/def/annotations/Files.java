package jaframework.def.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jaframework.def.annotations.File;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Files
{
	File[] value();
}
