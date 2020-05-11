package com.qa.pages;
import com.qa.BaseClass;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BaseClass {
	@AndroidFindBy(id="com.kmr:id/login_editUserName") private MobileElement Userid;
	@AndroidFindBy(id="com.kmr:id/login_editPassword") private MobileElement Password;
	@AndroidFindBy(id="com.kmr:id/login_editToken") private MobileElement Token;
	@AndroidFindBy(id="com.kmr:id/login_relLogin") private MobileElement login;
	@AndroidFindBy(id="android:id/alertTitle") private MobileElement AlertPopup;
	@AndroidFindBy(id="android:id/message") private MobileElement errMsg;
	@AndroidFindBy(id="android:id/button3") private MobileElement ErrorOk;
	@AndroidFindBy(id="android:id/button1") private MobileElement Ok;




	public LoginPage enterUserId(String userid)  {
		sendKeys(Userid,userid);
		return this;
	}

	public LoginPage enterPassword(String pw)  {
		sendKeys(Password,pw);
		return this;
	}
	public LoginPage enterToken(String xt)  {
		sendKeys(Token,xt);
		return this;
	}
	public MemberDetails tapLogin()  {
		click(login);
		return new MemberDetails();
	}



	public String getErrorMsg1() {

		return getAttribute(errMsg, "text");
	}
	public String getAlertTitle() {

		return getAttribute(AlertPopup, "text");
	}

	public LoginPage tapAlertOk() {
		click(ErrorOk);
		return this;

	}
	public LoginPage tapFillAlertOk() {
		click(Ok);
		return this;

	}
}


