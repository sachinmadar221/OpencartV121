package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // ----- Login section -----
    @FindBy(xpath = "//input[@data-qa='login-email']")
    WebElement txtLoginEmail;

    @FindBy(xpath = "//input[@data-qa='login-password']")
    WebElement txtLoginPassword;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    WebElement btnLogin;

    @FindBy(xpath = "//p[contains(text(),'Your email or password is incorrect')]")
    WebElement msgLoginError;

    // ----- New user signup section -----
    @FindBy(xpath = "//input[@data-qa='signup-name']")
    WebElement txtSignupName;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    WebElement txtSignupEmail;

    @FindBy(xpath = "//button[@data-qa='signup-button']")
    WebElement btnSignup;

    public void setLoginEmail(String email) {
        txtLoginEmail.clear();
        txtLoginEmail.sendKeys(email);
    }

    public void setLoginPassword(String pwd) {
        txtLoginPassword.clear();
        txtLoginPassword.sendKeys(pwd);
    }

    public void clickLogin() {
        clickElement(btnLogin);
    }

    public String getLoginErrorMsg() {
        try {
            return msgLoginError.getText();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public void setSignupName(String name) {
        txtSignupName.sendKeys(name);
    }

    public void setSignupEmail(String email) {
        txtSignupEmail.sendKeys(email);
    }

    public void clickSignup() {
        clickElement(btnSignup);
    }
}