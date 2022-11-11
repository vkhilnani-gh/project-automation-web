//package com.company.project.objects.pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//
//import com.google.inject.Inject;
//
//import nz.co.companyname.automation.webdriver.selenium.utils.WebDriverWaitUtility;
//
//public class WelcomePage {
//	private WebDriver webDriver;
//	private WebDriverWaitUtility webDriverWaitUtility;
//	private By formAuthenticationLinkLocator = By.linkText("Form Authentication");
//
//	@Inject
//	public WelcomePage(WebDriver webDriver) {
//		this.webDriver = webDriver;
//	}
//
//	//Open Login Page by clicking on Form Authentication Link
//	public void clickFormAuthenticationLink() {
//		webDriver.findElement(formAuthenticationLinkLocator).click();
//		System.out.println("Loaded Form Auth page");
//	}
//}
