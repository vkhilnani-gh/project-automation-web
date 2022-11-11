//package com.company.project.objects.pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//
//
//public class LoginPage {
//
//	private WebDriver webDriver;
//	private By usernameLocator = By.id("username");
//	private By passwordLocator = By.name("password");
//	private By LoginButtonLocator = By.xpath("//i[@class='fa fa-2x fa-sign-in");
//
//	public LoginPage(WebDriver webDriver) {
//		this.webDriver = webDriver;
//	}
//
//	public LoginPage assertLoginPage(String expectedText) {
//		System.out.println("Code to assert login page");
//		return this;
//	}
//
//	//execute login, return type is the new page that will be rendered post login
//	public SecureAreaPage logIn(String username, String password) {
//		System.out.println("Code to login");
//		return new SecureAreaPage(webDriver);
//	}
//
//}
