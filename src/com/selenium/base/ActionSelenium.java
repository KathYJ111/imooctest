package com.selenium.base;

import java.awt.event.ItemEvent;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ActionSelenium {
	public WebDriver driver;
	public String windowsHandle;
	/**
	 * 初始化driver
	 * */
	public void InitDriver(){
		System.setProperty("webdriver.chrome.driver", "C:/Users/Administrator/AppData/Local/Google/Chrome/Application/chromedriver.exe");
		driver = new ChromeDriver();
		String url = "http://www.imooc.com";
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
		driver.get("xxxxxx");
		driver.findElement(By.id("signup-form")).submit();//调用submit方法，就是一个简单的表单提交
	}
	/**
	 * upfile文件上传
	 * case:慕课网个人中心修改头像
	 * 难点："更换头像"的文字会隐藏
	 * 三种解决思路：1、用driver执行隐藏js修改源码的命令；2、模拟鼠标放在元素上，然后进行点击；3、借助autoit上传。
	 * */
	//方式一：用driver执行隐藏js修改源码的命令。
	public void upfile_js(){
		driver.get("https://www.imooc.com/user/setbindsns");
		this.sleep(5000);//调用等待的方法
		//用js命令将更换头像按钮设置为不隐藏
		String js = "document.getElementsByClassName('update-avator'[0].style.bottom='0px';)";
		JavascriptExecutor jscript = (JavascriptExecutor) driver;
		jscript.executeScript(js);
		this.sleep(2000);
		driver.findElement(By.id("js-avator-link")).click();//点击更换头像按钮
		driver.findElement(By.id("upload")).sendKeys("C:\\Users\\Administrator\\Desktop\\test.png");
		driver.findElement(By.linkText("确定")).click();
	}
	//方式二：模拟鼠标放在头像上，然后进行点击；
	//应用场景：上传头像的标签是input类型，直接通过sendkeys上传文件
	//实现方法：调用手势操作类，将鼠标移动到头像上
	public void upfile_mouse(){
		driver.get("https://www.imooc.com/user/setbindsns");
		this.sleep(3000);
		WebElement header = driver.findElement(By.className("update-avator"));
		Actions ac = new Actions(driver);
		ac.moveToElement(header).perform();//将鼠标移动到头像上
		driver.findElement(By.className("js-avator-link")).click();//点击更换头像按钮
		driver.findElement(By.id("upload")).sendKeys("C:\\Users\\Administrator\\Desktop\\test.png");//定位上传元素的按钮，并通过sendkeys上传文件
		driver.findElement(By.linkText("确定")).click();
	}
	//方式三：借助autoit上传
	//应用场景：上传头像的标签如果不是input标签，就只能通过autoit工具上传
	//实现方法：打开选择文件的弹框以后，通过autoIt工具实现头像上传
	public void upfile_autoit(){
		driver.get("https://www.imooc.com/user/setbindsns");
		this.sleep(3000);
		WebElement header = driver.findElement(By.className("updata-avator"));
		Actions ac = new Actions(driver);
		ac.moveToElement(header).perform();
		driver.findElement(By.className("js-avator-link")).click();
		driver.findElement(By.className("avator-btn-fake")).click();//点击上传头像的按钮，弹出文件选择框
		//执行autoit命令文件，进行头像上传
		try {
			Runtime.getRuntime().exec("C:\\Users\\Administrator\\Desktop\\upfile.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.linkText("确定")).click();
	}
	/**
	 * 封装等待时间的方法
	 * */
	public void sleep(int sleeptime){
		try {
			Thread.sleep(sleeptime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 下拉框操作
	 * case：慕课网个人中心页面，选择职业下拉框
	 * */
	public void downSelectBox(){
		driver.get("http://www.imooc.com/user/setprofile");
		driver.findElement(By.className("pull-right")).click();
		this.sleep(3000);
		WebElement formElement = driver.findElement(By.id("profile"));
		WebElement job = formElement.findElement(By.id("job"));
		Select downList = new Select(job);//selenium自带的select方法，仅用于select标签
		//downList.selectByIndex(2);//通过index方法进行选择
		//downList.selectByValue("1");//通过value方式进行定位
		downList.selectByVisibleText("学生");//通过visibleText方式进行定位
		downList.isMultiple();//判断是否是下拉多选列表
		//获取当前下拉框所有值的内容，用于多选列表
		List<WebElement> list = downList.getAllSelectedOptions();
		for (WebElement options : list){
			options.getText();
		}
		downList.getFirstSelectedOption();//获取当前下拉框的第一个值的内容
	}
	/**
	 * 常用的鼠标操作：鼠标左击、鼠标双击、鼠标右击、鼠标悬停
	 * */
	public void mouseActions(){
		WebElement login = driver.findElement(By.className("menuContent"));
		List<WebElement> item = login.findElement(By.className("item"));
		Actions ac = new Actions(driver);
		ac.click(login).perform();//鼠标左击元素
		ac.doubleClick(login).perform();//鼠标双击元素
		ac.contextClick(login).perform();//鼠标右击元素
		ac.moveToElement(item.get(0)).perform();//鼠标悬停在元素上
		//获取当前窗口的信息
		windowsHandle = driver.getWindowHandle();
		driver.findElement(By.linkText("HTML/CSS")).click();
	}
	/**
	 * windowsHandles窗口切换
	 * */
	public void windowsHandle(){
		Set<String> handles = driver.getWindowHandles();//获取所有窗口的信息
		for (String s:handles) {
			if (s.equals(windowsHandle)){
				continue;
			}
			System.out.println(s);
			driver.switchTo().window(s);
		}
	}
	/**
	 * iframe富文本编辑框切换
	 * */
	public void iframe(){
		driver.get("http://www.imooc.com/wiki/create");
		WebElement iframeElement = driver.findElement(By.id("ueditor_0"));//先定位到iframe
		driver.switchTo().frame(iframeElement);//再切换到iframe窗口
		driver.findElement(By.tagName("body")).sendKeys("This is test.");
	}
	
	
	public static void main(String[] args) {
		ActionSelenium as = new ActionSelenium();
		as.InitDriver();
//		as.InputBox();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
