package GenericUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonlyUsedFunc {
	
	public WebDriver driver;
	String propfile = System.getProperty("user.dir")+"/src/test/resources/test_property.properties";
	
	public CommonlyUsedFunc(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	public void LaunchUrl()
	{
			Properties prop = new Properties();
            try {
				prop.load(new FileInputStream(propfile));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
		driver.get(prop.getProperty("searchurl"));
	}
	
	public void WaitUntilElementVisible(WebElement e)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));
		System.out.println("We are in Wait for Element Visibility");
		wait.until(ExpectedConditions.visibilityOf(e));
	}
	
	public void WaitUntilElementInvisible(WebElement e)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));
		System.out.println("We are in Loader");
		wait.until(ExpectedConditions.invisibilityOf(e));
	}
	
	public void WaitUntilUrlGet(String url)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		System.out.println("We are in URL");
		wait.until(ExpectedConditions.urlContains(url));
	}

}
