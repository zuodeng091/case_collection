package com.casecollection.common.utils;

import com.eleme.export.util.ExcelUtils;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

public class ExportUtil {
	public static void doExport(HttpServletResponse response, List data, String fileName, Class clazz, String propName) {

		try {
			Workbook wb = ExcelUtils.export(data, clazz, propName);
			response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"; filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
			response.setContentType("application/msexcel");
			wb.write(response.getOutputStream());
		} catch (IOException e) {
		}
	}
	
	public static void doExport(HttpServletResponse response, List data, String fileName, Class clazz) {
		try {
			Workbook wb = ExcelUtils.export(data, clazz, null);
			response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"; filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
			response.setContentType("application/msexcel");
			wb.write(response.getOutputStream());
		} catch (IOException e) {
		}
	}

    public static void doExport(HttpServletResponse response, Workbook wb, String fileName) {
        try {
            response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"; filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType("application/msexcel");
            wb.write(response.getOutputStream());
        } catch (IOException e) {
        }
    }
}
