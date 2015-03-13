package br.com.democracy.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD, ElementType.PARAMETER })
@ValidationAnnotation
/**
 * Indicates that a field cannot be Empty or Null
 */
public @interface NotEmpty {
	public String validationMethod = "isEmptyOrVoid";

	public boolean expected = false;

	/**
	 * The message which will be throw with the Validation Exception if this
	 * field fails in its validation.
	 * 
	 * @return
	 */
	public String message() default "";
}
