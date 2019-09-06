/**
 * ***************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 * ****************************************************************************
 */
package zcq.myjpa.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 消费
 */
@ApiModel(value = "消费")
public class ConsumeVo {

    @ApiModelProperty(value = "设备机器id")
    private String machineId;

    @ApiModelProperty(value = "用户编号，多个以“|”隔开")
    private String customerCode;

    @ApiModelProperty(value = "银行卡号")
    private String bankCardCode;

    @ApiModelProperty(value = "银行卡加密后密码")
    private String bankCardPassWord;

    @ApiModelProperty(value = "金额")
    private String money;

    @ApiModelProperty(value = "二磁道")
    private String twoTrack;

    @ApiModelProperty(value = "三磁道")
    private String threeTrack;

    @ApiModelProperty(value = "55域，ic卡芯片信息")
    private String icInfo;

    @ApiModelProperty(value = "有效日期，ic卡芯片 5F24")
    private String effectiveDate;

    @ApiModelProperty(value = "读卡类型，021:刷卡，071:拍卡，051:插卡")
    private String readCardType;

    @ApiModelProperty(value = "银行卡序列号，ic卡芯片 5F34")
    private String cardNo;

    public String getBankCardCode() {
        return bankCardCode;
    }

    public void setBankCardCode(String bankCardCode) {
        this.bankCardCode = bankCardCode;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getBankCardPassWord() {
        return bankCardPassWord;
    }

    public void setBankCardPassWord(String bankCardPassWord) {
        this.bankCardPassWord = bankCardPassWord;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getTwoTrack() {
        return twoTrack;
    }

    public void setTwoTrack(String twoTrack) {
        this.twoTrack = twoTrack;
    }

    public String getThreeTrack() {
        return threeTrack;
    }

    public void setThreeTrack(String threeTrack) {
        this.threeTrack = threeTrack;
    }

    public String getIcInfo() {
        return icInfo;
    }

    public void setIcInfo(String icInfo) {
        this.icInfo = icInfo;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getReadCardType() {
        return readCardType;
    }

    public void setReadCardType(String readCardType) {
        this.readCardType = readCardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
