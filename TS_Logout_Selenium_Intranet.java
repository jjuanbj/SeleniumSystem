package TestStep;

import library.utility.SeleniumActionController;
import library.utility.SeleniumUtility;
import resources.TestStep.TSLogoutSeleniumIntranetHelper;
import PageObjects.IntranetWebElements;

/**
 * Description : Functional Test Script
 * 
 * @author jjimenez
 */
public class TSLogoutSeleniumIntranet extends
		TSLogoutSeleniumIntranetHelper {
	/**
	 * Script Name : <b>TS_Logout_Selenium_Intranet</b> Generated : <b>Dec 12,
	 * 2019 8:09:54 AM</b> Description : Functional Test Script Original Host :
	 * WinNT Version 6.3 Build 9600 ()
	 * 
	 * @since 2019/12/05
	 * @author jjimenez
	 */
	public void testMain(Object[] args) {

		addDescription("Log out of Intranet");

		if (SeleniumActionController.clickWaitObject(
				IntranetWebElements.linkLeave_XpathSelector,
				IntranetWebElements.textboxUser_XpathSelector)) {
			SeleniumUtility.killDriver();			
		}else {
			setScreenshot(getRootTestObject().getScreenSnapshot());

			logTestResult("Verification Point Failed!", false,
					"The session was not closed correctly. Please check.");		
		}
	}
}
