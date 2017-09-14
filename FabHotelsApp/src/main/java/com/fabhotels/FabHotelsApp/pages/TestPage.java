package com.fabhotels.FabHotelsApp.pages;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;

/**
 * This class includes methods that are used to perform actions on TestPage
 * 
 * @author UDIT KHANNA
 *
 */
public class TestPage extends AbstractPage {
	private static final String CONSTRUCTOR_INFO = "In Test page constructor";
	private static final String CouponAppliedVerificationText = "Fab! You just saved";
	// private static Map<String, String> LocationsAndHotels;
	List<String> listOfTextOfElements = new ArrayList<>();

	// locators
	private static final String skipButton = "com.fabhotels.guests:id/btn_skip";
	private static final String newDelhiIcon = "com.fabhotels.guests:id/tv_cityName";
	private static final String localitiesDelhi = "//android.widget.TextView[@index='0']";
	private static final String phoneIcon = "//android.widget.ImageButton[@index='0']";
	private static final String phoneNumberOnDialer = "com.android.dialer:id/digits";
	private static final String hotelIcon = "com.fabhotels.guests:id/tv_property_name";
	private static final String selectDatesButton = "com.fabhotels.guests:id/btn_bookNow";
	private static final String backButtonOnHotelDescription = "//android.widget.ImageButton[@index='0']";
	private static final String doneButtonOnDateSelector = "com.fabhotels.guests:id/btn_done";
	private static final String enterCouponCode = "com.fabhotels.guests:id/et_coupon_code";
	private static final String reviewDetailsLabel = "com.fabhotels.guests:id/tv_title";
	private static final String applyButton = "com.fabhotels.guests:id/btn_apply";
	private static final String couponApplied = "com.fabhotels.guests:id/tv_coupon_applied_text";
	private static final String continueButton = "com.fabhotels.guests:id/btn_continue";
	private static final String fullNameInput = "com.fabhotels.guests:id/et_full_name";
	private static final String emailInput = "com.fabhotels.guests:id/et_email";
	private static final String mobileInput = "com.fabhotels.guests:id/et_mobile_number";
	private static final String proceedToPayButton = "com.fabhotels.guests:id/btn_pay";
	private static final String removeCouponCode = "com.fabhotels.guests:id/btnPayAtHotel";
	private static final String payAtHotelText = "com.fabhotels.guests:id/textView13";
	private static final String iWantToPayNowButton = "com.fabhotels.guests:id/btnPayNow";
	private static final String payButton = "com.fabhotels.guests:id/btnPay";
	private static final String payAtHotelButton = "com.fabhotels.guests:id/btnPayAtHotel";
	private static final String searchIcon = "com.fabhotels.guests:id/tv_whereTo";
	private static final String linearLayout = "//android.widget.LinearLayout/android.widget.TextView";
	private static final String pageHeaderLabel = "//android.widget.TextView[@index='1']";
	private static final String hotelIconLink = "com.fabhotels.guests:id/iv_property";

	public TestPage(AndroidDriver<WebElement> driver) {
		super(driver);
		LOGGER.info(CONSTRUCTOR_INFO);
	}

	public void tapSkipButton() {
		LOGGER.info("Tapping Skip button");
		try {
			clickElement(By.id(skipButton));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void tapNewDelhiCity() {
		LOGGER.info("Tapping New Delhi City icon");
		try {
			if (driver.findElement(By.id(newDelhiIcon)).getText().contains("New Delhi"))
				clickElement(By.id(newDelhiIcon));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void findLocalities() {
		LOGGER.info("finding Localities");
		try {
			getNumberOfElements(By.xpath(localitiesDelhi));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void tapPhoneIcon() {
		LOGGER.info("Tapping Phone Icon");
		try {
			if (doesElementExist(By.xpath(phoneIcon)))
				clickElement(By.xpath(phoneIcon));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getPhoneNumber() {
		LOGGER.info("Getting Phone number");
		try {
			waitForElement(By.id(phoneNumberOnDialer));
			getTextOfElement(By.id(phoneNumberOnDialer));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void tapHotel() {
		LOGGER.info("Tapping Hotel icon");
		try {
			waitForElement(By.id(hotelIcon));
			clickElement(By.id(hotelIcon));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void tapSelectDatesButton() {
		LOGGER.info("Tapping Select Dates button");
		try {
			if (getTextOfElement(By.id(selectDatesButton)).equalsIgnoreCase("SELECT DATES")
					|| getTextOfElement(By.id(selectDatesButton)).equalsIgnoreCase("Book Now"))
				clickElement(By.id(selectDatesButton));
			else {
				LOGGER.info("Clicking Back button");
				clickElement(By.xpath(backButtonOnHotelDescription));
				tapHotel();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void tapDoneButtonOnDateSelector() {
		LOGGER.info("Tapping Done Button On Date Selector");
		try {
			clickElement(By.id(doneButtonOnDateSelector));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkIfHotelSoldOut() {
		LOGGER.info("Checking if Hotel is Sold Out");
		try {
			if (doesElementExist(By.id(selectDatesButton))) {
				if (getTextOfElement(By.id(selectDatesButton)).equalsIgnoreCase("SOLD OUT")) {
					LOGGER.info("Clicking Back button");
					clickElement(By.xpath(backButtonOnHotelDescription));
					tapHotel();
					tapSelectDatesButton();
					checkIfHotelSoldOut();
				}
			} else {
				LOGGER.info("Hotel not sold out");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void scrollToEnterCouponCode() {
		LOGGER.info("Scrolling To enter Coupon Code element");
		try {
			if (doesElementExist(By.id(reviewDetailsLabel))) {
				Dimension dim = getScreenSize();
				do {
					scrollPage(setDimensionsForScrollVertical(dim));
					if (doesElementExist(By.id(enterCouponCode)))
						break;
				} while (doesElementExist(By.id(enterCouponCode)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterCouponCode(String couponCode) {
		LOGGER.info("Entering Coupon Code");
		try {
			enterInput(By.id(enterCouponCode), couponCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void tapApplyButton() {
		LOGGER.info("Tapping Apply Button");
		try {
			clickElement(By.id(applyButton));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkCouponAppliedAndClickContinue() {
		LOGGER.info("Tapping Apply Button");
		try {
			if (getTextOfElement(By.id(couponApplied)).contains(CouponAppliedVerificationText)) {
				LOGGER.info("Coupon Applied");
				LOGGER.info("Clicking continue button");
				clickElement(By.id(continueButton));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterDetails(String fullName, String email, String mobile) {
		LOGGER.info("Entering Details");
		try {
			waitForElement(By.id(fullNameInput));
			enterInput(By.id(fullNameInput), fullName);
			enterInput(By.id(emailInput), email);
			enterInput(By.id(mobileInput), mobile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void tapProceedToPayButton() {
		LOGGER.info("Tapping Proceed To Pay Button");
		try {
			clickElement(By.id(proceedToPayButton));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void scrollToRemoveCouponAndClick() {
		LOGGER.info("Scrolling To Remove Coupon button");
		try {
			if (doesElementExist(By.id(reviewDetailsLabel))) {
				Dimension dim = getScreenSize();
				do {
					scrollPage(setDimensionsForScrollVertical(dim));
					if (doesElementExist(By.id(removeCouponCode))) {
						clickElement(By.id(removeCouponCode));
						break;
					}
				} while (!(doesElementExist(By.id(removeCouponCode))));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void tapIWantToPayNowButton() {
		LOGGER.info("Tapping I Want To Pay Now Button");
		try {
			waitForElement(By.id(payAtHotelText));
			clickElement(By.id(iWantToPayNowButton));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void checkCouponRemoved() {
		LOGGER.info("checking Coupon is removed");
		try {
			runAppInBackground();
			waitForElement(By.id(payButton));
			String payButtonText = getTextOfElement(By.id(payButton));
			Assert.assertTrue(getTextOfElement(By.id(payAtHotelButton)).contains(payButtonText));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void tapSearchIcon() {
		LOGGER.info("Tapping Search Icon");
		try {
			waitForElement(By.id(searchIcon));
			clickElement(By.id(searchIcon));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getLocationsAndHotels() {
		LOGGER.info("Getting locations and Hotels");
		try {
			Dimension dim = getScreenSize();
			List<String> newList;
			do {
				newList = getListOfTextOfElements(By.xpath(linearLayout));
				listOfTextOfElements.addAll(newList);
				scrollPage(setDimensionsForScrollVertical(dim));
			} while (!(listOfTextOfElements.contains("Visakhapatnam")));
			while (listOfTextOfElements.contains("POPULAR CITIES") || listOfTextOfElements.contains("ALL CITIES")) {
				listOfTextOfElements.remove("POPULAR CITIES");
				listOfTextOfElements.remove("ALL CITIES");
			}
			LOGGER.info(listOfTextOfElements);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printLocationsAndHotels() {
		LOGGER.info("Printing Locations with Hotels");
		try {
			for (int i = 1; i < listOfTextOfElements.size(); i++) {
				LOGGER.info("Location: " + listOfTextOfElements.get(i-1) + " : " + listOfTextOfElements.get(i));
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getURL(String url) {
		LOGGER.info("Getting url: " + url);
		try {
			driver.navigate().to(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkDelhiSearchOpens() {
		LOGGER.info("Checking Delhi Search Opens");
		try {
			waitForElement(By.xpath(pageHeaderLabel));
			Assert.assertTrue(getTextOfElement(By.xpath(pageHeaderLabel)).equalsIgnoreCase("New Delhi"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkHotelsPresent() {
		LOGGER.info("Checking Hotels are Present");
		try {
			Assert.assertTrue(doesElementExist(By.id(hotelIconLink)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
