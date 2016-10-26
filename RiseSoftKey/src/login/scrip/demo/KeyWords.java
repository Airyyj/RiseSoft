package login.scrip.demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class KeyWords {
	public static WebDriver FirefoxDriver = null;
	
	//打开浏览器
	public static void openBrowser() {
		FirefoxDriver = new FirefoxDriver();
		FirefoxDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		FirefoxDriver.manage().window().maximize();

	}
	//输入网址
	public static void getUrl() {
		
		FirefoxDriver.get(Parameter.url);
		FirefoxDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		System.out.println(FirefoxDriver.getCurrentUrl());

	}
	// 输入用户名
	public static void inputUserName(String loginName) {
		FirefoxDriver.findElement(By.id("username")).clear();
		FirefoxDriver.findElement(By.id("username")).sendKeys(loginName);

	}
	// 输入密码
	public static void inPutPassWord(String loginPassWord) {
		WebElement passElement = FirefoxDriver.findElement(By.id("password"));
		// 输入获取的密码
		passElement.clear();
		passElement.sendKeys(loginPassWord);
		FirefoxDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	//点击登录
	public static void clickButton() {
		WebElement loginElement = FirefoxDriver.findElement(By
				.xpath(".//*[@id='align-center-div']/div[9]/img"));

		loginElement.click();
		FirefoxDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
	}
	// 验证是否成功登录
	public static boolean verifyLogin() {
		String currentUrl = FirefoxDriver.getCurrentUrl();
		System.out.println(currentUrl);
		if (currentUrl.equals("http://192.168.78.168:8000/bpm/client/")) {
			WebElement findtextElement = FirefoxDriver.findElement(By
					.xpath(".//*[@id='bs_bannercenter']/div/div[2]/span"));

			String textString = findtextElement.getText();

			if (textString.equals("退出系统")) {

				System.out.println("登陆成功！");
				return true;
				//System.out.println("操作发文");

				//CreateFawen.createFawen(FirefoxDriver);
				

			}

			else {
				System.out.println("登陆失败，请重新操作！");
				return false;
			}
		} else {
			System.out.println("请检查用户名和密码是否正确");
			return false;
		}
		
	}
	// 关闭浏览器
	public static void clossBrowser() {
		System.out.println("关闭浏览器");
		FirefoxDriver.quit();
		

	}

}
