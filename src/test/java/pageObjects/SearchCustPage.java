package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.waitHelper;

public class SearchCustPage {

public WebDriver ldriver;
 waitHelper waithelper;
	
	public SearchCustPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(ldriver,this);
		waithelper = new waitHelper(ldriver);
	}
	
	@FindBy (how=How.ID, using = "SearchEmail")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy (how=How.ID, using = "SearchFirstName")
	@CacheLookup
	WebElement txtFName;
	
	@FindBy (how=How.ID, using = "SearchLastName")
	@CacheLookup
	WebElement txtLName;
	
	@FindBy (how=How.ID, using = "SearchMonthOfBirth")
	@CacheLookup
	WebElement drpMonth;
	
	@FindBy (how=How.ID, using = "SearchDayOfBirth")
	@CacheLookup
	WebElement drpday;
	
	
	@FindBy (how=How.ID, using = "SearchCompany")
	@CacheLookup
	WebElement txtCompany;
	
	@FindBy (how=How.ID, using = "SearchIpAddress")
	@CacheLookup
	WebElement txtIPAdrress;
	
	@FindBy (how=How.XPATH, using = "//div[@class='k-multiselect-wrap k-floatwrap']")
	@CacheLookup
	WebElement txtCustRoles;
	
	@FindBy (how=How.XPATH, using = "//li[contains(text(),'Administrators')]")
	@CacheLookup
	WebElement lstItemAdmin;
	
	@FindBy (how=How.XPATH, using = "//li[contains(text(),'Registered')]")
	@CacheLookup
	WebElement lstItemReg;
	
	@FindBy (how=How.XPATH, using = "//li[contains(text(),'Guests')]")
	@CacheLookup
	WebElement lstItemGuest;
	
	@FindBy (how=How.XPATH, using = "//li[contains(text(),'Vendors')]")
	@CacheLookup
	WebElement lstItemVendor;
	
	@FindBy (how=How.ID, using = "search-customers")
	@CacheLookup
	WebElement btnSearch;
	
	@FindBy (how=How.XPATH, using = "//table[@role='grid']")
	WebElement tblSearchRslts;
	
	@FindBy (how=How.XPATH, using = "//table[@id='customers-grid']")
	WebElement table;
	
	@FindBy (how=How.XPATH, using = "//table[@id='customers-grid']/tbody/tr")
	List<WebElement> tblRows;
	
	@FindBy (how=How.XPATH, using = "//table[@id='customers-grid']/tbody/tr/td")
	List<WebElement> tblCols;
	
	public void setEmail(String email) {
		waithelper.waitForElement(txtEmail, 10);
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}
	
	public void setFName(String FName) {
		waithelper.waitForElement(txtFName, 10);
		txtFName.clear();
		txtFName.sendKeys(FName);
	}
	
	public void setLName(String LName) {
		waithelper.waitForElement(txtLName, 10);
		txtLName.clear();
		txtLName.sendKeys(LName);
	}
	
	public void bthSearch() {
		btnSearch.click();
	}
	
	public int getNoOfRows() {
		return (tblRows.size());
	}
	
	public int getNoOfColumns() {
		return (tblCols.size());
	}
	
	public boolean searchCustbyEmail(String email) {
		boolean flag = false;
		
		for (int i=1;i<=getNoOfRows();i++)
		{
			String emailid = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[2]")).getText();
			System.out.println(emailid);
			if(emailid.equals("victoria_victoria@nopCommerce.com"))
			{
				flag = true;
			}
		}
		  return flag;
	}
	
	public boolean searchCustbyName(String Name) {
		boolean flag = false;
		
		for (int i=1;i<=getNoOfRows();i++)
		{
			String name = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[3]")).getText();
			String names[]=name.split(" ");  // separating first name and last name in arrya
			
			System.out.println(name);
			if(names[0].equals("Victoria") && names[1].equals("Terces"))
			{
				flag = true;
			}
		}
		  return flag;
	}
	
	
	
}
