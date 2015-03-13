package br.com.democracy.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
/** This annotation must be used to validate a field of Collection Type (lists, sets...). 
 */
@ValidationAnnotation
public @interface ValidateCollection {
	/**
	 * If the field under validation is a Collection of simple type, use this
	 * property to validate it.
	 */
	public ValidationGroup validationGroup() default @ValidationGroup(validations = {});

	/**
	 * If the field under validation is a Collection of a composite Object (an
	 * object with fields annoteds with any Validation annotation), them set the
	 * value of this property to true. Doing this, the validationGroup property
	 * will be ignored.
	 */
	public boolean recursive() default false;
	
	public boolean nullable() default false;
}
