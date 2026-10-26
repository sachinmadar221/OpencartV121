package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AccountRegistrationPage extends BasePage {

    public AccountRegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "id_gender1")
    WebElement rdoMr;

    @FindBy(id = "password")
    WebElement txtPassword;

    @FindBy(id = "days")
    WebElement drpDay;

    @FindBy(id = "months")
    WebElement drpMonth;

    @FindBy(id = "years")
    WebElement drpYear;

    @FindBy(id = "newsletter")
    WebElement chkNewsletter;

    @FindBy(id = "first_name")
    WebElement txtFirstName;

    @FindBy(id = "last_name")
    WebElement txtLastName;

    @FindBy(id = "address1")
    WebElement txtAddress;

    @FindBy(id = "country")
    WebElement drpCountry;

    @FindBy(id = "state")
    WebElement txtState;

    @FindBy(id = "city")
    WebElement txtCity;

    @FindBy(id = "zipcode")
    WebElement txtZipcode;

    @FindBy(id = "mobile_number")
    WebElement txtMobile;

    @FindBy(xpath = "//button[@data-qa='create-account']")
    WebElement btnCreateAccount;

    @FindBy(xpath = "//h2[@data-qa='account-created']")
    WebElement msgConfirmation;

    public void selectTitleMr() {
        clickElement(rdoMr);
    }

    public void setPassword(String pwd) {
        txtPassword.sendKeys(pwd);
    }

    public void setDateOfBirth(String day, String month, String year) {
        new Select(drpDay).selectByValue(day);
        new Select(drpMonth).selectByVisibleText(month);
        new Select(drpYear).selectByValue(year);
    }

    public void tickNewsletter() {
        clickElement(chkNewsletter);
    }

    public void setFirstName(String fname) {
        txtFirstName.sendKeys(fname);
    }

    public void setLastName(String lname) {
        txtLastName.sendKeys(lname);
    }

    public void setAddress(String address) {
        txtAddress.sendKeys(address);
    }

    public void selectCountry(String country) {
        new Select(drpCountry).selectByVisibleText(country);
    }

    public void setState(String state) {
        txtState.sendKeys(state);
    }

    public void setCity(String city) {
        txtCity.sendKeys(city);
    }

    public void setZipcode(String zip) {
        txtZipcode.sendKeys(zip);
    }

    public void setMobile(String mobile) {
        txtMobile.sendKeys(mobile);
    }

    public void clickCreateAccount() {
        clickElement(btnCreateAccount);
    }

    public String getConfirmationMsg() {
        try {
            return msgConfirmation.getText();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}