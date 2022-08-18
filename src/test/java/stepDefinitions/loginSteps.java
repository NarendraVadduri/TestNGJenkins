package stepDefinitions;

/* author Narendra */

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

//import cucumber.api.java.en.*;

import io.cucumber.java.en.*;
import pageObjects.SearchCustPage;
import pageObjects.addCustomerPage;
import pageObjects.loginPage;


public class loginSteps extends baseClass{
	
	
	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		lp = new loginPage(driver);
		
	    
	}

	@When("User opens url  {string}")
	public void user_opens_url(String url) {
	    driver.get(url);
	    driver.manage().window().maximize();
	}

	@When("User enter as email {string} and password as {string}")
	public void user_enter_as_email_and_password_as(String uname, String pwd) {
	    lp.setUserName(uname);
	    lp.setPassword(pwd);
	}
	
	
	@When("click on Login")
	public void click_on_login() throws InterruptedException {
		lp.clickLogin();
		Thread.sleep(2000);	
		    
	}
	
	@Then ("Login not successful and message should be {string}")
	public void invalid_login(String errmsg) {
		if(driver.getPageSource().contains(errmsg)) {
			Assert.assertTrue(true);
//			driver.close();
		} else 
		{
		//	driver.close();
			Assert.assertTrue(false);
		}
	}


	@When("User click on Logout link")
	public void user_click_on_logout_link() throws InterruptedException {
		lp.clickLogout();
		Thread.sleep(3000);
	}

	@Then("page title should be {string}")
	public void page_title_should_be(String title) throws InterruptedException {
		
		if(driver.getPageSource().contains("Login was unsuccessful.")) {
			  driver.close();
		        Assert.assertTrue(false);
				System.out.println("Login was unsuccessful with Invalid credentials");
				
			} 
		else {				
				Assert.assertEquals(title,driver.getTitle());
			}
		Thread.sleep(2000);
	}
	@Then("close the browser")
	public void close_the_browser() throws InterruptedException {
		driver.close();
		Thread.sleep(3000);
	    
	}
	
	//Customer Features Definition
	
	@Then("User can view Dashboard")
	public void user_can_view_dashboard() {
		
		addcust = new addCustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration",addcust.getPageTitle()); 
	    
	}

	@When("User clicks on Customers menu")
	public void user_clicks_on_customers_menu() throws InterruptedException {
	    Thread.sleep(3000);
	    addcust.clickonCustomersMenu();
	}

	@When("Clicks on Customers Menu Item")
	public void clicks_on_customers_menu_item() throws InterruptedException {
		Thread.sleep(3000);
	    addcust.clickonCustomersMenuItem();
	    
	}

	@When("Click on Add New button")
	public void click_on_add_new_button() throws InterruptedException {
	    Thread.sleep(3000);
	    addcust.clickonAddNewCustomer();
	}

	@Then("User can view Add New Customer Page")
	public void user_can_view_add_new_customer_page() {
		Assert.assertEquals("Add a new customer / nopCommerce administration",addcust.getPageTitle()); 
	}

	@When("User enters Customers Info")
	public void user_enters_customers_info() throws InterruptedException {
		String email = randomString()+"@gmail.com";
		addcust.setEmail(email);
		addcust.setPassword("test123");
		addcust.setRolecustomer("Guest");
		Thread.sleep(3000);
		
		addcust.setGender("Male");
		addcust.setFName("Narendra");
		addcust.setLname("Kumar");
		addcust.setDob("04/22/1985");
		addcust.setCompanyName("Dheeraj Company");
		addcust.setAdminContent("This is for testing");
	  
	}

	@When("Click on Save button")
	public void click_on_save_button() throws InterruptedException {
	    addcust.savebtnClick();
	    Thread.sleep(3000);
	}

	@Then("User can view Customer Information message {string}")
	public void user_can_view_customer_information_message(String msg) {
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully"));
		
	}
	
	// Customers Search page
	
	@When("enter Customers Email")
	public void enter_customers_email() {
		searchcust=new SearchCustPage(driver);
		searchcust.setEmail("victoria_victoria@nopCommerce.com");
	   
	}

	@When("click on Search button")
	public void click_on_search_button() throws InterruptedException {
		searchcust.bthSearch();
		Thread.sleep(3000);
	    
	}

	@Then("User should found email in Search table")
	public void user_should_found_email_in_search_table() {
	    boolean status = searchcust.searchCustbyEmail("victoria_victoria@nopCommerce.com");
	    Assert.assertEquals(true, status);
	   	}


	// Customer Search page with First and Last Name
	
	@When("enter Customers First Name")
	public void enter_customers_first_name() {
		searchcust=new SearchCustPage(driver);
		searchcust.setFName("Victoria");
	}
	@When("enter Customers Last Name")
	public void enter_customers_last_name() {
		searchcust.setLName("Terces");
	    
	}
	@Then("User should found Name in Search table")
	public void user_should_found_name_in_search_table() {
		boolean status = searchcust.searchCustbyName("Victoria Terces");
		Assert.assertEquals(true, status);
	   
	}
	
	
}
