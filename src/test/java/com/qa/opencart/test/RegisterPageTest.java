package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.util.ConstantUtil;
import com.qa.opencart.util.ExelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void registationPageSetUp() {
		registerPage =  loginpage.navigateToRegisterPage();
	}
	@DataProvider
	public Object[][] getRegisterData() {
		Object data[][]=ExelUtil.getTestData(ConstantUtil.Exel_sheet_name);
		return data;
	}
	@Test(dataProvider="getRegisterData")
	@Description("Verify user RegisterPage Test..")
	@Severity(SeverityLevel.BLOCKER)
	public void userRegistationTest(String firstname,String lastname,String email ,String telephone,
    		String password,String subscribe) {
		Assert.assertTrue(registerPage.registationpageform(firstname, lastname, email, telephone, password, subscribe));
		
	}
	
}
