package zcq.myjpa.annotation;

import java.lang.annotation.*;

/**
 * TODO
 *
 * @author zhengchuqin
 * @history 2019-07-31 zhengchuqin 新建
 * @since JDK1.8
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface SendMsg {
}