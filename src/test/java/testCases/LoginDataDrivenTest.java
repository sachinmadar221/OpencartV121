package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class LoginDataDrivenTest extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = { "Datadriven", "Master" })
	public void verify_loginDDT(String email, String pwd, String exp) {

		logger.info("***** Starting LoginDataDrivenTest for: " + email + " *****");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickSignupLogin();

			LoginPage lp = new LoginPage(driver);
			lp.setLoginEmail(email);
			lp.setLoginPassword(pwd);
			lp.clickLogin();

			HomePage after = new HomePage(driver);
			boolean loggedIn = after.isLoggedInDisplayed();

			if (exp.equalsIgnoreCase("Valid")) {
				if (loggedIn) {
					after.clickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.fail("Valid data, but login failed");
				}
			}

			if (exp.equalsIgnoreCase("Invalid")) {
				if (loggedIn) {
					after.clickLogout();
					Assert.fail("Invalid data, but login succeeded");
				} else {
					Assert.assertTrue(true);
				}
			}

		} catch (Exception e) {
			Assert.fail("Exception: " + e.getMessage());
		}

		logger.info("***** Finished LoginDataDrivenTest *****");
	}
}