package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stepDefinitions.baseClass;


public class loginPage extends baseClass {

	public WebDriver ldriver;
	

	public loginPage(WebDriver rdriver) {

		PageFactory.initElements(rdriver, this);
		

		ldriver = rdriver;
	}
	
	@FindBy (id = "Email")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy (id = "Password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy (css = "button[type='submit']")
	@CacheLookup
	WebElement bthLogin;
	
	@FindBy (css = "div[class='content-header'] h1")
	@CacheLookup
	WebElement txtDahsbrd;
	
	
	
	//@FindBy (css = "div[class='page-title'] h1")
	@FindBy (className = "brand-image-xl logo-xl")
	@CacheLookup
	WebElement loginHeader;
	
	@FindBy (linkText = "Logout")
	@CacheLookup
	WebElement lnkLogout;
	
	public void setUserName(String uname) {
		txtEmail.clear();
		txtEmail.sendKeys(uname);
		//logger.info("Username is entered");
				
	}
	
	public boolean btnLogin_isDisplayed() {
		
		if(txtDahsbrd.isDisplayed()) 
			
			return true;
		else 
			return false;
	}
	
	public boolean loginHeader_isDisplayed() {
		
		if(loginHeader.isDisplayed() | loginHeader.isEnabled() ) 			
			return true;
		else 
			return false;
	}
	
	public void setPassword(String pwd) {
		txtPassword.clear();
		txtPassword.sendKeys(pwd);
	//	logger.info("password is entered");
			
	}
	
	public void clickLogin() {
		bthLogin.click();
		//logger.info("Login button is clicked");
				
	}
	public void clickLogout() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(ldriver,30);		
		wait.until(ExpectedConditions.elementToBeClickable(lnkLogout));
		Thread.sleep(4000);
		lnkLogout.click();
				
	}
	

	public void user_click_on_logout_link() throws InterruptedException {
		clickLogout();
		Thread.sleep(3000);
	}


	public void page_title_should_be() throws InterruptedException {
		
	//	System.out.println("Not logged in");
		
		if(!loginHeader.isDisplayed()) {
			Assert.assertTrue(false);	
			
			} 
		else {				
			System.out.println("Login was unsuccessful with Invalid credentials");
			}
		Thread.sleep(2000);
	}

	public void close_the_browser() throws InterruptedException {
		ldriver.close();
		Thread.sleep(3000);
	    
	}
	public void invalid_login(String errmsg) {
		
		if(ldriver.getPageSource().contains(errmsg)) {
			Assert.assertTrue(true);
//			ldriver.close();
		} else 
		{
		//	driver.close();
			Assert.assertTrue(false);
		}
	}
}
