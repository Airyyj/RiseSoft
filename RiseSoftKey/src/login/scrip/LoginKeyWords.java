package login.scrip;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import StartEngine.StartEngine_Excel;
import utility.Contants;
import utility.OrpUtil;

public class LoginKeyWords {
	public static WebDriver driver=null;
	
//	启动浏览器并最大化
	public static void OpenBrowser (String OR){
		
		
		try {
			
			driver= new FirefoxDriver();
			driver.manage().window().maximize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			StartEngine_Excel.bResult=false;
			
		}
		
		
	}

//	打开开源中国网址
	public static void Navigate (String OR){
		driver.get(Contants.url);
	}
	
//	点击登录
	
	
	public static void Login_Click (String OR){
		try {
			driver.findElement(By.xpath(OrpUtil.readValue(OR))).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			StartEngine_Excel.bResult=false;
		}
	}
	
//	输入用户名
	public static void Input_Name (String OR){
		try {
			driver.findElement(By.xpath(OrpUtil.readValue(OR))).clear();
			driver.findElement(By.xpath(OrpUtil.readValue(OR))).sendKeys(Contants.userName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			StartEngine_Excel.bResult=false;
		}
	}
	
//	输入密码
	public static void Input_Password (String OR){
		try {
			driver.findElement(By.xpath(OrpUtil.readValue(OR))).click();
			driver.findElement(By.xpath(OrpUtil.readValue(OR))).sendKeys(Contants.userPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			StartEngine_Excel.bResult=false;
		}
	}
	
//	点击登录按钮
	public static void Login_Button (String OR){
		try {
			driver.findElement(By.xpath(OrpUtil.readValue(OR))).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			StartEngine_Excel.bResult=false;
		}
	}

	//	点击退出按钮
	public static void Logout_Click (String OR){
		try {
			Thread.sleep(600);
			driver.findElement(By.xpath(OrpUtil.readValue(OR))).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			StartEngine_Excel.bResult=false;
		}
		
	}

//	关闭浏览器
	public static void CloseBrowser (String OR){
		driver.quit();
	}

}




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



















