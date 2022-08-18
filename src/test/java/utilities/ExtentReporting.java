package utilities;


// Listener class to generate the extent report
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import stepDefinitions.baseClass;

public class ExtentReporting extends TestListenerAdapter 
	{

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	public baseClass bs;
  public String screenshotPath;
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // for time stamp
	//	System.out.println(timeStamp);
		String repName = "Test-Report-"+timeStamp+".html";
	//	System.out.println(repName);
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\test-output\\"+repName); //specify the report location
		System.out.println(System.getProperty("user.dir")+"\\test-output\\"+repName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"\\extent-config.xml");
		System.out.println(System.getProperty("user.dir")+"\\extent-config.xml");
		
		extent = new ExtentReports();
		bs = new baseClass();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Narendra");
		
		htmlReporter.config().setDocumentTitle("Narendra Selenium"); // title of the report
		htmlReporter.config().setReportName("Functional Test Report"); // Report Name
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);  // chart location
		htmlReporter.config().setTheme(Theme.STANDARD);		
		
	}
	
	   public void onTestSuccess(ITestResult tr) {
		//   System.out.println(tr.getName());
		   logger=extent.createTest(tr.getName());  // Create New entry in the report
		  // System.out.println(logger);
		   logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); // send the passed tc information in green
		   try {
				screenshotPath = bs.takeScreenShot(tr.getName());  // called from base class to take the screenshot
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	       
		       //String screenshotPath = System.getProperty("user.dir")+"\\Screenshots\\";
		       
		       File f = new File(screenshotPath);
		       System.out.println(f);
		
		       try {
				logger.pass("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath)); // adding screenshot in report
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    	
		
		   
		   extent.flush();
			
	   }
	   
	   public void onTestFailure(ITestResult tr) {
		   logger=extent.createTest(tr.getName());  // Create New entry in the report
		   logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(), ExtentColor.RED)); // send the passed tc information in green
		
		try {
			screenshotPath = bs.takeScreenShot(tr.getName());  // called from base class to take the screenshot
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       
	       //String screenshotPath = System.getProperty("user.dir")+"\\Screenshots\\";
	       
	       File f = new File(screenshotPath);
	       System.out.println(f);
	
	       try {
			logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath)); // adding screenshot in report
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
	
	         extent.flush();
	   } 
	   
	   public void onTestSkipped(ITestResult tr) {
		   logger=extent.createTest(tr.getName());  // Create New entry in the report
		   logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE)); // send the passed tc information in green
		   extent.flush();
	   }
	   
	}

