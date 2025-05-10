package finalautom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Loginpage {
	
	private WebDriver driver;
	private By usernamebox= By.id("user-name");
	protected By passwordbox = By.id("password");
	private By loginbutton = By.xpath(("//*[@id=\"login-button\"]"));
	
	
	public Loginpage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void enterUsername(String username) {
		driver.findElement(usernamebox).clear();
		driver.findElement(usernamebox).sendKeys(username);;
	}
	
	public void enterPassword(String password) {
		driver.findElement(passwordbox).clear();
		driver.findElement(passwordbox).sendKeys(password);
	}
	public void clearPass() {
		driver.findElement(passwordbox).clear();
	}
	public void clicklogin() {
		driver.findElement(loginbutton).click();
		
	}
}
