package com.experitest.auto;

import java.net.MalformedURLException;
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
	protected final String DEFAULT_QUERY = "@os='android'";

	@BeforeMethod
	@Parameters({ "devicequery" })
	public void setUp1(@Optional(DEFAULT_QUERY) String devicequery) throws Exception {
		init(devicequery);

		dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.eribank/com.experitest.ExperiBank.LoginActivity");
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.eribank");
		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.experitest.ExperiBank.LoginActivity");
		dc.setCapability("appVersion", "1.1");
		


	}
	
	@Test
		public void login() throws Exception {
		dc.setCapability("testName", "Login");
		driver = new AndroidDriver<>(new URL(getProperty("url",cloudProperties) + "/wd/hub"), dc);
		seetest = new SeeTestClient(driver);
		driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
		driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");

		seetest.startPerformanceTransaction("");
		driver.findElement(By.xpath("//*[@id='loginButton']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 20, 100);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='pBar']")));
		seetest.endPerformanceTransaction("Login");
	}

	@Test
	public void Logout() throws Exception {
		dc.setCapability("testName", "Logout");
		driver = new AndroidDriver<>(new URL(getProperty("url",cloudProperties) + "/wd/hub"), dc);
		seetest = new SeeTestClient(driver);
		driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
		driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");

		driver.findElement(By.xpath("//*[@id='loginButton']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 20, 100);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='pBar']")));
		driver.findElement(By.xpath("//*[@id='logoutButton']")).click();
		seetest.startPerformanceTransaction("");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='passwordTextField']")));
		seetest.endPerformanceTransaction("Logout");


	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
}
