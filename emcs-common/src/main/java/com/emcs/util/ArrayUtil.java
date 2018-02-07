package com.emcs.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;

public final class ArrayUtil {
    public static String[] NULL_STRINGS = new String[0];
    public static Object[] NULL_OBJECTS = new Object[0];
    public static Class[] NULL_CLASSES = new Class[0];

    /**
     * 复制数组
     * @param sourceObjects
     * @param targetObjects
     * @throws IllegalArgumentException
     */
    public static void copyArray(Object[] sourceObjects, Object[] targetObjects) throws IllegalArgumentException {
        if(!ArrayUtils.isEmpty(sourceObjects) && !ArrayUtils.isEmpty(targetObjects)) {
            if(sourceObjects.length != targetObjects.length) {
                throw new IllegalArgumentException("The length of the two arrays must be equal");
            } else {
                System.arraycopy(sourceObjects, 0, targetObjects, 0, targetObjects.length);
            }
        }
    }

    public static String[] getStringArrayValues(Object value) {
        if(!(value instanceof Collection)) {
            if(value instanceof Object[]) {
                Object[] var5 = (Object[])((Object[])value);
                return getStringArrayValues(var5);
            } else {
                return NULL_STRINGS;
            }
        } else {
            String[] objects = new String[((Collection)value).size()];
            Iterator iterator = ((Collection)value).iterator();

            for(int index = 0; iterator.hasNext(); ++index) {
                Object t_Object = iterator.next();
                objects[index] = ObjectUtils.toString(t_Object, (String)null);
            }

            return objects;
        }
    }

    public static String[] getStringArrayValues(Object[] value) {
        if(ArrayUtils.isEmpty(value)) {
            return NULL_STRINGS;
        } else {
            String[] stringArray = new String[value.length];

            for(int i = 0; i < value.length; ++i) {
                Object object = value[i];
                stringArray[i] = ObjectUtils.toString(object, (String)null);
            }

            return stringArray;
        }
    }

    public static Object[] getArrayValues(Object value) {
        return value instanceof Object[]?(Object[])((Object[])value):(value instanceof Collection?((Collection)value).toArray():null);
    }

    public static boolean hasType(Object[] objects, Class clazz) {

        return hasType(objects, clazz, false);
    }

    public static boolean hasType(Object[] objects, Class clazz, boolean allowSuperType) {
        if(null == clazz) {
            return false;
        } else if(ArrayUtils.isEmpty(objects)) {
            return false;
        } else {
            for(int i = 0; i < objects.length; ++i) {
                Object object = objects[i];
                if(null != object) {
                    if(allowSuperType) {
                        if(clazz.isAssignableFrom(object.getClass())) {
                            return true;
                        }
                    } else if(clazz == object.getClass()) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static Object[] sort(Object[] array) {
        if(ArrayUtils.isEmpty(array)) {
            return array;
        } else {
            Arrays.sort(array);
            return array;
        }
    }

    public static boolean isArray(Object r_Object) {
        if(null == r_Object) {
            return false;
        } else {
            Class t_Class = r_Object.getClass();
            return t_Class.isArray();
        }
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }
}
