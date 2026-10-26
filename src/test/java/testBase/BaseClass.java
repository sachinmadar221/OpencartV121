package testBase;

import java.io.FileReader;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; //log4j
import org.apache.logging.log4j.Logger; //log4j
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass {

	public WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups = { "Sanity", "Regression", "Master", "Datadriven" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {

		FileReader file = new FileReader("./src/test/resources/config.properties");
		p = new Properties();
		p.load(file);

		logger = LogManager.getLogger(this.getClass());
		logger.info("Running on OS: " + os + ", browser: " + br);

		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {

			DesiredCapabilities capabilities = new DesiredCapabilities();

			// operating system
			   if (os.equalsIgnoreCase("windows")) {
			       capabilities.setPlatform(Platform.WINDOWS);
			   } else if (os.equalsIgnoreCase("mac")) {
			       capabilities.setPlatform(Platform.MAC);
			   } else if (os.equalsIgnoreCase("linux")) {
			       capabilities.setPlatform(Platform.LINUX);
			   } else {
			       System.out.println("No matching os..");
			       return;
			   }

			// browser
			switch (br.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			case "firefox":
				capabilities.setBrowserName("firefox");
				break;
			default:
				System.out.println("No matching browser");
				return;
			}

			driver = new RemoteWebDriver(new URL(p.getProperty("gridURL")), capabilities);
		}

		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {

			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			// case "firefox": driver = new FirefoxDriver(); break;
			default:
				System.out.println("Invalid browser name: " + br);
				return;
			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}

	@AfterClass(groups = { "Sanity", "Regression", "Master", "Datadriven" })
	public void tearDown() {
		driver.quit();
	}

	public String randomeString() {
		return RandomStringUtils.randomAlphabetic(5);
	}

	public String randomeNumber() {
		return RandomStringUtils.randomNumeric(10);
	}

	public String randomeAlphaNumberic() {
		return RandomStringUtils.randomAlphabetic(3) + "@" + RandomStringUtils.randomNumeric(3);
	}
}