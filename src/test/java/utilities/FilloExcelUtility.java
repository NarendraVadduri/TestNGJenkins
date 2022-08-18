package utilities;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import com.codoid.products.exception.FilloException;
	import com.codoid.products.fillo.Connection;
	import com.codoid.products.fillo.Fillo;
	import com.codoid.products.fillo.Recordset;
	 
	public class FilloExcelUtility  {
		public static String uname;
		public static String password;
		
	     
	    static WebDriver driver;
	     
	   	     
	   public static void main(String args[]) throws InterruptedException, 
	   		FilloException{
	     
	    //Calling up the GoogleChrome driver
//	    System.setProperty("webdriver.chrome.driver", "D:\\Srinivas\\New
//	 folder\\exe\\chromedriver.exe");
//	    driver = newChromeDriver();
//	     
	    //Opening the demo site - wordpress.com
	  //  driver.get("https://wordpress.com/start/about?ref=create-blog-lp");
	     
	    //Locating the Test data excel file
	    String excelPath = System.getProperty("user.dir") +"\\src\\test\\java\\testData\\LoginData.xlsx";
	    System.out.println(excelPath);
	     
	    //Create an Object of Fillo Class
	    Fillo fillo = new Fillo();
	     
	    //Create an Object for Connection class and use getConnection()
	    //method defined inside Fillo class, to establish connection between excel sheet and Fillo API’s.
	    Connection connection = fillo.getConnection(excelPath);
	     
	    //Select all the values present in a sheet, which is present inside the excel and store its output in a String variable
	    String strSelectQuerry = "Select * from  Sheet1";
	    System.out.println(strSelectQuerry);
	     
	    //Execute the Select query and store the result in a Recordset class present in Fillo API.
	    Recordset recordset = null;
	    recordset = connection.executeQuery(strSelectQuerry);
	     
	    //use while loop to iterate through all columns and rows available in sheet present inside excel file
	    while(recordset.next()){
	         
	    System.out.println("Column 1 = " +recordset.getField("username"));
	    try {
			uname = recordset.getField("username");
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
//	    driver.findElement(By.xpath("//input[@name='siteTitle']")).clear();
//	 
//	    driver.findElement(By.xpath("//input[@name='siteTitle']")).sendKeys(siteTitle);
	             
	    System.out.println("Column 2 = " +recordset.getField("password"));
	    try {
			password = recordset.getField("password");
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	             
//	    driver.findElement(By.xpath("//input[@name='siteTopic']")).clear();
//	 
//	driver.findElement(By.xpath("//input[@name='siteTopic']")).sendKeys(siteTopic);
//	    connection.close();
	             
	            }
	    //Use update query to update the details in excel  file
//	    Connection connection1 = fillo.getConnection(excelPath);
//	     
//	    System.out.println("Column 1 value before Update clause = "	+recordset.getField("SiteTitle"));      
//	    String strUpdateQuerry = "Update Data Set SiteTitle = 'SoftwareTestingHelp.com' ";
//	     
//	    System.out.println(strUpdateQuerry);
//	    connection1.executeUpdate(strUpdateQuerry);
//	     
//	    System.out.println("Column 1 value after Update clause = "+recordset.getField("SiteTitle"));
//	     
//	    //Use Insert query to update the data in excel sheet
//	    Connection connection2 = fillo.getConnection(excelPath);
//	     
//	    System.out.println("Column 1 and column 2 value before insert clause = " +recordset.getField("SiteTitle") 
//	    +recordset.getField("siteTopic"));      
//	     
//	    String strInsertQuerry = "INSERT INTO Data (SiteTitle,SiteTopic) Values('Bharat','NewDelhi')";
//	    System.out.println(strInsertQuerry);
//	     
//	    connection2.executeUpdate(strInsertQuerry);
//	     
//	    System.out.println("Column 1 and column 2 value after insert clause = "	+recordset.getField("SiteTitle") 
//	    +recordset.getField("siteTopic"));
	             
	    
	}
}
