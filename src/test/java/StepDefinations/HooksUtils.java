package StepDefinations;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import RunnerPack.BaseClass;
import io.cucumber.java.*;
import io.cucumber.java.Scenario;

public class HooksUtils {

	public WebDriver driver;
	BaseClass base = new BaseClass();
	public HooksUtils()
	{
		
		this.driver = base.getDriver();
		System.out.println("I am in Hook COns, Driver here "+driver);
	}
	
	@AfterStep
	public void add_Screenshot_Report(Scenario scenario) throws IOException
	{
		
		System.out.println("IN Screenshot step");
		if(scenario.isFailed())
		{
			TakesScreenshot pic = (TakesScreenshot) driver;
			System.out.println("IN failed step");
			File takess = pic.getScreenshotAs(OutputType.FILE);
			byte[] data = FileUtils.readFileToByteArray(takess);
			scenario.attach(data, "image/png", "failstep");
			
		}
	}
	
}
