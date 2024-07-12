package ProjectManagers;

import org.openqa.selenium.WebDriver;

import RunnerPack.BaseClass;

public class TextContextObject {

	public WebDriver driver;
	public BaseClass base;
	
	public TextContextObject()
	{
		base = new BaseClass();
    	this.driver = base.getDriver();
    	
	}
	
	
}
