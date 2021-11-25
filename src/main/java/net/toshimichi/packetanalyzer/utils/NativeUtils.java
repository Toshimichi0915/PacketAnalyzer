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

    public Object getValue(Object o, String... fieldNames) {
        for (String fieldName : fieldNames) {
            try {
                Field f = o.getClass().getField(fieldName);
                boolean accessible = f.isAccessible();
                f.setAccessible(true);
                Object result = f.get(o);
                f.setAccessible(accessible);
                return result;
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // maybe next one will do
            }
        }
        throw new IllegalArgumentException("Unknown field name(s): " + Arrays.toString(fieldNames));
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
