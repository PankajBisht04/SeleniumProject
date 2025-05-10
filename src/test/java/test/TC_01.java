package test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import base.base;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import finalautom.Loginpage;
import utils.ExcelUtils;
import utils.ExtentReportManager;

//LoginPageSuccessTest

public class TC_01 extends base {
	@DataProvider(name = "LoginData")
	public Object[][] getLoginData() throws IOException {
		String filepath = System.getProperty("user.dir") + "/testdata/logindata.xlsx";
		ExcelUtils.loadExcel(filepath, "Sheet1");
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
	public void login(String username, String password) {
		test = ExtentReportManager.createTest("Login Test: " + username);

		// Login into website
		Loginpage loginpage = new Loginpage(driver);

		loginpage.enterUsername(username);
		loginpage.enterPassword(password);
		loginpage.clicklogin();
		// check if login was successfull
		String exp_Title = driver.getCurrentUrl();
		String act_Title = "https://www.saucedemo.com/inventory.html";
		Assert.assertEquals(act_Title, exp_Title);
		// https://www.saucedemo.com/inventory.html
		test.info("Pass");
	}

}
