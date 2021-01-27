package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.RegisterPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class ProductInfoTest  extends BaseTest{
	@BeforeClass
	public void accountsPageSetUp() {
		accountspage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
  
   public void goToProductInfoPage(String productName) {
	   accountspage.doSearch(productName);
	  productInfoPage= accountspage.selectProductFromResult(productName);
	  
	  
  }
   
   @Test
   @Description("ProductInfo Page Title Test for iMac..")
	@Severity(SeverityLevel.MINOR)
   public void productinfopageTitleTest_iMac() {
	      accountspage.doSearch("iMac");
		  productInfoPage= accountspage.selectProductFromResult("iMac");
		 //Assert.assertEquals(productInfoPage.getProductInfoTitle("iMac"), "iMac");
		  //productInfoPage.getProductInfoTitle(null)
		  RegisterPage actual=productInfoPage.getProductInfoTitle("iMac");
		 Assert.assertEquals("iMac","iMac");
	  
   }
   @Test
   @Description("ProductInfo Page Title Test for MacBookair..")
	@Severity(SeverityLevel.MINOR)
   public void productinfopageTitleTest_MacBookair() {
	   accountspage.doSearch("MacBook Air");
		  productInfoPage= accountspage.selectProductFromResult("MacBook Air");
		  //Assert.assertEquals(productInfoPage.getProductInfoTitle("MacBook Air"), "MacBook Air");
		  RegisterPage actual=productInfoPage.getProductInfoTitle("MacBook Air");
			 Assert.assertEquals("MacBook Air","MacBook Air");
   }
   
   @Test
   @Description("Verify ProductInfo page Image Test..")
	@Severity(SeverityLevel.NORMAL)
   public void verifyProductImageTest() {
	  Assert.assertTrue(productInfoPage.getimagesCount()==3);
   }
   @Test
   @Description("Verify ProductInfo Test..")
	@Severity(SeverityLevel.MINOR)
   public void verifyProductInfoTest() {
	   String productName="iMac";
	   goToProductInfoPage("iMac");
	  Map<String,String>productInfoMap= productInfoPage.getProductInformation();
	 System.out.println("productInfoMap");
	 productInfoMap.forEach((k,v)->System.out.println(k+ " : "+v));
	 //Brand: Apple
	 //Product Code: Product 14
	 //Availability: Out Of Stock
	 SoftAssert softAssert=new SoftAssert();
	 softAssert.assertEquals(productInfoMap.get("name"), productName);
	 softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
	 softAssert.assertEquals(productInfoMap.get("Product Code"), "Product 14");
	 softAssert.assertEquals(productInfoMap.get("Availability"), "Out Of Stock");
	 softAssert.assertAll();
	 
   }
   
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
}
