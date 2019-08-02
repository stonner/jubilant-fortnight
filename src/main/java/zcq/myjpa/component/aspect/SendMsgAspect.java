package zcq.myjpa.component.aspect;

import com.google.common.eventbus.AsyncEventBus;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zcq.myjpa.component.handler.SendMsgHandler;
import zcq.myjpa.entity.Product;

/**
 * TODO
 *
 * @author zhengchuqin
 * @history 2019-07-31 zhengchuqin 新建
 * @since JDK1.8
 */
@Aspect
@Component
public class SendMsgAspect {
    protected static Logger logger = LoggerFactory.getLogger(SendMsgAspect.class);
    @Autowired
    private AsyncEventBus asyncEventBus;
    @Autowired
    private SendMsgHandler sendMsgHandler;

    @Pointcut(value = "@annotation(zcq.myjpa.annotation.SendMsg))")
    public void executeMethod(){
        // Do nothing
    }
    @AfterReturning(value = "executeMethod()", returning = "product")
    public void afterReturning(JoinPoint joinPoint, Product product) {
        logger.debug("SendMsgAspect -->>product: "+ (null == product?"null":"code = "+product.getCode()+" ; name = "+product.getName()+" ; id = "+product.getId()));
        System.out.println();
        asyncEventBus.register(sendMsgHandler);
        asyncEventBus.post(product==null?new Product():product);
    }

}
