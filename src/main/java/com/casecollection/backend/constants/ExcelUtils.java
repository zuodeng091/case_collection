package com.casecollection.backend.constants;

import org.apache.poi.ss.usermodel.Cell;

import java.text.NumberFormat;

/**
 * Created by zuodeng on 16/4/10.
 */
public class ExcelUtils {

    /**
     * 获取Excel单元格中得数据，作为字符串
     *
     * @param cell
     * @return
     */
    public static String getCellStringValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        Integer type = cell.getCellType();
        if (type == Cell.CELL_TYPE_BLANK) {
            return "";
        } else if (type == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (type == Cell.CELL_TYPE_NUMERIC) {
            NumberFormat nf = NumberFormat.getInstance();
            nf.setGroupingUsed(false);
            return nf.format(cell.getNumericCellValue());
        } else if (type == Cell.CELL_TYPE_STRING) {
            return cell.getStringCellValue();
        }
        return null;
    }

}
