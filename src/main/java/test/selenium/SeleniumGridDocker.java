package test.selenium;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumGridDocker {

	WebDriver driver;
	String folderName="/usr/share/tag/outputfolder";

	@BeforeTest
	public void Driver() throws MalformedURLException {
		
		try {
			String seleniumHubHost = System.getProperty("seleniumHubHost");
			Reporter.log("seleniumHubHost :: "+seleniumHubHost);
			String browser = System.getProperty("browser");
			Reporter.log("browser :: "+browser);
			String exePath = System.getProperty("inputfolder")+File.separator+"chromedriver";
			System.setProperty("webdriver.chrome.driver", exePath);
			ChromeOptions co=new ChromeOptions();
			co.setCapability("throwOnCapabilityNotPresent", false);
			Thread.sleep(10000);
			driver = new RemoteWebDriver(new URL("http://"+seleniumHubHost+":4444/wd/hub"), co);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		} catch (Exception e) {
			Reporter.log(e.getMessage(),true);
		}
	}

	@Test
	public void doThese() {
		try {
			driver.get("http://www.google.com");
			Reporter.log("Hello Worlds This is docker : ",true);
			WebElement hello = driver.findElement(By.xpath(".//input[@title='Search']"));
			hello.sendKeys("Java Code Geeks");
			hello.submit();

			WebDriverWait wait = new WebDriverWait(driver, 20);
			WebElement hello1 = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("java Code Geeks")));
			WebDriver Driver = new Augmenter().augment(driver);
			File srcFile = ((TakesScreenshot) Driver).getScreenshotAs(OutputType.FILE);
			try {
				Reporter.log("Going to take screenshot : ",true);
				FileUtils.copyFile(srcFile, new File(folderName+File.separator+"image.png"));
				Reporter.log("take screenshot success : ",true);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			
			driver.quit();
		} catch (Exception e) {
			WebDriver Driver = new Augmenter().augment(driver);
			File srcFile = ((TakesScreenshot) Driver).getScreenshotAs(OutputType.FILE);
			try {
				Reporter.log("Going to take screenshot catch: ",true);
				FileUtils.copyFile(srcFile, new File(folderName+File.separator+"image1.png"));
				Reporter.log("take screenshot success catch: ",true);
			} catch (IOException e1) {
				System.out.println(e.getMessage());
			}
			driver.quit();
		}
		
	}
}
