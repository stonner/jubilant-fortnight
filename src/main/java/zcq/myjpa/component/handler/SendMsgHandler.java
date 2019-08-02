package zcq.myjpa.component.handler;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import zcq.myjpa.component.aspect.SendMsgAspect;
import zcq.myjpa.entity.Product;

/**
 * TODO
 *
 * @author zhengchuqin
 * @history 2019-07-31 zhengchuqin 新建
 * @since JDK1.8
 */
@Component
public class SendMsgHandler {
    private static Logger logger = LoggerFactory.getLogger(SendMsgHandler.class);


    @Subscribe
    public void handler(Product product) {
        logger.debug("SendMsgHandler -->>product: "+ (null == product?"null":"code = "+product.getCode()+" ; name = "+product.getName()+" ; id = "+product.getId()));
    }
}
