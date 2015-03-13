package br.com.democracy.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD, ElementType.PARAMETER })
/**
 * This annotation is used to mark a field of an object to be validated. 
 * The field MUST have a default getter method.
 */
@ValidationAnnotation
public @interface Validate {
	/**
	 * Indicates the method name from ValidationHelper that must be invoked to
	 * validate this field.
	 * 
	 * @return
	 */
	public String validation() default "";

	/**
	 * The message that will be throw with the Validation Exception if this
	 * field fails in its validation.
	 * 
	 * @return
	 */
	public String message() default "";

	/**
	 * The value that is expected from the field to it be valid.
	 * 
	 * @return
	 */
	public boolean expected() default true;
}
