package zcq.myjpa.bean.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/08/19
 */
public class CustomerVo {

    @ApiModelProperty(value = "用户编号")
    private String customerCode;

    @ApiModelProperty(value = "用户名称")
    private String customerName;

    @ApiModelProperty(value = "用户地址")
    private String customerAddress;

    @ApiModelProperty(value = "用户名称脱敏")
    private String customerNameHide;

    @ApiModelProperty(value = "用户地址脱敏")
    private String customerAddressHide;

    @ApiModelProperty(value = "用户类型,personal:个人，company：单位")
    private String customerType;

    @ApiModelProperty(value = "纳税人识别号")
    private String payerTaxid;

    @ApiModelProperty(value = "用户手机号码")
    private String customerPhone;

    @ApiModelProperty(value = "用户邮箱")
    private String payerEmail;

    @ApiModelProperty(value = "用户证件类型,identity:身份证,hk:港澳通行证,license:营业执照", hidden = true)
    private String customerCardType;

    @ApiModelProperty(value = "用户证件号码", hidden = true)
    private String customerCardCode;

    @ApiModelProperty(value = "银行名称")
    private String payingBank;

    @ApiModelProperty(value = "银行卡号")
    private String payingAccount;

    @ApiModelProperty(value = "银行账户名称")
    private String payername;

    @ApiModelProperty(value = "银行账户证件类型,identity:身份证,hk:港澳通行证", hidden = true)
    private String bankUserCardType;

    @ApiModelProperty(value = "银行账户证件号码")
    private String customerID;

    @ApiModelProperty(value = "银行账户手机号码")
    private String payerPhone;


    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }


    public String getCustomerCardType() {
        return customerCardType;
    }

    public void setCustomerCardType(String customerCardType) {
        this.customerCardType = customerCardType;
    }

    public String getCustomerCardCode() {
        return customerCardCode;
    }

    public void setCustomerCardCode(String customerCardCode) {
        this.customerCardCode = customerCardCode;
    }


    public String getBankUserCardType() {
        return bankUserCardType;
    }

    public void setBankUserCardType(String bankUserCardType) {
        this.bankUserCardType = bankUserCardType;
    }


    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerNameHide() {
        return customerNameHide;
    }

    public void setCustomerNameHide(String customerNameHide) {
        this.customerNameHide = customerNameHide;
    }

    public String getCustomerAddressHide() {
        return customerAddressHide;
    }

    public void setCustomerAddressHide(String customerAddressHide) {
        this.customerAddressHide = customerAddressHide;
    }

    public String getPayerTaxid() {
        return payerTaxid;
    }

    public void setPayerTaxid(String payerTaxid) {
        this.payerTaxid = payerTaxid;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public String getPayingBank() {
        return payingBank;
    }

    public void setPayingBank(String payingBank) {
        this.payingBank = payingBank;
    }

    public String getPayingAccount() {
        return payingAccount;
    }

    public void setPayingAccount(String payingAccount) {
        this.payingAccount = payingAccount;
    }

    public String getPayername() {
        return payername;
    }

    public void setPayername(String payername) {
        this.payername = payername;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getPayerPhone() {
        return payerPhone;
    }

    public void setPayerPhone(String payerPhone) {
        this.payerPhone = payerPhone;
    }
}
