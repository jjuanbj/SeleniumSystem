package TestStep.TC607;

import library.utility.SeleniumUtility;
import resources.TestStep.TC607.TS607ClickSearchIDHelper;
import PageObjects.CertificationRequestPO;

/**
 * Description : Functional Test Script
 * 
 * @author jjimenez
 */
public class TS607ClickSearchID extends TS607ClickSearchIDHelper {
	/**
	 * Script Name : <b>TS607ClickSearchID</b> Generated : <b>22/10/2019
	 * 15:52:06</b> Description : Functional Test Script Original Host : WinNT
	 * Version 6.3 Build 9600 ()
	 * 
	 * @since 2019/10/22
	 * @author jjimenez
	 */
	public void testMain(Object[] args) {
		addDescription("Click 'Consultar' button");
		
		CertificationRequestPO certificationRequestPO = new CertificationRequestPO(
				SeleniumUtility.getDriver());
		certificationRequestPO.clickBtnConsult();
	}
}
