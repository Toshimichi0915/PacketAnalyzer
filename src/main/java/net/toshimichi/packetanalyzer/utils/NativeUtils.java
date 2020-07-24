package net.toshimichi.packetanalyzer.utils;

import org.bukkit.Bukkit;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class NativeUtils {

    public String getVersion() {
        Class<?> clazz = Bukkit.getServer().getClass();
        return clazz.getPackage().getName().split("\\.")[3];
    }

    public Class<?> getClass(String str) {
        str = str.replace("{version}", getVersion());
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Field getField(Object o, String fieldName) {
        Class<?> c = o.getClass();
        try {
            return c.getField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public Object getValue(Object o, String fieldName) {
        try {
            return getField(o, fieldName).get(o);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void setValue(Object o, String fieldName, Object value) {
        Field field = getField(o, fieldName);
        try {
            field.set(o, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Object invoke(Object o, String methodName, Object... args) {
        Class<?>[] classes = Arrays.stream(args)
                .map(Object::getClass)
                .toArray(Class[]::new);
        try {
            Method method = o.getClass().getMethod(methodName, classes);
            return method.invoke(o, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
