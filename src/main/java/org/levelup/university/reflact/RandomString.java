package org.levelup.university.reflact;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RandomString {
    int lengthString() default 16;
    /*
    * 0 - регистр не важен
    * 1 - верхний регистр
    * 2 - нижний регистр
     */
    int registr() default 0;
}
