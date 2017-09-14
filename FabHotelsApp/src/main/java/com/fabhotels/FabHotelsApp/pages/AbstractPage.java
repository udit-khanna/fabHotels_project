package com.fabhotels.FabHotelsApp.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

/**
 * This class includes methods that are commonly used to perform actions
 * 
 * @author UDIT KHANNA
 *
 */
public class AbstractPage {

	public static final Logger LOGGER = Logger.getLogger(AbstractPage.class);
	public static final String TRUE_TEXT = "true";
	public static final String CHECKED_TEXT = "checked";
	// Content Descriptions
	private static final String CONSTRUCTOR_INFO = "In Abstract Page Constructor";
	private static final String DOES_ELEMENT_EXIST_INFO = "Verifying that the element exists";
	private static final String ENTER_INPUT_INFO = "Entering the input in the field";
	private static final String CLICK_ELEMENT_INFO = "Clicking on the element";
	private static final String WAIT_FOR_ELEMENT_INFO = "Waiting for the element to be visible";
	private static final String KEYBOARD_NOT_FOUND_INFO = "The keyboard is not present";
	private static final String CLICKING_ELEMENT_NUM = "Clicking on the element with index: ";
	public static String androidVersion;
	protected AndroidDriver<WebElement> driver;
	protected WebDriverWait wait;

	/**
	 * Constructor
	 *
	 * @param driver
	 *            Reference to the android driver which is instantiated in
	 *            BaseTest.java
	 */
	public AbstractPage(AndroidDriver<WebElement> driver) {
		LOGGER.info(CONSTRUCTOR_INFO);
		this.driver = driver;
		// androidVersion =
		// parseAndroidVersion(driver.getCapabilities().getCapability("platformVersion").toString());
		wait = new WebDriverWait(driver, 60);
	}

	/**
	 * This element verifies the existence of element
	 *
	 * @param locator
	 *            locator of the element
	 * @return true if element exists
	 */
	public boolean doesElementExist(By locator) {
		LOGGER.info(DOES_ELEMENT_EXIST_INFO);
		return (driver.findElements(locator).size() != 0);
	}

	/**
	 * This method enters input in the field specified in the parameters
	 *
	 * @param identifier
	 *            locator of the text element
	 * @param input
	 *            text to be entered in the text element
	 */
	public void enterInput(By identifier, String input) {
		LOGGER.info(ENTER_INPUT_INFO + " : " + input);
		WebElement element = driver.findElement(identifier);
		element.click();
		element.clear();
		element.sendKeys(input);
		try {
			driver.hideKeyboard();
		} catch (WebDriverException exception) {
			LOGGER.info(KEYBOARD_NOT_FOUND_INFO);
		}
	}

	/**
	 * This method clicks on the element with id given in parameter
	 *
	 * @param identifier
	 *            locator of the element
	 */
	public void clickElement(By identifier) {
		LOGGER.info(CLICK_ELEMENT_INFO);
		driver.findElement(identifier).click();
	}

	/**
	 * Waiting for element to be visible
	 *
	 * @param identifier
	 *            locator of the element
	 */
	public void waitForElement(By identifier) {
		LOGGER.info(WAIT_FOR_ELEMENT_INFO);
		wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));
	}

	/**
	 * This method clicks on the element with index given in parameter
	 *
	 * @param list
	 *            of the elements
	 * @param index
	 *            of element to be clicked
	 */
	public void clickElementByIndex(By identifier, int index) {
		LOGGER.info(CLICKING_ELEMENT_NUM + index);
		driver.findElements(identifier).get(index).click();
	}

	/**
	 * This method clicks on the element with index given in parameter
	 *
	 * @param list
	 *            of the elements
	 * @param index
	 *            of element to be clicked
	 */
	public void getNumberOfElements(By identifier) {
		LOGGER.info("Getting number of elements");
		for (WebElement e : driver.findElements(identifier)) {
			System.out.println(e);
		}
	}

	/**
	 * To get the dimensions of the screen
	 *
	 * @return size of current screen
	 */
	public Dimension getScreenSize() {
		LOGGER.info("Getting screen size.");
		return driver.manage().window().getSize();
	}

	/**
	 * To scroll the page based on coordinates provided
	 *
	 * @param coordinates
	 *            startx, starty, endx, endy
	 */
	public void scrollPage(int[] coordinates) {
		LOGGER.info("Scrolling the page");
		LOGGER.info("startx:" + coordinates[0] + " starty: " + coordinates[1]);
		LOGGER.info("endx:" + coordinates[2] + " endy: " + coordinates[3]);
		driver.swipe(coordinates[0], coordinates[1], coordinates[2], coordinates[3], 3000);
	}

	/**
	 * To get text of the element
	 *
	 * @param identifier
	 *            of the element
	 * @return text of the element
	 */
	public String getTextOfElement(By identifier) {
		LOGGER.info("Getting text of element");
		String returnText = driver.findElement(identifier).getText();
		LOGGER.info("Returning text: " + returnText);
		return returnText;
	}

	/**
	 * To set dimentions for vertical scroll
	 *
	 * @param size
	 *            of the element
	 * @return integer array of dimensions set
	 */
	public int[] setDimensionsForScrollVertical(Dimension size) {
		int[] returnScroll = new int[4];
		returnScroll[1] = (int) (size.height * 0.80);
		returnScroll[3] = (int) (size.height * 0.20);
		returnScroll[0] = (int) (size.width / 2);
		returnScroll[2] = (int) (size.width / 2);
		return returnScroll;
	}

	/**
	 * To put the app in background and then run
	 *
	 */
	public void runAppInBackground() {
		try {
			driver.runAppInBackground(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To get number of the elements
	 *
	 * @param identifier
	 *            of the element
	 * @return number of the elements
	 */
	public int getNumOfElements(By identifier) {
		LOGGER.info("Getting number of elements");
		return driver.findElements(identifier).size();
	}

	/**
	 * To get list of text of the elements
	 *
	 * @param identifier
	 *            of the element
	 * @return list of text of the elements
	 */
	public List<String> getListOfTextOfElements(By identifier) {
		LOGGER.info("Getting list of text of elements");
		List<String> returnList = new ArrayList<>();
		for (WebElement element : driver.findElements(identifier)) {
			returnList.add(element.getText());
		}
		return returnList;
	}

}
