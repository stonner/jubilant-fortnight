package zcq.myjpa.vo;
/**
 * ***************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 * ****************************************************************************
 */

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/09/04
 */
public class ReceiptStageVo {

    Long billingladderid;
    //用户id
    Long userid;
    //账单月份
    Integer billmonth;
    //用水性质
    String waterusagename;
    //费用类型
    String wateritemname;
    // 阶梯档位
    Integer laddersn;
    // 阶梯水量
    Integer ladderunit;
    //阶梯水价
    Double ladderprice;
    //阶梯水费
    Double landercost;

    public Long getBillingladderid() {
        return billingladderid;
    }

    public void setBillingladderid(Long billingladderid) {
        this.billingladderid = billingladderid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Integer getBillmonth() {
        return billmonth;
    }

    public void setBillmonth(Integer billmonth) {
        this.billmonth = billmonth;
    }

    public String getWaterusagename() {
        return waterusagename;
    }

    public void setWaterusagename(String waterusagename) {
        this.waterusagename = waterusagename;
    }

    public String getWateritemname() {
        return wateritemname;
    }

    public void setWateritemname(String wateritemname) {
        this.wateritemname = wateritemname;
    }

    public Integer getLaddersn() {
        return laddersn;
    }

    public void setLaddersn(Integer laddersn) {
        this.laddersn = laddersn;
    }

    public Integer getLadderunit() {
        return ladderunit;
    }

    public void setLadderunit(Integer ladderunit) {
        this.ladderunit = ladderunit;
    }

    public Double getLadderprice() {
        return ladderprice;
    }

    public void setLadderprice(Double ladderprice) {
        this.ladderprice = ladderprice;
    }

    public Double getLandercost() {
        return landercost;
    }

    public void setLandercost(Double landercost) {
        this.landercost = landercost;
    }
}
