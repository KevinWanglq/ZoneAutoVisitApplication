package com.hyls.test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.hyls.utils.PageUtils;

/**
 * 用来测试 Selenium
 */
public class TestSeleniumDemo {

   /* *//**
     * 用来测试第一个代码，访问百度
     *//*
    @Test
    public void testHelloWordl() throws Exception {
        //开启个浏览器并且输入链接
        WebDriver driver = PageUtils.getChromeDriver("https://www.baidu.com/");
        //得到浏览器的标题
        System.out.println(driver.getTitle());
        Thread.sleep(5000);
        //关闭浏览器 下面是关闭所有标签页，还有一个代码是 driver.close();, 关闭当前标签页
        driver.quit();

    }

    *//**
     * 测试向input标签输入值
     *//*
    @Test
    public void testInputStrByJS(){
        //开启个浏览器并且输入链接
        WebDriver driver = PageUtils.getChromeDriver("https://www.baidu.com/");
        //向input输入值
        PageUtils.inputStrByJS(driver, "kw", "月之暗面 博客园");
    }

    *//**
     * 测试点击
     *//*
    @Test
    public void testScrollToElementAndClick() throws Exception {
        //1、开启个浏览器并且输入链接
        WebDriver driver = PageUtils.getChromeDriver("https://www.baidu.com/");

        //2、向百度输入框输入需要查询的值
        PageUtils.inputStrByJS(driver, "kw", "月之暗面 博客园");

        //3、得到百度一下的标签
        WebElement submitElement = driver.findElement(By.cssSelector("input#su"));

        //4、点击百度一下
        PageUtils.scrollToElementAndClick(submitElement, driver);

        //休息3秒，加载数据
        Thread.sleep(3000);

        //5、首先找到 id 为 content_left 的 div 下面的所有 div
        List<WebElement> divElements = driver.findElements(By.cssSelector("div#content_left div"));
        //6、找到搜索的第一个链接
        WebElement aElement = divElements.get(0).findElement(By.cssSelector("div.f13 a[href]"));

        //7、点击该链接
        PageUtils.scrollToElementAndClick(aElement, driver);
    }
*/
    
    @Test
    public void testGetAnotherPage()  {
        //1、开启个浏览器并且输入链接
        WebDriver driver = PageUtils.getChromeDriver("http://jiaojiang.tzedu.org/");

        //2、向百度输入框输入需要查询的值
        //PageUtils.inputStrByJS(driver, "kw", "月之暗面 博客园");

        //3、得到百度一下的标签
        try {
        WebElement login =driver.findElement(By.cssSelector("a#login_btn"));
        PageUtils.scrollToElementAndClick(login, driver);
        
        Thread.sleep(3000);
        
        PageUtils.inputStrByJS(driver, "info_username-1", "yjxxyy");
        PageUtils.inputStrByJS(driver, "info_password-1", "@yy032045");
        
        WebElement loginA =driver.findElement(By.cssSelector("input[value=\"立即登录\"]"));
        PageUtils.scrollToElementAndClick(loginA, driver);
        //如果时间过短，可能页面还没加载完成
        Thread.sleep(8000);
        //点击搜索 ，出现搜索框
        WebElement search =driver.findElement(By.cssSelector("a#searchBtn"));
        PageUtils.scrollToElementAndClick(search, driver);
        
        Thread.sleep(5000);
        //  xpath： //*[@id="search_nav"]/dt
        WebElement seloptions = driver.findElement(By.xpath("//*[@id=\"search_nav\"]/dt"));
        PageUtils.setAttribute(driver,seloptions,"3");
        
        WebElement inputSearch = driver.findElement(By.cssSelector("input[class=\"inp_txt\"]"));
        inputSearch.sendKeys("阮玲燕");
        
        //输入搜索内容后，点击搜索
        WebElement searchA =driver.findElement(By.cssSelector("input[class=\"inp_btn\"]"));
        PageUtils.scrollToElementAndClick(searchA, driver);
        //找到搜索按钮
        Thread.sleep(3000);
        //    
        WebElement yyhref = driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li/div[2]/dl/dt/a"));
        //WebElement yyhref = driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[1]/div[2]/dl/dt/a"));
        PageUtils.scrollToElementAndClick(yyhref, driver);
        Thread.sleep(5000);
        driver.quit();
        }catch(Exception e) {
        	e.printStackTrace();
        }
        //WebElement submitElement = driver.findElement(By.cssSelector("input#su"));

        //4、点击百度一下
      
       // PageUtils.scrollToElementAndClick(submitElement, driver);

        //休息3秒，加载数据
       /* Thread.sleep(3000);

        //5、首先找到 id 为 content_left 的 div 下面的所有 div
        List<WebElement> divElements = driver.findElements(By.cssSelector("div#content_left div"));
        //6、找到搜索的第一个链接
        WebElement aElement = divElements.get(0).findElement(By.cssSelector("div.f13 a[href]"));

        //7、点击该链接
        PageUtils.scrollToElementAndClick(aElement, driver);


        //8、当前页面是百度的页面
        System.out.println("现在的页面是："+driver.getTitle());
        //9、切换到博客园页面
        PageUtils.getAnotherPage(driver);
        System.out.println("现在的页面是："+driver.getTitle());*/
    }
}