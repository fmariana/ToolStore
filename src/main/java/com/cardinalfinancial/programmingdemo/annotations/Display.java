package main.java.com.cardinalfinancial.programmingdemo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Display annotation for fields to be included in toString() method
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Display {
    enum DataType {
        DATE,
        CURRENCY,
        PERCENT,
        OTHER
    }

    String name();
    DataType type() default DataType.OTHER;
}
