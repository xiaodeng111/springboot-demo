package com.demo.clockin.common.lang;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ExcelWriter {

	private static Logger logger = LoggerFactory.getLogger(ExcelWriter.class);

	@SuppressWarnings("deprecation")
	public static HSSFWorkbook export(String[] headers, List<Object[]> list) {
		logger.info("开始写Excel：{}", list == null ? "null" : list.size());

		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();

		HSSFCellStyle hStyle = workbook.createCellStyle();
		hStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		hStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中

		HSSFFont font = workbook.createFont();
		font.setFontName("微软雅黑");
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 14);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		hStyle.setFont(font);
		logger.info("样式初始化完成");

		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet();

		HSSFRow row = sheet.createRow(0);

		int column = headers.length;
		for (int i = 0; i < column; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(headers[i]);
			cell.setCellStyle(hStyle);
			sheet.autoSizeColumn(i, true);
		}
		logger.info("表头初始化完成：{}", column);

		HSSFCellStyle cStyle = workbook.createCellStyle();
		cStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		cStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		cStyle.setWrapText(true);

		logger.info("开始填充单元格");
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				row = sheet.createRow(i + 1);
				Object[] obj = list.get(i);
				for (int j = 0; j < column; j++) {
					HSSFCell cell = row.createCell(j);
					cell.setCellStyle(cStyle);
					String value = obj[j] == null ? "" : obj[j].toString();
					cell.setCellValue(value);
					if (i == 0) {
						sheet.autoSizeColumn(j, true);
					}
					// sheet.setColumnWidth(j, value.length() * 512);
				}
				int n = i + 1;
				if (n % 1000 == 0) {
					logger.info("完成第{}条数据", n);
				}
			}
		}
		logger.info("单元格填充完毕");

		return workbook;
	}

	@SuppressWarnings("deprecation")
	public static HSSFWorkbook exportWithExpand(String[] headers, List<Object[]> list) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();

		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFFont font = workbook.createFont();
		font.setFontName("微软雅黑");

		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 14);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		style.setFont(font);

		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet();

		HSSFRow row = sheet.createRow(0);

		int column = headers.length;
		for (int i = 0; i < column; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(headers[i]);
			cell.setCellStyle(style);
			sheet.autoSizeColumn(i, true);
		}

		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i + 1);
			Object[] obj = list.get(i);
			for (int j = 0, n = obj.length; j < n; j++) {
				row.createCell(j).setCellValue(obj[j] == null ? "" : obj[j].toString());
			}
		}

		return workbook;
	}
	
	
	
/*
	// xlxs 高版本
	public static XSSFWorkbook exportXSSF(String[] headers, List<Object[]> list) {
		logger.info("开始写Excel：{}", list == null ? "null" : list.size());

		// 声明一个工作薄
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFCellStyle hStyle = workbook.createCellStyle();
		hStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		hStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		

		XSSFFont font = workbook.createFont();
		font.setFontName("微软雅黑");
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 14);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		hStyle.setFont(font);
		
		
		hStyle.setFont(font);
		logger.info("样式初始化完成");

		// 生成一个表格
		XSSFSheet sheet = workbook.createSheet();

		XSSFRow row = sheet.createRow(0);

		int column = headers.length;
		for (int i = 0; i < column; i++) {
			XSSFCell cell = row.createCell(i);
			cell.setCellValue(headers[i]);
			cell.setCellStyle(hStyle);
			sheet.autoSizeColumn(i, true);
		}
		logger.info("表头初始化完成：{}", column);

		XSSFCellStyle cStyle = workbook.createCellStyle();
		cStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		cStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		cStyle.setWrapText(true);

		logger.info("开始填充单元格");
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				row = sheet.createRow(i + 1);
				Object[] obj = list.get(i);
				for (int j = 0; j < column; j++) {
					XSSFCell cell = row.createCell(j);
					cell.setCellStyle(cStyle);
					String value = obj[j] == null ? "" : obj[j].toString();
					cell.setCellValue(value);
					if (i == 0) {
						sheet.autoSizeColumn(j, true);
					}
					if(j==1){
						sheet.setColumnWidth(j, 10 * 256 * 4);
					}else{
						sheet.autoSizeColumn(j, true);
					}
					// sheet.setColumnWidth(j, value.length() * 512);
				}
				int n = i + 1;
				if (n % 1000 == 0) {
					logger.info("完成第{}条数据", n);
				}
			}
		}
		logger.info("单元格填充完毕");

		return workbook;
	}
*/	
}
