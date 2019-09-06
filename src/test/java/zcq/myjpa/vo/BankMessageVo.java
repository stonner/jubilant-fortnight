/**
 * ***************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 * ****************************************************************************
 */
package zcq.myjpa.vo;


import zcq.myjpa.utils.LoUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 银行8583报文
 * @author huanglong
 * @since 2017/10/12
 * @version 1.0
 */
public class BankMessageVo {

    /**
     * TPDU
     */
    public static final String TPDU = "6000100000";

    //测试
    //public static final String BANK_CODE = "861440360120020";

    /**
     * 商户号
     */
    //public static final String BANK_CODE = "000000000000536";
    public static final String BANK_CODE = "000000000020984";

    /**
     * 银联报文头
     */
    public static final String BANK_HEAD = "603200310000";

    /**
     * 基于PBOC借/贷记卡标准的IC卡脚本处理结果通知请求/应答
     */
    public static final String MSGTYE_PBOC = "0800";

    /**
     * 终端参数传递请求/应答
     */
    public static final String MSGTYE_PARAM = "0820";

    /**
     * 金融类请求/应答消息
     */
    public static final String MSGTYE_CONSUME = "0200";

    /**
     * 金融类请求/应答消息 --成功
     */
    public static final String SUPPLY_TWO = "00";

    /**
     * 金融类请求/应答消息
     */
    public static Map<String,String> VAL39 = new HashMap<>();

    static {
        VAL39.put("01","请持卡人与发卡银行联系");
        VAL39.put("03","无效商户");
        VAL39.put("04","此卡为无效卡（POS）");
        VAL39.put("05","持卡人认证失败");
        VAL39.put("1","请持卡人与发卡银行联系");
        VAL39.put("3","无效商户");
        VAL39.put("4","此卡为无效卡（POS）");
        VAL39.put("5","持卡人认证失败");
        VAL39.put("12","无效交易");
        VAL39.put("13","无效金额");
        VAL39.put("14","无效卡号");
        VAL39.put("15","此卡无对应发卡方");
        VAL39.put("21","该卡未初始化或睡眠卡");
        VAL39.put("22","操作有误，或超出交易允许天数");
        VAL39.put("25","没有原始交易，请联系发卡方");
        VAL39.put("30","请重试");
        VAL39.put("34","作弊卡,吞卡");
        VAL39.put("38","密码错误次数超限，请与发卡方联系");
        VAL39.put("40","发卡方不支持的交易");
        VAL39.put("41","挂失卡（POS）");
        VAL39.put("43","被窃卡（POS）");
        VAL39.put("45","交易失败，请插卡或挥卡");
        VAL39.put("51","可用余额不足");
        VAL39.put("54","该卡已过期");
        VAL39.put("55","密码错误");
        VAL39.put("57","不允许此卡交易");
        VAL39.put("58","发卡方不允许该卡在本终端进行此交易");
        VAL39.put("59","卡片校验错");
        VAL39.put("61","交易金额超限");
        VAL39.put("62","受限制的卡");
        VAL39.put("64","交易金额与原交易不匹配");
        VAL39.put("65","超出取款次数限制");
        VAL39.put("68","交易超时，请重试");
        VAL39.put("75","银行卡多次密码错误已被锁定，需要去银行解绑");
        VAL39.put("90","系统日切，请稍后重试");
        VAL39.put("91","发卡方状态不正常，请稍后重试");
        VAL39.put("92","发卡方线路异常，请稍后重试");
        VAL39.put("94","拒绝，重复交易，请稍后重试");
        VAL39.put("96","拒绝，交换中心异常，请稍后重试");
        VAL39.put("97","终端号未登记");
        VAL39.put("98","发卡方超时");
        VAL39.put("99","PIN格式错，请重新签到");
    }

    /**
     * 人民币
     */
    public static final String RMB = "156";

    /**
     * 读卡方式-刷卡
     */
    public static final String READ_CARD_TYPE_PAY = "021";

    /**
     * 读卡方式-拍卡
     */
    public static final String READ_CARD_TYPE_TAKE = "071";

    /**
     * 读卡方式-插卡
     */
    public static final String READ_CARD_TYPE_INSERT = "051";

    //报文字节长度（10进制）
    private int length;

    //tpdu
    private String tpdu;

    //银联标准报文头
    private String bankHead;

    //消息类型
    private String msgtye;

    //位图
    private BitMapVo bitMapVo;

    //易办事头报表
    private HeaderMapVo headerMapVo;



    //截取位图坐标-开始位置
    private int start;

    //截取位图坐标-结束位置
    private int end;

    public int getLength() {
        return length;
    }

    public String getTpdu() {
        return tpdu;
    }

    public void setTpdu(String tpdu) {
        if(tpdu.length() != 10){
            throw new RuntimeException("8583报文加密失败，tpdu长度应为10，实际为" + tpdu.length());
        }
        this.tpdu = tpdu;
    }

    public String getBankHead() {
        return bankHead;
    }

    public void setBankHead(String bankHead) {
        if(bankHead.length() != 12){
            throw new RuntimeException("8583报文加密失败，银行报文头标识长度应为12，实际为" + bankHead.length());
        }
        this.bankHead = bankHead;
    }

    public String getMsgtye() {
        return msgtye;
    }

    public void setMsgtye(String msgtye) {
        if(msgtye.length() != 4){
            throw new RuntimeException("8583报文加密失败，银行报文头标识长度应为4，实际为" + msgtye.length());
        }
        this.msgtye = msgtye;
    }

    public BitMapVo getBitMapVo() {
        return bitMapVo;
    }

    public void setBitMapVo(BitMapVo bitMapVo) {
        this.bitMapVo = bitMapVo;
    }

    public HeaderMapVo getHeaderMapVo() {
        return headerMapVo;
    }

    public void setHeaderMapVo(HeaderMapVo headerMapVo) {
        this.headerMapVo = headerMapVo;
    }

    /**
     * 初始化
     */
    public BankMessageVo(){

    }

    /**
     * 初始化银行8583报文实体类
     * @param message   报文
     * @param isHeader  是否有易办事报文头
     */
    public BankMessageVo(String message,boolean isHeader){
        //初始化坐标
        start = 0;
        end = 0;
        StringBuffer sb = new StringBuffer(message);
        //获取报文长度,4个字符长度，16进制转10进制
        setIndex(4);
        this.length = Integer.parseInt(sb.substring(start,end),16)*2;
        //获取tpdu,10个字符长度
        setIndex(10);
        this.tpdu = sb.substring(start,end);

        //判断是否有易办事报文头
        if(isHeader){
            //获取易办事标识,2个字符长度
            setIndex(2);
            String headId = sb.substring(start,end);
            //获取易办事报文头长度,4个字符长度
            setIndex(4);
            int headLength = Integer.parseInt(sb.substring(start,end),16)*2;
            //获取易办事报文头
            setIndex(headLength);
            String head = sb.substring(start,end);
            headerMapVo = new HeaderMapVo(headId,headLength,head);
        }

        //获取银联报文头,12个字符长度
        setIndex(12);
        this.bankHead = sb.substring(start,end);
        //获取msgtye,4个字符长度
        setIndex(4);
        this.msgtye = sb.substring(start,end);
        //初始化位图
        bitMapVo = new BitMapVo(sb.substring(end));
    }

    /**
     * 获取8583报文
     * @return
     */
    public String toMessage(){
        StringBuffer sb = new StringBuffer("");
        //拼接tpdu
        sb.append(tpdu);

        //判断是否已经有易办事头
        if(null != headerMapVo){
            sb.append(headerMapVo.doDateToMessage());
        }

        //拼接银联报文头
        sb.append(bankHead);
        //拼接消息类型
        sb.append(msgtye);

        //判断是否已经有位图
        if(null == bitMapVo){
            throw new RuntimeException("位图未初始化成功bitMapVo===》null");
        }

        //报文头
        String message = bitMapVo.doDateToMessage(msgtye);
        sb.append(message);

        length = sb.length();

        sb.insert(0, LoUtils.doLenToString(length/2,4,true));
        return sb.toString();
    }

    /**
     * 设置坐标
     * @param index
     */
    private void setIndex(int index){
        this.start = this.end;
        this.end += index;
    }



}
