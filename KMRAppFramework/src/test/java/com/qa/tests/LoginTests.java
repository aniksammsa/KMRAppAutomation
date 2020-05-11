package com.qa.tests;

import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.qa.BaseClass;
import com.qa.pages.LoginPage;
import com.qa.pages.MemberDetails;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class LoginTests extends BaseClass{

	LoginPage loginPage;
	MemberDetails memberDetails;
	InputStream datais;
	JSONObject loginUsers;

	@BeforeClass
	public void beforeClass() throws Exception {
		try {
			String dataFileName="data/loginUsers.json";
			datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			JSONTokener tokener = new JSONTokener(datais);
			loginUsers = new JSONObject(tokener);
		}
		catch(Exception e) {
			e.printStackTrace();

		}finally {
			if(datais!=null) {
				datais.close();
			}

		}

	}

	@AfterClass
	public void afterClass() {
	}

	@BeforeMethod
	public void beforeMethod(Method m) {
		loginPage = new LoginPage();
		memberDetails =new MemberDetails();
		System.out.println("\n"+"********* Starting Test: "+m.getName()+"*******"+"\n");
	}

	@AfterMethod
	public void afterMethod() {
	}

	@Test
	public void Login_with_Restricted_Userid() {
		loginPage.enterUserId(loginUsers.getJSONObject("RestrictedUserid").getString("userid"));
		loginPage.enterPassword(loginUsers.getJSONObject("RestrictedUserid").getString("password"));
		loginPage.enterToken(loginUsers.getJSONObject("RestrictedUserid").getString("token"));
		loginPage.tapLogin();

		String ActualAlerttxt = loginPage.getAlertTitle();
		System.out.println("Actual Alert Header: "+ActualAlerttxt);
		System.out.println("Expected Alert Header: "+strings.get("AlertHeader"));		
		String ActualErrorTxt = loginPage.getErrorMsg1();
		System.out.println("Actual Error Message: "+ActualErrorTxt);
		String ExpectedError = strings.get("RestrictedLoginMsg");
		System.out.println("Expected Error Message: "+ExpectedError);
		if(ActualErrorTxt.contains(ExpectedError)) {

			System.out.println("Error Messge is Correct");
			loginPage.tapAlertOk();
		}
		else {
			System.out.println("Error Message Mismatch");
			loginPage.tapAlertOk();
			Assert.fail();
		}


	}
	@Test
	public void Login_with_Deactivated_App_Access() {
		loginPage.enterUserId(loginUsers.getJSONObject("DeactivatedAppAccess").getString("userid"));
		loginPage.enterPassword(loginUsers.getJSONObject("DeactivatedAppAccess").getString("password"));
		loginPage.enterToken(loginUsers.getJSONObject("DeactivatedAppAccess").getString("token"));
		loginPage.tapLogin();

		String ActualAlerttxt = loginPage.getAlertTitle();
		System.out.println("Actual Alert Header: "+ActualAlerttxt);
		System.out.println("Expected Alert Header: "+strings.get("AlertHeader"));	
		String ActualErrorTxt = loginPage.getErrorMsg1();
		System.out.println("Actual Error Message: "+ActualErrorTxt);
		String ExpectedError =strings.get("DeactivatedAppMsg"); 
		System.out.println("Expected Error Message: "+ExpectedError);
		if(ActualErrorTxt.contains(ExpectedError)) {

			System.out.println("Error Messge is Correct");
			loginPage.tapAlertOk();
		}
		else {
			System.out.println("Error Message Mismatch");
			loginPage.tapAlertOk();
			Assert.fail();
		}

	}
	@Test
	public void Login_with_is_Deleted_Member() {
		loginPage.enterUserId(loginUsers.getJSONObject("IsDeletedMember").getString("userid"));
		loginPage.enterPassword(loginUsers.getJSONObject("IsDeletedMember").getString("password"));
		loginPage.enterToken(loginUsers.getJSONObject("IsDeletedMember").getString("token"));
		loginPage.tapLogin();

		String ActualAlerttxt = loginPage.getAlertTitle();
		System.out.println("Actual Alert Header: "+ActualAlerttxt);
		System.out.println("Expected Alert Header: "+strings.get("AlertHeader"));	
		String ActualErrorTxt = loginPage.getErrorMsg1();
		System.out.println("Actual Error Message: "+ActualErrorTxt);
		String ExpectedError =strings.get("DeletedMemberMsg"); 
		System.out.println("Expected Error Message: "+ExpectedError);
		if(ActualErrorTxt.contains(ExpectedError)) {

			System.out.println("Error Messge is Correct");
			loginPage.tapAlertOk();
		}
		else {
			System.out.println("Error Message Mismatch");
			loginPage.tapAlertOk();
			Assert.fail();
		}

	}

	@Test
	public void alert_Fill_All_Fields() {

		loginPage.tapLogin();
		String ActualAlerttxt = loginPage.getAlertTitle();
		System.out.println("Actual Alert Header: "+ActualAlerttxt);
		System.out.println("Expected Alert Header: "+strings.get("AlertHeader"));
		String ActualAlertTxt = loginPage.getErrorMsg1();
		System.out.println("Actual Error Message: "+ActualAlertTxt);
		String ExpectedAlertTxt =strings.get("FillAlertMsg"); 
		if(ActualAlertTxt.contains(ExpectedAlertTxt)) {

			System.out.println("Fill Up Alert Message Displayed Correctly");
			loginPage.tapFillAlertOk();
		}
		else {
			System.out.println("Alert Message Mismatch");
			loginPage.tapFillAlertOk();
			Assert.fail();
		}

		loginPage.enterUserId(loginUsers.getJSONObject("alertFill").getString("userid"));
		loginPage.tapLogin();
		String ActualAlerttxt1 = loginPage.getAlertTitle();
		System.out.println("Actual Alert Header: "+ActualAlerttxt1);
		System.out.println("Expected Alert Header: "+strings.get("AlertHeader"));
		
		
		String ActualAlertTxt1 = loginPage.getErrorMsg1();
		System.out.println("Actual Error Message: "+ActualAlertTxt1);
		String ExpectedAlertTxt1 =strings.get("FillAlertMsg"); 
		if(ActualAlertTxt1.contains(ExpectedAlertTxt1)) {

			System.out.println("Fill Up Alert Message Displayed Correctly");
			loginPage.tapFillAlertOk();
		}
		else {
			System.out.println("Alert Message Mismatch");
			loginPage.tapFillAlertOk();
			Assert.fail();
		}
		loginPage.enterPassword(loginUsers.getJSONObject("alertFill").getString("password"));
		loginPage.tapLogin();
		String ActualAlerttxt2 = loginPage.getAlertTitle();
		System.out.println("Actual Alert Header: "+ActualAlerttxt2);
		System.out.println("Expected Alert Header: "+strings.get("AlertHeader"));
		
		String ActualAlertTxt2 = loginPage.getErrorMsg1();
		System.out.println("Actual Error Message: "+ActualAlertTxt2);
		String ExpectedAlertTxt2 =strings.get("FillAlertMsg");
		if(ActualAlertTxt2.contains(ExpectedAlertTxt2)) {

			System.out.println("Fill Up Alert Message Displayed Correctly");
			loginPage.tapFillAlertOk();
		}
		else {
			System.out.println("Alert Message Mismatch");
			loginPage.tapFillAlertOk();
			Assert.fail();
		}

	}

	@Test(priority=1)
	public void incorrect_Userid_login() {
		loginPage.enterUserId(loginUsers.getJSONObject("incorrectUserid").getString("userid"));
		loginPage.enterPassword(loginUsers.getJSONObject("incorrectUserid").getString("password"));
		loginPage.enterToken(loginUsers.getJSONObject("incorrectUserid").getString("token"));
		loginPage.tapLogin();

		String ActualAlerttxt = loginPage.getAlertTitle();
		System.out.println("Actual Alert Header: "+ActualAlerttxt);
		System.out.println("Expected Alert Header: "+strings.get("AlertHeader"));		
		String ActualErrorTxt = loginPage.getErrorMsg1();
		System.out.println("Actual Error Message: "+ActualErrorTxt);
		String ExpectedError =strings.get("IncorrectCredentialsMsg"); 
		System.out.println("Expected Error Message: "+ExpectedError);
		if(ActualErrorTxt.contains(ExpectedError)) {

			System.out.println("Error Messge is Correct");
			loginPage.tapAlertOk();
		}
		else {
			System.out.println("Error Message Mismatch");
			loginPage.tapAlertOk();
			Assert.fail();
		}		
	}
	@Test(priority=2)
	public void incorrect_Password_login() {
		loginPage.enterUserId(loginUsers.getJSONObject("incorrectPassword").getString("userid"));
		loginPage.enterPassword(loginUsers.getJSONObject("incorrectPassword").getString("password"));
		loginPage.enterToken(loginUsers.getJSONObject("incorrectPassword").getString("token"));
		loginPage.tapLogin();

		String ActualAlerttxt = loginPage.getAlertTitle();
		System.out.println("Actual Alert Header: "+ActualAlerttxt);
		System.out.println("Expected Alert Header: "+strings.get("AlertHeader"));		
		String ActualErrorTxt = loginPage.getErrorMsg1();
		System.out.println("Actual Error Message: "+ActualErrorTxt);
		String ExpectedError =strings.get("IncorrectCredentialsMsg"); 
		System.out.println("Expected Error Message: "+ExpectedError);
		if(ActualErrorTxt.contains(ExpectedError)) {

			System.out.println("Error Messge is Correct");
			loginPage.tapAlertOk();
		}
		else {
			System.out.println("Error Message Mismatch");
			loginPage.tapAlertOk();
			Assert.fail();
		}		
	}
	@Test(priority=3)
	public void incorrect_Token_login() {
		loginPage.enterUserId(loginUsers.getJSONObject("incorrectToken").getString("userid"));
		loginPage.enterPassword(loginUsers.getJSONObject("incorrectToken").getString("password"));
		loginPage.enterToken(loginUsers.getJSONObject("incorrectToken").getString("token"));
		loginPage.tapLogin();

		String ActualAlerttxt = loginPage.getAlertTitle();
		System.out.println("Actual Alert Header: "+ActualAlerttxt);
		System.out.println("Expected Alert Header: "+strings.get("AlertHeader"));	
		String ActualErrorTxt = loginPage.getErrorMsg1();
		System.out.println("Actual Error Message: "+ActualErrorTxt);
		String ExpectedError =strings.get("IncorrectTokenMsg"); 
		System.out.println("Expected Error Message: "+ExpectedError);
		if(ActualErrorTxt.contains(ExpectedError)) {

			System.out.println("Error Messge is Correct");
			loginPage.tapAlertOk();
		}
		else {
			System.out.println("Error Message Mismatch");
			loginPage.tapAlertOk();
			Assert.fail();
		}		
	}
	@Test(priority=4)
	public void valid_login() {
		loginPage.enterUserId(loginUsers.getJSONObject("validlogin").getString("userid"));
		loginPage.enterPassword(loginUsers.getJSONObject("validlogin").getString("password"));
		loginPage.enterToken(loginUsers.getJSONObject("validlogin").getString("token"));
		loginPage.tapLogin();

		String ActualHeader = memberDetails.getTitle();
		System.out.println("Page Header: "+ActualHeader);
		String ExpectedHeader = "Local 13";
		if(ActualHeader.contains(ExpectedHeader)) {
			System.out.println("Login Successful.Header Matched.");
		}
		else {
			System.out.println("Login is not Successful");
			Assert.fail();
		}		
	}
	@Test
	public void Login_with_Special_Characters_in_Password() {
		loginPage.enterUserId(loginUsers.getJSONObject("SpecialCharacterPwLogin").getString("userid"));
		loginPage.enterPassword(loginUsers.getJSONObject("SpecialCharacterPwLogin").getString("password"));
		loginPage.enterToken(loginUsers.getJSONObject("SpecialCharacterPwLogin").getString("token"));
		loginPage.tapLogin();

		String ActualHeader = memberDetails.getTitle();
		System.out.println("Page Header: "+ActualHeader);
		String ExpectedHeader = "Local 13";
		if(ActualHeader.contains(ExpectedHeader)) {
			System.out.println("Login Successful.Header Matched.");
		}
		else {
			System.out.println("Login is not Successful");
			Assert.fail();
		}

	}
	
	public void Login_with_Resetted_Password() {
		
	}

}
