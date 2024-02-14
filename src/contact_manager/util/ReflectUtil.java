package contact_manager.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectUtil {

	public static Object invokeGetter(Object object, String variableName) {
		Class<?> cls = object.getClass();
		Field field = findField(object, variableName);
		
		if (field != null) {
			String methodName = makeMethodName(variableName, "get");
			Method getter = findMethod(cls, methodName, null);
			
			if (getter != null) {
				try {
					return getter.invoke(object);
				} catch (IllegalAccessException | InvocationTargetException e) {
					System.out.println(e.getMessage());
					return null;
				}
			}
		}
		
		return null;
	}
	
	public static void invokeSetter(Object object, String variableName, Object value) {
		Class<?> cls = object.getClass();
		Field field = findField(object, variableName);
		
		if (field != null) {
			String methodName = makeMethodName(variableName, "set");
			Method setter = findMethod(cls, methodName, field);
			
			if (setter != null) {
				try {
					setter.invoke(object, value);
				} catch (IllegalAccessException | InvocationTargetException e) {
					System.out.println(e.getMessage());
					return;
				}
			}
		}
	}
	
	private static Field findField(Object object, String variablename) {
		Class<?> cls = object.getClass();
		Field field = null;
		try {
			field = cls.getDeclaredField(variablename);
		} catch (NoSuchFieldException | SecurityException e) {
			System.out.println(e.getMessage());
			return null;
		}
		
		return field;
	}
	
	private static Method findMethod(Class<?> cls, String methodName, Field field) {
		Method method = null;
		try {
			if (methodName.startsWith("get")) {
				method = cls.getDeclaredMethod(methodName);
			}
			else if (methodName.startsWith("set")) {
				method = cls.getDeclaredMethod(methodName, field.getType());
			}
		} catch (NoSuchMethodException | SecurityException e) {
			System.out.println(e.getMessage());
			return null;
		}
		
		return method;
	}
	
	private static String makeMethodName(String memberVariableName, String prefix) {
		String firstLetter = (memberVariableName.charAt(0) + "").toUpperCase();
		String capitalVariableName = firstLetter + memberVariableName.substring(1);
		return prefix + capitalVariableName;
	}
	
}















