package POMClasses;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RunnerPack.BaseClass;

public class TrainListForDate {

	WebDriver driver;
	BaseClass base;
	
	List<WebElement> enablebutton;
	
	public TrainListForDate(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	@FindBy(xpath="//button/span[contains(text(),'Show Available Trains')]")
	WebElement showtrainbtn;
	
	
	@FindBy(xpath="//app-train-avl-enq / div [@class='ng-star-inserted'] / div / div / strong")
	List<WebElement> trainnames;
	
	@FindBy(xpath="//app-train-avl-enq / div [@class='ng-star-inserted'] / div [5] / div / table / tr / td [1] / div /div [1]")
	List<WebElement> trainSleeperclass;
	
	@FindBy(xpath="//app-train-avl-enq / div [@class='ng-star-inserted'] / div [7] / div / div [3] / table / tr / td [2] / div /div [1]")
	List<WebElement> date;
	
	@FindBy(xpath="//app-train-avl-enq / div [@class='ng-star-inserted'] / div [7] / div / div [3] / table / tr / td [2] / div /div [2] / strong")
	List<WebElement> sit;
	
	@FindBy(xpath="//button[contains(text(),'Book Now')]")
	List<WebElement> book;
	
	@FindBy(xpath="//div[@id='loader']")
	WebElement load;
	
	
	public int findTrain(String train)
	{
		base = new BaseClass();
		base.SetDriver(driver);
		base.WaitUntilElementVisible(showtrainbtn);
		
		int index=0;
		for(int i=0;i<trainnames.size();i++)
		{
			if(trainnames.get(i).getText().trim().contains(train))
			{
				index = i;
			}
		}

		return index;
	}
	
	public int choose_Sit_If_Available(int train1index) throws Exception
	{
		WebElement train1Sleepr = trainSleeperclass.get(train1index);
		train1Sleepr.click();
		base.WaitUntilElementInvisible(load);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		
		String train1Sit = sit.get(0).getText();
		int index = -1;
		
		if(train1Sit.contains("AVAILABLE"))
		{
			sit.get(0).click();
			index = 0;
			sit.get(index).click();
		}
		else
		{
			throw new Exception("Train is Not Available");
		}
		
		return index;
		
	}
	
	public int compareTrainSitAvailable(int train1index, int train2index)
	{
		WebElement train1Sleepr = trainSleeperclass.get(train1index);
		WebElement train2Sleepr = trainSleeperclass.get(train2index);
		
		train1Sleepr.click();
		base.WaitUntilElementInvisible(load);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		train2Sleepr.click();
		base.WaitUntilElementInvisible(load);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		String train1Sit = sit.get(0).getText();
		String train2Sit = sit.get(1).getText();
		int index = -1;
		if(train1Sit.contains("AVAILABLE") && train2Sit.contains("AVAILABLE"))
		{
			String[] t1 = train1Sit.split("-");
			String[] t2 = train2Sit.split("-");
			
			int n1 = Integer.parseInt(t1[1]);
			int n2 = Integer.parseInt(t2[1]);
			
			if(n1 >= n2)
			{
				sit.get(0).click();
				index = 0;
			}
			else if(n2 > n1)
			{
				sit.get(1).click();
				index = 1;
			}
			
			
		}
		else
		{
			System.out.println("Insert in Else");
			if(train1Sit.contains("AVAILABLE"))
			{
				sit.get(0).click();
				index = 0;
				System.out.println("Insert in Else IF");
			}
			else if(train2Sit.contains("AVAILABLE"))
			{
				sit.get(1).click();
				index = 1;
				System.out.println("Insert in Else Else IF");
			}
		}
		System.out.println("index "+index);
		sit.get(index).click();
		return index;
	}
	
	
	public void findEnableBookButton()
	{
		enablebutton = new ArrayList<>();
		
		for (WebElement button : book) {
	        String classAttribute = button.getAttribute("class");
	        boolean isDisabled = classAttribute != null && classAttribute.contains("disable-book");

	        if (!isDisabled) {
	            enablebutton.add(button);
	        }
	    }
		
		System.out.println(enablebutton.size());
		
		
	}
	
	public void choosTrain()
	{
		findEnableBookButton();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		enablebutton.get(0).click();
		
	}
	
	
	
}
