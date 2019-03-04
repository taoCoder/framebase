package com.tao.utils;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Iterator;
import java.util.Set;

/**
 * @author huangtao54
 * @description
 * @date 2019/3/4
 */
public class BeanValidators {
    /**
     * 如果返回null则表示没有错误
     * @param obj
     * @return
     */
    public static String check(Object obj) {
        if (null == obj) {
            return "入参vo不能为空";
        }
        Set<ConstraintViolation<Object>> validResult = Validation.buildDefaultValidatorFactory().getValidator().validate(obj);
        if (null != validResult && validResult.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (Iterator<ConstraintViolation<Object>> iterator = validResult.iterator(); iterator.hasNext();) {
                ConstraintViolation<Object> constraintViolation =  iterator.next();
                if(StringUtils.isNotBlank(constraintViolation.getMessage())) {
                    sb.append(constraintViolation.getMessage()).append("、");
                } else {
                    sb.append(constraintViolation.getPropertyPath().toString()).append("不合法、");
                }
            }
            if (sb.lastIndexOf("、") == sb.length() - 1) {
                sb.delete(sb.length() - 1, sb.length());
            }
            return sb.toString();
        }
        return null;
    }
}
