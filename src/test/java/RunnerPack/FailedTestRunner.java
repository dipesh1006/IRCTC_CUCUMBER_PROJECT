package RunnerPack;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "@target/FailedTestcases.txt",
	    glue = "StepDefinations", 
	    monochrome = true
	)
public class FailedTestRunner extends AbstractTestNGCucumberTests {

}
