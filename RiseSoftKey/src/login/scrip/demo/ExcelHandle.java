package login.scrip.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHandle {
	
	public static XSSFSheet ExcelSheet;
	public static XSSFWorkbook	ExcelBook;
	public static XSSFRow Row;
	public static XSSFCell	Cell;
	
	
	/**
	 * 加载Excel
	 * @param Path
	 * 文件路径
	 */
	public static void setExcelFile(String Path) {
		FileInputStream ExcelFile;
		try {
			ExcelFile = new FileInputStream(Path);
			ExcelBook=new XSSFWorkbook(ExcelFile);
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
			//设置表格边框
			XSSFCellStyle cellStyle = ExcelBook.createCellStyle();
			 //下边框    
			cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
			//左边框    
			cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
			//上边框    ;
			cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
			//右边框 
			cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
			// 居中  
			cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); 
			Row  = ExcelSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
			//保存边框
			Cell.setCellStyle(cellStyle);
			
			
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
		Cell.setCellType(Cell.CELL_TYPE_STRING);
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
