package RunnerPack;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "@target/FailedTestcases.txt",
	    glue = "StepDefinations", 
	    monochrome = true,
	    plugin = {"html:test-output/HtmlReport/CucumberReport.html",
	    	    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
	)
public class FailedTestRunner extends AbstractTestNGCucumberTests {

}
