package libreria.utility;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import librery.Action;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.rational.test.ft.script.RationalTestScript;

public class SeleniumActionController extends RationalTestScript {

	private static JavascriptExecutor js = null;

	private static String message;
	public static String firstPage;

	private static String lastStyleObtained;
	private static WebDriverWait wait = new WebDriverWait(
			SeleniumUtility.getDriver(), Timeout.get10());

	public static boolean acceptAlert() {		

		boolean closed = false;

		try {
			if (itIsShowingAlert()) {
				wait.until(ExpectedConditions.alertIsPresent());

				SeleniumUtility.getDriver().switchTo().alert().accept();
				closed = true;
			}
		} catch (NoAlertPresentException Ex) {
			closed = false;
		}

		return closed;
	}

	public static WebElement searchObject(By elementExpression) {
		return searchObject(elementExpression, Timeout.get5());
	}	
	
	public static WebElement searchObject(By elementExpression, long seconds) {
		return wait.withTimeout(seconds, TimeUnit.SECONDS)
				.until(ExpectedConditions
						.presenceOfElementLocated(elementExpression));
	}

	public static List<WebElement> searchObjectsWithAttempts(
			By elementExpression, long seconds) {

		return wait.withTimeout(seconds, TimeUnit.SECONDS).until(
				ExpectedConditions
						.presenceOfAllElementsLocatedBy(elementExpression));
	}
	
	
	public static boolean changeWindow(int windowsQuantity) {

		wait.until(ExpectedConditions.numberOfWindowsToBe(windowsQuantity));
		firstPage = SeleniumUtility.getDriver().getWindowHandle();

		for (String window : SeleniumUtility.getDriver().getWindowHandles()) {

			SeleniumUtility.getDriver().switchTo().window(window);

			Set<String> winHandles = SeleniumUtility.getDriver()
					.getWindowHandles();
			// Loop through all handles
			for (String handle : winHandles) {
				if (!handle.equals(firstPage)) {
					SeleniumUtility.getDriver().switchTo().window(handle);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {						
						e.printStackTrace();
					}
					System.out.println("Title of the new window: "
							+ SeleniumUtility.getDriver().getTitle());
				}
			}
		}
		return SeleniumUtility.getDriver().getWindowHandle() != firstPage;
	}
}
