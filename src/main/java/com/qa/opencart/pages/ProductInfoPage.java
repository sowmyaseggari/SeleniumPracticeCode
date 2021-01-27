package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class ProductInfoPage {
	private WebDriver driver ;
	private ElementUtil elementutil;
	
	private By productNameHeader=By.cssSelector("#content h1");
	private By productMetaData=By.cssSelector("#content .list-unstyled:nth-of-type(1) li");
	private By productprice=By.cssSelector("#content .list-unstyled:nth-of-type(2) li");
	private By productQuantity=By.id("input-quantity");
	private By productAddToCart=By.id("button-cart");
	private By productImgescount=By.cssSelector(".thumbnail img");
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		elementutil=new ElementUtil(driver);
	}
	@Step("get Product Information..")
   public Map<String, String> getProductInformation()
   {
	   Map<String,String>productinfoMap=new HashMap<String,String>();
	   productinfoMap.put("name", elementutil.doGetText(productNameHeader).trim());
	   List<WebElement>productMetaDataList=elementutil.getElements(productMetaData);
	   for(WebElement e:productMetaDataList) {
		String meta[]=e.getText().split(":");
		String metaname=meta[0].trim();
		String metavalue=meta[1].trim();
		productinfoMap.put(metaname, metavalue);
	   }
		
		//price
	   List<WebElement>productPriceList=elementutil.getElements(productprice);
	   productinfoMap.put("price", productPriceList.get(0).getText().trim());
	   productinfoMap.put("exTax", productPriceList.get(1).getText().trim());
	   
	   return productinfoMap;
	  
   }
   @Step("Select Quantity..")
   public void  selectQuantity(String qty) {
	   elementutil.doSendKeys(productQuantity, qty);
	   
   }
   @Step("Add to cart...")
   public void addToCart() {
	   elementutil.doClick(productAddToCart);
	   
   }
   @Step("get images Count..")
   public int getimagesCount() {
	  List<WebElement>imagesCountList= elementutil.getElements(productImgescount);
	  int count=imagesCountList.size();
	  System.out.println("images count :"+count);
	  return count;
   }
   @Step("get ProductInfo Title")
   public RegisterPage getProductInfoTitle(String ProductName) {
	 String title= elementutil.waitForPageTitle(ProductName, 30);
	  //String title=elementutil.waitForPageUrl(ProductName, 30);
	  //String title= elementutil.waitForPageTitleToBe(ProductName, 30);
	  System.out.println("page title is :"+title);
	  
     return new RegisterPage(driver);
   
   
   }
}
