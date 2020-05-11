package com.qa.pages;

import org.openqa.selenium.support.PageFactory;

import com.qa.BaseClass;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MemberDetails extends BaseClass{
	
	@AndroidFindBy(id="com.kmr:id/header_txtHead") private MobileElement Header;
	

	
	public String getTitle() {
		return getAttribute(Header, "text");
	}
	
	

}
