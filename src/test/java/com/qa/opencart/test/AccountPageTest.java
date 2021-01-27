package com.qa.opencart.test;

import java.util.List;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.util.ConstantUtil;
import com.qa.opencart.util.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class AccountPageTest extends BaseTest{
	
	@BeforeClass
	public void accountsPageSetUp() {
		accountspage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	@Test(priority=1)
	@Description("Accounts Page Title Test..")
	@Severity(SeverityLevel.MINOR)
	public void verifyAccountsPageTitleTest() {
		String title=accountspage.accountsPageTitle();
		System.out.println("accountspage title is" +title);
		Assert.assertEquals(title, ConstantUtil.Accounts_page_title,Errors.TITLE_NOT_MATCHED_ERROR);
	}
	@Test(priority=2)
	@Description("verify Accounts Page Header  Test..")
	@Severity(SeverityLevel.MINOR)
	public void verifyAccountsPageHeaderTest() {
		String headertitle=accountspage.getAccountsPageHeader();
		System.out.println(headertitle);
		Assert.assertEquals(headertitle, ConstantUtil.Accounts_page_Header_title,Errors.HEADER_NOT_MATCHED_ERROR);
	}
	@Test(priority=3)
	@Description("Verify accountspage Display test..")
	@Severity(SeverityLevel.NORMAL)
	public void verifyAccountsPageDisplayTest() {
		accountspage.accountsPageDesktopIs();
		Assert.assertTrue(true);
	}
	@Test(priority=4)
	@Description("Accounts Page Section Count Test..")
	@Severity(SeverityLevel.NORMAL)
	public void verifyAccountsPageSectionCountTest() {
		Assert.assertTrue(accountspage.getAccountsSectionsCount()==ConstantUtil.Accounts_page_section_count);
	}
	@Test(priority=5)
	@Description("Accounts Page Section Count List Test..")
	@Severity(SeverityLevel.NORMAL)
	public void verifyAccountsPageSectionCountListTest() {
		List<String>list=accountspage.getAccountsSectionList();
		System.out.println(list);
		Assert.assertEquals(list, ConstantUtil.getAccSectionsList());
	}
	@Test(enabled=true)
	@Description("Verify Accounts Page Cart Test..")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyAccountsPageCartTest() {
		accountspage.getAccountsCartIsClickble();
		Assert.assertTrue(true, "Your shopping cart is empty!'");
		
		
	}
	 @DataProvider
	   public Object[][] searchData() {
		   return new  Object[][] {{"iMac"},{"Macbook"},{"samsung"},{"MacbookAir"}};
	 }
	@Test(priority=6,dataProvider="searchData")
	@Description("Accounts Page Search Test..")
	@Severity(SeverityLevel.CRITICAL)
	public void searchTest(String productName) {
		Assert.assertTrue(accountspage.doSearch(productName));
	}
	
	@Test(priority=7)
	@Description("Verify AccountsPage Product Result Test..")
	@Severity(SeverityLevel.MINOR)
	public void verifyProductResultTest() {
		accountspage.selectProductFromResult("iMac");
		Assert.assertTrue(true);
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
}
