package TestStep.TC30361;

import library.utility.SeleniumUtility;

import org.openqa.selenium.WebDriver;

import resources.TestStep.TC30361.TS30361InsertIDHelper;
import PageObjects.CertificationRequestPO;

/**
 * Description : Functional Test Script
 * 
 * @author Jjimenez
 */
public class TS30361InsertID extends TS30361InsertIDHelper {
	/**
	 * Script Name : <b>TS_30361_InsertarRNC</b> Generated : <b>21/11/2019
	 * 16:09:19</b> Description : Functional Test Script Original Host : WinNT
	 * Version 6.3 Build 9600 ()
	 * 
	 * @since 2019/11/21
	 * @author jjimenez
	 */
	private WebDriver driver;
	private CertificationRequestPO certificacionRequest;
	private String ID;

	public TS30361InsertID(String _ID) {
		driver = SeleniumUtility.getDriver();
		ID = _ID;
	}

	public void testMain(Object[] args) {

		addDescription("Insert ID");
		setName("Insert ID");

		certificationRequest = new CertificacionRequestPO(driver);

		certificacionRequest.insertID(ID);

		setScreenshot(getRootTestObject().getScreenSnapshot());
	}
}
