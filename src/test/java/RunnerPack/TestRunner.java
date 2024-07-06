package RunnerPack;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/java/FeatureFiles",
	    glue = "StepDefinations", 
	    monochrome = true,
	    plugin = {"html:test-output/HtmlReport/CucumberReport.html",
	    		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
	    			"rerun:target/FailedTestcases.txt"},
	    tags="@Regressiontest"
	)
public class TestRunner extends AbstractTestNGCucumberTests {

	
}

