package PageObjects;

import library.utility.SeleniumActionController;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CertificationRequestPO extends PageObjectMain {

	public CertificationRequestPO(WebDriver driver) {
		super(driver);
		// TODO Auto-generated builder appendix
	}

	@FindBy(id = "ctl00_ContentPlaceHolder1_txtUser")
	private static WebElement txtID;

	public void insertID(String ID) {
		SeleniumActionController.waitForElementExist(txtID);
		txtID.sendKeys(ID);
	}

	@FindBy(id = "ctl00_ContentPlaceHolder1_btnConsult")
	private static WebElement btnConsult;

	public void clickBtnConsult() {
		SeleniumActionController.waitForElementExist(btnConsult);
		btnConsult.click();
	}
	
	@FindBy(xpath = "//*[@id='content_content_derecha']/table/tbody/tr/td")
	private WebElement right_content;

	public boolean verifyEntranceApplicationCertification(
			String _rightContent) {

		SeleniumActionController.waitForElementExist(rightContent);
		SeleniumActionController.markField(rightContent);

		rightContent.click();

		System.out.println("title sent equals: "
				+ _rightContent.equals(rightContent.getText()));
		
		if (_rightContent.equals(rightContent.getText().trim())) {
			return true;
		} else {
			return false;
		}
	}
	
	@FindBy(xpath = "//*[@id='logo']/a/img")
	private WebElement mainPage;
	
	public void clickMainPage(){
		mainPage.click();
	}
}
