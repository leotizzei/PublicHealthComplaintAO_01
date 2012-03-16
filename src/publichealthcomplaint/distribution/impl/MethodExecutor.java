package publichealthcomplaint.distribution.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Executes methods based on reflection.
 * All possible primitive types are used
 * 
 * @author Thiago Tonelli Bartolomei <thiago.bartolomei@gmail.com>
 */
class MethodExecutor {

	synchronized static Object invoke(Object target, String methodName, Object[] params) {
		if( (target == null) || (methodName == null) || (params == null)  ){
			if( target == null )
				System.err.println("[MethodExecutor:invoke()] target = null");
			if( methodName == null )
				System.err.println("[MethodExecutor:invoke()] methodName = null");
			if( params == null )
				System.err.println("[MethodExecutor:invoke()] params = null");
			throw new IllegalArgumentException("null parameter has been passed to MethodExecutor:invoke()");
		}
		try {


			Method m = findMethod(target.getClass().getDeclaredMethods(), methodName, params);
			if (m == null) {
				System.out.println("[MethodExecutor:invoke()] m is null");
				m = findMethod(target.getClass().getMethods(), methodName, params);
			}

			if( (target == null) || (params == null))
				System.err.println("[MethodExecutor:invoke()] Either target or params is null");
			else{
				int k;
				System.out.println("[MethodExecutor:invoke()] target = "+target.getClass().getCanonicalName());
				for(k = 0; k < params.length; k++){
					System.out.println("[MethodExecutor:invoke()] params ["+k+"] = "+params[k].toString());
				}
			}

			System.out.println("[MethodExecutor:invoke()] returning m="+m.getName());
			return m.invoke(target, params);

		} catch (IllegalAccessException e) {
			System.err.println("IllegalAccessException:" + e.getLocalizedMessage());
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			System.err.println("InvocationTargetException:" + e.getLocalizedMessage());
			Throwable throwableExc = e.getTargetException();
			if( throwableExc != null){
				Throwable cause = throwableExc.getCause();
				if( cause != null)
					System.err.println("Cause: " + cause.getLocalizedMessage());
			}
			e.printStackTrace();
			return null;
		} catch(Exception e){
			System.err.println("Exception:" + e.getLocalizedMessage());
			e.printStackTrace();
			return null;
		}
	}

	private static Method findMethod(Method[] methods, String name, Object[] params) {

		if( (methods == null) || (name == null) || (params == null) ){
			System.err.println("[MethodExecutor:findMethod()] Null parameter");
			throw new IllegalArgumentException("Null parameter");
		}

		for (int i = 0; i < methods.length; i++) {
			if (compareMethod(methods[i], name, params)) {
				return methods[i];
			}
		}
		return null;

	}

	private static boolean compareMethod(Method method, String name, Object[] params) {

		if (! name.equals(method.getName()) || method.getParameterTypes().length != params.length) {
			return false;
		}

		Class[] types = method.getParameterTypes();
		for(int i = 0; i < types.length; i++) {
			if (! isCompatible(types[i], params[i].getClass())) {
				return false;
			}
		}

		return true;
	}

	private static boolean isCompatible(Class type, Class param) {
		if (type.equals(param) ||
				toPrimitive(type).equals(toPrimitive(param)) ||
				checkSuperTypes(type, param)) {
			return true;
		}
		return false;
	}

	protected static Class toPrimitive(Class type) {
		if (type.getName().equals("java.lang.Integer")) {
			return int.class;
		} else if (type.getName().equals("java.lang.Char")) {
			return char.class;
		} else if (type.getName().equals("java.lang.Byte")) {
			return byte.class;
		} else if (type.getName().equals("java.lang.Short")) {
			return short.class;
		} else if (type.getName().equals("java.lang.Long")) {
			return long.class;
		} else if (type.getName().equals("java.lang.Float")) {
			return float.class;
		} else if (type.getName().equals("java.lang.Double")) {
			return double.class;
		} else if (type.getName().equals("java.lang.Boolean")) {
			return boolean.class;
		}
		return type;
	}

	private static boolean checkSuperTypes(Class type, Class param) {
		

		if( type == null){
			throw new IllegalArgumentException("Null argument");
		}
		if( param == null){
			throw new IllegalArgumentException("Null argument");
		}
		
		
		if (! type.equals(Object.class)) {
			

			if (type.equals(param)) {
				return true;
			}
			else{
				if(param != null){
					Class[] interfaces = param.getInterfaces();
					if( interfaces != null ){
						int i;
						for( i = 0 ; i < interfaces.length; i++ ){
							if(type.equals(interfaces[i])){
								return true;
							}
						}
					}
				}
			}

			return checkSuperTypes(type, param.getSuperclass());
		}
		
		return false;
	}
}
