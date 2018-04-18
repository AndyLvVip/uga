package uga.framework.interceptor;

import uga.framework.interceptor.response.AspireResponse;
import uga.framework.jackson.annotation.JsonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @Author: andy.lv
 * @Date: created on 2018/3/7
 * @Description:
 */
@Aspect
@Service
public class GlobalInterceptor {

    @Around("within(aspire.user.controller.*) && (" +
                "@target(org.springframework.web.bind.annotation.RestController) || " +
                "@annotation(org.springframework.web.bind.annotation.ResponseBody)" +
            ")")
    public Object wrapResponse(final ProceedingJoinPoint jp) throws Throwable {
        MethodSignature ms = (MethodSignature) jp.getSignature();
        JsonResult[] jsonResults = ms.getMethod().getDeclaredAnnotationsByType(JsonResult.class);
        Object realResult = jp.proceed(jp.getArgs());
        AspireResponse resp;
        if(realResult instanceof AspireResponse)
            resp = (AspireResponse) realResult;
        else
            resp =  AspireResponse.ok(realResult);
        for (int i = 0; i < jsonResults.length; i++) {
            if(jsonResults[i].included().length > 0)
                resp.putIncludedFields(jsonResults[i].type(), new HashSet<>(Arrays.asList(jsonResults[i].included())));
            if(jsonResults[i].excluded().length > 0)
                resp.putExcludedFileds(jsonResults[i].type(), new HashSet<>(Arrays.asList(jsonResults[i].excluded())));
        }
        return resp;
    }
}
