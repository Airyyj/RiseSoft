package com.risesoft.gongwen.fawen.create;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginControl {

	public static void main(String[] args) throws InterruptedException,
			IOException {

		// 调用 读取用户信息的方法

		List<String> infoList = getUserInfor("段亚平");

		// 人员登录
		if (infoList.size() == 3 && infoList.get(1) != null
				&& infoList.get(2) != null) {
			String username = infoList.get(1);
			String password = infoList.get(2);
			// 调用人员登录方法
			userLogin(username, password);
		}

		else {
			System.out.println("请检查用户信息是否正确！");
		}

	}

	// 获取人员信息的方法， 返回用户名 和密码
	private static List<String> getUserInfor(String name) throws IOException {
		String userName = name;
		FileInputStream inputStream = new FileInputStream(
				"D:\\StudyProgram\\eclipseSR\\WorkSpace\\RiseSoft\\data\\userInfo.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

		// 获取sheet 页数
		int sheetNum = workbook.getNumberOfSheets();

		// System.out.println(sheetNum);
		// 定义个 list
		List<String> infoList = new ArrayList<String>();
		// 从第一个sheet页开始 遍历

		for (int sheetIndex = 0; sheetIndex < sheetNum; sheetIndex++) {
			XSSFSheet sheet = workbook.getSheetAt(sheetIndex);

			for (int rowIndex = 1; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
				XSSFRow row = sheet.getRow(rowIndex);

				// XSSFCell cell = row
				if (row == null) {
					continue;
				}

				if (row.getCell(0) != null
						&& name.equals(row.getCell(0).toString())) {

					for (int cellnum = 0; cellnum < row.getLastCellNum(); cellnum++) {
						// 读取单元格
						XSSFCell cell = row.getCell(cellnum);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						System.out.println("第" + rowIndex + "行      第"
								+ cellnum + "列    内容为：" + cell);

						infoList.add(cell.getRichStringCellValue().getString());

						System.out.println(infoList.get(cellnum) + " "
								+ infoList.size());

					}

				} else {

					break;
				}

			}
		}

		workbook.close();
		inputStream.close();

		return infoList;

	}

	private static void userLogin(String username, String password) {

		WebDriver fireDriver = new FirefoxDriver();

		fireDriver.get("http://192.168.78.168:8000/bpm");
		fireDriver.manage().window().maximize();
		fireDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		System.out.println(fireDriver.getCurrentUrl());
		WebElement userElement = fireDriver.findElement(By.id("username"));
		// 输入获取的用户名
		userElement.clear();
		userElement.sendKeys(username);
		fireDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		WebElement passElement = fireDriver.findElement(By.id("password"));
		// 输入获取的密码
		passElement.clear();
		passElement.sendKeys(password);
		fireDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		WebElement loginElement = fireDriver.findElement(By
				.xpath(".//*[@id='align-center-div']/div[9]/img"));

		loginElement.click();
		fireDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		String currentUrl = fireDriver.getCurrentUrl();

		if (currentUrl.equals("http://192.168.78.168:8000/bpm/client/")) {
			WebElement findtextElement = fireDriver.findElement(By
					.xpath(".//*[@id='bs_bannercenter']/div/div[2]/span"));

			String textString = findtextElement.getText();

			if (textString.equals("退出系统")) {

				System.out.println("登陆成功！");
				System.out.println("操作发文");

				CreateFawen.createFawen(fireDriver);

			}

			else {
				System.out.println("登陆失败，请重新操作！");
				fireDriver.quit();
			}
		} else {
			System.out.println("请检查用户名和密码是否正确");
			fireDriver.quit();
		}

	}

}
