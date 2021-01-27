package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.util.ConstantUtil;
import com.qa.opencart.util.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginpPageTest extends BaseTest {
	
	@Test(priority=1)
	@Description("Login page Test..")
	@Severity(SeverityLevel.MINOR)
	public void loginPageTest() 
	{
		String title=loginpage.getLoginPageTitle();
		System.out.println(title);
		Assert.assertEquals(title,ConstantUtil.login_page_title,Errors.TITLE_NOT_MATCHED_ERROR);
	}
	@Test(priority=2)
	@Description("verify forgot Password Test..")
	@Severity(SeverityLevel.CRITICAL)
	public void forgotPasswordTest() 
	{
		Assert.assertTrue(loginpage.isForgotPasswordExists());
	}
	@Test(priority=3)
	@Description("Verify Login page Test..")
	@Severity(SeverityLevel.BLOCKER)
    public void LoginTest()
	{
		AccountsPage accountspage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountspage.accountsPageTitle(), ConstantUtil.Accounts_page_title);
	 }
   
}
