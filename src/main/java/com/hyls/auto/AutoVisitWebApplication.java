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
	private final static String VISITED_MAIN_PAGE= "http://jiaojiang.space.tzedu.org/index.php?r=space/person/show&sid=706083";
	private final static String OUTSPACE_PAGE= "http://jiaojiang.tzedu.org/index.php?r=portal/user/logout";
	private final static String qrySql = "select login_name,password from tb_user_info where status = '0'";

	private WebDriver driver = null;
	
	public static void main(String[] args) {
		new AutoVisitWebApplication().visit();
	}
	
    public void visit()  {
    	
    	List<User> list = DbConnection.getUserList(qrySql, DbConnection.getConn());
    	driver = PageUtils.getChromeDriver(WEB_SITE);
    	for(User user:list) {
    		//driver = PageUtils.getChromeDriver(WEB_SITE);
    		
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
            Thread.sleep(5000);
            
            driver.get(VISITED_MAIN_PAGE);
            
            Thread.sleep(2000);
            
            driver.get(OUTSPACE_PAGE);
            
            Thread.sleep(5000);
            //  /html/body/div[1]/div/ul/li[5]/a
            /*WebElement settings = driver.findElement(By.xpath("/html/body/div[1]/div/ul/li[5]/a"));
            Actions action = new Actions(driver);
            Action mouserOverlogin =  action.moveToElement(settings).build();
            mouserOverlogin.perform();
            Thread.sleep(2000);*/
            
           // /html/body/div[1]/div/ul/li[5]/p/a[3]
           /* WebElement logging_out = driver.findElement(By.xpath("/html/body/div[1]/div/ul/li[5]/p/a[3]"));
            PageUtils.scrollToElementAndClick(logging_out, driver);
            Thread.sleep(2000);*/
            /*Actions actionOpenLinkInNewTab = new Actions(driver);
            actionOpenLinkInNewTab.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).perform();
            
            // 获取当前页面句柄  
            String handle = driver.getWindowHandle();  
            // 获取所有页面的句柄，并循环判断不是当前的句柄 
            for (String temhandle : driver.getWindowHandles()) {  
                if (!temhandle.equals(handle))  
                	driver.close();
                else
                    driver.switchTo().window(temhandle); 
            } 
            Thread.sleep(5000);
            driver.get(WEB_SITE);
            Thread.sleep(5000);*/
           /* PageUtils.getAnotherPageAndCloseCurrentPage(driver);
            
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
            Thread.sleep(5000);*/
            //driver.close();
            //driver.quit();
            }catch(Exception e) {
            	e.printStackTrace();
            }
    	}
    }
}
