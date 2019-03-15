package com.selenium.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

public class ActionSelenium {
	public WebDriver driver;
	/**
	 * 初始化driver
	 * */
	public void InitDriver(){
		System.setProperty("webdriver.chrome.driver", "C:/Users/Administrator/AppData/Local/Google/Chrome/Application/chromedriver.exe");
		driver = new ChromeDriver();
		String url = "www.imooc.com";
		driver.get(url);
	}
	/**
	 * 输入框(input)元素处理
	 * */
	public void InputBox(){
		WebElement user = driver .findElement(By.name("email"));//定位到账号输入框
		user.sendKeys("13991166949");//输入账号
		user.clear();//清除输入框的内容
		user.sendKeys("13991166949");//再次输入账号
		String user_info = user.getAttribute("placeholder");//获取元素的placeholder属性值
		System.out.println(user_info);
		String user_value = user.getAttribute("value");//获取value的属性值
		System.out.println(user_value);
		boolean enable = user.isEnabled();//判断元素是否有效
		System.out.println(enable);
		driver.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(By.className("btn-red")).click();
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * 单选框radio元素处理
	 * case:immooc的编辑个人信息页面，选中性别
	 * */
	public void radio(){
		driver.get("http://www.baidu.com");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//js命令：隐藏遮罩窗元素的js命令
		String jscript = "document.getElementById('J_GotoTop').style.display='none'";
		//将js命令转换为driver
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//执行js命令
		js.executeScript(jscript);
		driver.findElement(By.className("pull-right")).click();
		//获取选择性别的元素
		WebElement sex_node = driver.findElement(By.xpath("//*[@id='profile']/div[4]/div"));
		//获取该元素下所有的input标签
		List<WebElement> list_sex = sex_node.findElements(By.tagName("input"));
		//遍历集合
		for (WebElement list:list_sex) {
			boolean sex = list.isSelected();//获取元素是否被选中，如果被选中就继续循环，直到有一个元素被点击到
			if (sex == true) {
				continue;
			}else {
				list.click();
				break;
			}
		}
	}
	/**
	 * 多选框check元素处理
	 * case：imooc登录页的是否勾选7天免登陆框
	 * */
	public void checkBox(){
		WebElement check = driver.findElement(By.id("auto-signin"));
		check.isSelected();//元素是否被选中
		check.isEnabled();//元素是否有效
//		check.clear();//错误示例：clear在这里不会生效，有些多选框可用，有些多选框不可用
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		check.click();//对多选框进行点击
	}
	/**
	 * 按钮button元素处理
	 * case：immoc网站的登录按钮
	 * */
	public void button(){
		WebElement login_button = driver.findElement(By.className("btn-red"));
		//使用js命令，将元素隐藏
		String jScript = "document.getElementsClassName('btn-red')[0].style.display='none'";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(jScript);
		boolean is_display = login_button.isDisplayed();//查看元素是否被显示
		System.out.println(is_display);//元素被隐藏成功
		boolean is_enable = login_button.isEnabled();//查看元素是否生效
		System.out.println(is_enable);//这里应该是不生效的，但实际结果显示生效，原因是isEnabled仅适用于编辑框
		login_button.click();
	}
	
	/**
	 * webform表单提交
	 * case：immoc登录页 
	 * 特别说明：表单提交只会以get的形式提交到当前页面的URL，有些场景适合，有些场景不适合。
	 * */
	public void web_form(){
		driver.findElement(By.id("signup-form")).submit();//调用submit方法，就是一个简单的表单提交
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
