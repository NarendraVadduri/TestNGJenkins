package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class addCustomerPage {
	
public WebDriver ldriver;
	
	public addCustomerPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver,this);
	}
	
	By lnkCustomer_menu=By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
	By lnkCustomer_menu_Item=By.xpath("//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]");
	
	By btnAddNew=By.xpath("//a[normalize-space()='Add new']");
	By txtEmail = By.xpath("//input[@id='Email']");
	By txtPassword = By.xpath("//input[@id='Password']");
	
	By txtFName = By.id("FirstName");
	By txtLName = By.id("LastName");
	
	By rdbGenderMale = By.id("Gender_Male");
	By rdbGenderFemale = By.xpath("Gender_Female");
	
	By dtDob = By.id("DateOfBirth");
	By txtCompName = By.id("Company");
	
	By listroleitem = By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");
	By ListItemAdmin = By.xpath("//li[contains(text(),'Administrators')]");
	By ListItemRegistered = By.xpath("//li[contains(text(),'Registered')]");
	By ListItemGuest = By.xpath("//li[contains(text(),'Guests')]");
	By ListItemVendor = By.xpath("//li[contains(text(),'Vendors')]");
	
	By chkTaxExempt = By.xpath("//input[@id='IsTaxExempt']");
	By txtNewsLetter = By.className("k-multiselect-wrap k-floatwrap");
	
	By drpMgrVendor = By.id("VendorId");
	By chkActive = By.id("Active");
	By txtAdminContent = By.id("AdminComment");
	By btnSave = By.name("save");
	
	
	public String getPageTitle() {
		return ldriver.getTitle();
	}
	public void clickonCustomersMenu() {
		ldriver.findElement(lnkCustomer_menu).click();
	}
	
	public void clickonCustomersMenuItem() {
		ldriver.findElement(lnkCustomer_menu_Item).click();
	}
	
	public void clickonAddNewCustomer() {
		ldriver.findElement(btnAddNew).click();
	}
	
	public void setEmail(String email) {
		ldriver.findElement(txtEmail).sendKeys(email);
	}
	
	public void setPassword(String pwd) {
		ldriver.findElement(txtPassword).sendKeys(pwd);
	}
	
	public void setRolecustomer(String role) throws InterruptedException {
		Thread.sleep(3000);
		WebElement listItem;
		listItem = ldriver.findElement(ListItemAdmin);
		
		JavascriptExecutor js = (JavascriptExecutor)ldriver;
		js.executeScript("arguments[0].click", listItem);
	}
	
	public void setGender(String gender) {
		if(gender.equals("Male")){
			ldriver.findElement(rdbGenderMale).click();
		}else if(gender.equals("Female")){
				ldriver.findElement(rdbGenderFemale).click();
		}else {		
			ldriver.findElement(rdbGenderMale).click();  // by default click Male radio button
		}
		
	}
	public void setFName(String FName) {
		ldriver.findElement(txtFName).sendKeys(FName);
	}
	
	public void setLname(String LName) {
		ldriver.findElement(txtLName).sendKeys(LName);
	}
	
	public void setDob(String dob) {
		ldriver.findElement(dtDob).sendKeys(dob);
	}
	
	public void setCompanyName(String CName) {
		ldriver.findElement(txtCompName).sendKeys(CName);
	}
		
	public void setAdminContent(String Adcontent) {
		ldriver.findElement(txtAdminContent).sendKeys(Adcontent);
	}
	
	public void savebtnClick() {
		ldriver.findElement(btnSave).click();
	}
	
	
}
