package com.lean.ssm.chapter2.anno;

import java.lang.reflect.Field;
import java.sql.Date;

public class SqlBuilder {
    /**
     * insert into pro(p_id, name, price, birth) values(1, 'IPhone X', 9999, '2018-2-30');
     *
     * @param object
     * @return
     */
    public static String insert(Object object) throws Exception {
        Class c = object.getClass();
        // 1. 获取表名
        String tableName = null;
        if (c.isAnnotationPresent(Table.class)) {
            Table table = (Table) c.getAnnotation(Table.class);
            tableName = table.value();
        }
        // 2. 获取列名
        StringBuffer fieldNames = new StringBuffer();
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                fieldNames.append(column.value()).append(",");
            } else {
                fieldNames.append(field.getName()).append(",");
            }
        }
        fieldNames.deleteCharAt(fieldNames.length() - 1);

        // 3. 获取值列表
        StringBuffer fieldValues = new StringBuffer();
        for (Field field : fields) {
            field.setAccessible(true);
            Object v = field.get(object);
            if (v.getClass() == String.class || v.getClass() == Date.class) {
                fieldValues.append("\"").append(v).append("\"").append(",");
            } else {
                fieldValues.append(v).append(",");
            }
        }
        fieldValues.deleteCharAt(fieldValues.length() - 1);
        return String.format("insert into %s (%s) values(%s)", tableName, fieldNames, fieldValues);
    }

    /**
     * delete from pro where p_id = 1
     *
     * @param object
     * @return
     */
    public static String delete(Object object) throws Exception {
        // 1. 获取表名
        Class c = object.getClass();
        String tableName = null;
        if (c.isAnnotationPresent(Table.class)) {
            Table table = (Table) c.getAnnotation(Table.class);
            tableName = table.value();
        } else {
            tableName = c.getSimpleName();
        }
        // 2. 获取主键名
        String primaryId = null;
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                if (column.value().contains("id")) {
                    primaryId = column.value();
                }
            }
        }
        // 3. 获取列的值
        String fieldValue = null;
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().contains("id")) {
                Object v = field.get(object);
                fieldValue = v.toString();
            }
        }
        return String.format("delete from %s where %s = %s", tableName, primaryId, fieldValue);
    }

    /**
     * update pro set name = "电视机",price = 2100,birth = "2018-1-1" where p_id = 1
     *
     * @return
     */
    public static String update(Object object) throws Exception {
        // 1. 获取表名
        String tableName = null;
        Class c = object.getClass();
        if (c.isAnnotationPresent(Table.class)) {
            Table table = (Table) c.getAnnotation(Table.class);
            tableName = table.value();
        } else {
            tableName = c.getSimpleName();
        }
        // 2. 获取修改字段以及修改的值
        Field[] fields = c.getDeclaredFields();
        StringBuffer stringBuffer1 = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                Object v = field.get(object);
                if (!field.getName().contains("id")) {
                    if (v.getClass() == String.class || v.getClass() == Date.class) {
                        stringBuffer1.append(column.value()).append(" = ").append("\"").append(v).append("\"").append(",");
                    } else {
                        stringBuffer1.append(column.value()).append(" = ").append(v).append(",");
                    }
                } else {
                    stringBuffer2.append(column.value()).append(" = ").append(v).append(",");
                }
            } else {
                Object v = field.get(object);
                if (!field.getName().contains("id")) {
                    if (v.getClass() == String.class || v.getClass() == Date.class) {
                        stringBuffer1.append(field.getName()).append(" = ").append("\"").append(v).append("\"").append(",");
                    } else {
                        stringBuffer1.append(field.getName()).append(" = ").append(v).append(",");
                    }
                } else {
                    stringBuffer2.append(field.getName()).append(" = ").append(v).append(",");
                }
            }
        }
        stringBuffer1.deleteCharAt(stringBuffer1.length()-1);
        stringBuffer2.deleteCharAt(stringBuffer2.length()-1);
        return String.format("update %s set %s where %s", tableName, stringBuffer1, stringBuffer2);
    }

    /**
     * select * from pro
     *
     * @param object
     * @return
     */
    public static String select(Object object) {
        // 1. 获取列名
        Class c = object.getClass();
        StringBuffer stringBuffer = new StringBuffer();
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                stringBuffer.append(column.value()).append(",");
            }else{
                stringBuffer.append(field.getName()).append(",");
            }
        }
        stringBuffer.deleteCharAt(stringBuffer.length()-1);
        // 2. 获取表名
        String tableName = null;
        if (c.isAnnotationPresent(Table.class)){
            Table table = (Table)c.getAnnotation(Table.class);
            tableName = table.value();
        }
        return String.format("select %s from %s",stringBuffer,tableName);
    }
}
