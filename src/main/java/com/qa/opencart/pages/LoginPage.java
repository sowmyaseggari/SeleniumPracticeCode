package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.ConstantUtil;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil elementutil;
	
	private By username=By.id("input-email");
	private By password=By.id("input-password");
	private By loginbutton=By.xpath("//input[@value='Login']");
	private By forgotlink=By.cssSelector("div.form-group a");
	 private By registerLink=By.linkText("Register");

	//constructor of page class
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elementutil=new ElementUtil(this.driver);
	}
	//pageactions//methods/libs 
	@Step("Get Loggin Page Title..")
	public String getLoginPageTitle() {
		return elementutil.waitForPageTitle(ConstantUtil.login_page_title, 10);
		
	}
	@Step("Forgot Password exists..")
	public boolean isForgotPasswordExists() {
		//return driver.findElement(forgotlink).isDisplayed();
		return elementutil.doIsDisplayed(forgotlink);
	}
	@Step("Login with username: {0} and password: {1}..")
	public AccountsPage doLogin(String un,String pwd) {
		elementutil.doSendKeys(username, un);
		elementutil.doSendKeys(password, pwd);
		elementutil.doClick(loginbutton);
		//driver.findElement(username).sendKeys(un);
		//driver.findElement(password).sendKeys(pwd);
		//driver.findElement(loginbutton).click();
		return new AccountsPage(driver);
	}
	@Step("Navigate to Registerpage.")
	public RegisterPage navigateToRegisterPage() {
		elementutil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	

}
