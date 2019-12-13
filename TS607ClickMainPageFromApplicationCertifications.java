package TestStep.TC_607;

import library.utility.SeleniumUtility;
import resources.TestStep.TC_607.TS607ClickMainPageFromApplicationCertificationsHelper;
import PageObjects.CertificationsRequestPO;

/**
 * Description : Functional Test Script
 * 
 * @author jjimenez
 */
public class TS607ClickMainPageFromApplicationCertifications extends
		TS607ClickMainPageFromApplicationCertificationsHelper {
	/**
	 * Script Name :
	 * <b>TS607ClickMainPageFromApplicationCertifications</b> Generated
	 * : <b>05/11/2019 11:36:18</b> Description : Functional Test Script
	 * Original Host : WinNT Version 6.3 Build 9600 ()
	 * 
	 * @since 2019/11/05
	 * @author jjimenez
	 */
	public void testMain(Object[] args) {
		addDescription("Click link Main Page from Typing Requests");

		CertificationsRequestPO certificationsRequestPO = new CertificationsRequestPO(
				SeleniumUtility.getDriver());
		CertificationsRequestPO.clickMainPage();
	}
}
