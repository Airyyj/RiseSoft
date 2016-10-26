package com.risesoft.gongwen.fawen.create;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateFawen {

	public static void createFawen(WebDriver fireDriver) {

		WebDriver fireWebDriver = fireDriver;

		// --------------------------------------
		// 切换frame

		// 该页面有多个iframe ，下面是切换frame
		fireWebDriver.switchTo().frame("frmleft");
		// 点击待办件
		fireWebDriver.findElement(By.id("tree_33_span")).click();

		fireWebDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		// 从一个iframe 切换到另一个 iframe 时要先切换到 默认的iframe 中，否则，直接切换，会报错。

		fireWebDriver.switchTo().defaultContent();

		fireWebDriver.switchTo().frame("frmright");
		// 点击办理
		fireWebDriver.findElement(By.className("icon_pencil")).click();
		fireWebDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		// --------------------------------------

		// +++++++++++++++++++++++++++++++++++++++++++++
		// 切换新旧窗口
		// 此处点击办理后 页面会打开一个新的窗口，所以需要切换到新的窗口
		// 下面时获取当前窗口的句柄。
		String currentHandle = fireWebDriver.getWindowHandle();

		// 下面是获取 所有窗口的句柄
		Set<String> handles = fireWebDriver.getWindowHandles();

		for (String handle : handles) {

			System.out.println("handle:" + handle);

			System.out.println("currentHandle:" + currentHandle);
			if (handle.equals(currentHandle)) {
				System.out.println("1");
				continue;

			} else {
				// 切换到最新的窗口
				System.out.println("2");
				fireWebDriver.switchTo().window(handle);

				System.out.println("切换到最新窗口！");

				// 下面equals是判断是否相等，contains是判断是否包含
				// if (fireWebDriver.getTitle().equals("国家发改委网上办公系统")) {
				if (fireWebDriver.getTitle().contains("国家发改委网上办公系统")) {
					System.out.println("Switch to window: " + "国家发改委办公系统"
							+ " successfully!");
					break;
				} else
					continue;
			}
		}
		fireWebDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// +++++++++++++++++++++++++++++++++++++++++++++

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// 操作 复选框
		fireWebDriver.switchTo().frame("formIframe");
		fireWebDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		fireWebDriver.findElement(By.id("bwd_bwd_islinklw")).click();
		fireWebDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
		// 操作 下拉选项 看似下拉选项，其实不是，而是两个步骤，先打开下拉框，再点击 选中内容
		// 处理下拉选项时 要先new一个对象然后对其操作
		fireWebDriver.findElement(By.id("3_button")).click();

		fireWebDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		fireWebDriver.findElement(By.id("3_input_2")).click();
		fireWebDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		// 发送 首先切换会默认 frame
		fireWebDriver.switchTo().defaultContent();

		// 鼠标悬浮 此处时鼠标悬浮，所以需要 进行鼠标悬浮处理，然后再操作点击，要不然找不到元素
		Actions builder = new Actions(fireWebDriver);
		builder.moveToElement(
				fireWebDriver.findElement(By.className("icon_email")))
				.perform();
		fireWebDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		// 点击悬浮出来的元素
		fireWebDriver.findElement(By.className("icon_lightOn")).click();
		// 点击弹出框确定
		fireWebDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		fireWebDriver.findElement(By.id("_ButtonCancel_0")).click();

		// 跳转到办文要报，切换frame
		fireWebDriver.switchTo().frame("formIframe");
		// 填写 呈送单位 此处后期可以做参数化

		fireWebDriver.findElement(By.id("cslddeptId")).sendKeys("测试呈送单位1803");

		// 填写 标题 此处可以参数化，根据日期做参数

		fireWebDriver.findElement(By.id("bwyb_bwyb_bwybtitle")).sendKeys(
				"测试办文要报标题1803");

		// 填写拟稿人

		fireWebDriver.findElement(By.className("img_pencil")).click();

		// 发送 首先切换会默认 frame
		fireWebDriver.switchTo().defaultContent();

		// 鼠标悬浮 此处时鼠标悬浮，所以需要 进行鼠标悬浮处理，然后再操作点击，要不然找不到元素

		builder.moveToElement(
				fireWebDriver.findElement(By.className("icon_email")))
				.perform();
		fireWebDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		// 点击悬浮出来的元素
		fireWebDriver.findElement(By.className("icon_lightOn")).click();
		// 点击弹出框确定
		fireWebDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		fireWebDriver.findElement(By.id("_ButtonCancel_0")).click();

		// 页面跳转到 发稿 切换frame
		fireWebDriver.switchTo().frame("formIframe");
		// 选择文件密级
		fireWebDriver.findElement(By.id("2_button")).click();
		fireWebDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		fireWebDriver.findElement(By.id("2_input_机密")).click();
		fireWebDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		// 填写标题
		fireWebDriver.findElement(By.id("gaozhi_gaozhi_fwtitle")).sendKeys(
				"测试发文稿纸标题1803");

		// 填写主送单位
		fireWebDriver.findElement(By.id("gaozhi_gaozhi_zsdept")).sendKeys(
				"测试主送单位1803");

		// 填写拟稿人
		fireWebDriver.findElement(By.className("img_pencil")).click();

		// 选择规范性
		fireWebDriver.findElement(By.id("5_button")).click();

		fireWebDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		fireWebDriver.findElement(By.id("5_input_2")).click();
		fireWebDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		// 发送 首先切换会默认 frame
		fireWebDriver.switchTo().defaultContent();

		// 鼠标悬浮 此处时鼠标悬浮，所以需要 进行鼠标悬浮处理，然后再操作点击，要不然找不到元素

		builder.moveToElement(
				fireWebDriver.findElement(By.className("icon_email")))
				.perform();
		fireWebDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		fireWebDriver.findElement(By.className("icon_lightOn")).click();
		// 点击弹出框确定
		fireWebDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		// 选择发送对象 但要先切换frame
		fireWebDriver.switchTo().frame("_DialogFrame_0");
		fireWebDriver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

		fireWebDriver.findElement(By.id("orgTree_7_span")).click();
		fireWebDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		// 确定人选
		fireWebDriver.findElement(By.id("send")).click();

		// 切换会默认frame
		fireWebDriver.switchTo().defaultContent();
		fireWebDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		// 完成确认
		fireWebDriver.findElement(By.id("_ButtonCancel_1")).click();

		// 至此完成发文起草的操作

		System.out.println("到此发文发送完成");
		fireDriver.quit();

	}

	// 定义一个获取参数方法

	// 获取时间
	public String dateNum() {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		Date nowtime = new Date();
		String dateNum = simpleDateFormat.format(nowtime);
		System.out.println(dateNum);

		return null;

	}

}
