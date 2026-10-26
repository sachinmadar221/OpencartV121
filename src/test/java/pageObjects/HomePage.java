package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[@href='/login']")
	WebElement lnkSignupLogin;

	@FindBy(xpath = "//a[contains(text(),'Logged in as')]")
	WebElement lblLoggedInAs;

	@FindBy(linkText = "Logout")
	WebElement lnkLogout;

	public void clickSignupLogin() {
		clickElement(lnkSignupLogin);
	}

	public boolean isLoggedInDisplayed() {
		try {
			return lblLoggedInAs.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void clickLogout() {
		clickElement(lnkLogout);
	}
}