package com.tao.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author huangtao54
 * @description 时间格式化
 * @date 2019/1/15
 */
public class DateFormatter implements DataFormatter<Date, String> {
    public static final String YYMMDD = "yyyy-MM-dd";

    @Override
    public String format(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYMMDD);
        String value = sdf.format(data);
        return value;
    }
}
