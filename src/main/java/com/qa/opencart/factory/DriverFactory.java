package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	private static final Logger LOGGER=Logger.getLogger(String.valueOf(DriverFactory.class));
	WebDriver driver; 
	Properties prop;
	public static String highlight;
	OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver>tlDriver=new ThreadLocal<>();
	public WebDriver init_driver(Properties prop) {
		String browserName=prop.getProperty("browser");
		LOGGER.info("browser name is :" +browserName);
		System.out.println("browser name is :" +browserName );
		highlight=	prop.getProperty("highlight").trim();
		OptionsManager optionsManager=new OptionsManager(prop);
		
		if(browserName.equals(browserName)) {
			WebDriverManager.chromedriver().setup();
			//driver=new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
		 else if(browserName.equals(browserName)) {
			WebDriverManager.firefoxdriver().setup();
			//driver=new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			
		} else if(browserName.equals(browserName)) {
			//driver=new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		else {
			System.out.println("please enter valid browsername" +browserName);
		}
		
		getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();
		
		return getDriver();
	}
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	 
	
	public Properties init_prop() {
		prop=new Properties();
		try {
			FileInputStream fs=new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(fs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
		
	}
	/**
	 * take screenshot
	 * @return
	 */
	public String getScreenshot() {
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		//File srcFile = new File(src);
		String path = System.getProperty("user.dir")+ "/screenshots/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
