package TestCase;

import java.util.LinkedList;
import java.util.List;

import resources.TestCase.TC707OFVCertRegVHMThatHasNotUpdatedYourDataHelper;
import TestStep.LoginSeleniumIntranet;
import TestStep.LogoutSeleniumIntranet;
import TestStep.TC30361.TS30361InsertID;
import TestStep.TC607.TS607ClickSearchID;
import TestStep.TC607.TS607ClickLinkApplicationCertifications2;
import TestStep.TC607.TS607ClickMainPageFromApplicationCertifications;
import TestStep.TC707.TS707CloseSecondaryWindow;
import framework.interface.TestStep;

/**
 * Description : Functional Test Script
 * 
 * @author Jbjimenezm
 */
public class TC707OFVCertRegVHMThatHasNotUpdatedYourData
		extends
		TC707OFVCertRegVHMThatHasNotUpdatedYourDataHelper {
	/**
	 * Script Name :
	 * <b>TC707OFVCertRegVHMThatHasNotUpdatedYourData</b>
	 * Generated : <b>26/11/2019 10:30:47</b> Description : Functional Test
	 * Script Original Host : WinNT Version 6.3 Build 9600 ()
	 * 
	 * @since 2019/11/26
	 * @author Jbjimenezm
	 */
	private List<TestStep> steps;

	@Override
	public List<TestStep> getSteps() {

		setName("Test Case 707: OFV Intranet Cert. Reg. VHM that has not updated your data.");

		steps = new LinkedList<>();
		steps.add(new LoginSeleniumIntranet("username", "password"));

		steps.add(new TS607ClickLinkApplicationCertifications2());
		steps.add(new TS30361InsertID("00000000000"));
		steps.add(new TS607ClickSearchID());
		steps.add(new TS707CloseSecondaryWindow());
		steps.add(new TS607ClickMainPageFromApplicationCertifications());
		steps.add(new LogoutSeleniumIntranet());

		return steps;
	}
}
