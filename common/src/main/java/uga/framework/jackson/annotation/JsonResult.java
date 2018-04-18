package uga.framework.jackson.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(JsonResults.class)
public @interface JsonResult {
    Class<?> type();
    String[] included() default {};
    String[] excluded() default {};
}

