package stepDefinitions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import pageObjects.SearchCustPage;
import pageObjects.addCustomerPage;
import pageObjects.loginPage;
import utilities.ReadConfig;

public class baseClass {
	
	ReadConfig readconfig = new ReadConfig();
	
	public static WebDriver driver;
	public loginPage lp;
	public addCustomerPage addcust;
	public SearchCustPage searchcust;
	
	public String baseURL = readconfig.getapplicationUrl();
	public String userName = readconfig.getUserName();
	public String password = readconfig.getpassword();
	public String title = readconfig.gettitle();
	public static Logger logger;

	
	public static String randomString() {
		String generatestring1 = RandomStringUtils.randomAlphabetic(5);
		return(generatestring1);
	}
	

	public WebDriver setUp(String br) {
		
		PropertyConfigurator.configure("log4j.properties");
		logger = Logger.getLogger("Narendra-Selenium");
	
		if(br.equals("chrome")) {
		System.setProperty("webdriver.chrome.driver",readconfig.getchromepath());
		driver = new ChromeDriver();
		} 
		else  if(br.equals("edge")) {
			System.setProperty("webdriver.edge.driver",readconfig.getedgepath());
			driver = new EdgeDriver();
			}
		else  if(br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",readconfig.geckopath());
			driver = new FirefoxDriver();
			System.out.println("for test browser");
			}
	return driver;
			
		}
public static String takeScreenShot(String screenShotName) throws IOException {
        
        //create a string variable which will be unique always
        String df = new SimpleDateFormat("yyyyMMddhhss").format(new Date());
        
        //create object variable of TakeScreenshot class  
        TakesScreenshot ts = (TakesScreenshot)driver;
        
        //create File object variable which holds the screen shot reference 
        File source = ts.getScreenshotAs(OutputType.FILE);
        
        //store the screen shot path in path variable. Here we are storing the screenshots under screenshots folder 
        String path = System.getProperty("user.dir") + "\\test-output\\Screenshots\\" + screenShotName + df + ".png";
        
        System.out.println(path);
     
        //create another File object variable which points(refer) to the above stored path variable
        File destination = new File(path);
        
        //use FileUtils class method to save the screen shot at desired path
        FileUtils.copyFile(source, destination);
        
        //return the path where the screen shot is saved 
        return path;        
		} 
        
    }
