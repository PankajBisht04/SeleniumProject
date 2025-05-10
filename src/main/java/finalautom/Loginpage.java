package finalautom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Loginpage {
	
	private WebDriver driver;
	private By usernamebox= By.id("Email");
	private By passwordbox = By.id("Password");
	private By loginbutton = By.xpath(("//*[@id=\"main\"]/div/div/div/div[2]/div[1]/div/form/div[3]/button"));
	
	
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
	public void clicklogin() {
		driver.findElement(loginbutton).click();
		
	}
}
