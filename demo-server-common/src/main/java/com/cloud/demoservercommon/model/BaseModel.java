package com.cloud.demoservercommon.model;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BaseModel {

    public BaseModel() {
    }

    public String toRequest() throws Exception {
        String request = toRequest(this);
        return leftCompWithZero(request.length(), 4).concat(request);
    }

    private String toRequest(Object o) throws Exception {
        StringBuffer request = new StringBuffer();
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fieldOrders(fields)) {
            field.setAccessible(true);
            String fieldStr = "";
            int length = field.getAnnotation(ModelField.class).length();
            TypeEnum type = field.getAnnotation(ModelField.class).type();
            switch (type) {
                case NORMAL:
                    fieldStr = (String) field.get(o);
                    break;
                case CLAZZ:
                    fieldStr = toRequest(field.get(o));
                    break;
                case STRING:
                    fieldStr = rightCompWithSpace((String) field.get(o), length);
                    break;
                case NUMBER:
                    fieldStr = leftCompWithZero(new Integer((String) field.get(o)), length);
                    break;
                case DECIMAL:
                    fieldStr = leftCompWithZero(new BigDecimal((String) field.get(o)), length);
                    break;
                default:
                    break;
            }
            request.append(fieldStr);
        }
        return request.toString();
    }

    private List<Field> fieldOrders(Field[] fields) {
        List<Field> fieldList = new ArrayList<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ModelField.class)) {
                fieldList.add(field);
            }
        }
        fieldList.sort(Comparator.comparingInt(
                f -> f.getAnnotation(ModelField.class).order()));
        return fieldList;
    }

    private String leftCompWithZero(Integer source, int formatLength) {
        return String.format("%0" + formatLength + "d", source);
    }

    private String leftCompWithZero(BigDecimal sourceDec, int formatLength) {
        Integer sourceInt = sourceDec.multiply(new BigDecimal(Math.pow(10D, 2D))).intValue();
        return String.format("%0" + formatLength + "d", sourceInt);
    }

    private String rightCompWithSpace(String source, int formatLength) {
        return String.format("%1$-" + formatLength + "s", source);
    }
}
