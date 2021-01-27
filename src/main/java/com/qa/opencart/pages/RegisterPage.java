package com.qa.opencart.pages;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.opencart.util.ConstantUtil;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil elementutil;
	
	
	private By firstname= By.id("input-firstname");
	private By lastname= By.id("input-lastname");
	private By email= By.id("input-email");
	private By telephone= By.id("input-telephone");
	private By password= By.id("input-password");
	private By confirmPassword= By.id("input-confirm");

	private By subscribeYes=By.xpath("//label[@class='radio-inline'][position()=1]/input");
	private By subscribeNo=By.xpath("//label[@class='radio-inline'][position()=2]/input");
	
	private By checkbox=By.xpath("//div[@class='pull-right']//input[@type='checkbox']");
	private By continuebutton=By.xpath("//div[@class='pull-right']//input[@value='Continue']");
	
    private By acceptedatext=By.cssSelector("#content h1");
    private By logout=By.linkText("Logout");
    private By register=By.linkText("Register");
    
    public  RegisterPage(WebDriver driver) {
    	this.driver=driver;
    	elementutil=new ElementUtil(this.driver);
    }
    @Step("Get Random Number..")
    public int getRandomNumber(int min,int max) {
    	Random random = new Random();
    	int randomRange = random.nextInt(max - min) + min;
    	 return randomRange;
   }
    
    @Step("Registationpage Form..")
	public boolean registationpageform(String firstname,String lastname,String email ,String telephone,
    		String password,String subscribe ) 
    {
		email="email" + getRandomNumber(1, 10000) + "@gmail.com";
    	elementutil.doSendKeys(this.firstname, firstname);
    	elementutil.doSendKeys(this.lastname, lastname);
    	elementutil.doSendKeys(this.email, email);
    	elementutil.doSendKeys(this.telephone, telephone);
    	elementutil.doSendKeys(this.password, password);
    	elementutil.doSendKeys(this.confirmPassword,password );
   
    	if(subscribe.equals("yes")){
    		elementutil.doClick(subscribeYes);
    		}
    		  else {
    		elementutil.doClick(subscribeNo);
    		}
    		
    	elementutil.doClick(checkbox);
    	elementutil.doClick(continuebutton);
    	String text=elementutil.doGetText(acceptedatext);
    	if(text.contains(ConstantUtil.Account_sucess_message)) {
    
    	elementutil.doClick(logout);
    	elementutil.doClick(register);
    	return true;
    	}
    	
   return false;
    }    		 
    	}
    	
    	

    
    

