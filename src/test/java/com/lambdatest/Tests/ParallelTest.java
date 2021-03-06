package com.lambdatest.Tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import java.lang.reflect.Method;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.net.MalformedURLException;
import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.Keys;
import java.util.Iterator;		
import java.util.Set;	
import java.util.List;
import java.lang.String;
import java.lang.Object;

public class ParallelTest {

	// Lambdatest Credentails can be found here at https://www.lambdatest.com/capabilities-generator
	String username = System.getenv("LT_USERNAME") == null ? "YOUR LT_USERNAME" : System.getenv("LT_USERNAME"); 
	String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "YOUR LT_ACCESS_KEY" : System.getenv("LT_ACCESS_KEY");

	public static WebDriver driver;

	@BeforeTest(alwaysRun = true)
	@Parameters(value = { "browser", "version", "platform" })
	protected void setUp(String browser, String version, String platform) throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		// set desired capabilities to launch appropriate browser on Lambdatest
		capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
		capabilities.setCapability(CapabilityType.VERSION, version);
		capabilities.setCapability(CapabilityType.PLATFORM, platform);
		capabilities.setCapability("build", "TestNG Parallel");
		capabilities.setCapability("name", "TestNG Parallel");
		capabilities.setCapability("network", true);
		capabilities.setCapability("video", true);
		capabilities.setCapability("console", true);
		capabilities.setCapability("visual", true);


		System.out.println("capabilities" + capabilities);

		// Launch remote browser and set it as the current thread
		String gridURL = "https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub";
		try {
			driver = new RemoteWebDriver(new URL(gridURL), capabilities);
		} catch (Exception e) {
			System.out.println("driver error");
			System.out.println(e.getMessage());
		}

	}


	public static boolean waitForElementVisible(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*@Test
	public void test() throws Exception {

		try {
			// Launch the app
			driver.get("https://lambdatest.github.io/sample-todo-app/");

			// Click on First Item
			driver.findElement(By.name("li1")).click();

			// Click on Second Item
			driver.findElement(By.name("li2")).click();

			// Add new item is list
			driver.findElement(By.id("sampletodotext")).clear();
			driver.findElement(By.id("sampletodotext")).sendKeys("Yey, Let's add it to list");
			driver.findElement(By.id("addbutton")).click();

			// Verify Added item
			String item = driver.findElement(By.xpath("/html/body/div/div/div/ul/li[6]/span")).getText();
			AssertJUnit.assertTrue(item.contains("Yey, Let's add it to list"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
	}*/

	@Test(timeOut = 20000)
	public void test01() throws Exception {
	SoftAssert sa = new SoftAssert();
	driver.get("https://www.lambdatest.com/selenium-automation/");
    driver.manage().window().maximize();

	WebElement CiCdTools = driver.findElement(By.xpath("//*[@id='__next']/div[1]/section[9]/div/div/div/div[2]/div/div[4]/div/div[1]"));
	waitForElementVisible(CiCdTools);
    CiCdTools.click();
    System.out.println("CICD tool clicked");
    WebElement learnmore = driver.findElement(By.xpath("//*[@id='__next']/div[1]/section[9]/div/div/div/div[2]/div/div[4]/div/div[2]/span/a"));
    waitForElementVisible(learnmore);
    //To open a link in new tab.
	String url = learnmore.getAttribute("href");
	JavascriptExecutor js = (JavascriptExecutor) driver;  
 	js.executeScript("window.open('" + url + "');");
	
	String MainWindow=driver.getWindowHandle();
	System.out.println(driver.getWindowHandles().size());
	 // To handle all new opened window.				
            Set<String> s1=driver.getWindowHandles();	
			System.out.println("Child window handle is" + s1);	
        Iterator<String> i1=s1.iterator();		
        		
        while(i1.hasNext())			
        {		
            String ChildWindow=i1.next();	
				
            	System.out.println(MainWindow); 
				System.out.println(ChildWindow); 	
            if(!MainWindow.equalsIgnoreCase(ChildWindow))			
            {    		
                 
                    // Switching to Child window
                    driver.switchTo().window(ChildWindow);
					System.out.println("I am inside the child window");  
					
					String expectedURL = "https://www.lambdatest.com/support/docs/integrations-with-ci-cd-tools/";
	                sa.assertEquals(driver.getCurrentUrl(), expectedURL);
	                System.out.println("Learnmore page opened");                                                                                                          
                    
					js.executeScript("window.scrollTo(0, document.body.scrollHeight)");		
                    System.out.println("I scrolled down");        
					js.executeScript("window.scrollTo(0,0)");
					System.out.println("I scrolled up");  	    
			// Closing the Child Window.
                        driver.close();		
            }		
        }		
		System.out.println("outside window");  
        // Switching to Parent window i.e Main Window.
            driver.switchTo().window(MainWindow);
			System.out.println("main window");  		

	
	sa.assertAll();

	}

	@Test(timeOut = 20000)
	public void test02() throws Exception {
	SoftAssert sa = new SoftAssert();

	WebElement resources = driver.findElement(By.xpath("//*[@id='header']/nav/div/div/div[2]/div/div/button"));
	waitForElementVisible(resources);
	resources.click();
	System.out.println("resources clicked"); 

	WebElement newsletter = driver.findElement(By.xpath("//*[@id='header']/nav/div/div/div[2]/div/div/ul/a[6]"));
	waitForElementVisible(newsletter);
	newsletter.click();
    System.out.println("newsletter is clicked");

	WebElement read = driver. findElement(By.cssSelector("body > div:nth-child(1) > section:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(6) > a:nth-child(1)"));
	waitForElementVisible(read);
	read.click();
	System.out.println("read is clicked"); 

    WebElement edition = driver.findElement(By.xpath("//h1[contains(text(),'All Editions')]"));
	waitForElementVisible(edition);
	
	sa.assertEquals(edition.getText(), "ALL EDITIONS");
		System.out.println("ALL EDITIONS is present"); 

	sa.assertAll();

	}


	@AfterTest(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
	}

}
