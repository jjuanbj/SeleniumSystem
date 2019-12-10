package TestStep;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import resources.TestStep.LoginSeleniumIntranetHelper;
import library.utility.Selenium;
import library.utility.SeleniumUtility;
import PageObjects.IntranetWebElements;


/**
 * Description   : Functional Test Script
 * @author jjimenez
 */
public class LoginSeleniumIntranet extends LoginSeleniumIntranetHelper
{
	/**
	 * Script Name   : <b>TS_Login_Selenium_Intranet</b>
	 * Generated     : <b>Dec 04, 2019 3:21:31 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.3  Build 9600 ()
	 * 
	 * @since  2019/12/04
	 * @author jjimenez
	 */
	private String user;
	private String password;
	boolean capsLock = false;	 
	private static String serer = "xxxxxxxxxxxxxx:xxx";	

	public LoginSeleniumIntranet(String user, String password) {
		super();
		this.user = user;
		this.password = password;

		SeleniumUtility.initializeDriver();
	}

	public void testMain(Object[] args) {
		
		addDescription("Start User Session");

		// We start the browser at the specified URL
		SeleniumUtility.getDriver().navigate().to("http://" + server + "/Intranet/login.aspx");

		SeleniumActionController.waitForPageLoaded();

		// property to maximize the window
		SeleniumUtility.getDriver().manage().window().maximize();

		// we capture the current status of the Caps Lock key (uppercase)
		capsLock = Toolkit.getDefaultToolkit().getLockingKeyState(
				KeyEvent.VK_CAPS_LOCK);

		// if the key is ACTIVE
		if (capsLock) {
			// we put it off
			Toolkit.getDefaultToolkit().setLockingKeyState(
					KeyEvent.VK_CAPS_LOCK, false);
		}

		SeleniumActionController.setText(IntranetWebElements.textboxUserXpathSelector, user);
		SeleniumActionController.setText(IntranetWebElements.textboxPasswordXpathSelector, password);
		
		setScreenshot(getRootTestObject().getScreenSnapshot());

		String xpathUserLabel = String.format("//*[@id='infouser']/table/tbody/tr/td/font[contains(text(),'%s')]",user);

		if(SeleniumActionController.clickWaitObject(
				IntranetWebElements.buttonLogInXpathSelector,
				By.xpath(xpathUserLabel)))
		{
			logTestResult("Verification Point Checked!", true,
					"Login Intranet Checked user " + user);
		} else {
			logTestResult("Verification Point Failed!", false,
					"Login Intranet Failed user " + user);
		}
	}
}
