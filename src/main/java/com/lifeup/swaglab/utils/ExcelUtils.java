package com.lifeup.swaglab.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static Object[][] readExcelData(String fileName, String sheetName) {
		Object[][] objects = null;
		String filePath = GlobalIdentifier.getProjectBasePath() + "\\src\\test\\resource\\data\\" + fileName + ".xlsx";
		File file = new File(filePath);

		try {
			FileInputStream fs = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetName);

			int rows = sheet.getLastRowNum();
			int clms = sheet.getRow(0).getLastCellNum();

			objects = new Object[rows + 1][clms];
			DataFormatter df = new DataFormatter();
			// rows
			for (int i = 0; i < (rows + 1); i++) {
				// clms
				for (int j = 0; j < clms; j++) {
					objects[i][j] = df.formatCellValue(sheet.getRow(i).getCell(j));
				}
			}
			workbook.close();
			fs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objects;

	}

	public static Object[][] readExcelDataInKeyValuePair(String fileName, String sheetName) {
		Object[][] obj = null;
		List<Map<String, String>> list = new ArrayList<>();

		String filePath = GlobalIdentifier.getProjectBasePath() + "\\src\\test\\resource\\data\\" + fileName + ".xlsx";
		File file = new File(filePath);

		try {
			FileInputStream fs = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetName);

			int rows = sheet.getLastRowNum();
			int clms = sheet.getRow(0).getLastCellNum();

			DataFormatter df = new DataFormatter();
			// rows
			for (int i = 1; i < (rows + 1); i++) {
				Map<String, String> map = new HashMap<String, String>();

				// clms
				for (int j = 0; j < clms; j++) {
					// key
					String key = df.formatCellValue(sheet.getRow(0).getCell(j));
					// value
					String value = df.formatCellValue(sheet.getRow(i).getCell(j));

					map.put(key, value);
				}
				list.add(map);
			}
			workbook.close();
			fs.close();

			// Excel row data store in object array
			obj = new Object[list.size()][1];
			for (int i = 0; i < list.size(); i++) {
				obj[i][0] = list.get(i);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

}
