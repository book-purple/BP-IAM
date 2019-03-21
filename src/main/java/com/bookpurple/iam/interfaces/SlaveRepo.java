package com.bookpurple.iam.interfaces;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.lang.annotation.*;

/*
 * Created by Gaurav Sharma on 21 Mar 2019
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface SlaveRepo {
    @AliasFor(
            annotation = Repository.class
    )
    String value() default "";
}
