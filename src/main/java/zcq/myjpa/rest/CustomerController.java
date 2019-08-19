package zcq.myjpa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zcq.myjpa.service.OnlineService;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/08/19
 */
@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private OnlineService onlineService;

    @RequestMapping("get")
    public Object getCustomerDetail(String id) {
        return onlineService.getCustomerDetail(id);
    }
}
