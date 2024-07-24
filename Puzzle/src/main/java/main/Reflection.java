package main;

import java.lang.reflect.Field;

	/*
		Reflection is a utility class that provides methods to retrieve metadata
		and values of fields from an object using Java Reflection.
 	*/
public class Reflection {

	/*
	  Retrieves the number of fields in the given object's class.
	 */
	public static int retrieveNumberOfFields(Object object) {
		int noFields = 0;
		for (Field field : object.getClass().getDeclaredFields()) {
			noFields++;
		}
		return noFields;
	}

	/*
	 Retrieves the names of all fields in the given object's class.
	 */
	public static String[] retrieveFieldNames(Object object) {
		String[] fieldNames = new String[retrieveNumberOfFields(object)];
		int i = 0;
		for (Field field : object.getClass().getDeclaredFields()) {
			//field.setAccessible(true); // set modifier to public
			String name;
			try {
				name = field.getName();
				fieldNames[i] = name;
				i++;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		return fieldNames;
	}

	/*
	 Retrieves the values of all fields in the given object.
	 */
	public static Object[] retrieveFieldValues(Object object) {
		Object[] values = new Object[retrieveNumberOfFields(object)];
		int i = 0;
		for (Field field : object.getClass().getDeclaredFields()) {
			//field.setAccessible(true); // set modifier to public
			Object value;
			try {
				value = field.get(object);
				values[i] = value;
				i++;
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return values;
	}
}
