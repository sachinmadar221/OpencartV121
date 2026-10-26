package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class LoginTest extends BaseClass {

	@Test(groups = {"Sanity", "Master"})
    public void verify_login() {

        logger.info("***** Starting LoginTest *****");

        try {
            LoginPage lp = new LoginPage(driver);
            lp.setLoginEmail(p.getProperty("email"));
            lp.setLoginPassword(p.getProperty("password"));  
            lp.clickLogin();

            HomePage hp = new HomePage(driver);
            Assert.assertTrue(hp.isLoggedInDisplayed(), "Logged in message not shown");

        } catch (Exception e) {
        	logger.error("Exception: " + e.getMessage());
            Assert.fail("Exception: " + e.getMessage());
        }

        logger.info("***** Finished LoginTest *****");
    }
}