package library.utility;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import library.Action;

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
	
	public static boolean changeWindow(String windowTitle,
			WebElement element, int windowsQuantity) {

		wait.until(ExpectedConditions.numberOfWindowsToBe(windowsQuantity));
		firstPage = SeleniumUtility.getDriver().getWindowHandle();

		for (String window : SeleniumUtility.getDriver().getWindowHandles()) {

			SeleniumUtility.getDriver().switchTo().window(window);

			if (!firstPage.equals(SeleniumUtility.getDriver()
					.getWindowHandle())) {

				wait.until(ExpectedConditions.titleContains(windowTitle));

				if (SeleniumUtility.getDriver().getTitle()
						.contains(windowTitle)) {

					wait.until(ExpectedConditions.visibilityOf(element));
					return true;
				}
			}
		}
		return false;
	}	
	
	public static void closeSecondaryWindow() {

		SeleniumUtility.getDriver().close();
		System.out.println("seleniumUtility focus value: "
				+ firstPage);
		SeleniumUtility.getDriver().switchTo().window(firstPage);
	}	
	
	public static boolean consultDataTableByContains(By tableExpression,
			String... data) {

		boolean result = false;

		if (tableExpression == null || data.length == 0)
			return result;

		List<WebElement> rows = SeleniumUtilitys.getDriver().findElements(
				tableExpression);
		int validations = data.length;

		for (WebElement row : rows) {

			List<WebElement> columns = row.findElements(By.tagName("td"));

			int col_count = columns.size();

			for (int columnIndex = 0; columnIndex < col_count; columnIndex++) {

				String columnValue = columns.get(columnIndex).getText();

				for (int i = 0; i < data.length; i++) {
					if (columnValue.trim().toLowerCase()
							.contains(data[i].trim().toLowerCase())) {
						validations--;
						System.out.println("match column:" + columnIndex
								+ " = " + columnValue);
						break;
					}
				}
			}

			if (validations == 0) {
				markField(row);
				result = true;
				hoover(row);
				break;
			} else {
				validations = data.length;
			}

		}

		return result;
	}	

	public static boolean consultDataTableByContains(String xpathTabla,
			String... data) {

		boolean result = false;

		if (xpathTable.isEmpty() || data.length == 0)
			return result;

		int validation = data.length;
		int rowSize = SeleniumUtility.getDriver()
				.findElements(By.xpath(xpathTable + "/tbody/tr")).size();

		int columnSize = 0;
		int startRowIndex = 1;

		if (rowSize > 1) {
			columnSize = SeleniumUtility.getDriver()
					.findElements(By.xpath(xpathTable + "/tbody/tr[2]/td"))
					.size();
			startRowIndex = 2;
		} else {
			columnSize = SeleniumUtility.getDriver()
					.findElements(By.xpath(xpathTable + "/tbody/tr[1]/td"))
					.size();
		}

		for (int rowIndex = startRowIndex; rowIndex <= rowSize; rowIndex++) {

			for (int columnIndex = 1; columnIndex <= columnSize; columnIndex++) {

				String columnValue = SeleniumUtility
						.getDriver()
						.findElement(
								By.xpath(xpathTable + "/tbody/tr[" + rowIndex
										+ "]/td[" + columnIndex + "]"))
						.getText();

				for (int i = 0; i < data.length; i++) {
					if (columnValue.toLowerCase().trim()
							.contains(datos[i].toLowerCase())) {
						validation--;
						System.out.println("match row:" + rowIndex + " = "
								+ columnValue);
						break;
					}
				}
			}

			if (validation == 0) {
				markField(SeleniumUtility.getDriver().findElement(
						By.xpath(xpathTable + "/tbody/tr[" + rowIndex + "]")));
				result = true;
				hoover(SeleniumUtility.getDriver().findElement(
						By.xpath(xpathTable + "/tbody/tr[" + rowIndex + "]")));
				break;
			} else {
				validation = data.length;
			}
		}

		return result;
	}	
	
	public static boolean consultDataTableByEqual(String xpathTable,
			String... data) {

		boolean result = false;

		if (xpathTable.isEmpty() || data.length == 0)
			return result;

		// List<String> datosTemp = new ArrayList<String>();
		// dataTemp.addAll(data);
		int validation = data.length;
		int rowSize = SeleniumUtility.getDriver()
				.findElements(By.xpath(xpathTable + "/tbody/tr")).size();

		int columnSize = SeleniumUtility.getDriver()
				.findElements(By.xpath(xpathTable + "/tbody/tr[2]/td")).size();

		for (int rowIndex = 2; rowIndex <= rowSize; rowIndex++) {

			for (int columnIndex = 1; columnIndex <= columnSize; columnIndex++) {

				String columnValue = SeleniumUtility
						.getDriver()
						.findElement(
								By.xpath(xpathTable + "/tbody/tr[" + rowIndex
										+ "]/td[" + columnIndex + "]"))
						.getText();

				for (int i = 0; i < data.length; i++) {
					if (columnValue.equalsIgnoreCase(data[i])) {
						validation--;
						System.out.println("match row:" + rowIndex + " = "
								+ columnValue);
						break;
					}
				}
			}

			if (validation == 0) {
				markField(SeleniumUtility.getDriver().findElement(
						By.xpath(xpathTable + "/tbody/tr[" + rowIndex + "]")));
				result = true;
				hoover(SeleniumUtility.getDriver().findElement(
						By.xpath(xpathTable + "/tbody/tr[" + rowIndex + "]")));
				break;
			} else {
				validation = data.length;
			}
		}

		return result;
	}	
	
	public static boolean consultInTable(String idRegistry, String xpath) {

		boolean result = false;
		List<WebElement> registry = SeleniumUtility.getDriver()
				.findElements(By.xpath(xpath));
		js = (JavascriptExecutor) SeleniumUtility.getDriver();

		try {
			for (WebElement row : registry) {
				if (row.getText().contains(idRegistry)) {
					getAction().moveToElement(row).build().perform();
					js.executeScript(
							"arguments[0].style.backgroundColor='yellow';"
									+ "arguments[0].style.color='red';",
							row.findElement(By.xpath("//*[contains(text(), '"
									+ idRegistry + "')]")));
					result = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static Actions getAction() {
		return new Actions(SeleniumUtility.getDriver());
	}
	
	// **** Consult only
	public static boolean consultMultiple(WebElement Table, List<String> data) {
		boolean result = false;

		List<WebElement> rows = Table.findElements(By.tagName("tr"));

		for (WebElement row : rows.subList(1, rows.size())) {
			for (int i = 0; i < data.size(); i++) {

				List<WebElement> columns = row.findElements(By.tagName("td"));
				String temp = columns.get(i).getText();

				// Break loop if does not contain next years
				if (Integer.valueOf(columns.get(0).getText()) < Integer
						.valueOf(data.get(0)))
					return false;

				if (temp.contains(",") || temp.contains("."))
					temp = Action.currencyToInt(temp);

				if (temp.equals(data.get(i))) {
					System.out.println("match:" + temp + " = " + data.get(i));
					result = true;
				} else {
					result = false;
					break;
				}
			}

			if (result) {
				markField(row);
				hoover(row);
				break;
			}
		}

		return result;
	}
	
	public static void click(By expressionClickElement) {

		WebElement clickElement = searchObject(expressionClickElement);
		if (clickElement != null) {

			markField(clickElement);

			mesagge += "The item was clicked "
					+ clickElement.getAttribute("id");

			clickElement.click();
		}
	}	
	
	public static boolean clickWaitObject(By expressionClickElement,
			By expressionWaitElement) {

		WebElement clickElement = searchObject(expressionClickElement);

		if (clickElement == null) {
			mensagge += "The element with expression "
					+ expressionClickElement + " it's not visible";
			return false;
		}

		markField(clickElement);
		clickElement.click();
		waitForPageLoaded();
		WebElement waitedElement = searchObject(expressionWaitElement);

		if (waitedElement != null
				&& wait.until(ExpectedConditions.visibilityOf(waitedElement)) != null) {
			markField(waitedElement);
			mensagge += "The element " + waitedElement.getAttribute("id")
					+ " shows correctly";
			return true;
		}

		mensagge += "The element " + waitedElement.getAttribute("id")
				+ " not showing correctly";
		return false;
	}	
	
	public static void uncheckElement(By elementExpression) {

		uncheckElement(elementExpression, false);
	}	
	
	public static void uncheckElement(WebElement element) {
		js = (JavascriptExecutor) SeleniumUtility.getDriver();
		js.executeScript("arguments[0].style.backgroundColor='white';"
				+ "arguments[0].style.color='black';", element);
	}	
	
	public static void uncheckElement(By elementExpression,
			boolean removeStyle) {

		WebElement element = searchObject(elementExpression);

		if (element != null) {
			js = (JavascriptExecutor) SeleniumUtility.getDriver();

			System.out.println(element.getAttribute("style"));

			if (!removeStyle) {
				js.executeScript("arguments[0].style.backgroundColor='white';"
						+ "arguments[0].style.color='black';", element);
				return;
			}

			if (lastStyleObtained.isEmpty()) {
				js.executeScript(
						"arguments[0].removeAttribute('style', 'style');",
						element);
			} else {

				js.executeScript(
						"arguments[0].removeAttribute('style', 'style');",
						element);

				js.executeScript("arguments[0].setAttribute('style','"
						+ lastStyleObtained + "')", element);
			}
		}
	}	
	
	public static void refreshPage() {
		((JavascriptExecutor) SeleniumUtility.getDriver())
				.executeScript("location.reload()");
	}	
	
	public static void wait(int segs) {

		try {
			Thread.sleep(segs * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public static void waitVisibleElement(WebElement element) {

		waitForElementoExist(element);

		long time = 1;

		do {
			sleep(time);
		} while (!element.isDisplayed());
	}	
	
	public static void waitPage() {

		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) SeleniumUtility.getDriver())
						.executeScript("return document.readyState").equals(
								"complete");
			}
		});
	}	
	
	public static void waitElementExist(By by, long time) {

		do {

			sleep(time);

		} while (!waitElement(by));
	}
	
	public static void waitElementExist(WebElement element) {

		long time = 1;

		do {
			sleep(time);
		} while (!waitElement(element));
	}
	
	public static void waitEnable(WebElement element) {

		long time = 1;

		do {
			sleep(time);
		} while (!element.isEnabled());
	}
	
	private static boolean elementExist(By by) {

		try {
			SeleniumUtility.getDriver().findElement(by);
			return true;

		} catch (NoSuchElementException e) {

			return false;
		}
	}
		
	public static boolean elementExist(By elementExpression) {
		return elementExist(elementExpression, Timeout.get5());
	}
	
	public static boolean elementoExist(By elementExpression, long seconds) {

		boolean result = false;
		WebElement element = searchObject(elementExpression, seconds);

		if (element != null && element.isDisplayed()) {
			result = true;
			markField(element);
			message += " \nElement " + element
					+ " is correctly displayed";
		}
		return result;
	}
	
	
	public static boolean elementExist(WebElement element) {
		boolean result = false;
		if (wait.until(ExpectedConditions.visibilityOf(element)) != null
				&& element.isDisplayed()) {
			result = true;
			markField(element);
			message += " \nElement " + element
					+ " is correctly displayed";
		}
		return result;
	}
		
	public static boolean elementExist(WebElement element) {

		try {
			try {
				element.isDisplayed();
				return true;

			} catch (NoSuchElementException e) {

				return false;
			}
		} catch (StaleElementReferenceException e) {
			return false;
		}
	}
	
	public static String getMessage() {
		return message;
	}
	
	public static void hoover(By by) {

		Actions action = new Actions(SeleniumUtility.getDriver());
		WebElement we = SeleniumUtility.getDriver().findElement(by);
		action.moveToElement(we).build().perform();
	}	
	
	public static void hoover(WebElement we) {

		sleep(2);
		Actions action = new Actions(SeleniumUtility.getDriver());
		action.moveToElement(we).build().perform();
	}	
	
	public static void markField(WebElement we) {
		lastStyleObtained = we.getAttribute("style");
		js = (JavascriptExecutor) SeleniumUtility.getDriver();

		js.executeScript("arguments[0].style.backgroundColor='yellow';"
				+ "arguments[0].style.color='red';", we);
	}
	
	public static boolean markInTableText(int col1, WebElement table,
			String text) {

		boolean value = false;
		List<WebElement> columns = table.findElements(By
				.cssSelector("td:nth-child(" + col1 + ")"));

		for (int i = 1; i < columns.size(); ++i) {

			if (columns.get(i).getText().contains(text)) {
				markField(columns.get(i));
				value = true;
				break;
			}
		}
		return value;
	}
	
	public static boolean markInTableValue(int col1, int col2,
			WebElement table, String ref, String data) {

		boolean value = false;
		List<WebElement> columns = table.findElements(By
				.cssSelector("td:nth-child(" + col1 + ")"));
		List<WebElement> columns2 = table.findElements(By
				.cssSelector("td:nth-child(" + col2 + ")"));

		for (int i = 1; i < columns.size(); ++i) {

			if (columns.get(i).getText().contains(ref)
					&& columns2.get(i).getText().contains(dato)) {
				markField(columns.get(i));
				markField(columns2.get(i));
				value = true;
				break;
			}
		}
		return valor;
	}
	
	public static boolean removeHighlightTable(String xpathTable,
			int startRowIndex, int endRowIndex) {
		boolean result = false;

		for (int rowIndex = startRowIndex; rowIndex <= endRowIndex; rowIndex++) {
			uncheckItem(By.xpath(xpathTable + "/tbody/tr[" + rowIndex
					+ "]"));
		}
		return result;
	}
	
	public static void removeReadOnly(WebElement element) {

		js = (JavascriptExecutor) SeleniumUtility.getDriver();
		
		js.executeScript("arguments[0].readOnly = false", element);
	}
	
	public static boolean itIsShowingAlert() {
		try {
			SeleniumUtility.getDriver().switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/*
	* Parameter: The Webelement table, the list of values to be compared
	* with the table. Note: This is used in case the header is within
	* the table, if outside use the other method
	*/

	public static boolean itIsShowingAlert(Alert alert) {

		boolean itIsShowing = false;

		try {
			do {
				sleep(1);
			} while (alert.equals(null));

			itIsShowing = true;

		} catch (NullPointerException e) {

			itIsShowing = false;
		}
		return itIsShowing;
	}
	
	/*
	* Parameter: The Webelement table, and the list of values to be validated
	* In the table
	*/

	public static void selectList(WebElement element, String value) {

		Select dropList = new Select(element);
		wait(1);
		element.click();
		dropList = new Select(element);
		dropList.selectByVisibleText(value);

		waitForPageLoaded();
	}
	
	// Select an item from a drop-down list
	public static boolean selectList(WebElement element, String value) {

		boolean setCriterion = false;

		try {

			WebElement selectElement = element.findElement(By
					.cssSelector("[value='" + value + "']"));

			if (selectElement.isSelected()) {
				setCriterion = true;
			}

		} catch (NoSuchElementException e) {
			System.out.println("========== Element Not Found ==========");
			System.out.println(e.toString());
			setCriterion = false;
		}
		return setCriterion;
	}
	
	public static boolean selectDropDown(WebElement element, String value) {

		Select dropDown = new Select(element);

		String idComboBox = element.getAttribute("id");
		element.click();
		dropDown.selectByVisibleText(valor);
		waitForPageLoaded();
		String xpath = "//*[@id='" + idComboBox + "']/option";
		List<WebElement> options = null;
		int attempts = 0;
		while (attempts < 2) {
			try {
				options = SeleniumUtility.getDriver().findElements(
						By.xpath(xpath));
				break;
			} catch (Exception e) {
			}
			attempts++;
		}

		if (options != null) {

			WebElement selectedOption = null;
			for (WebElement option : options) {
				if (option.getText() != null
						&& option.getText().contains((value))) {
					selectedOption = option;
					break;
				}
			}

			if (selectedOption != null) {
				markField(selectedOption);
				return true;
			}
		}
		message += value + "-" + "could not be selected";
		return false;
	}
	
	/*
	* Parameter: Receive the table to be clicked, The value in the table
	* that will be clicked and the column in which the value is
	* to click
	*/
	public static boolean selectInTable(String idRegistry,
			List<WebElement> records, String linkText) {

		boolean result = false;

		for (WebElement row : records) {
			if (row.getText().contains(idRegistry)) {
				row.findElement(By.linkText(linkText)).click();
				result = true;
				sleep(5);
				break;
			}
		}
		return result;
	}
	
	public static boolean selectInTable(String idRegistry, String xpath,
			String linkText) {

		boolean result = false;

		List<WebElement> registry = SeleniumUtility.getDriver()
				.findElements(By.xpath(xpath));

		for (WebElement row : registry) {
			if (row.getText().contains(idRegistry)) {
				row.findElement(By.linkText(linkText)).click();
				result = true;
				sleep(5);
				break;
			}
		}
		return result;
	}
	
	public static boolean selectInTableValue(String idRegistry,
			List<WebElement> records, String linkText) {

		boolean result = false;

		for (WebElement row : records) {
			if (row.getText().contains(idRegistry)) {				
				markField(row);
				row.findElement(By.linkText(linkText)).click();
				result = true;
				sleep(5);
				break;
			}
		}
		return result;
	}
	
	public static boolean select(List<WebElement> Table,
			List<String> TaxpayerData, int CantColum) {

		int t = 1, p = 0, Cant = 0;
		message = " ";
		boolean Result = false;

		for (WebElement row : Table) {
			for (int i = 1; i < CantColum + 1; i++) {
				t = 1;
				List<WebElement> column = row.findElements(By
						.cssSelector("td:nth-child(" + i + ")"));

				while (t < column.size()) {
					Result = false;
					if (column.get(t).getText()
							.equals(TaxpayerData.get(p))) {
						System.out.println(column.get(t).getText() + " = "
								+ TaxpayerData.get(p));
						markField(column.get(t));
						Result = true;
						break;
					}
					t++;
				}
				p++;

				if (Result == false) {
					mensaje = column.get(0).getText() + " - " + message;
					Cant++;
				}
			}
		}

		if (Cant == 0) {
			message = "The table was validated correctly";
			return Result;
		} else {
			Result = false;
			return Result;
		}
	}
	
	public static boolean select(List<WebElement> Table,
			List<String> TaxpayerData, WebElement NameColumn) {
		int t = 0, p = 0, Cant = 0;
		message = " ";
		boolean Result = false;
		List<WebElement> column = NameColumn.findElements(By.tagName("td"));
		List<WebElement> rows = Table.get(0).findElements(By.tagName("tr"));
		Smash: for (WebElement row : rows) {
			List<WebElement> columns = row.findElements(By.tagName("td"));
			t = 0;
			for (WebElement registry : columns) {
				if (registry.getText().equals(TaxpayerData.get(p))) {
					System.out.println(registry.getText() + " = "
							+ TaxpayerData.get(p));
					markField(registry);
					Result = true;
					Cant = p;
					p++;
				}

				if ((!registry.getText().equals(TaxpayerData.get(Cant)))
						&& Result == true) {
					message = column.get(p).getText() + " - " + message;
					Result = false;
					p++;
					t++;
				}
			}
			if (p == columns.size()) {
				break Rompe;
			}
		}
		if (t > 0) {
			Result = false;
		}
		return Result;
	}
	
	public static boolean select(List<WebElement> Table,
			List<String> TaxpayerData) {
		int t = 0, p = 0, Cant = 0;
		message = " ";
		boolean Result = false;
		List<WebElement> rows = Table.get(0).findElements(By.tagName("tr"));
		Rompe: for (WebElement row : rows) {
			List<WebElement> columns = row.findElements(By.tagName("td"));
			t = 0;
			System.out.println(columns.get(0));
			for (WebElement registry : columns) {
				if (registry.getText().equals(TaxpayerData.get(p))) {
					System.out.println(registry.getText() + " = "
							+ TaxpayerData.get(p));
					markField(registry);
					Result = true;
					Cant = p;
					p++;
				}

				if ((!registry.getText().equals(TaxpayerData.get(Cant)))
						&& Result == true) {
					message = columns.get(0).getText() + " - " + message;
					Result = false;
					p++;
					t++;
				}
			}
			if (p == columns.size()) {
				break Smash;
			}
		}
		if (t > 0) {
			Result = false;
		}
		return Result;
	}
	
	public static boolean SelectQuery(List<WebElement> Table,
			String Link, int Column) {
		// String message="";
		boolean result = false;
		for (WebElement row : Table) {
			List<WebElement> column = row.findElements(By
					.cssSelector("td:nth-child(" + Column + ")"));
			for (WebElement column : column) {
				if (column.getText().equals(Link)) {
					WebElement linck = column.findElement(By.tagName("a"));
					markField(link);
					link.click();
					result = true;
					message = "Link was clicked";
					break;
				}
			}
		}

		if (result == false) {
			message = "Link to click was not found";
		}
		return result;
	}
	
	
	/***
	* Search the table taking into account the number of attributes sent in
	* the "data" list
	 * 
	 * 
	 * ***/

	public static boolean SelectMultiple(WebElement Table,
			List<String> data, String link) {
		boolean result = false;

		List<WebElement> rows = Table.findElements(By.tagName("tr"));

		for (WebElement row : rows.subList(1, rows.size())) {
			for (int i = 0; i < data.size(); i++) {

				List<WebElement> columns = row.findElements(By.tagName("td"));

				if (Integer.valueOf(columns.get(0).getText()) < Integer
						.valueOf(data.get(0))) {
					return false;
				}

				if (columns.get(i).getText().equals(data.get(i))) {
					System.out.println(columns.get(i).getText() + " = "
							+ data.get(i));

					result = true;
				} else {
					result = false;
					break;
				}
			}

			if (result) {
				markField(row);
				row.findElement(By.tagName("a")).click();
				wait(1);
				break;
			}

		}

		return result;
	}
	
	public static void selectValue(WebElement element, String value) {

		Select dropList = new Select(element);

		do {
			wait(1);
			dropList = new Select(element);
			dropList.selectByVisibleText(value);
			wait(1);
		} while (dropList.getOptions().isEmpty());
	}
	
	public static boolean setText(By elementExpression, String txt) {

		WebElement field = searchObject(elementExpression);
		if (field != null) {
			field.clear();
			field.sendKeys(txt);
			markField(field);
			if (field.getAttribute("value").contains(txt)) {
				message += "/nWas written +" + txt + " in the field "
						+ field.getAttribute("id");
				return true;
			}
		}
		message += "/nWas written +" + txt + " in the field "
				+ field.getAttribute("id");
		return false;
	}
	
	public static boolean setText(WebElement element, String txt) {

		if (element != null) {
			element.clear();
			element.sendKeys(txt);
			markField(element);
			if (element.getAttribute("value").contains(txt)) {
				message += "/nWas written+" + txt + " in the field "
						+ element.getAttribute("id");
				return true;
			}
		}
		message += "/nWas not written +" + txt + " in the field "
				+ element.getAttribute("id");
		return false;
	}
	
	public static boolean setValue(String txt, WebElement field, String failure) {

		boolean val;
		field.clear();
		field.sendKeys(txt);
		val = field.getAttribute("value").contains(txt);
		System.out.println(val ? "Correct" : Failure + " Failed");
		if (!val)
			message += message + " ";
		return val;
	}
	
	public static boolean validateAssertList(HashMap<String, Boolean> asserts) {
		boolean validation = true;

		for (Entry<String, Boolean> pair : asserts.entrySet()) {

			if (!pair.getValue()) {
				System.out.println("Failed in: " + pair.getKey());
				validation = false;
			}
		}

		for (Entry<String, Boolean> pair : asserts.entrySet()) {

			if (!pair.getValue()) {
				System.out.println("Failed in: " + pair.getKey());
				validation = false;
			}
		}

		return validation;
	}
}
