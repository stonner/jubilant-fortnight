package zcq.myjpa.component.aspect;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import zcq.myjpa.annotation.PrintLog;
import zcq.myjpa.entity.Bill;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * TODO
 *
 * @author zhengchuqin
 * @history 2019-08-02 zhengchuqin 新建
 * @since JDK1.8
 */
@Aspect
@Component
public class PrintLogAspect {
    protected static Logger logger = LoggerFactory.getLogger(PrintLogAspect.class);

    @Pointcut("@within(zcq.myjpa.annotation.PrintLog)")
    public void executeClass() {}

    @Pointcut("@annotation(zcq.myjpa.annotation.PrintLog)")
    public void executeMethod(){}

    @Around(value = "executeMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        Object aThis = joinPoint.getThis();
        String name = joinPoint.getSignature().getName();
        logger.debug("PrintLogAspect -->>"+name+"; class  "+joinPoint.getSignature().getDeclaringTypeName()+"--" + target+ "--"+aThis);
        Object proceed = joinPoint.proceed();
        logger.debug("PrintLogAspect --out");
        List emptyList = (ArrayList<Bill>)proceed;
        emptyList.add(new Bill());
        return proceed;
    }

    @AfterReturning(value = "executeMethod()", returning = "result")
    public void afterMethodReturning(JoinPoint joinPoint, Object result){
        process (joinPoint, result);
    }

    @AfterReturning(value = "executeClass()", returning = "result")
    public void afterClassReturning(JoinPoint joinPoint, Object result){
        process (joinPoint, result);
    }

    private void process (JoinPoint joinPoint, Object result) {
        //方法参数名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] argNames = methodSignature.getParameterNames();
        //方法参数值
        Object[] args = joinPoint.getArgs();
        //方法名称
        String methodName = joinPoint.getSignature().getName();
        //类名称
        String clazzName = joinPoint.getTarget().getClass().getName();
        //类
        Class clazz;
        try {
            clazz = Class.forName(clazzName);
        } catch (ClassNotFoundException e) {
            return;
        }
        //获取注解desc
        String desc = StringUtils.EMPTY;
        for (Method method : clazz.getMethods()) {
            if (method.getName().equals(methodName)) {
                Class[] params = method.getParameterTypes();
                if (params.length == args.length) {
                    PrintLog annotation = method.getAnnotation(PrintLog.class);
                    if (annotation != null) {
                        desc = annotation.desc();
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(desc)) {
            logger.info(desc);
        }
        logger.info("method name: " + clazzName + "." + methodName);
        logger.info("method args: " + JSONObject.toJSONString(args));
        logger.info("method return: " + JSONObject.toJSONString(result));
        System.out.println("method name: " + clazzName + "." + methodName);
        System.out.println("method args: " + JSONObject.toJSONString(args));
        System.out.println("method return: " + JSONObject.toJSONString(result));
    }
}
