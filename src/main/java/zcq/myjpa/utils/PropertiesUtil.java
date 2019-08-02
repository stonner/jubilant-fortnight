package zcq.myjpa.utils;

import org.springframework.beans.BeanUtils;
import zcq.myjpa.bean.vo.BillVo;
import zcq.myjpa.entity.Bill;

/**
 * @author zhengchuqin
 * @history 2019-08-02 zhengchuqin 新建
 * @since JDK1.8
 */
public class PropertiesUtil {
    public static BillVo copyToBillVo(Bill bill) {
        BillVo billVo = new BillVo();
        BeanUtils.copyProperties(bill,billVo);
        return billVo;
    }
}
