package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;


public class IntranetWebElements {
	
	public static final By buttonEnter_XpathSelector = new ByXPath("//*[@id='ctl00_ContentPlaceHolder1_BtnAccept']");
	public static final By buttonCancelInvalid_XpathSelector = new ByXPath("//*[@id='ctl00_ContentPlaceHolder1_lnk_cancel_invalid']");
	public static final By buttonCompletedValidated_XpathSelector = new ByXPath("//*[@id='ctl00_ContentPlaceHolder1_lnk_completed_validated']");
	public static final By buttonSubmitApproval_XpathSelector = new ByXPath("//*[@id='ctl00_ContentPlaceHolder1_lnk_submit_approval']");
	public static final By buttonSubmitRejection_XpathSelector = new ByXPath("//*[@id='ctl00_ContentPlaceHolder1_lnk_submit_rejection']");	
}
