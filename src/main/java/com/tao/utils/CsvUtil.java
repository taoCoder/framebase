package com.tao.utils;

import com.tao.annotation.Column;
import com.tao.common.DataFormatter;
import com.tao.domain.CsvVo;
import org.springframework.util.CollectionUtils;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author huangtao54
 * @description csv
 * @date 2019/1/10
 */
public class CsvUtil {
    public static <T> List<String[]> getContent(List<T> list) throws Exception {
        if (CollectionUtils.isEmpty(list)) {
            throw new IllegalArgumentException("list is empty");
        }
        Class cls = list.get(0).getClass();
        Field[] declaredFields = cls.getDeclaredFields();
        List<Field> fields = new ArrayList<>();
        List<String[]> content = new ArrayList<>(fields.size());
        List<String> header = new ArrayList<>(fields.size());
        Map<String, DataFormatter> formatterMap = new HashMap<>(fields.size());
        for (Field field : declaredFields) {
            if (field.getAnnotation(Column.class) != null) {
                fields.add(field);
                header.add(field.getAnnotation(Column.class).name());
                formatterMap.put(field.getName(), field.getAnnotation(Column.class).formatter().newInstance());
            }
        }
        content.add(header.toArray(new String[fields.size()]));

        for (T bean : list) {
            List<String> values = new ArrayList<>(fields.size());
            for (Field field : fields) {
                String fieldName = field.getName();
                PropertyDescriptor pd = new PropertyDescriptor(fieldName, cls);
                Method getter = pd.getReadMethod();
                Object value = getter.invoke(bean);
                if (value instanceof Class) {
                    continue;
                } else {
                    DataFormatter df = formatterMap.get(fieldName);
                    value = df.format(value);
                }
                values.add(String.valueOf(value));
            }
            content.add(values.toArray(new String[values.size()]));
        }
        return content;
    }

    public static void main(String[] args) throws Exception {
        List<CsvVo> list = new ArrayList<>();
        list.add(new CsvVo(1, "tao1", new Date(), "no1"));
        list.add(new CsvVo(2, "tao2", new Date(), "no2"));
        List<String[]> content = CsvUtil.getContent(list);
        System.out.println(content);
    }
}
