package zcq.myjpa.bean.vo;

import zcq.myjpa.entity.Bill;

/**
 * @author zhengchuqin
 * @history 2019-08-02 zhengchuqin 新建
 * @since JDK1.8
 */
public class BillVo extends Bill {
    private Long version;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
