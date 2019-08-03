package zcq.myjpa.component.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import zcq.myjpa.entity.Bill;

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

    @Pointcut("@annotation(zcq.myjpa.annotation.PrintLog)")
    public void executeMethod(){
        // Do nothing
    }

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
}
