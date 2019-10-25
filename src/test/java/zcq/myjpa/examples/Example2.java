package zcq.myjpa.examples;
/**
 * ***************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 * ****************************************************************************
 */

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/08/15
 */
public class Example2 {

    public static void main(String[] args) {
        new Example2().doing1();
    }

    public void doing1() {
        /*final String format = String.format("sdgsg%s", 2, 3);
        System.out.println(format);*/

         StringBuffer sb = new StringBuffer();
         MessageFormat mf = new MessageFormat("(#'{'list[{0}].businessId,jdbcType=VARCHAR},#'{'list[{0}].customerType,jdbcType=VARCHAR}" +
                ",#'{'list[{0}].debugType,jdbcType=VARCHAR},#'{'list[{0}].materialId,jdbcType=VARCHAR}" +
                ",#'{'list[{0}].isMust,jdbcType=VARCHAR})");
        for (int i = 0; i < 5; i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i != 5 - 1){
                sb.append(",");
            }
        }
        System.out.println(sb);
    }

    public void doing2() {
        final HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("1", new int[2]);
        stringObjectHashMap.put("dsljgf", new ArrayList<>());
        stringObjectHashMap.put("nnnn", "bbbb");
        stringObjectHashMap.forEach((key,value)-> System.out.println(key+": "+value));
    }
}
