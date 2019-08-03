package zcq.myjpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import zcq.myjpa.annotation.PrintLog;
import zcq.myjpa.annotation.SendMsg;
import zcq.myjpa.bean.vo.BillVo;
import zcq.myjpa.entity.Bill;
import zcq.myjpa.entity.Product;
import zcq.myjpa.repository.BillRepository;
import zcq.myjpa.repository.ProductRepository;
import zcq.myjpa.utils.PropertiesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author zhengchuqin
 * @history 2019-07-31 zhengchuqin 新建
 * @since JDK1.8
 */
@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public List<Bill> findAll() {
        return (List<Bill>) billRepository.findAll();
    }

    public Bill save(String type) {
        Bill bill = new Bill();
        bill.setCode("n9342");
        bill.setName("bility");
        bill.setType(type);
        Product product = new Product();
        product.setCode("lpe44");
        product.setName("aa");
        product.setType("666");
        productRepository.save(product);
        bill.setProduct(product);
        return billRepository.save(bill);
    }

    @SendMsg
    public Product getProductById(Long id) {
        List<Long> ids = new ArrayList<>();
        ids.add(id);
        List<Product> allById = productRepository.findAllById(ids);
        return allById.isEmpty()?null:allById.get(0);
    }

    @SendMsg
    public Product getProductByCode(String code) {
        return productRepository.findByCode(code);
    }

    @PrintLog
    public List<Bill> selectByCodeAndId(String code, String id) {
        return billRepository.selectByCodeAndId(code, id);
    }

    public BillVo sendMsg(String msg) {
        Bill bill = new Bill();
        bill.setType("3");
        bill.setName("nan");
        bill.setCode("u482");
        threadPoolTaskExecutor.execute(()->{
            System.out.println(System.currentTimeMillis()+">>"+msg);
        },30000);
        return PropertiesUtil.copyToBillVo(bill);
    }
}
