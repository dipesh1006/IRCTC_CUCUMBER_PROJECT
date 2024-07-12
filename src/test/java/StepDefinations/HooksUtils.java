package StepDefinations;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import ProjectManagers.TextContextObject;
import RunnerPack.BaseClass;
import io.cucumber.java.*;
import io.cucumber.java.Scenario;

public class HooksUtils {

	TextContextObject textcontext;
	public HooksUtils(TextContextObject textcontext)
	{
		this.textcontext = textcontext;
	}
	

	
	@AfterStep
	public void add_Screenshot_Report(Scenario scenario) throws IOException
	{
		
		System.out.println("IN Screenshot step");
		if(scenario.isFailed())
		{
			TakesScreenshot pic = (TakesScreenshot) textcontext.driver;
			System.out.println("IN failed step");
			File takess = pic.getScreenshotAs(OutputType.FILE);
			byte[] data = FileUtils.readFileToByteArray(takess);
			scenario.attach(data, "image/png", "failstep");
			
		}
	}
	
	/*@After
	public void Close_Browser()
	{
		
		textcontext.driver.close();
      
	}*/
	
	
}
