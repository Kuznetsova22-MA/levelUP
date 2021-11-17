package org.levelup.university.reflact;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RandomInt {
    int MIN_VALUE = Integer.MIN_VALUE;
    int MAX_VALUE = Integer.MAX_VALUE;

    int minRange() default MIN_VALUE;
    int maxRange() default  MAX_VALUE;
}
