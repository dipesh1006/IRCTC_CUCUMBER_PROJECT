package StepDefinations;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import ProjectManagers.TextContextObject;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class ExtentReportUtils {
	
	
	private static TextContextObject textcontext;
	private static ExtentReports extentReport;
	private Throwable scenarioException;
	private static ExtentTest extenttest;
	public ExtentReportUtils(TextContextObject textcontext)
	{
		this.textcontext = textcontext;
	}
	
	@BeforeAll
	public static void SetupReport()
	{
		extentReport = new ExtentReports();
		
		File file = new File("./Reports/SparkReport.html");
		
		ExtentSparkReporter spark = new ExtentSparkReporter(file);
		
		spark.config().setDocumentTitle("IRCTC Automation Script");
		spark.config().setReportName("Automation Script");
		spark.config().setTheme(Theme.STANDARD);
		
		extentReport.attachReporter(spark);
		
		
	}
	
	@Before
	public void SetupReportForEachScenario(Scenario scenario) throws Exception
	{
		if(extentReport!=null)
		{
			extenttest =  extentReport.createTest(scenario.getName());
			
		}
		
	}
	
	@After
	public void MonitorSteps(Scenario scenario) throws Exception
	{
		if(extentReport!=null)
		{
			System.out.println("In Extent report After step");
			if(!scenario.isFailed())
			{
				System.out.println("In Extent report Pass step");
				extenttest.log(Status.PASS, "Test is Pass");
				
			}
			textcontext.driver.close();
			
		}
	}
	
	@AfterStep
	public void add_Screenshot_Report(Scenario scenario) throws Exception
	{
		if(extentReport!=null)
		{
			if(scenario.isFailed())
			{
				System.out.println("In Extent report fail step");
		        String exceptionmessage = scenario.getStatus().toString(); // You can retrieve more detailed info if needed.
		        String filepath = takeScreenShot();
		        extenttest.addScreenCaptureFromPath(filepath)
		        .log(Status.FAIL, MarkupHelper.createCodeBlock(exceptionmessage));
		       textcontext.driver.close();
			}

		}
	}
	
	
	@AfterAll
	public static void teardown()
	{
		extentReport.flush();
	}
	
	public static String takeScreenShot() throws Exception
	{
		TakesScreenshot ss = (TakesScreenshot)textcontext.driver;
		File source = ss.getScreenshotAs(OutputType.FILE);
		File destination = new File("./Reports/ScreenShots/FailStep.jpg");
		FileUtils.copyFile(source, destination);
		return destination.getAbsolutePath();
		
		
	}
	
	
}
