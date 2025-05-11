package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
	private static ExtentReports extent;
	private static ExtentTest test;

	public static ExtentReports getReportInstance() {
		if (extent == null) {
			String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
			String reportPath = "reports/ExtentReport_" + timestamp + ".html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

			reporter.config().setDocumentTitle("Automation Report");
			reporter.config().setReportName("Test report");

			extent = new ExtentReports();
			extent.attachReporter(reporter);
		}
		return extent;
	}

	public static ExtentTest createTest(String testname) {

		test = getReportInstance().createTest(testname);
		return test;
	}

	public static String captureScreenshot(WebDriver driver, String screenshotName) {
		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
			String path = System.getProperty("user.dir") + "/screenshots/" + screenshotName + "_" + timestamp + ".png";
			FileUtils.copyFile(src, new File(path));
			return path;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void logStep(WebDriver driver, String stepDescription) {
		Log.info("Taking screenshot");
		String ScreenshotPath = ExtentReportManager.captureScreenshot(driver, "test pass");
		test.pass("Step passed: "+stepDescription,MediaEntityBuilder.createScreenCaptureFromPath(ScreenshotPath).build());
	}
}
