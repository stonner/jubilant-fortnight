package zcq.myjpa.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.awt.Color;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/09/23
 */
public class ExcelUtils {
    /**
     * 根据输入的数据生成一个XSSFWorkbook
     * @param title：sheet名称
     * @param propertyHeaderMap：<property, header>（<T中的property名称、有getter就行, 对应显示在Excel sheet中的列标题>）
     * 用LinkedHashMap保证读取的顺序和put的顺序一样
     * @param dataSet：实体类集合
     * @return：XSSFWorkbook
     */
    public static <T> Workbook generateXlsxWorkbook(String title, LinkedHashMap<String,String> propertyHeaderMap, List<List<T>> dataSet) {
        // 声明一个工作薄
        SXSSFWorkbook workbook = new SXSSFWorkbook(1000);
        // 生成一个表格
        Sheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);
        CellStyle headerStyle = getHeaderStyle(workbook);
        CellStyle contentStyle = getContentStyle(workbook);


        //第一行 合并单元格
        CellRangeAddress cra = new CellRangeAddress(0,0,0,propertyHeaderMap.size()); // 起始行, 终止行, 起始列, 终止列
        sheet.addMergedRegion(cra);
        Row head = sheet.createRow(0);
        Cell headCell = head.createCell(0);
        headCell.setCellValue(title);
        head.setHeight((short)650);
        headCell.setCellStyle(getMergeHeadStyle(workbook, IndexedColors.BLACK.getIndex()));

        // 生成字段标题行
        int k=1;
        Row row = sheet.createRow(k);
        row.setHeight((short) 500);
        int i = 0;
        for(String key : propertyHeaderMap.keySet()){
            sheet.setColumnWidth(i,5000);
            Cell cell = row.createCell(i);
            cell.setCellStyle(headerStyle);
            XSSFRichTextString text = new XSSFRichTextString(propertyHeaderMap.get(key));
            cell.setCellValue(text);
            i++;
        }

        //循环dataSet，每一条对应一行
        int index = k;
        if (dataSet!=null){
            for(List<T> datas : dataSet){
                for (T data:datas){
                    index ++;
                    row = sheet.createRow(index);
                    row.setHeight((short) 500);
                    int j = 0;
                    for(String property : propertyHeaderMap.keySet()){
                        Cell cell = row.createCell(j);
                        j++;
                        cell.setCellStyle(contentStyle);
                        //拼装getter方法名
                        String getMethodName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1);
                        try {
                            //利用反射机制获取dataSet中的属性值，填进cell中
                            Class<? extends Object> tCls = data.getClass();
                            Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
                            Object value = getMethod.invoke(data, new Object[] {}); //调用getter从data中获取数据
                            if (value == null){
                                value = "-";
                            }
                            // 判断值的类型后进行类型转换
                            String textValue;
                            if (value instanceof Date) {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                if (property.equals("applyDate") ||property.equals("correspondDate")|| property.equals("createDate")){
                                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                                }
                                Date date = (Date) value;
                                textValue = sdf.format(date);
                            } else {
                                // 其它数据类型都当作字符串简单处理
                                textValue = value.toString();
                            }
                            XSSFRichTextString richString = new XSSFRichTextString(textValue);
                            cell.setCellValue(richString);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
        return workbook;
    }

    /**
     * 生成一个标题style
     * @return style
     * @param workbook
     */

    public static CellStyle getHeaderStyle(Workbook workbook){
        return getHeaderStyle(workbook, java.awt.Color.GRAY, IndexedColors.BLACK.getIndex());
    }

    /**
     * 合并行style
     * @param workbook
     * @param fontColor
     * @return
     */
    public static CellStyle getMergeHeadStyle(Workbook workbook, short fontColor){

        // 生成一个样式（用于标题）
        CellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//水平居中
        style.setWrapText(true);//自动换行
        // 生成一个字体
        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setColor(fontColor);
        font.setFontHeightInPoints((short) 25);
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }

    /**
     * 生成一个指定颜色的标题style
     * @param workbook
     * @param foregroundColor
     * @param fontColor
     * @return
     */
    public static CellStyle getHeaderStyle(Workbook workbook, Color foregroundColor, short fontColor){

        // 生成一个样式（用于标题）
        CellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//水平居中
        style.setWrapText(true);//自动换行
        // 生成一个字体
        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setColor(fontColor);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }

    /**
     * 生成一个用于内容的style
     * @param workbook
     * @return
     */
    public static CellStyle getContentStyle(Workbook workbook){
        // 生成并设置另一个样式（用于内容）
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//水平居中
        style.setWrapText(true);//自动换行
        // 生成另一个字体
        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }

}
