package com.tao.wrapper;

import com.tao.utils.JacksonUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author huangtao54
 * @description request处理
 * @date 2018/10/17
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper {
    private Map<String, String[]> params = new HashMap<String, String[]>();


    public ParameterRequestWrapper(HttpServletRequest request) {
        // 将request交给父类，以便于调用对应方法的时候，将其输出，其实父亲类的实现方式和第一种new的方式类似
        super(request);
        //将参数表，赋予给当前的Map以便于持有request中的参数
        //网关接口取body中参数
        String body = request.getParameter("body");
        if (StringUtils.isNotEmpty(body)) {
            Map<String, Object> map = JacksonUtils.json2Map(body);
            addAllParameters(map);
        }
        String clientVersion = request.getParameter("version");
        addParameter("clientVersion",clientVersion);
        this.params.putAll(request.getParameterMap());
    }

    public ParameterRequestWrapper(HttpServletRequest request, Map<String, Object> extendParams) {
        this(request);
        //这里将扩展参数写入参数表
        addAllParameters(extendParams);
    }

    /**
     * 复写获取key的方法
     *
     * @return
     */
    @Override
    public Enumeration getParameterNames() {
        Vector names = new Vector(params.keySet());
        return names.elements();
    }

    /**
     * 复写获取值value的方法
     */
    @Override
    public String getParameter(String name) {
        Object v = params.get(name);
        if (v == null) {
            return null;
        } else if (v instanceof String[]) {
            String[] strArr = (String[]) v;
            if (strArr.length > 0) {
                return strArr[0];
            } else {
                return null;
            }
        } else if (v instanceof String) {
            return (String) v;
        } else {
            return v.toString();
        }
    }

    @Override
    public String[] getParameterValues(String name) {
        Object v = params.get(name);
        if (v == null) {
            return null;
        } else if (v instanceof String[]) {
            return (String[]) v;
        } else if (v instanceof String) {
            return new String[]{(String) v};
        } else {
            return new String[]{v.toString()};
        }
    }

    public void addAllParameters(Map<String, Object> otherParams) {//增加多个参数
        for (Map.Entry<String, Object> entry : otherParams.entrySet()) {
            addParameter(entry.getKey(), entry.getValue());
        }
    }


    public void addParameter(String name, Object value) {//增加参数
        if (value != null) {
            if (value instanceof String[]) {
                params.put(name, (String[]) value);
            } else if (value instanceof String) {
                params.put(name, new String[]{(String) value});
            } else {
                params.put(name, new String[]{String.valueOf(value)});
            }
        }
    }

    /**
     * 简单封装，请根据需求改进
     */
    public void addObject(Object obj) {
        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getMethods();
        try {
            for (Method method : methods) {
                if (!method.getName().startsWith("get")) {
                    continue;
                }
                Object invoke = method.invoke(obj);
                if (invoke == null || "".equals(invoke)) {
                    continue;
                }

                String filedName = method.getName().replace("get", "");
                filedName = toLowerCaseFirstOne(filedName);

                if (invoke instanceof Collection) {
                    Collection collections = (Collection) invoke;
                    if (collections != null && collections.size() > 0) {
                        String[] strings = (String[]) collections.toArray();
                        addParameter(filedName, strings);
                        return;
                    }
                }

                addParameter(filedName, invoke);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }
}
