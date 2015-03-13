package br.com.democracy.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import br.com.democracy.exception.ValidationException;
import br.com.democracy.helper.ValidationHelper;
import br.com.democracy.validation.annotation.NotEmpty;
import br.com.democracy.validation.annotation.Recursive;
import br.com.democracy.validation.annotation.Validate;
import br.com.democracy.validation.annotation.ValidateCollection;
import br.com.democracy.validation.annotation.ValidationAnnotation;
import br.com.democracy.validation.annotation.ValidationGroup;

public class Validator {

	/**
	 * private constructor to make it an static class
	 */
	private Validator() {
	}

	/**
	 * Gets all the fields from a class, even the inherited ones.
	 * 
	 * @param fields
	 * @param type
	 * @return
	 */
	private static List<Field> getAllFields(List<Field> fields, Class<?> type) {
		for (Field field : type.getDeclaredFields()) {
			fields.add(field);
		}

		if (type.getSuperclass() != null) {
			fields = getAllFields(fields, type.getSuperclass());
		}

		return fields;
	}

	/**
	 * Build the default getter name for a field.
	 * 
	 * @param field
	 * @return
	 */
	private static String getFieldGetterName(Field field) {
		String name = field.getName();
		String firstPart = String.valueOf(name.charAt(0)).toUpperCase();
		String secondPart = name.length() > 0 ? name
				.substring(1, name.length()) : "";
		return "get" + firstPart + secondPart;
	}

	/**
	 * Gets the field value of an object.
	 * 
	 * @param <T>
	 * @param field
	 * @param instance
	 * @return
	 */
	private static <T> Object getFieldValue(Field field, T instance) {
		String getName = getFieldGetterName(field);
		try {
			Method method = instance.getClass().getMethod(getName);

			if (method == null) {
				throw new Error("Getter for the field " + field.getName()
						+ " was not found.");
			}

			Object o = method.invoke(instance);

			return o;

		} catch (Exception e) {
			throw new Error(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Method used to validate a field.
	 * 
	 * @param <T>
	 * @param methodName
	 * @param field
	 * @param instance
	 * @param message
	 * @param recursive
	 * @throws ValidationException
	 */
	private static <T> void validateField(Annotation annotation, Field field,
			T instance) throws ValidationException {
		try {

			// gets the field of the value that is under validation.
			Object fieldValue = getFieldValue(field, instance);

			// encapsulates the data necessary by the validation
			// The validation object must be populated in each validation type
			// and them invokeValidation() must be called.
			Validation validation = new Validation();
			validation.setAnnotation(annotation);
			validation.setParams(null);

			// gets the class type of the annotation
			Class<?> type = annotation.annotationType();

			// the following block of code is to deal with each type of
			// Validation. If necessary, new types can be added here
			if (type == Validate.class) { // the Validate type
				simpleValidation(annotation, field, instance, fieldValue,
						validation);

			} else if (type == NotEmpty.class) { // the NotEmpty type
				NotEmpty notEmpty = (NotEmpty) annotation;
				validation.setMessage(notEmpty.message());
				validation.setValidationMethod(NotEmpty.validationMethod);
				validation.setExpected(NotEmpty.expected);
				invokeValidation(field.getType(), fieldValue, validation);

			} else if (type == Recursive.class) { // the Recursive type
				validate(fieldValue);

			} else if (type == ValidateCollection.class) {
				ValidateCollection validateCollection = (ValidateCollection) annotation;

				if (validateCollection.recursive()) {
					if (fieldValue != null) {
						if (fieldValue instanceof Collection<?>) {
							Collection<?> c = (Collection<?>) fieldValue;
							for (Object o : c) {
								validate(o);
							}
						} else {
							throw new Error(
									"The field "
											+ field.getName()
											+ " is annoted with ValidateCollection but is not of Collection type.");
						}
					} else if (!validateCollection.nullable()) {
						throw new Error(
								"The field "
										+ field.getName()
										+ " is annoted with ValidateCollection but is null.");
					}
				} else {
					if (fieldValue instanceof Collection<?>) {
						Collection<?> c = (Collection<?>) fieldValue;

						Validate[] validates = validateCollection
								.validationGroup().validations();

						if (validates.length == 0) {
							throw new Error("The field " + field.getName()
									+ " doesn`t has any Validate associated.");
						}

						for (Validate validate : validates) {
							for (Object o : c) {
								validation.setMessage(validate.message());
								validation.setValidationMethod(validate
										.validation());
								validation.setExpected(validate.expected());
								invokeValidation(o.getClass(), o, validation);
							}
						}

					} else {
						throw new Error(
								"The field "
										+ field.getName()
										+ "is annoted with ValidateCollection but is not of Collection type.");
					}
				}
			} else {
				throw new Error(
						"The validation annotation is not been treated.");
			}

		} catch (ValidationException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Error(e.getMessage(), e.getCause());
		}
	}

	private static <T> void simpleValidation(Annotation annotation,
			Field field, T instance, Object fieldValue, Validation validation)
			throws NoSuchMethodException, Error, IllegalAccessException,
			InvocationTargetException, ValidationException {
		Validate validate = (Validate) annotation;
		validation.setMessage(validate.message());
		validation.setValidationMethod(validate.validation());
		validation.setExpected(validate.expected());
		invokeValidation(field.getType(), fieldValue, validation);
	}

	/**
	 * Invokes the validation method from ValidationHelper.
	 * 
	 * @param <T>
	 * @param field
	 * @param fieldValue
	 * @param validation
	 * @throws NoSuchMethodException
	 * @throws Error
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ValidationException
	 */
	private static <T> void invokeValidation(Class<?> fieldType,
			Object fieldValue, Validation validation)
			throws NoSuchMethodException, Error, IllegalAccessException,
			InvocationTargetException, ValidationException {

		// extracts the validation method
		Method method = ValidationHelper.class.getMethod(
				validation.getValidationMethod(), fieldType);

		// if the method is null, it doesnt exist, so an error is throw.
		if (method == null) {
			throw new Error("Method " + validation.getValidationMethod()
					+ "() was not found in class ValidationHelper.");
		}

		// construct the params array
		Object[] params = constructParams(fieldValue, validation.getParams());

		// invoke the validation method
		Object o = method.invoke(null, params);

		// the returned object must be of Boolean type
		if (o instanceof Boolean) {

			Boolean value = (Boolean) o;
			// if not equals from the expected value a ValidationExpection must
			// be throw with the given message.
			if (!validation.getExpected().equals(value)) {
				throw new ValidationException(validation.getMessage());
			}
		} else {
			throw new Error("The method \"" + validation.getValidationMethod()
					+ "\" returned something not equals than boolean.");
		}
	}

	/**
	 * Creates the params array to be invoked.
	 * 
	 * @param fieldValue
	 * @param params
	 * @return
	 */
	private static Object[] constructParams(Object fieldValue, Object[] params) {
		List<Object> ret = new ArrayList<Object>();

		ret.add(fieldValue);
		if (params != null) {
			for (Object o : params) {
				ret.add(o);
			}
		}

		return ret.toArray();
	}

	/**
	 * Validates the fields annotated with the Validate annotation of an object.
	 * Throws ValidateException for any field that fails the validation. If
	 * anything goes wrong, an Error is launched, so pay atention at the Console
	 * for these errors.
	 * 
	 * @param <T>
	 * @param obj
	 * @throws ValidationException
	 */
	public static <T> void validate(T obj) throws ValidationException {
		List<Field> fields = new ArrayList<Field>();

		// gets all the fields of this object
		fields = getAllFields(fields, obj.getClass());

		// iterates through all the fields to check its validations
		for (Field field : fields) {
			Annotation[] annotations = field.getDeclaredAnnotations();
			if (annotations == null || annotations.length <= 0) {
				continue;
			}

			List<Annotation> validateAnnotations = new LinkedList<Annotation>();

			// constructs the list with the validation annotations
			for (Annotation a : annotations) {

				// if the annotation is not annoted with the ValidateType, the
				// annotation is not a validation annotation.
				if (!a.annotationType().isAnnotationPresent(
						ValidationAnnotation.class)) {
					continue;
				}

				// if the annotation is a ValidationGroup, all its inner
				// Validates must be added in the validation list.
				if (a.annotationType().equals(ValidationGroup.class)) {
					ValidationGroup group = (ValidationGroup) a;

					for (Annotation v : group.validations()) {
						validateAnnotations.add(v);
					}
				} else {
					validateAnnotations.add(a);
				}
			}

			// validates all annotations
			for (Annotation a : validateAnnotations) {
				// validate the field
				validateField(a, field, obj);
			}
		}
	}
}
