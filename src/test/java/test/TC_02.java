package test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import base.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;

import finalautom.Loginpage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;

//LoginPageFailTest

public class TC_02 extends base {
	@DataProvider(name = "LoginData")
	public Object[][] getLoginData() throws IOException {
		String filepath = System.getProperty("user.dir") + "/testdata/logindata.xlsx";
		ExcelUtils.loadExcel(filepath, "Sheet2");
		int rowcount = ExcelUtils.getRowCount();
		Object[][] data = new Object[rowcount - 1][2];
		for (int i = 1; i < rowcount; i++) {
			data[i - 1][0] = ExcelUtils.getCellData(i, 0);
			data[i - 1][1] = ExcelUtils.getCellData(i, 1);
		}
		ExcelUtils.closeExcel();
		return data;
	}

	@Test(dataProvider = "LoginData")
	public void login(String username, String password) throws InterruptedException {
		test = ExtentReportManager.createTest("Login Fail Test: " + username);

		// Login into website
		Loginpage loginpage = new Loginpage(driver);

		// check if username was not entered
		loginpage.clicklogin();
		//Thread.sleep(2000);
		WebElement failBox = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3"));
		String actMsg = failBox.getText();
		Assert.assertTrue(failBox.isDisplayed());
		String fail_message = "Epic sadface: Username is required";
		Assert.assertEquals(fail_message, actMsg);
		ExtentReportManager.logStep(driver, "Epic sadface: Username is required");
		// check if password was not entered
		loginpage.enterUsername(username);

		loginpage.clicklogin();
		actMsg = failBox.getText();
		Assert.assertTrue(failBox.isDisplayed());
		fail_message = "Epic sadface: Password is required";
		Assert.assertEquals(fail_message, actMsg);
		ExtentReportManager.logStep(driver, "Epic sadface: Password is required");
		// check if username and password was incorrect

		loginpage.enterUsername("a");
		loginpage.enterPassword("b");
		loginpage.clicklogin();
		actMsg = failBox.getText();
		Assert.assertTrue(failBox.isDisplayed());
		fail_message = "Epic sadface: Username and password do not match any user in this service";
		Assert.assertEquals(fail_message, actMsg);
		ExtentReportManager.logStep(driver, "Epic sadface: Username and password do not match any user in this service");
		
	}
}
