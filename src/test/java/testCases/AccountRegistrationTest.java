package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class AccountRegistrationTest extends BaseClass {

	@Test(groups = { "Regression", "Master" })
	public void verify_account_registration() {

		logger.info("***** Starting AccountRegistrationTest *****");

		try {
			LoginPage lp = new LoginPage(driver);

			logger.info("Providing name and email for signup");
			lp.setSignupName(randomeString().toUpperCase());
			lp.setSignupEmail(randomeString().toLowerCase() + "@gmail.com"); // random, so each run is a new user
			lp.clickSignup();

			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

			logger.info("Providing account details");
			regpage.selectTitleMr();
			regpage.setPassword(randomeAlphaNumberic());
			regpage.setDateOfBirth("10", "May", "1995");
			regpage.tickNewsletter();

			logger.info("Providing address details");
			regpage.setFirstName(randomeString().toUpperCase());
			regpage.setLastName(randomeString().toUpperCase());
			regpage.setAddress("MG Road");
			regpage.selectCountry("India");
			regpage.setState("Karnataka");
			regpage.setCity("Bengaluru");
			regpage.setZipcode("560001");
			regpage.setMobile(randomeNumber());

			regpage.clickCreateAccount();

			logger.info("Validating confirmation message");
			String confmsg = regpage.getConfirmationMsg();

			if (confmsg.equalsIgnoreCase("Account Created!")) {
				Assert.assertTrue(true);
			} else {
				logger.error("Test failed. Message was: " + confmsg);
				Assert.fail();
			}

		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			Assert.fail();
		}

		logger.info("***** Finished AccountRegistrationTest *****");
	}
}