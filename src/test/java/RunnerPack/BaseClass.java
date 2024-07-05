package RunnerPack;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {

	public static WebDriver driver;
	
	Properties prop;
	String propfile = System.getProperty("user.dir")+"/src/test/resources/test_property.properties";
	
	 public BaseClass() {

		 		prop = new Properties();
		        try {
		            prop.load(new FileInputStream(propfile));
		        } catch (IOException e) {
		            e.printStackTrace();
		        }

	        
	    }
	
	public WebDriver getDriver()
	{
		System.out.println("Driver from Base "+driver);
		if(driver==null)
		{
			System.out.println("Hi Champ, Driver is null here");
			String browsername = prop.getProperty("browser");
			switch(browsername)
			{
				case "chrome": WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
		        options.addArguments("--disable-notifications");
		        driver = new ChromeDriver(options);
		        break;
				case "edge": WebDriverManager.edgedriver().setup();
				EdgeOptions option = new EdgeOptions();
				//option.addArguments("--disable-notifications");
		        driver = new EdgeDriver(option);
		        break;
			}
			
	        driver.manage().window().maximize();
	       
		}
		
        
		return driver;
	}
	
	public void SetDriver(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void LaunchUrl()
	{
		//prop = new Properties();
		driver.get(prop.getProperty("searchurl"));
	}
	
	public void WaitUntilElementVisible(WebElement e)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));
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
