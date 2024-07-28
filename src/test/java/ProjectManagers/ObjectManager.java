package ProjectManagers;

import org.openqa.selenium.WebDriver;

import POMClasses.*;

public class ObjectManager {

	
	WebDriver driver;
	
	public ObjectManager(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	public LoginPage getLogInPageObject()
	{
		return new LoginPage(driver);
	}
	
	public TrainListForDate getTrainListObject()
	{
		return new TrainListForDate(driver);
	}
	
	public BookingConfirmation getBookConfirmationObject()
	{
		return new BookingConfirmation(driver);
	}
	
}
