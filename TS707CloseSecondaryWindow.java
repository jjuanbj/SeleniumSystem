package TestStep.TC_707;

import library.utility.SeleniumUtility;
import resources.TestStep.TC_707.TS_707_CloseSecondaryWindowHelper;
import PageObjects.CertificationRequestPO;

/**
 * Description : Functional Test Script
 * 
 * @author jjimenez
 */
public class TS707CloseSecondaryWindow extends
		TS707CloseSecondaryWindowHelper {
	/**
	 * Script Name : <b>TS707CloseSecondaryWindow</b> Generated :
	 * <b>26/11/2019 10:42:53</b> Description : Functional Test Script Original
	 * Host : WinNT Version 6.3 Build 9600 ()
	 * 
	 * @since 2019/11/26
	 * @author jjimenez
	 */
	public void testMain(Object[] args) {
		addDescription("Close Secondary Window");

		CertificationRequestPO certificationRequestPO = new CertificationRequestPO(
				SeleniumUtility.getDriver());
		certificacionRequestPO.enterIframeAuthorizationPayment();
		certificacionRequestPO.clickBtnAcceptIframe();
		certificacionRequestPO.exitIframeAuthorizationPayment();
	}
}
