package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import utils.ExtentReportManager;
import utils.Log;

public class base {
	protected WebDriver driver;
	protected static ExtentReports extent;
	protected ExtentTest test;

	@BeforeSuite
	public void setupReport() {
		extent = ExtentReportManager.getReportInstance();
	}

	@AfterSuite
	public void tearDownReport() {
		extent.flush();
	}

	@BeforeMethod
	public void setup() {
		Log.info("Starting Web Driver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@AfterMethod(alwaysRun = true)
	public void teardown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			Log.info("Taking screenshot");
			String ScreenshotPath = ExtentReportManager.captureScreenshot(driver, "login failure");
			test.fail("Test Failed..", MediaEntityBuilder.createScreenCaptureFromPath(ScreenshotPath).build());
		}

		if (driver != null) {
			Log.info("Quitting Driver");
			driver.quit();
		}
	}
}
