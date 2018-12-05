package com.hyls.auto;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.hyls.db.DbConnection;
import com.hyls.model.User;
import com.hyls.utils.PageUtils;


/**
 * 用来测试 Selenium
 */
public class AutoVisitWebApplication {
	private final static String WEB_SITE= "http://jiaojiang.tzedu.org/";
	
	private final static String qrySql = "select login_name,password from tb_user_info where status = '0'";
	
	private final static String VISIT_NAME= "##";
	
	private WebDriver driver = null;
	
	public static void main(String[] args) {
		new AutoVisitWebApplication().visit();
	}
	
    public void visit()  {
    	
    	List<User> list = DbConnection.getUserList(qrySql, DbConnection.getConn());
    	for(User user:list) {
    		driver = PageUtils.getChromeDriver(WEB_SITE);

            try {
            WebElement loginBtn =driver.findElement(By.cssSelector("a#login_btn"));
            PageUtils.scrollToElementAndClick(loginBtn, driver);
            
            Thread.sleep(3000);
            
            //input name and password
            PageUtils.inputStrByJS(driver, "info_username-1", user.getName());
            PageUtils.inputStrByJS(driver, "info_password-1", user.getPassword());
            
            WebElement doLogin =driver.findElement(By.cssSelector("input[value=\"立即登录\"]"));
            PageUtils.scrollToElementAndClick(doLogin, driver);
            //如果时间过短，可能页面还没加载完成
            Thread.sleep(8000);
            //学生跟老师都可能出现非认证情况
            WebElement cancelBtn = driver.findElement(By.cssSelector("input[class=\"d-button\"]"));
            PageUtils.scrollToElementAndClick(cancelBtn, driver);
            Thread.sleep(2000);
            //点击搜索 ，出现搜索框
            WebElement search =driver.findElement(By.cssSelector("a#searchBtn"));
            PageUtils.scrollToElementAndClick(search, driver);
            
            Thread.sleep(5000);
            //  xpath： //*[@id="search_nav"]/dt
            WebElement seloptions = driver.findElement(By.xpath("//*[@id=\"search_nav\"]/dt"));
            PageUtils.setAttribute(driver,seloptions,"3");
            
            WebElement inputSearch = driver.findElement(By.cssSelector("input[class=\"inp_txt\"]"));
            inputSearch.sendKeys(VISIT_NAME);
            
            //输入搜索内容后，点击搜索
            WebElement searchA =driver.findElement(By.cssSelector("input[class=\"inp_btn\"]"));
            PageUtils.scrollToElementAndClick(searchA, driver);
            //找到搜索按钮
            Thread.sleep(5000);
            //    //*[@id="content"]/ul/li[1]/div[2]/dl/dt/a
            //WebElement rlyhref = driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li/div[2]/dl/dt/a"));
            WebElement yyhref = driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[1]/div[2]/dl/dt/a"));
            PageUtils.scrollToElementAndClick(yyhref, driver);
            Thread.sleep(5000);
            //driver.quit();
            }catch(Exception e) {
            	e.printStackTrace();
            }finally {
            	/*if(driver !=null) {
            		 driver.quit();
            	}*/
            }
    	}
    }
}
