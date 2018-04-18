package uga.framework.interceptor.response;

import aspire.common.jackson.IJsonFilterFieldsHolder;
import com.fasterxml.jackson.annotation.JsonFilter;

import java.util.*;

/**
 * @Author: andy.lv
 * @Date: created on 2018/3/7
 * @Description:
 */
@JsonFilter("AspireResponse")
public class AspireResponse implements IJsonFilterFieldsHolder {

    public static final AspireResponse
            ACCESS_DENIED = AspireResponse.newInstance("403", "Access Denied"),
            INTERNAL_SERVER_ERROR = AspireResponse.newInstance("500", "Internal Server Error")
                    ;

    private String message;
    private String code;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private Object data;


    public static AspireResponse newInstance(String code, String msg) {
        AspireResponse resp = new AspireResponse();
        resp.setCode(code);
        resp.setMessage(msg);
        resp.putIncludedFields(AspireResponse.class, new HashSet<>(Arrays.asList("code", "message", "data")));
        return resp;
    }



    public static AspireResponse ok(Object data) {
        AspireResponse resp = AspireResponse.newInstance("200", "Success");
        resp.setData(data);
        return resp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private Map<Class<?>, Set<String>> includedFieldsMap = new HashMap<>();

    public Map<Class<?>, Set<String>> includedFieldsMap() {
        return includedFieldsMap;
    }

    public void putIncludedFields(Class<?> type, Set<String> fields) {
        includedFieldsMap.put(type, fields);
    }

    private Map<Class<?>, Set<String>> excludedFieldsMap = new HashMap<>();

    public void putExcludedFileds(Class<?> type, Set<String> fields) {
        excludedFieldsMap.put(type, fields);
    }
    public Map<Class<?>, Set<String>> excludedFieldsMap() {
        return excludedFieldsMap;
    }
}
