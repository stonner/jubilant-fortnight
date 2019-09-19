package zcq.myjpa.examples.impl;
/**
 * ***************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 * ****************************************************************************
 */

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import zcq.myjpa.examples.Example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/09/19
 */
public class Example8 implements Example {


    public static void main(String[] args) {
        try {
            new Example8().doing1();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void doing1() throws Exception {
//        String filePath = "C:\\Users\\zhengchuqin\\Desktop\\0919.xls";
//        String tmpFilePath = "C:\\Users\\zhengchuqin\\Desktop\\~$0919.xls";
//
//        File file = Files.copy(Paths.get(filePath), Paths.get(tmpFilePath), StandardCopyOption.REPLACE_EXISTING).toFile();
        File file = new File("C:\\Users\\zhengchuqin\\Desktop\\2015年5月25日版卡表.xls");
        NPOIFSFileSystem fs = new NPOIFSFileSystem(file);
        HSSFWorkbook wb = new HSSFWorkbook(fs.getRoot(), true);
        HSSFSheet sheetAt = wb.getSheetAt(0);
        Row row = sheetAt.getRow(9);
        Cell cell = row.getCell(9);
        String stringCellValue = cell.getStringCellValue();
        System.out.println(stringCellValue);
        fs.close();
    }

    @Override
    public void doing2() throws IOException, InvalidFormatException {


        String filePath = "C:\\Users\\zhengchuqin\\Desktop\\2015年5月25日版卡表.xlsx";
        String tmpFilePath = "C:\\Users\\zhengchuqin\\Desktop\\~$2015年5月25日版卡表.xlsx";

        File file = Files.copy(Paths.get(filePath), Paths.get(tmpFilePath), StandardCopyOption.REPLACE_EXISTING).toFile();


        final FileInputStream fileInputStream = new FileInputStream(file);
        System.out.println(fileInputStream.available());
        final Workbook workbook = WorkbookFactory.create(fileInputStream);
//        final XSSFWorkbook xssfSheets = new XSSFWorkbook(fileInputStream);
//        final SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(xssfSheets);

        System.out.println(workbook.getNumberOfSheets());
        final Sheet sheetAt = workbook.getSheetAt(0);
        final Row row = sheetAt.getRow(9);
        final Cell cell = row.getCell(9);
        final String stringCellValue = cell.getStringCellValue();
        System.out.println(stringCellValue);
    }

//    /**
//     * 初始化开卡银行表功能
//     */
//    private void initBankSheet() {
//        logger.info("初始化开卡银行表");
//        final int count = ibhBankCodeMapper.queryCount();
//        if (count > 0) {
//            logger.info("已存在开卡银行表，无需初始化，数量：" + count);
//            return;
//        }
//        File file = new File("C:\\Users\\zhengchuqin\\Desktop\\2015年5月25日版卡表.xls");
//        NPOIFSFileSystem fs = null;
//        HSSFWorkbook wb = null;
//        try {
//            fs = new NPOIFSFileSystem(file);
//            wb = new HSSFWorkbook(fs.getRoot(), true);
//            HSSFSheet sheetAt = wb.getSheetAt(0);
//            ArrayList<BankVo> bankVos = new ArrayList<>();
//            //从第5行开始
//            int rowNum = 4;
//            Row row = null;
//            String bankId = null;
//            String bankName = null;
//            String bankCodePrefix = null;
//            do {
//                row = sheetAt.getRow(rowNum);
//                bankName = row.getCell(0).getStringCellValue();
//                bankCodePrefix = row.getCell(13).getStringCellValue();
//                //如果取不到则退出循环
//                if (StringUtils.isBlank(bankName)) break;
//                BankVo bankVo = new BankVo();
//                bankId = UUidUtils.getId();
//                bankVo.setBankId(bankId);
//                bankVo.setBankName(bankName);
//                bankVo.setBankCodePrefix(bankCodePrefix);
//                bankVos.add(bankVo);
//                rowNum ++;
//            } while (rowNum < 5000);
//            ibhBankCodeMapper.saveList(bankVos);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (fs != null) {
//                try {
//                    fs.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

//    public String saveBankSheet(Map map) {
//        List<BankVo> list = (List<BankVo>)map.get("list");
//        StringBuilder sb = new StringBuilder();
//        sb.append("INSERT INTO ibh_bank_code (bank_id,bank_name,bank_code_prefix) VALUES ");
//        MessageFormat messageFormat = new MessageFormat("(#'{'list[{0}].bankId,jdbcType=VARCHAR},#'{'list[{0}].bankName,jdbcType=VARCHAR}," +
//                "#'{'list[{0}].bankCodePrefix,jdbcType=VARCHAR})");
//        for (int i = 0; i < list.size(); i++) {
//            sb.append(messageFormat.format(new Object[]{i}));
//            if (i != list.size() - 1){
//                sb.append(",");
//            }
//        }
//        return sb.toString();
//    }
}
