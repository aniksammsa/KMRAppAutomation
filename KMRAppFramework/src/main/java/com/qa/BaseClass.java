package com.qa;

import com.qa.utils.TestUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;

public class BaseClass {

	protected static AppiumDriver<MobileElement> driver;
	protected Properties prop;
	protected static HashMap<String,String>strings = new HashMap<String,String>();
	InputStream inputStream;
	InputStream stringis;
	TestUtils utils;

	public BaseClass() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@Parameters({"platformName","platformVersion","deviceName"})

	@BeforeTest
	public void beforeTest(String platformName, String platformVersion, String deviceName ) throws Exception {
		try {
			prop = new Properties();
			String propFilename = "config.properties";
			String xmlFilename ="strings/strings.xml";

			inputStream =getClass().getClassLoader().getResourceAsStream(propFilename);
			prop.load(inputStream);

			stringis = getClass().getClassLoader().getResourceAsStream(xmlFilename);
			utils = new TestUtils();
			strings = utils.parseStringXML(stringis);

			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("automationName", prop.getProperty("androidAutomationName"));
			cap.setCapability("deviceName", deviceName);
			cap.setCapability("platformName", platformName);	
			cap.setCapability("platformVersion", platformVersion);
			cap.setCapability("udid", "2835ca7a");

			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
			cap.setCapability("appPackage", prop.getProperty("androidAppPackage"));
			cap.setCapability("appActivity", prop.getProperty("androidAppActivity"));

			//URL appURL = getClass().getClassLoader().getResource(prop.getProperty("androidAppLocation"));
			//String appURL = getClass().getResource(prop.getProperty("androidAppLocation")).getFile();
			//System.out.println("App URL is"+appURL);
			//cap.setCapability("app", appURL);
			URL url = new URL(prop.getProperty("appiumURL"));
			driver = new AppiumDriver<MobileElement>(url,cap);

		} catch (Exception exp) {

			System.out.println("Cause is: "+exp.getCause());
			System.out.println("Message is: "+exp.getMessage());
			exp.printStackTrace();
		}finally {
			if(inputStream !=null) {
				inputStream.close();
			}
			if(stringis!=null) {
				stringis.close();
			}
		}
	}

	public void WaitForVisibility(MobileElement e) {
		WebDriverWait wait = new WebDriverWait(driver,TestUtils.WAIT);
		wait.until(ExpectedConditions.visibilityOf(e));

	}

	public void click(MobileElement e) {
		WaitForVisibility(e);;
		e.click();
	}

	public void sendKeys(MobileElement e, String txt) {
		WaitForVisibility(e);
		e.sendKeys(txt);
	}
	public String getAttribute(MobileElement e, String attribute) {
		WaitForVisibility(e);
		return e.getAttribute(attribute);
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

	@BeforeSuite
	public void beforeSuite() {
	}

	@AfterSuite
	public void afterSuite() {
	}

}
