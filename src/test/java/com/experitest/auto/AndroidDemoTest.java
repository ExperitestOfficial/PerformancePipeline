package com.experitest.auto;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import com.experitest.appium.SeeTestClient;


public class AndroidDemoTest extends BaseTest {
	protected AndroidDriver<AndroidElement> driver = null;

	@BeforeMethod
	@Parameters("deviceQuery")
	public void setUp(@Optional("@os='android'") String deviceQuery) throws Exception{
		init(deviceQuery);

		dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.eribank/com.experitest.ExperiBank.LoginActivity");
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.eribank");
		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.experitest.ExperiBank.LoginActivity");
		dc.setCapability("appVersion", "1.0");
		
		dc.setCapability("testName", "AndroidDemoTest");
		driver = new AndroidDriver<>(new URL(getProperty("url",cloudProperties) + "/wd/hub"), dc);
		seetest = new SeeTestClient(driver);

	}
	
	@Test
	public void test(){
		driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
		driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");

		seetest.startPerformanceTransaction("");
		driver.findElement(By.xpath("//*[@id='loginButton']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 20, 100);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='pBar']")));

		seetest.endPerformanceTransaction("Login");

		driver.findElement(By.xpath("//*[@id='logoutButton']")).click();
		
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
}
