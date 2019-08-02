package zcq.myjpa.utils;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * TODO
 *
 * @author zhengchuqin
 * @history 2019-07-31 zhengchuqin 新建
 * @since JDK1.8
 */
public class EventBusUtil {
    @Autowired
    private static EventBus eventBus;
    @Autowired
    private static AsyncEventBus asyncEventBus;

    public EventBusUtil() {
    }

    public static void syncPost(Object event) {
        eventBus.post(event);
    }

    public static void asyncPost(Object event) {
        asyncEventBus.post(event);
    }

    /*public static EventBus getEventBus() {
        if (null == eventBus) {
            eventBus = new EventBus();
        }

        return eventBus;
    }

    public static EventBus getAsyncEventBus() {
        if (null == asyncEventBus) {
            asyncEventBus = new AsyncEventBus();
        }

        return asyncEventBus;
    }*/
}
