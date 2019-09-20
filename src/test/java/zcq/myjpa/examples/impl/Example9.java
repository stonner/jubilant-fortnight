package zcq.myjpa.examples.impl;
/**
 * ***************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 * ****************************************************************************
 */

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import zcq.myjpa.examples.Example;
import zcq.myjpa.utils.HttpClientUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/09/20
 */
public class Example9 implements Example {
    public static void main(String[] args) {
        try {
            new Example9().doing1();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doing1() throws IOException {
        File file = new File("D:\\workNotes\\water\\waterBank\\0919.xls");
        NPOIFSFileSystem fs = new NPOIFSFileSystem(file);
        HSSFWorkbook wb = new HSSFWorkbook(fs.getRoot(), true);
        HSSFSheet sheetAt = wb.getSheetAt(0);

//        从第5行开始
            int rowNum = 4;
            Row row = null;
            String bankId = null;
            String bankCode = null;
            String bankCodePrefix = null;
            String s = null;
            String bank = null;
            do {
                row = sheetAt.getRow(rowNum);
                if (row == null || row.getCell(9) == null) {
                    break;
                }
                bankCode = row.getCell(9).getStringCellValue();
                if (StringUtils.isBlank(bankCode)) break;
                bankCode = bankCode.replaceAll("x", "1");
                System.out.println(bankCode);
                //如果取不到则退出循环
                s = HttpClientUtils.newInstance().get("https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8&cardBinCheck=true&cardNo="+bankCode);
                System.out.println(s);
                bank = JSONObject.parseObject(s).getString("bank");
                row.getCell(18).setCellValue(bank);
                rowNum ++;
            } while (rowNum < 4000);
            wb.write(new FileOutputStream("C:\\Users\\zhengchuqin\\Desktop\\2015年5月25日导出.xls"));
        fs.close();

    }

    @Override
    public void doing2() {

    }
}
