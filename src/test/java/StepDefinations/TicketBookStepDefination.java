package StepDefinations;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import GenericUtils.CommonlyUsedFunc;
import POMClasses.BookingConfirmation;
import POMClasses.LoginPage;
import POMClasses.TrainListForDate;
import ProjectManagers.TextContextObject;
import RunnerPack.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class TicketBookStepDefination {

	LoginPage login;
	BaseClass base;
	TrainListForDate train;
	BookingConfirmation bookcnf;
	String tainclass;
	CommonlyUsedFunc common;
	TextContextObject textcontext;
	
	public TicketBookStepDefination(TextContextObject textcontext)
	{
		this.textcontext = textcontext;
		System.out.println("I am from TicketBookStepDefination cons");
	}
	
    @Given("User is present in IRCTC Website")
    public void userIsOnLoginPage() {
    	
    	//base = new BaseClass();
    	//driver = base.getDriver();
    	//System.out.println("Recive Drive "+driver);

    	this.login = new LoginPage(textcontext.driver);
    	System.out.println("I am from userIsOnLoginPage() "+textcontext.driver);
    	login.launchBrowser();
    	
    	
    }

    @When("User will be login through {string} and {string}")
    public void userLogsInWithUsernameAndPassword(String username, String password) {
    	
    	login.clickLoginToSelectDestination();
    	login.fillUpNameAndPass(username, password);
    	
    	// Let's wait 15 sec for capcha
    	
    	try {
			Thread.sleep(12000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
    	
    	login.SignIn();
    	
    	
    }

    @When("Purchase Ticket from {string} to {string} on {string} and class {string}")
    public void purchaseTicketFromToOn(String source, String destination, String date, String classes) throws InterruptedException {
    	
    	this.tainclass = classes;
    	login.fillSourceAndDest(source, destination);
    	login.selectDate(date);
    	login.SelectClass(classes);
    	login.submitJournyForm();

    	
    }

    @When("Book {string} Ticket for your journey")
    public void book_ticket_for_your_journey(String train1) throws Exception {
        
    	train = new TrainListForDate(textcontext.driver);
        int train1Sit = train.findTrain(train1);
        int index = train.choose_Sit_If_Available(train1Sit,tainclass);
        if(index>=0)
        {
        	train.choosTrain();
        }
        else
        {
        	System.out.println("No Available Train");
        }
        
        
    }
    
    @When("Compare Train {string} and {string} where ticket is available")
    public void compare_train_and_where_ticket_is_available(String train1, String train2) 
    {
    	train = new TrainListForDate(textcontext.driver);
        int train1Sit = train.findTrain(train1);
        int train2Sit = train.findTrain(train2);
        
        try
        {
        	train.compareTrainSitAvailable(train1Sit, train2Sit,tainclass);
        }
        catch(IndexOutOfBoundsException e)
        {
        	System.out.println("NO Available sit, Either RAC sit present or WL");
        }
        
        
        train.choosTrain();
        
    }
    
    
    
    @When("Fill the passengers details following:")
    public void fillThePassengersDetailsFollowing(DataTable dataTable) {
        List<String> passengerNames = dataTable.column(0);
        for(int i=0; i<passengerNames.size();i++)
        {
        	System.out.println(passengerNames.get(i));
        }
        
        

        bookcnf = new BookingConfirmation(textcontext.driver);
        
        bookcnf.selectPassengersDetails(passengerNames);
        bookcnf.submitPassengerDetails();
        
        
     }


    @Then("Make the Payment and Confirm ticket book")
    public void makeThePaymentAndConfirmTicketBook() {
      
    	bookcnf.reviewAndSubmit();
    	
    }
	
	
}
