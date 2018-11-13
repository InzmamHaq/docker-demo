package test.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestClass {
	
	public static void main(String[] ar) {
		
		System.setProperty("webdriver.chrome.driver", "/home/fran/Selenium_Test_Input/exe/chromedriver");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.google.com");
		driver.quit();
	}

}
