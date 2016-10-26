package login.scrip;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelHandle {
	
	public static HSSFSheet ExcelSheet;
	public static HSSFWorkbook	ExcelBook;
	public static HSSFRow Row;
	public static HSSFCell	Cell;
	
	/**
	 * 加载Excel
	 * @param Path
	 * 文件路径
	 */
	public static void setExcelFile(String Path) {
		FileInputStream ExcelFile;
		try {
			ExcelFile = new FileInputStream(Path);
			ExcelBook=new HSSFWorkbook(ExcelFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}					
	}
	
	/**
	 * 值得写入
	 * @param Result
	 * @param RowNum
	 * @param ColNum
	 * @param Path
	 * @param SheetName
	 */
	public static void setCellData(String Result,  int RowNum, int ColNum,String Path,String SheetName) {
		try {
			ExcelSheet=ExcelBook.getSheet(SheetName);
			Row  = ExcelSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
				} else {
					Cell.setCellValue(Result);
				}
			FileOutputStream fileOut = new FileOutputStream(Path);
			ExcelBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取Excel中对应单元格的值
	 * @param RowNum
	 * @param CloNum
	 * @param SheetName
	 * @return
	 */
	public static String getCellDate(int RowNum,int CloNum,String SheetName){
		ExcelSheet=ExcelBook.getSheet(SheetName);
		Cell=ExcelSheet.getRow(RowNum).getCell(CloNum);
		String cellData=Cell.getStringCellValue();
		return cellData;

	}
	
	/**
	 * 获取到sheet页最后一行
	 * @param SheetName
	 * @return
	 * 
	 */
	public static int getLastRowNums(String SheetName) {
	    try {
	    	ExcelSheet=ExcelBook.getSheet(SheetName);
		    int rowCount = ExcelSheet.getLastRowNum();
	        return rowCount;
	    }catch (Exception e){
	        throw(e);
		    }
	    }				
}
