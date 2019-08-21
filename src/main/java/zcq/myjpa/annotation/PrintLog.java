package zcq.myjpa.annotation;

import java.lang.annotation.*;

/**
 * @author zhengchuqin
 * @history 2019-08-02 zhengchuqin 新建
 * @since JDK1.8
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface PrintLog {
    String desc();
}
