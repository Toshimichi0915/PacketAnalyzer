package net.toshimichi.packetanalyzer.gui;

import org.apache.commons.lang.ArrayUtils;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class PacketTableModel extends AbstractTableModel {
    private final Object packet;
    private final ArrayList<Field> fields;

    public PacketTableModel(Object packet) {
        this.packet = packet;
        fields = getFields(packet.getClass(), new ArrayList<>());
    }

    private ArrayList<Field> getFields(Class<?> clazz, ArrayList<Field> list) {
        Class<?> superClass = clazz.getSuperclass();
        list.addAll(Arrays.asList(clazz.getDeclaredFields()));
        if (superClass.equals(Object.class)) return list;
        return getFields(superClass, list);
    }

    @Override
    public int getRowCount() {
        int count = 0;
        for (Field field : fields) {
            Object obj;
            field.setAccessible(true);
            try {
                obj = field.get(packet);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                continue;
            }
            if (obj instanceof Object[]) count += ((Object[]) obj).length + 1;
            else if (obj instanceof byte[]) count += ((byte[]) obj).length;
            else if (obj instanceof short[]) count += ((short[]) obj).length;
            else if (obj instanceof int[]) count += ((int[]) obj).length;
            else if (obj instanceof long[]) count += ((long[]) obj).length;
            else if (obj instanceof float[]) count += ((float[]) obj).length;
            else if (obj instanceof double[]) count += ((double[]) obj).length;
            else if (obj instanceof boolean[]) count += ((boolean[]) obj).length;
            else if (obj instanceof char[]) count += ((char[]) obj).length;
            else count++;
        }
        return count;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "フィールド";
            case 1:
                return "型";
            case 2:
                return "値";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Field field = null;
        String fieldName = null;
        Object obj = null;
        int index = 0;
        for (Field value : fields) {
            field = value;
            fieldName = field.getName();
            field.setAccessible(true);
            try {
                obj = field.get(packet);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                continue;
            }

            if (obj instanceof byte[]) obj = ArrayUtils.toObject((byte[]) obj);
            else if (obj instanceof short[]) obj = ArrayUtils.toObject((short[]) obj);
            else if (obj instanceof int[]) obj = ArrayUtils.toObject((int[]) obj);
            else if (obj instanceof long[]) obj = ArrayUtils.toObject((long[]) obj);
            else if (obj instanceof float[]) obj = ArrayUtils.toObject((float[]) obj);
            else if (obj instanceof double[]) obj = ArrayUtils.toObject((double[]) obj);
            else if (obj instanceof boolean[]) obj = ArrayUtils.toObject((boolean[]) obj);
            else if (obj instanceof char[]) obj = ArrayUtils.toObject((char[]) obj);

            if (obj instanceof Object[]) {
                if (index == rowIndex) {
                    fieldName = fieldName + " (長さ: " + ((Object[]) obj).length + ")";
                    break;
                }
                int arrayIndex = rowIndex - index - 1;
                if (arrayIndex < ((Object[]) obj).length) {
                    fieldName = fieldName.replace("[]", "") + "[" + arrayIndex + "]";
                    obj = ((Object[]) obj)[arrayIndex];
                    break;
                } else {
                    index += ((Object[]) obj).length + 1;
                }
            } else {
                if (index == rowIndex)
                    break;
                index++;
            }
        }

        if (field == null) return null;

        switch (columnIndex) {
            case 0:
                return fieldName;
            case 1:
                if (obj == null) return field.getType().getSimpleName();
                else return obj.getClass().getSimpleName();
            case 2:
                if (obj == null) return "null";
                else return obj;
            default:
                return null;
        }
    }
}
