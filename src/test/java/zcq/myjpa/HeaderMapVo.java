/**
 * ***************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 * ****************************************************************************
 */
package zcq.myjpa;

import org.apache.commons.lang3.StringUtils;
import zcq.myjpa.utils.LoUtils;

/**
 * 易办事报文头数据
 * @author huanglong
 * @since 2017/10/17
 * @version 1.0
 */
public class HeaderMapVo {

    /**
     * fd
     */
    public static final String TYPE_FD = "fd";

    /**
     * 业务代码
     */
    public static final String BUSINESS_CODE = "000536";

    /**
     * 业务代码
     */
    public static final String VERSION = "100001";

    //应用类别定义
    private String type;

    //报文头长度
    private int length;

    //报文头校验码
    private String code;

    //sim卡号(IMSI) 05
    private String imsi;

    //sim卡号（ICCID) 06
    private String iccid;

    //位置信息 07
    private String addrInfo;

    //订单号   0a
    private String num;

    //业务代码	0b
    private String businessCode;

    //商户号	0c
    private String merchantCode;

    //经度信息	0e
    private String xInfo;

    //纬度信息	0f
    private String yInfo;

    //终端硬件序列号	10
    private String serialNum;

    //应用程序版本	11
    private String version;

    //订单信息	f0
    private String orderInfo;

    //终端号
    private String posCode;

    //截取位图坐标-开始位置
    private int start;

    //截取位图坐标-结束位置
    private int end;

    public HeaderMapVo(){

    }

    public HeaderMapVo(String type, int length, String message){
        doHeadToData(type,length,message);
    }

    public void doHeadToData(String type, int length,String message){
        this.type = type;
        this.length = length;
        start = 0;
        end = 0;
        setIndex(32);
        //获取验证编码
        code = message.substring(start,end);
        //判断取值是否完毕
        while (end < length){
            doMessageToData(message);
        }
    }

    /**
     * 转换易办事数据为字符串格式
     * @return
     */
    public String doDateToMessage(){
        StringBuffer sb = new StringBuffer("");

        //sim卡号(IMSI) 05
        sb.append(doDateToMessage(imsi,"05"));

        //sim卡号（ICCID) 06
        sb.append(doDateToMessage(iccid,"06"));

        //位置信息 07
        sb.append(doDateToMessage(addrInfo,"07"));

        //订单号   0a
        sb.append(doDateToMessage(num,"0A"));

        //业务代码	0b
        sb.append(doDateToMessage(businessCode,"0B"));

        //商户号	0c
        sb.append(doDateToMessage(merchantCode,"0C"));

        //经度信息	0e
        sb.append(doDateToMessage(xInfo,"0E"));

        //纬度信息	0f
        sb.append(doDateToMessage(yInfo,"0F"));

        //终端硬件序列号	10
        sb.append(doDateToMessage(serialNum,"10"));

        //应用程序版本	11
        sb.append(doDateToMessage(version,"11"));

        //应用程序版本	f0
        sb.append(doDateToMessage(orderInfo,"F0"));

        //验证码为 数据域，加41域终端号生成MD5值
        code = LoUtils.getMD5(sb.toString() + LoUtils.bin2HexStr(posCode.getBytes()));

        //验证码
        sb.insert(0,code);

        //应用类别定义 + 报文长度
        sb.insert(0,type + LoUtils.doLenToString(sb.length()/2,4,true));
        return sb.toString();
    }

    /**
     * 保存位图数据
     * @param value 位图数据-字符串
     * @param index 域类
     * @return
     */
    private String doDateToMessage(String value, String index){
        String val = "";
        if(StringUtils.isNotBlank(value)){
            value = LoUtils.bin2HexStr(value.getBytes());
            value += value.length() % 2 == 0 ? "":"0";
            val = index + LoUtils.doLenToString(value.length()/2,"F0".equals(index)?4:2,true) + value;
        }
        return val;
    }

    /**
     * 保存位图数据
     * @param value 位图数据-字符串
     */
    private void doMessageToData(String value){
        //获取类别
        setIndex(2);
        String index = value.substring(start,end);
        //F0-FF为2个字节
        if("F0".equals(index)){
            //获取长度
            setIndex(4);
        }else{
            //获取长度
            setIndex(2);
        }
        int length = Integer.parseInt(value.substring(start,end),16)*2;
        setIndex(length);
        switch (index){
            case "05": //sim卡号(IMSI) 05
                imsi = new String(LoUtils.hexStr2Bytes(value.substring(start,end)));
                break;
            case "06"://sim卡号（ICCID) 06
                iccid = new String(LoUtils.hexStr2Bytes(value.substring(start,end)));
                break;
            case "07"://位置信息 07
                addrInfo = new String(LoUtils.hexStr2Bytes(value.substring(start,end)));
                break;
            case "0A"://订单号   0A
                num = new String(LoUtils.hexStr2Bytes(value.substring(start,end)));
                break;
            case "0B"://业务代码	0B
                businessCode = new String(LoUtils.hexStr2Bytes(value.substring(start,end)));
                break;
            case "0C"://商户号	0C
                merchantCode = new String(LoUtils.hexStr2Bytes(value.substring(start,end)));
                break;
            case "0e"://经度信息	0e
                xInfo = new String(LoUtils.hexStr2Bytes(value.substring(start,end)));
                break;
            case "0F"://纬度信息	0f
                yInfo = new String(LoUtils.hexStr2Bytes(value.substring(start,end)));
                break;
            case "10"://终端硬件序列号	10
                serialNum = new String(LoUtils.hexStr2Bytes(value.substring(start,end)));
                break;
            case "11"://应用程序版本	11
                version = new String(LoUtils.hexStr2Bytes(value.substring(start,end)));
                break;
            case "F0"://订单信息	f0
                orderInfo = new String(LoUtils.hexStr2Bytes(value.substring(start,end)));
                break;
            //default:throw new RuntimeException("报文位图未找到相关文档格式："+ index);
        }
    }

    /**
     * 设置坐标
     * @param index
     */
    private void setIndex(int index){
        this.start = this.end;
        this.end += index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getAddrInfo() {
        return addrInfo;
    }

    public void setAddrInfo(String addrInfo) {
        this.addrInfo = addrInfo;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getxInfo() {
        return xInfo;
    }

    public void setxInfo(String xInfo) {
        this.xInfo = xInfo;
    }

    public String getyInfo() {
        return yInfo;
    }

    public void setyInfo(String yInfo) {
        this.yInfo = yInfo;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }
}
