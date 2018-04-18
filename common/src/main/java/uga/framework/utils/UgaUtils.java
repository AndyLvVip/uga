package uga.framework.utils;

import java.util.UUID;

/**
 * @Author: andy.lv
 * @Date: created on 2018/2/6
 * @Description:
 */
public class UgaUtils {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
