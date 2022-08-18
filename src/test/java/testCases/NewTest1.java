package testCases;

import pageObjects.loginPage;
import stepDefinitions.baseClass;
import utilities.ExtentReporting;
import utilities.ReadConfig;

import org.testng.annotations.*;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;



public class NewTest1 extends baseClass {
	ReadConfig readconfig = new ReadConfig();
	public WebDriver driver;
	public loginPage lp;
	public ExtentReporting extent;
	//org.apache.log4j.Logger log = logger.getLogger(NewTest1.class);
	
	
	 @BeforeTest	
     @Parameters("browser")
	 public void setup(String browser) {
		System.out.println(browser);
		driver = setUp(browser);     // to run the test case from TestNG.xml file
	//	driver = setUp("edge");  // To run the test case as TestNG test
		driver.get(baseURL);
	    lp = new loginPage(driver);
	    extent = new ExtentReporting();
	}

	 
	@Test
    public void loginTest() throws Exception   {
		logger.info("URL is opened");
		driver.manage().window().maximize();
		//log.info("browser is maxmimised");
		logger.info("browser maximized");	
		lp.setUserName(userName);
		logger.info("Username is entered");
		lp.setPassword(password);
		logger.info("password is provided");
		Thread.sleep(3000);
		lp.clickLogin();
		if(driver.getPageSource().contains("Login was unsuccessful.")) {
			driver.close();
		        Assert.assertTrue(false);
				System.out.println("Login was unsuccessful with Invalid credentials");
				
			} 
		else {				
				Assert.assertEquals(title,driver.getTitle());
				
				logger.info("title is validated");
			}
		Thread.sleep(2000);
  }
  

	@AfterTest
	public void tearDown() {
		
		driver.quit();
	
	}

}
