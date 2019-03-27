package com.selenium.base;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ActionSelenium {
	public WebDriver driver;
	/**
	 * ��ʼ��driver
	 * */
	public void InitDriver(){
		System.setProperty("webdriver.chrome.driver", "C:/Users/Administrator/AppData/Local/Google/Chrome/Application/chromedriver.exe");
		driver = new ChromeDriver();
		String url = "http://www.imooc.com";
		driver.get(url);
	}
	/**
	 * �����(input)Ԫ�ش���
	 * */
	public void InputBox(){
		WebElement user = driver .findElement(By.name("email"));//��λ���˺������
		user.sendKeys("13991166949");//�����˺�
		user.clear();//�������������
		user.sendKeys("13991166949");//�ٴ������˺�
		String user_info = user.getAttribute("placeholder");//��ȡԪ�ص�placeholder����ֵ
		System.out.println(user_info);
		String user_value = user.getAttribute("value");//��ȡvalue������ֵ
		System.out.println(user_value);
		boolean enable = user.isEnabled();//�ж�Ԫ���Ƿ���Ч
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
	 * ��ѡ��radioԪ�ش���
	 * case:immooc�ı༭������Ϣҳ�棬ѡ���Ա�
	 * */
	public void radio(){
		driver.get("http://www.baidu.com");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//js����������ִ�Ԫ�ص�js����
		String jscript = "document.getElementById('J_GotoTop').style.display='none'";
		//��js����ת��Ϊdriver
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//ִ��js����
		js.executeScript(jscript);
		driver.findElement(By.className("pull-right")).click();
		//��ȡѡ���Ա��Ԫ��
		WebElement sex_node = driver.findElement(By.xpath("//*[@id='profile']/div[4]/div"));
		//��ȡ��Ԫ�������е�input��ǩ
		List<WebElement> list_sex = sex_node.findElements(By.tagName("input"));
		//��������
		for (WebElement list:list_sex) {
			boolean sex = list.isSelected();//��ȡԪ���Ƿ�ѡ�У������ѡ�оͼ���ѭ����ֱ����һ��Ԫ�ر������
			if (sex == true) {
				continue;
			}else {
				list.click();
				break;
			}
		}
	}
	/**
	 * ��ѡ��checkԪ�ش���
	 * case��imooc��¼ҳ���Ƿ�ѡ7�����½��
	 * */
	public void checkBox(){
		WebElement check = driver.findElement(By.id("auto-signin"));
		check.isSelected();//Ԫ���Ƿ�ѡ��
		check.isEnabled();//Ԫ���Ƿ���Ч
//		check.clear();//����ʾ����clear�����ﲻ����Ч����Щ��ѡ����ã���Щ��ѡ�򲻿���
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		check.click();//�Զ�ѡ����е��
	}
	/**
	 * ��ťbuttonԪ�ش���
	 * case��immoc��վ�ĵ�¼��ť
	 * */
	public void button(){
		WebElement login_button = driver.findElement(By.className("btn-red"));
		//ʹ��js�����Ԫ������
		String jScript = "document.getElementsClassName('btn-red')[0].style.display='none'";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(jScript);
		boolean is_display = login_button.isDisplayed();//�鿴Ԫ���Ƿ���ʾ
		System.out.println(is_display);//Ԫ�ر����سɹ�
		boolean is_enable = login_button.isEnabled();//�鿴Ԫ���Ƿ���Ч
		System.out.println(is_enable);//����Ӧ���ǲ���Ч�ģ���ʵ�ʽ����ʾ��Ч��ԭ����isEnabled�������ڱ༭��
		login_button.click();
	}
	
	/**
	 * webform���ύ
	 * case��immoc��¼ҳ 
	 * �ر�˵�������ύֻ����get����ʽ�ύ����ǰҳ���URL����Щ�����ʺϣ���Щ�������ʺϡ�
	 * */
	public void web_form(){
		driver.get("xxxxxx");
		driver.findElement(By.id("signup-form")).submit();//����submit����������һ���򵥵ı��ύ
	}
	/**
	 * upfile�ļ��ϴ�
	 * case:Ľ�������������޸�ͷ��
	 * �ѵ㣺"����ͷ��"�����ֻ�����
	 * ���ֽ��˼·��1����driverִ������js�޸�Դ������2��ģ��������Ԫ���ϣ�Ȼ����е����3������autoit�ϴ���
	 * */
	//��ʽһ����driverִ������js�޸�Դ������
	public void upfile_js(){
		driver.get("https://www.imooc.com/user/setbindsns");
		this.sleep(5000);//���õȴ��ķ���
		//��js�������ͷ��ť����Ϊ������
		String js = "document.getElementsByClassName('update-avator'[0].style.bottom='0px';)";
		JavascriptExecutor jscript = (JavascriptExecutor) driver;
		jscript.executeScript(js);
		this.sleep(2000);
		driver.findElement(By.id("js-avator-link")).click();//�������ͷ��ť
		driver.findElement(By.id("upload")).sendKeys("C:\\Users\\Administrator\\Desktop\\test.png");
		driver.findElement(By.linkText("ȷ��")).click();
	}
	//��ʽ����ģ��������ͷ���ϣ�Ȼ����е����
	//Ӧ�ó������ϴ�ͷ��ı�ǩ��input���ͣ�ֱ��ͨ��sendkeys�ϴ��ļ�
	//ʵ�ַ������������Ʋ����࣬������ƶ���ͷ����
	public void upfile_mouse(){
		driver.get("https://www.imooc.com/user/setbindsns");
		this.sleep(3000);
		WebElement header = driver.findElement(By.className("update-avator"));
		Actions ac = new Actions(driver);
		ac.moveToElement(header).perform();//������ƶ���ͷ����
		driver.findElement(By.className("js-avator-link")).click();//�������ͷ��ť
		driver.findElement(By.id("upload")).sendKeys("C:\\Users\\Administrator\\Desktop\\test.png");//��λ�ϴ�Ԫ�صİ�ť����ͨ��sendkeys�ϴ��ļ�
		driver.findElement(By.linkText("ȷ��")).click();
	}
	//��ʽ��������autoit�ϴ�
	//Ӧ�ó������ϴ�ͷ��ı�ǩ�������input��ǩ����ֻ��ͨ��autoit�����ϴ�
	//ʵ�ַ�������ѡ���ļ��ĵ����Ժ�ͨ��autoIt����ʵ��ͷ���ϴ�
	public void upfile_autoit(){
		driver.get("https://www.imooc.com/user/setbindsns");
		this.sleep(3000);
		WebElement header = driver.findElement(By.className("updata-avator"));
		Actions ac = new Actions(driver);
		ac.moveToElement(header).perform();
		driver.findElement(By.className("js-avator-link")).click();
		driver.findElement(By.className("avator-btn-fake")).click();//����ϴ�ͷ��İ�ť�������ļ�ѡ���
		//ִ��autoit�����ļ�������ͷ���ϴ�
		try {
			Runtime.getRuntime().exec("C:\\Users\\Administrator\\Desktop\\upfile.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.linkText("ȷ��")).click();
	}
	/**
	 * ��װ�ȴ�ʱ��ķ���
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
	 * ���������
	 * case��Ľ������������ҳ�棬ѡ��ְҵ������
	 * */
	public void downSelectBox(){
		driver.get("http://www.imooc.com/user/setprofile");
		driver.findElement(By.className("pull-right")).click();
		this.sleep(3000);
		WebElement formElement = driver.findElement(By.id("profile"));
		WebElement job = formElement.findElement(By.id("job"));
		Select downList = new Select(job);//selenium�Դ���select������������select��ǩ
		//downList.selectByIndex(2);//ͨ��index��������ѡ��
		//downList.selectByValue("1");//ͨ��value��ʽ���ж�λ
		downList.selectByVisibleText("ѧ��");//ͨ��visibleText��ʽ���ж�λ
		downList.isMultiple();//�ж��Ƿ���������ѡ�б�
		//��ȡ��ǰ����������ֵ�����ݣ����ڶ�ѡ�б�
		List<WebElement> list = downList.getAllSelectedOptions();
		for (WebElement options : list){
			options.getText();
		}
		downList.getFirstSelectedOption();//��ȡ��ǰ������ĵ�һ��ֵ������
	}
	
	public static void main(String[] args) {
		ActionSelenium as = new ActionSelenium();
		as.InitDriver();
//		as.InputBox();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
