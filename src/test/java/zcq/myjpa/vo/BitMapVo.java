/**
 * ***************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 * ****************************************************************************
 */
package zcq.myjpa.vo;

import org.apache.commons.lang3.StringUtils;
import zcq.myjpa.utils.LoUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 银联位图数据
 * @author huanglong
 * @since 2017/10/17
 * @version 1.0
 */
public class BitMapVo {

    //位图
    private String bitMap;

    //位图数据
    private Map<Integer, String> bitMapData = new HashMap<>();

    //截取位图坐标-开始位置
    private int start;

    //截取位图坐标-结束位置
    private int end;

    //mac秘钥
    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    public String getBitMap() {
        return bitMap;
    }

    public Map<Integer, String> getBitMapData() {
        return bitMapData;
    }

    public void setBitMapData(Map<Integer, String> bitMapData) {
        //初始化位图位置，0位64位
        StringBuffer sb = new StringBuffer("0");
        //遍历位图数据，从2开始，64
        for (int i = 2 ; i <= 64 ; i ++){
            String str = bitMapData.get(i);
            //判断是否有数据，没有给0，有则为1
            if(StringUtils.isBlank(str))
            {
                sb.append("0");
            }
            else
            {
                sb.append("1");
            }
        }
        this.bitMap = sb.toString();
        this.bitMapData = bitMapData;
    }

    /**
     * 初始化位图
     */
    public BitMapVo(){
    }

    /**
     * 初始化位图
     * @param massage   位图-字符串
     */
    public BitMapVo(String massage){
        doMessageToData(massage);
    }

    /**
     * 初始化位图
     * @param bitMapData   位图
     */
    public BitMapVo(Map<Integer, String> bitMapData){
        setBitMapData(bitMapData);
    }

    /**
     * 保存位图数据
     * @param bitMapDataStr 位图数据-字符串
     */
    private void doMessageToData(String bitMapDataStr){
        start = 0;
        end = 0;
        setIndex(16);
        //获取位图，16进制转2进制
        this.bitMap = LoUtils.bytes2BinStr(LoUtils.hexStr2Bytes(bitMapDataStr.substring(start,end)));

        //截出之后的数据
        bitMapDataStr = bitMapDataStr.substring(end);
        //初始化坐标
        start = 0;
        end = 0;
        char [] c = bitMap.toCharArray();
        //遍历位图
        for (int i = 1 ; i < c.length ; i++){
            //判断位图是否有值
            if(StringUtils.equals(String.valueOf(c[i]),LoUtils.IS_DATA)){
                doMessageToData(bitMapDataStr,i+1);
            }
        }
    }

    /**
     * 保存位图数据
     * @param index      位图位置
     * @param value 位图数据-字符串
     */
    private void doMessageToData(String value, int index){
        int length;
        String val;
        switch (index){
            case 2://N..19  LLVAR
                setIndex(2);
                length = Integer.parseInt(value.substring(start,end));
                setIndex(length % 2 == 0 ? length : length + 1);
                val = value.substring(start,end);
                if(val.length() > length){
                    val = val.substring(0,length);
                }
                break;
            case 3://N6
                setIndex(6);
                val = value.substring(start,end);
                break;
            case 4://N12
                setIndex(12);
                val = value.substring(start,end);
                break;
            case 11://N6
                setIndex(6);
                val = value.substring(start,end);
                break;
            case 12: //N6  hhmmss
                setIndex(6);
                val = value.substring(start,end);
                break;
            case 13: //N4  MMDD
                setIndex(4);
                val = value.substring(start,end);
                break;
            case 14: //N4  YYMM
                setIndex(4);
                val = value.substring(start,end);
                break;
            case 15: //N4  MMDD
                setIndex(4);
                val = value.substring(start,end);
                break;
            case 22://N3
                setIndex(4);
                val = value.substring(start,end);
                break;
            case 23://N3
                setIndex(4);
                val = value.substring(start,end);
                break;
            case 25://N2
                setIndex(2);
                val = value.substring(start,end);
                break;
            case 26://N2
                setIndex(2);
                val = value.substring(start,end);
                break;
            case 32://N..11  LLVAR
                setIndex(2);
                length = Integer.parseInt(value.substring(start,end));
                setIndex(length % 2 == 0 ? length : length + 1);
                val = value.substring(start,end);
                if(val.length() > length){
                    val = val.substring(0,length);
                }
                break;
            case 35://Z..37  LLVAR
                setIndex(2);
                length = Integer.parseInt(value.substring(start,end));
                setIndex(length % 2 == 0 ? length : length + 1);
                val = value.substring(start,end);
                if(val.length() > length){
                    val = val.substring(0,length);
                }
                break;
            case 36://Z...104  LLLVAR
                setIndex(4);
                length = Integer.parseInt(value.substring(start,end));
                setIndex(length % 2 == 0 ? length : length + 1);
                val = value.substring(start,end);
                if(val.length() > length){
                    val = val.substring(0,length);
                }
                break;
            case 37://AN12
                setIndex(24);
                val = new String(LoUtils.hexStr2Bytes(value.substring(start,end)));
                break;
            case 38://AN6
                setIndex(12);
                val = new String(LoUtils.hexStr2Bytes(value.substring(start,end)));
                break;
            case 39://AN2
                setIndex(4);
                val = new String(LoUtils.hexStr2Bytes(value.substring(start,end)));
                break;
            case 41://ANS8
                setIndex(16);
                //16进制转汉字
                val = new String(LoUtils.hexStr2Bytes(value.substring(start,end)));
                break;
            case 42://ANS15
                setIndex(30);
                val = new String(LoUtils.hexStr2Bytes(value.substring(start,end)));
                break;
            case 44://ANS..25  LLVAR
                setIndex(2);
                length = Integer.parseInt(value.substring(start,end))*2;
                setIndex(length % 2 == 0 ? length : length + 1);
                val = new String(LoUtils.hexStr2Bytes(value.substring(start,end)));
                break;
            case 48://N...322   LLLVAR
                setIndex(4);
                length = Integer.parseInt(value.substring(start,end));
                setIndex(length % 2 == 0 ? length : length + 1);
                val = value.substring(start,end);
                if(val.length() > length){
                    val = val.substring(0,length);
                }
                break;
            case 49://AN3
                setIndex(6);
                val = new String(LoUtils.hexStr2Bytes(value.substring(start,end)));
                break;
            case 52://B64
                setIndex(16);
                val = value.substring(start,end);
                break;
            case 53://N16
                setIndex(16);
                val = value.substring(start,end);
                break;
            case 54://AN...020  LLLVAR
                setIndex(4);
                length = Integer.parseInt(value.substring(start,end))*2;
                setIndex(length % 2 == 0 ? length : length + 1);
                val = new String(LoUtils.hexStr2Bytes(value.substring(start,end)));
                break;
            case 55://字节...255  LLLVAR
                setIndex(4);
                length = Integer.parseInt(value.substring(start,end))*2;
                setIndex(length % 2 == 0 ? length : length + 1);
                val = value.substring(start,end);
                break;
            case 58://ans...100  LLLVAR
                setIndex(4);
                length = Integer.parseInt(value.substring(start,end))*2;
                setIndex(length % 2 == 0 ? length : length + 1);
                val = new String(LoUtils.hexStr2Bytes(value.substring(start,end)));
                break;
            case 60://N...17  LLLVAR
                setIndex(4);
                length = Integer.parseInt(value.substring(start,end));
                setIndex(length % 2 == 0 ? length : length + 1);
                val = value.substring(start,end);
                if(val.length() > length){
                    val = val.substring(0,length);
                }
                break;
            case 61://N...029  LLLVAR
                setIndex(4);
                length = Integer.parseInt(value.substring(start,end));
                setIndex(length % 2 == 0 ? length : length + 1);
                val = value.substring(start,end);
                if(val.length() > length){
                    val = val.substring(0,length);
                }
                break;
            case 62://ANS...512  LLLVAR
                setIndex(4);
                length = Integer.parseInt(value.substring(start,end))*2;
                setIndex(length % 2 == 0 ? length : length + 1);
                val = value.substring(start,end);
                break;
            case 63://ANS...163  LLLVAR
                setIndex(4);
                length = Integer.parseInt(value.substring(start,end))*2;
                setIndex(length % 2 == 0 ? length : length + 1);
                val = new String(LoUtils.hexStr2Bytes(value.substring(start,end)));
                break;
            case 64://B64
                setIndex(16);
                val = new String(LoUtils.hexStr2Bytes(value.substring(start,end)));
                break;
            default:return;//throw new RuntimeException("报文位图未找到相关文档格式："+ index);

        }
        bitMapData.put(index,val);
    }

    /**
     * 保存位图数据转报文
     */
    public String doDateToMessage(String msgtye){
        //初始化位图数据-字符串
        StringBuffer bitMapDataStr = new StringBuffer("");

        //遍历位图数据，从2开始，64
        for (int i = 2 ; i <= 64 ; i ++){
            String str = bitMapData.get(i);
            //判断是否有数据
            if(StringUtils.isNotBlank(str))
            {
                bitMapDataStr.append(doDateToMessage(str,i));
            }
        }

        //判断是否需要用密钥解密，有则为添加64域值
        if(StringUtils.isNotBlank(key)){
            //将修改位图64域
            bitMap = bitMap.substring(0,63) + "1";
        }

        //拼接位图2进制转16进制
        String hex = LoUtils.b2h(bitMap);
        bitMapDataStr.insert(0,hex);

        //判断是否需要用密钥解密，有则为添加64域值
        if(StringUtils.isNotBlank(key)){
            String mac = msgtye + bitMapDataStr.toString();
            mac = LoUtils.getMac(key.getBytes(),mac.getBytes());
            //添加64域
            bitMapDataStr.append(mac);
        }

        return bitMapDataStr.toString();
    }

    /**
     * 保存位图数据转报文
     */
    private String doDateToMessage(String val ,int index){
        switch (index){
            case 2://N..19  LLVAR
                val = getMessage(val, index, 2,false,false,true);
                break;
            case 3://N6
                if(val.length() != 6){
                    throw new RuntimeException("域3值域长度出错,应为6,实际值为:" + val);
                }
                break;
            case 4://N12
                if(val.length() != 12){
                    throw new RuntimeException("域4值域长度出错,应为12,实际值为:" + val);
                }
                break;
            case 11://N6
                if(val.length() != 6){
                    throw new RuntimeException("域11值域长度出错,应为6,实际值为:" + val);
                }
                break;
            case 12: //N6  hhmmss
                if(val.length() != 6){
                    throw new RuntimeException("域12值域长度出错,应为6,实际值为:" + val);
                }
                break;
            case 13: //N4  MMDD
                if(val.length() != 4){
                    throw new RuntimeException("域13值域长度出错,应为4,实际值为:" + val);
                }
                break;
            case 14: //N4  YYMM
                if(val.length() != 4){
                    throw new RuntimeException("域14值域长度出错,应为4,实际值为:" + val);
                }
                break;
            case 15: //N4  MMDD
                if(val.length() != 4){
                    throw new RuntimeException("域15值域长度出错,应为4,实际值为:" + val);
                }
                break;
            case 22://N3
                if(val.length() != 4){
                    throw new RuntimeException("域22值域长度出错,应为4,实际值为:" + val);
                }
                break;
            case 23://N3
                if(val.length() != 4){
                    throw new RuntimeException("域23值域长度出错,应为4,实际值为:" + val);
                }
                break;
            case 25://N2
                if(val.length() != 2){
                    throw new RuntimeException("域25值域长度出错,应为2,实际值为:" + val);
                }
                break;
            case 26://N2
                if(val.length() != 2){
                    throw new RuntimeException("域26值域长度出错,应为2,实际值为:" + val);
                }
                break;
            case 32://N..11  LLVAR
                val = getMessage(val, index, 2,false,false,true);
                break;
            case 35://Z..37  LLVAR
                val = getMessage(val, index, 2,false,false,true);
                break;
            case 36://Z...104  LLLVAR
                val = getMessage(val, index, 4,false,false,true);
                break;
            case 37://AN12
                val = LoUtils.bin2HexStr(val.getBytes());
                if(val.length() != 24){
                    throw new RuntimeException("域37值域长度出错,应为24,实际值为:" + val);
                }
                break;
            case 38://AN6
                val = LoUtils.bin2HexStr(val.getBytes());
                if(val.length() != 12){
                    throw new RuntimeException("域38值域长度出错,应为12,实际值为:" + val);
                }
                break;
            case 39://AN2
                val = LoUtils.bin2HexStr(val.getBytes());
                if(val.length() != 4){
                    throw new RuntimeException("域39值域长度出错,应为4,实际值为:" + val);
                }
                break;
            case 41://ANS8
                val = LoUtils.bin2HexStr(val.getBytes());
                if(val.length() != 16){
                    throw new RuntimeException("域41值域长度出错,应为16,实际值为:" + val);
                }
                break;
            case 42://ANS15
                val = LoUtils.bin2HexStr(val.getBytes());
                if(val.length() != 30){
                    throw new RuntimeException("域42值域长度出错,应为30,实际值为:" + val);
                }
                break;
            case 44://ANS..25  LLVAR
                val = getMessage(val, index, 2,true,true,true);
                break;
            case 48://N...322   LLLVAR
                val = getMessage(val, index, 2,false,false,true);
                break;
            case 49://AN3
                val = LoUtils.bin2HexStr(val.getBytes());
                if(val.length() != 6){
                    throw new RuntimeException("域49值域长度出错,应为6,实际值为:" + val);
                }
                break;
            case 52://B64
                if(val.length() != 16){
                    throw new RuntimeException("域52值域长度出错,应为16,实际值为:" + val);
                }
                break;
            case 53://N16
                if(val.length() != 16){
                    throw new RuntimeException("域53值域长度出错,应为16,实际值为:" + val);
                }
                break;
            case 54://AN...020  LLLVAR
                val = getMessage(val, index, 4,true,true,true);
                break;
            case 55://字节...255  LLLVAR
                val = getMessage(val, index, 4,true,false,false);
                break;
            case 58://ans...100  LLLVAR
                val = getMessage(val, index, 4,true,true,true);
                break;
            case 60://N...17  LLLVAR
                val = getMessage(val, index, 4,false,false,true);
                break;
            case 61://N...029  LLLVAR
                val = getMessage(val, index, 4,false,false,true);
                break;
            case 62://ANS...512  LLLVAR
                val = getMessage(val, index, 4,true,false,true);
                break;
            case 63://ANS...163  LLLVAR
                val = getMessage(val, index, 4,true,true,true);
                break;
            case 64://B64
                val = LoUtils.bin2HexStr(val.getBytes());
                if(val.length() != 16){
                    throw new RuntimeException("域64值域长度出错,应为16,实际值为:" + val);
                }
                break;
            default:throw new RuntimeException("报文位图未找到相关文档格式："+ index);
        }
        return val;
    }

    /**
     * 获取转报文数据
     * @param val       值
     * @param index     域
     * @param num       不能超出的长度
     * @param isbyte    是否字节
     * @param isHex     是否要转16进制
     * @param isLetf    是否左靠
     * @return
     */
    private String getMessage(String val, int index , int num, boolean isbyte, boolean isHex,boolean isLetf){
        //字符串转16进制
        if (isHex){
            val = LoUtils.bin2HexStr(val.getBytes());
        }
        int length = val.length();
        //因为奇数补位，2位数不能大于98，3位不能大于998
        int checkNum = num == 2 ? 98 : 998;
        //判断长度是否超出
        if(length > checkNum){
            throw new RuntimeException("域" + index + "值域长度出错,应为" + num + ",实际值为:" + val);
        }
        //判断是否左靠
        if(isLetf){
            //判断是否长度为奇数
            if(length % 2 != 0){
                val = val + "0";
            }
        }else{
            //判断是否长度为奇数
            if(length % 2 != 0){
                val = "0" + val;
            }
        }

        //判断是否是字节，字节2个字符是1个字节
        if(isbyte){
            length = val.length()/2;
        }
        String len = LoUtils.doLenToString(length,num,false);
        return len + val;
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
