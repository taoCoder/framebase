package com.tao.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tao.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * Created by huangtao8 on 2017/10/30.
 */
public class JacksonUtils {
    private static Logger log = LoggerFactory.getLogger(JacksonUtils.class);

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        //兼容单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        //空值不序列化
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //去掉默认时间戳格式
        //mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //序列化统一格式
        //mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //自定义处理时间反序列化
        //SimpleModule module = new SimpleModule();
        //module.addDeserializer(Date.class, new DateDeserializer());
        //mapper.registerModule(module);
    }

    public static Map<String, Object> json2Map(String json) {
        try {
            if (StringUtils.isBlank(json)) {
                return new HashMap<>();
            }
            return mapper.readValue(json, Map.class);
        } catch (Exception e) {
            log.error("json2Map 异常：json={}", json, e);
        }
        return new HashMap<>();
    }

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("转化为json异常", e);
            return null;
        }
    }

    public static <T> T parseJson(String json, Class<T> classOfT) {
        try {
            return mapper.readValue(json, classOfT);
        } catch (Exception e) {
            log.error("反序列化json为对象失败, json is {}, class is {}", json, classOfT.getName(), e);
            return null;
        }
    }

    public static <T> List<T> parseList(String json, Class<T> cls) {
        try {
            List<T> list = mapper.readValue(json,
                    mapper.getTypeFactory().constructParametricType(ArrayList.class, cls));
            return list;
        } catch (IOException e) {
            log.error("反序列化异常,json is {}", json, e);
            return null;
        }
    }

    public static <T> T parseJson(String json, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (Exception e) {
            log.error("json转化为对象异常", e);
            return null;
        }
    }

    public static void main(String[] args){
        String json ="{\"age\":0,\"date\":'2018-10-17'}";
        User user = new User();
        user.setDate(new Date());
        User a = parseJson(json, User.class);
        System.out.println(toJson(parseJson(json,User.class)));
    }
}
