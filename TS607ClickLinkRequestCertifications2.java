package TestStep.TC607;

import library.utility.SeleniumUtility;
import resources.TestStep.TC607.TS607ClickLinkRequestCertifications2Helper;
import PageObjects.MainPagePO;

/**
 * Description : Functional Test Script
 * 
 * @author jjimenez
 */
public class TS607ClickLinkRequestCertifications2 extends
		TS607ClickLinkRequestCertifications2Helper {
	/**
	 * Script Name : <b>TS607ClickLinkRequestCertifications2</b>
	 * Generated : <b>05/11/2019 11:26:29</b> Description : Functional Test
	 * Script Original Host : WinNT Version 6.3 Build 9600 ()
	 * 
	 * @since 2019/11/05
	 * @author jjimenez
	 */
	public void testMain(Object[] args) {
		addDescription("Click Link Request Certifications");

		MainPagelPO mainPagePO = new MainPagePO(
				SeleniumUtilidades.getDriver());
		mainPagePO.clickLinkRequestCertifications2();
	}
}
