package POMClasses;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RunnerPack.BaseClass;

public class BookingConfirmation {

	WebDriver driver;
	BaseClass base;
	
	
	public BookingConfirmation(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	@FindBy(xpath="//input[@role='searchbox' and @aria-autocomplete='list']")
	List<WebElement> nameinpute;
	
	@FindBy(xpath="//div[@class='zeroPadding pull-left ng-star-inserted'] / a/ span [1]")
	WebElement addPassenger;
	
	
	@FindBy(xpath="//button[contains(text(),'Continue ')]")
	WebElement continuebtn;
	
	@FindBy(xpath="//div[contains(text(),'Review Journey')]")
	WebElement review;
	
	@FindBy(xpath="//div[@class='form-group col-xs-12 hidden-xs'] / div /button[contains(text(),'Continue ')]")
	WebElement reviewcontinuebtn;
	
	
	
	@FindBy(xpath="//div[@id='loader']")
	WebElement load;
	
	public void selectPassengersDetails(List<String> passenger)
	{
		base = new BaseClass();
		base.SetDriver(driver);
		
		base.WaitUntilElementInvisible(load);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Actions action = new Actions(driver);
		
		for(int i=0;i<passenger.size();i++)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String name = passenger.get(i).trim();

			nameinpute.get(i).sendKeys(name);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			action.keyDown(Keys.ARROW_DOWN).build().perform();
			action.keyDown(Keys.ENTER).build().perform();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(i<passenger.size()-1)
			{
				addPassenger.click();
			}
			
		
			
		}
		
		
		
	}
	
	
	public void submitPassengerDetails()
	{
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		continuebtn.click();
	}
	
	public void reviewAndSubmit()
	{
		base.WaitUntilUrlGet("reviewBooking");
		base.WaitUntilElementInvisible(load);
		base.WaitUntilElementVisible(review);
		
		try {
			Thread.sleep(12000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		reviewcontinuebtn.click();
	}
	
	
}
