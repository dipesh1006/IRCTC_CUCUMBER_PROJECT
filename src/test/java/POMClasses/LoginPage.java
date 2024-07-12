package POMClasses;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import GenericUtils.CommonlyUsedFunc;
import ProjectManagers.TextContextObject;
import RunnerPack.BaseClass;

public class LoginPage {

	WebDriver driver;
	TextContextObject textcontext;
	CommonlyUsedFunc common;
	public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	@FindBy(xpath="//label[contains(text(),'BOOK TICKET')]")
	WebElement header;
	
	
	@FindBy(xpath="//a[contains(text(),'LOGIN')]")
	WebElement loginbtn;
	
	@FindBy(xpath="//input[@placeholder='User Name']")
	WebElement username;
	
	@FindBy(xpath="//input[@placeholder='Password']")
	WebElement password;
	
	@FindBy(xpath="//button[contains(text(),'SIGN IN')]")
	WebElement sign;
	
	@FindBy(xpath="//span[contains(text(),'Welcome Dipesh Pal (dipesh1006pal)')]")
	WebElement welcome;
	
	@FindBy(xpath="//input[@role='searchbox' and @aria-controls ='pr_id_1_list']")
	WebElement SourceStation;
	
	@FindBy(xpath="//div/ul[@role='listbox']/li/span")
	List<WebElement> sourcelist;
	
	//div/ul[@role='listbox']/li/span
	
	@FindBy(xpath="//li[1] //span[contains(text(),' NJP')]")
	WebElement namedest;
	@FindBy(xpath="//input[@role='searchbox' and @aria-controls ='pr_id_2_list']")
	WebElement DestStation;
	
	@FindBy(xpath="//span[@class='ng-tns-c58-10 ui-calendar'] / input")
	WebElement datebox;
	
	@FindBy(xpath="//div[@class='ui-datepicker-title ng-tns-c58-10'] / span[1]")
	WebElement Calmonth;
	
	@FindBy(xpath="//div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all ng-tns-c58-10'] /a [@class='ui-datepicker-next ui-corner-all ng-tns-c58-10 ng-star-inserted'] / span")
	WebElement rightshift;
	
	@FindBy(xpath="//table/tbody / tr / td /a")
	List<WebElement> listofDates;
	
	@FindBy(xpath="//div[@class='form-group'] / p-dropdown  / div / div[@role='button']")
	List<WebElement> CoatchClass;
	
	@FindBy(xpath="//div[@class='ui-dropdown-items-wrapper ng-tns-c65-11'] / ul / p-dropdownitem / li /span")
	List<WebElement> listofClass;
	
	@FindBy(xpath="//div[@class='ui-dropdown-items-wrapper ng-tns-c65-11'] / ul")
	WebElement classbox;
	
	
	@FindBy(xpath="// div / button[contains(text(),'Search')]")
	WebElement submit;
	
	
	@FindBy(xpath="//button/span[contains(text(),'Show Available Trains')]")
	WebElement showtrainbtn;
	
	@FindBy(xpath="//div[@id='loader']")
	WebElement load;
	
	public void launchBrowser()
	{
		//base = new BaseClass();
		//base.SetDriver(driver);
    	this.common = new CommonlyUsedFunc(driver);
    	common.LaunchUrl();
    	common.WaitUntilElementInvisible(load);
    	common.WaitUntilElementVisible(header);
		
	}
	
	public void clickLoginToSelectDestination()
	{
		loginbtn.click();
		common.WaitUntilElementVisible(username);
		common.WaitUntilElementVisible(password);
		common.WaitUntilElementVisible(sign);
	}
	
	public void fillUpNameAndPass(String name, String pass)
	{

		username.sendKeys(name);
		password.sendKeys(pass);
	}
	
	public void SignIn()
	{
		sign.click();
	}
	
	public void fillSourceAndDest(String source, String dest) throws InterruptedException
	{
		common.WaitUntilElementInvisible(load);
		Actions action = new Actions(driver);
		Thread.sleep(5000);
		SourceStation.clear();
		SourceStation.sendKeys(source);
		Thread.sleep(4000);
		for(int i = 0; i< sourcelist.size();i++)
		{
			if(sourcelist.get(i).getText().contains(source))
			{
				action.sendKeys(Keys.ENTER).build().perform();
				break;
			}
			else
			{
				action.sendKeys(Keys.ARROW_DOWN).build().perform();
			}
		}
		
		
		Thread.sleep(5000);

		DestStation.sendKeys(dest);
		
		Thread.sleep(3000);
		action.sendKeys(Keys.ENTER).build().perform();
		//dropdown1.selectByVisibleText("NEW JALPAIGURI - NJP (NEW JALPAIGURI)");
		
		Thread.sleep(2000);
		
	}
	
	public void selectDate(String date)
	{
		String[] date_month_year = date.split("-");
		
		String dat = date_month_year[0];
		String month = date_month_year[1];
		String year = date_month_year[2];

		datebox.click();
		
		common.WaitUntilElementVisible(Calmonth);
		
		while(!Calmonth.getText().equalsIgnoreCase(month))
		{
			System.out.println("In While loop");
			System.out.println("User Want "+month+" You present in "+Calmonth.getText());
			rightshift.click();
			common.WaitUntilElementVisible(Calmonth);
			
		}
		
		for(int i=0;i<listofDates.size();i++)
		{
			if(dat.equalsIgnoreCase(listofDates.get(i).getText()))
			{
				listofDates.get(i).click();
				break;
			}
		}
		
		
	}
	
	public void SelectClass(String classes)
	{
		WebElement coatch = CoatchClass.get(0);
		
		coatch.click();
		
		common.WaitUntilElementVisible(classbox);
		
		for(int i=0;i<listofClass.size();i++)
		{
			
			if(listofClass.get(i).getText().contains(classes))
			{
				listofClass.get(i).click();
				break;
			}
			
		}
		
	}
	
	
	public void submitJournyForm()
	{
		submit.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	
	
}
