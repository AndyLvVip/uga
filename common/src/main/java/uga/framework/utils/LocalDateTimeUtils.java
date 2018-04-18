package uga.framework.utils;

import java.time.format.DateTimeFormatter;

/**
 * @Author: andy.lv
 * @Date: created on 2018/2/6
 * @Description:
 */
public class LocalDateTimeUtils {

    public final static String DATE_TIME_FORMATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * Added by andy.lv on 20180206
     */
    public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMATE_PATTERN);
}
