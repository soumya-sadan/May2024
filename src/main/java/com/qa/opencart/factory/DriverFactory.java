package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	
	public static String isHighlight;
	/**
	 * This method is used  to initialize the browser on the basis of given browsername
	 * uses "Factory design pattern", object is returned based on the parameter recevied.
	 * @param browserName
	 * @return
	 */
	public WebDriver initDriver(Properties prop)
	{
		String browserName = prop.getProperty("browser");
		System.out.println("Browsername is :" + browserName);
		
		isHighlight = prop.getProperty("highlight");
		OptionsManager optionsmanager = new OptionsManager(prop);
		
	
		
		switch(browserName.trim().toLowerCase())
		{
		case "chrome":
			ChromeOptions co = optionsmanager.getChromeOptions();
			tlDriver.set(new ChromeDriver(co)); // threadlocal driver 
			//driver = new ChromeDriver(co);
			break;
		case "firefox":
			FirefoxOptions fo = optionsmanager.getFirefoxOptions();
			tlDriver.set(new FirefoxDriver(fo));
		//	driver = new FirefoxDriver(fo);
		case "edge":
			EdgeOptions eo = optionsmanager.getEdgeOptions();
			tlDriver.set(new EdgeDriver(eo));
			//driver = new EdgeDriver(eo);
		case "safari":
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		default:
			System.out.println(AppError.INVALID_BROWSER_MESG);
			throw new BrowserException(AppError.INVALID_BROWSER_MESG + browserName);
		}
		
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		return getDriver();
	}
	
	public static WebDriver getDriver()
	{
		return tlDriver.get();
	}
	
  
	/**
	 * This method initialize the properties from the config file
	 * @return
	 */
	
	//mvn clean install -Denv="qa"  (here -D and then environment name without space)
	//to read "env", use method System.getProperty(), the parameter passes behaves like an environment name (that we set in windows)
	public Properties initProp()
	{
		prop = new Properties();
		String envName = System.getProperty("env"); //reading 'env'
		FileInputStream ip=null;
		System.out.println("Environment : "+ envName);
		try {
		if(envName==null) {		
			System.out.println("env is null... hence running test in QA Env");
			ip=new FileInputStream("src/test/resources/config/qa.config.properties");
		}
		else {
			switch(envName.toLowerCase().trim())
			{
			case "qa":				
				ip=new FileInputStream("src/test/resources/config/qa.config.properties");
				break;
			case "dev":				
				ip=new FileInputStream("src/test/resources/config/dev.config.properties");
				break;
			case "stage":
				ip=new FileInputStream("src/test/resources/config/stage.config.properties");
				break;
			case "prod":
				ip=new FileInputStream("/src/test/resources/config/prod.config.properties");
				break;
			default:
				System.out.println("Please pass right environment name");
				throw new FrameworkException("Invalid Environment");
					
			}
	
		}
		
		prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	
	
	public static String getScreenshot(String methodName)
	{
		//screenshot will be created in a temporary directory by  selenium
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
	
	    String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_"  + System.currentTimeMillis() + ".png";
	    File destination = new File(path); 
	    
	    try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return path;
	}
}







