package zcq.myjpa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zcq.myjpa.bean.ResponseObject;
import zcq.myjpa.service.BillService;

/**
 * TODO
 *
 * @author zhengchuqin
 * @history 2019-07-31 zhengchuqin 新建
 * @since JDK1.8
 */
@RestController
public class BillController {
    @Autowired
    private BillService billService;

    @RequestMapping("save")
    public ResponseObject save(String type) {
        return ResponseObject.newOk("ok",billService.save(type));
    }
    @RequestMapping("findAll")
    public ResponseObject findAll() {
        return ResponseObject.newOk("ok",billService.findAll());
    }

    @RequestMapping("getProductById")
    public ResponseObject getProductById(Long id) {
        return ResponseObject.newOk("ok",billService.getProductById(id));
    }

    @RequestMapping("getProductByCode")
    public ResponseObject getProductByCode(String code) {
        return ResponseObject.newOk("ok",billService.getProductByCode(code));
    }

    @RequestMapping("selectByCodeAndId")
    public ResponseObject selectByCodeAndId(String code, String id) {
        return ResponseObject.newOk("ok",billService.selectByCodeAndId(code,id));
    }

    @RequestMapping("sendMsg")
    public ResponseObject sendMsg(String msg) {
        return ResponseObject.newOk("ok",billService.sendMsg(msg));
    }
}
