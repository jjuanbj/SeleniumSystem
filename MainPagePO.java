package PageObjects;

import library.utility.SeleniumActionController;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPagePO extends PageObject_Main {

	public MainPagePO(WebDriver driver) {
		super(driver);
		// TODO Auto-generated builder appendix
	}

	@FindBy(id = "2460")
	private WebElement linkRequestCertifications;

	public void clickLinkRequestCertifications() {
		SeleniumActionController
				.waitForElementExist(linkRequestCertifications);
		linkRequestCertifications.click();
	}	
}
