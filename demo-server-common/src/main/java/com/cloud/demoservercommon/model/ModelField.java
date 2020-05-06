package com.cloud.demoservercommon.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModelField {
    int order() default 999;

    TypeEnum type() default TypeEnum.NORMAL;

    int length() default 0;
}
