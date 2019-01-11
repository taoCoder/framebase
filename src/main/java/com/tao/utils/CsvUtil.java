package com.tao.utils;

import com.tao.annotation.Column;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huangtao54
 * @description csv
 * @date 2019/1/10
 */
public class CsvUtil {
    public static <T> List<String[]> getStringArray(List<T> list) throws Exception {
        if (CollectionUtils.isEmpty(list)) {
            throw new IllegalArgumentException("list is empty");
        }
        Class cls = list.get(0).getClass();
        Field[] declaredFields = cls.getDeclaredFields();
        List<Field> fields = Arrays.stream(declaredFields).
                filter(field -> field.getAnnotation(Column.class) != null)
                .collect(Collectors.toList());
        List<String> fieldNames = fields.stream().map(field -> field.getName()).collect(Collectors.toList());
        List<String[]> content = new ArrayList<>(fields.size());
        String[] header = fields.stream().map(field -> field.getAnnotation(Column.class).name()).toArray(String[]::new);
        content.add(header);

        for (T bean : list) {
            List<String> values = new ArrayList<>(fields.size());
            for (String fieldName : fieldNames) {
                PropertyDescriptor pd = new PropertyDescriptor(fieldName, cls);
                Method getter = pd.getReadMethod();
                Object value = getter.invoke(bean);
                if (value instanceof Class) {
                    continue;
                } else if (value instanceof Date) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    value = sdf.format(value);
                }
                values.add(String.valueOf(value));
            }
            content.add(values.toArray(new String[values.size()]));
        }
        return content;
    }
}
