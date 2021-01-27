package com.qa.opencart.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil
{
	private WebDriver driver;
	private JavaScriptUtil jsutil;
	
	public ElementUtil(WebDriver driver) 
	{
		this.driver=driver;
		jsutil=new JavaScriptUtil(this.driver);
	}
	
     public WebElement getElement(By locator) 
     {
    	 WebElement element= driver.findElement(locator);
    	 if(DriverFactory.highlight.equals("true")) {
    		 jsutil.flash(element);
    	 }
    	 return element;
     }
     
     public List<WebElement> getElements(By locator) {
    	 return driver.findElements(locator);
     }
     
     public void doSendKeys(By locator,String value) 
     { 
    	WebElement element= getElement(locator);
    	element.clear();
    	element.sendKeys(value);
    
     }
     
     public void doClick(By locator) 
     {
    	 getElement(locator).click();
     }
     public void doActionsClick(By locator) {
    	 Actions action=new Actions(driver);
    	 action.click(getElement(locator)).perform();
     }
     public void doActionSendKeys(By locator,String value) {
    	 Actions action=new Actions(driver);
    	 action.sendKeys(getElement(locator), value).perform();
     }
     public String doGetText(By locator) {
    	 return getElement(locator).getText();
     }
     public boolean doIsDisplayed(By locator) {
    	return getElement(locator).isDisplayed();
     }
    // ************************DROPDOWN**************************************
     
     public void doSelectDropDownVisibleText(By locator,String value) 
     {
    	 Select select=new Select(getElement(locator));
    	 select.selectByVisibleText(value);
     }
     
     public void doSelectDropDownByIndex(By locator,int index) 
     {
    	 Select select=new Select(getElement(locator));
    	 select.selectByIndex(index);
     }
     
     public void doSelectDropDownByValue(By locator,String value) 
     {
    	 Select select=new Select(getElement(locator));
    	 
    	 select.selectByValue(value);
     }
     //*********************LINKTEXT*********************************
     
     public List<String> footerLinksText(By locator) {
 		List<String> textlink=new ArrayList<String>();
 		
 		List<WebElement>footerlinks=getElements(locator);
 		
 		System.out.println(footerlinks.size());
 		
 		for(WebElement e:footerlinks)
 		{
 			System.out.println(e.getText());
 			textlink.add(e.getText());
 			
 			
 		}
 		return textlink;
 		
 		
 		
 	}
    //****************suggestionList********************** 
     public void selectFromSuggestList(By suggestionList, String value) {
 		List<WebElement> resultList = getElements(suggestionList);
 		System.out.println(resultList.size());
 		for (WebElement e : resultList) {
 			String text = e.getText();
 			System.out.println(text);
 			if (text.equals(value)) {
 				e.click();
 				break;
 			}
 		}

 	} 
    //****************MOVE TO ELEMENT*******************
     public void getMoveToElement(WebElement value1)   
 	{
 		
 		Actions act=new Actions(driver);
 		act.moveToElement(value1).perform();
 	
 	}
     public void contextClick(WebElement value) {
 		Actions act=new Actions(driver);
 		act.click(value).perform();
 		
 		
 	}
   //*************************ACTIONS UTILS  *********************************
 	
 	public List<String> getRightClickMenuList(By rightCickLocator, By listLocator) {

 		Actions act = new Actions(driver);
 		act.contextClick(getElement(rightCickLocator)).perform();

 		List<String> rightClickList = new ArrayList<String>();
 		List<WebElement> menuList = getElements(listLocator);

 		System.out.println(menuList.size());

 		for (WebElement e : menuList) {
 			String text = e.getText();
 			rightClickList.add(text);
 		}
 		return rightClickList;
 	}

 	public void rightClickOption(By rightCickLocator, By listLocator, String value) {

 		Actions act = new Actions(driver);
 		act.contextClick(getElement(rightCickLocator)).perform();

 		List<WebElement> menuList = getElements(listLocator);

 		System.out.println(menuList.size());

 		for (WebElement e : menuList) {
 			String text = e.getText();
 			if (text.equals(value)) {
 				e.click();
 				break;
 			}
 		}
 	}
 	//***************************SELECTCHOICEFROMDROPDOWN*********************************S
 	/**
	 * Pass the single String value for single selection,
	 * Pass the multiple String values for multiple selection,
	 * Pass "ALL" for all selection values from dropdown
	 * @param choices
	 * @param value
	 */
	public void selectChoiceFromDropDown(By choices, String... value) {

		List<WebElement> choiceList = getElements(choices);

		if (!value[0].equalsIgnoreCase("All")) {

			for (int i = 0; i < choiceList.size(); i++) {

				String text = choiceList.get(i).getText();
				System.out.println(text);

				for (int j = 0; j < value.length; j++) {
					if (text.equals(value[j])) {
						choiceList.get(i).click();
						break;
					}
				}
			}

		}

		// select all the values:
		else {
			try {
				for (WebElement e : choiceList) {
					e.click();
				}
			} catch (Exception e) {
				
			}
		}
	}
//***************************wait util***************************
	public  String waitForPageTitle(String title,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver,timeout);
		 wait.until(ExpectedConditions.titleContains(title));
		 return driver.getTitle();
		
	}
	public  String waitForPageTitleToBe(String title,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver,timeout);
		 wait.until(ExpectedConditions.titleIs(title));
		 return driver.getTitle();
		
	}
	public  String waitForPageUrl(String urlValue,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver,timeout);
		 wait.until(ExpectedConditions.urlContains(urlValue));
		return driver.getCurrentUrl();
		
	}
	public Alert IsAlertPresent(int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver,timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());
		
	}
	public String getAlertText(int timeout) {
		return IsAlertPresent(timeout).getText();
	}
	public void doAcceptAlert(int timeout) {
		IsAlertPresent(timeout).accept();
	}
	public void doAlertDismiss(int timeout) {
		IsAlertPresent(timeout).dismiss();
	}
	public WebElement WaitForElementPresent(By locator,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver,timeout);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public WebElement WaitForElementPresent(By locator,int timeout,int pollingtime) {
		WebDriverWait wait=new WebDriverWait(driver,timeout,pollingtime);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public WebElement WaitForElementVisibleText(By locator,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver,timeout);
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
		
	}
	public List<WebElement> VisibiblityOfAllElements(By locator,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver,timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		
	}
	public void getPageElemntText(By locator,int timeout) {
		VisibiblityOfAllElements(locator,timeout).stream().forEach(ele->System.out.println(ele.getText()));
	}
	
	public int getPageElemntCount(By locator,int timeout) {
		return VisibiblityOfAllElements(locator,timeout).size();
	}
	//An expectation for checking an element is visible and enabled such that you can click it.
	public void ActionsClickWhenReady(By locator,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver,timeout);
		WebElement element=wait.until(ExpectedConditions.elementToBeClickable(locator));
		Actions act=new Actions(driver);
		act.moveToElement(element).click().build().perform();
	}
	public WebElement waitForElementWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait  = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	
	public WebElement retryingElement(By locator) {
		WebElement element=null;
		int attempts=0;
		while(attempts<30) {
			try {
				element=driver.findElement(locator);
			
			}
			catch(NoSuchElementException e) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					
				}
			}
		
			catch(StaleElementReferenceException e) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					
				}
			}
			attempts++;
	}
	return element;
} 
	//*****

}


