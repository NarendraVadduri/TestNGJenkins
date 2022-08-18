package testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;

import pageObjects.loginPage;
import stepDefinitions.baseClass;
import utilities.ExcelUtils;
import utilities.ExtentReporting;
import utilities.ReadConfig;

import com.codoid.products.fillo.Recordset;
public class TC2_NewTest_DDT_01 extends baseClass
{
	ReadConfig readconfig = new ReadConfig();
	public WebDriver driver;
	public loginPage lp;
	public ExtentReporting extent;
	public String uname,password, excelPath;

	
//	 @BeforeTest	
//    @Parameters("browser")
//	 public void setup(String browser) {
//		System.out.println(browser);
//		driver = setUp(browser);     // to run the test case from TestNG.xml file
//		//driver = setUp("chrome");  // To run the test case as TestNG test
//		driver.get(baseURL);
//	    lp = new loginPage(driver);
//
//	}
	@Parameters("browser")
	@Test 	(dataProvider="filoData")

	public void DDTTest(String uname, String password) throws InterruptedException {
        
		
		//System.out.println(browser);
		driver = setUp("edge");     // to run the test case from TestNG.xml file
		//driver = setUp("chrome");  // To run the test case as TestNG test
		driver.get(baseURL);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    lp = new loginPage(driver);
		driver.manage().window().maximize();
		lp.setUserName(uname);
		lp.setPassword(password);
		lp.clickLogin();
		//lp.clickLogout();
		

		if(lp.loginHeader_isDisplayed()) {
			
			System.out.println("not logged in");
			lp.page_title_should_be();
		}
		
		else {
			System.out.println("Log In is valid");
			lp.clickLogout();
		}
	
//		
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	//	driver.quit();
		
}
	

	@AfterTest
	public void tearDown() {
		
		driver.quit();
	
	}
	
	@DataProvider(name="filoData")
	String [][]  getData() throws FilloException	{
		    excelPath = System.getProperty("user.dir") +"\\src\\test\\java\\testData\\LoginData.xlsx";
		    Fillo fillo=new Fillo();
		    Connection connection=fillo.getConnection(excelPath);
		    String strSelectQuerry = "Select * from  Sheet1";
		    Recordset recordset = null;
		    recordset = connection.executeQuery(strSelectQuerry);

			int iterationCount = recordset.getCount();
			ArrayList<String> columnLength = recordset.getFieldNames();
			int columnsCount = columnLength.size();
		    String data[][] = new String[iterationCount][columnsCount];
			
			recordset.next();
			for(int i=0;i<iterationCount;i++) {
				for(int j = 0; j < columnsCount; j++){
					data[i][j] = recordset.getField(j).value();
				}
				recordset.next();
			}
			connection.close();
			return data;
	}
	
	
}
