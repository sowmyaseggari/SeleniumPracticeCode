package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.ConstantUtil;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil elementutil;
	
	private By header=By.cssSelector("div#logo a");
	private By desktop=By.xpath("//a[text()='Desktops']");
	private By accountcontent=By.xpath("//div[@id='content']/h2");
	private By cart=By.xpath("//*[@id=\"cart\"]/button");
	private By cartdisplay=By.xpath("//p[text()='Your shopping cart is empty!']");
	private By searchText=By.cssSelector("div#search input[name='search']");
	private By searchbutton=By.cssSelector("div#search button[type='button']");
	private By searchItemResult=By.cssSelector("div.product-layout div.product-thumb");
	private By resultsItems=By.cssSelector(" div.product-thumb h4 a");
	//private By cartdisplay=By.xpath("//*[@id=\"cart\"]/ul");
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		elementutil=new ElementUtil(this.driver);
		
	}
	@Step("Get Accounts Page Title..")
	public String accountsPageTitle() {
		return elementutil.waitForPageTitle(ConstantUtil.Accounts_page_title, 10);
	}
	@Step("Accountspage Header..")
	public String getAccountsPageHeader() {
		
		 if(elementutil.doIsDisplayed(header)) {
			return elementutil.doGetText(header);
		 }
		 return null;
	}
	@Step("Accounts Page Desktop..")
	public void accountsPageDesktopIs() {
		elementutil.getElement(desktop);
		elementutil.doClick(desktop);
	}
	@Step("Accounts Sections Count..")
	public int getAccountsSectionsCount() {
		return elementutil.getElements(accountcontent).size();
	}
	@Step("Get Accounts Section List..")
	public List<String> getAccountsSectionList() {
		List<String>accountlist=new ArrayList<>();
		List<WebElement>accountsectionlist=elementutil.getElements(accountcontent);
		for(WebElement e:accountsectionlist) {
			String text=e.getText();
			System.out.println(text);
			accountlist.add(text);
		}
		return accountlist;
	}
	@Step("Cart is clickble..")
	public WebElement getAccountsCartIsClickble() {
		elementutil.getElement(cart);
		elementutil.doClick(cart);
		return elementutil.getElement(cartdisplay);
	
	}
	//search fild methods
	@Step("Do search..")
	public boolean doSearch(String productName) {
		elementutil.doSendKeys(searchText, productName);
		elementutil.doClick(searchbutton);
		if(elementutil.getElements(searchItemResult).size()>0) {
			return true;
		}
		return false;
	}
   @Step("Product from Results...")
    public ProductInfoPage selectProductFromResult(String productName) {
    	List<WebElement>resultitemslist=elementutil.getElements(resultsItems);
    	System.out.println("total number items to display :" +resultitemslist.size());
    	for(WebElement e:resultitemslist) {
    		e.getText().equals(productName);
    		e.click();
    		break;
    	}
    	return new ProductInfoPage(driver);
	}
}

