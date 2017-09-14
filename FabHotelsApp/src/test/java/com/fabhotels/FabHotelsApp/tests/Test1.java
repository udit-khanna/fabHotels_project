package com.fabhotels.FabHotelsApp.tests;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fabhotels.FabHotelsApp.pages.TestPage;

public class Test1 extends BaseTest {

	private static final String JSON_FILE_NAME = "Test1.json";
	private static final String INIT_INFO = "Initializing test data from Test1.json file";
	private static final String START_TESTCASE_EXECUTION = "------Starting Test case execution----";
	private static final String SUCCESSFUL_USER_FLOW_ERROR = " Thrown by test case: successfulUserFlow()";
	private static final String CONSTRUCTOR_INFO = "In Test1() constructor";
	private static final String EXCEPTION_ASSERT_ERROR = "In catch block -->Test case is not executed --> Received an exception before asserting";
	private JSONObject appConfig;
	private static TestPage testPage;
	private JSONObject testDataConfiguration;
	private String CouponCode;
	private String FullName;
	private String Email;
	private String Mobile;
	private String URL;

	/**
	 * Constructor
	 *
	 * @throws JSONException
	 */
	public Test1() throws Exception {
		super();
		LOGGER.info(CONSTRUCTOR_INFO);
		appConfig = this.getConfig(JSON_FILE_NAME);
		testDataConfiguration = appConfig.getJSONObject("testDataConfiguration");
		init();
	}

	/**
	 * This method reads all the test data from "Test1.json" file
	 *
	 * @throws JSONException
	 */
	private void init() throws JSONException {
		LOGGER.info(INIT_INFO);
		CouponCode = testDataConfiguration.getString("CouponCode");
		FullName = testDataConfiguration.getString("FullName");
		Email = testDataConfiguration.getString("Email");
		Mobile = testDataConfiguration.getString("Mobile");
		URL = testDataConfiguration.getString("URL");

	}

	/**
	 * List all the localities and number of hotels (count) in each locality.
	 */
	@Test(enabled = false, priority = -1)
	public void listLocalitiesNHotels() {
		try {
			testPage = new TestPage(driver);
			LOGGER.info(START_TESTCASE_EXECUTION);
			LOGGER.info("Inside listLocalitiesNHotels method");
			testPage.tapSkipButton();
			testPage.tapNewDelhiCity();
			testPage.findLocalities();
		} catch (Exception exception) {
			LOGGER.error(SUCCESSFUL_USER_FLOW_ERROR, exception);
			Assert.fail(EXCEPTION_ASSERT_ERROR, exception);
		}

	}

	/**
	 * Verify contact number for customer care is correct (phone icon on right
	 * top corner).
	 */
	@Test(enabled = true, priority = 1)
	public void verifyContactNumber() {
		try {
			testPage = new TestPage(driver);
			LOGGER.info(START_TESTCASE_EXECUTION);
			LOGGER.info("Inside verifyContactNumber method");
			testPage.tapSkipButton();
			testPage.tapPhoneIcon();
			testPage.getPhoneNumber();
		} catch (Exception exception) {
			LOGGER.error(SUCCESSFUL_USER_FLOW_ERROR, exception);
			Assert.fail(EXCEPTION_ASSERT_ERROR, exception);
		}

	}

	/**
	 * Apply coupon while booking hotel and on payment screen when user clicks
	 * on ‘Pay At Hotel’ verify that coupon is removed for all the payment
	 * sections (Credit/Debit/NetBanking/Wallet)
	 */
	@Test(enabled = true, priority = 2)
	public void applyCouponScenarios() {
		try {
			testPage = new TestPage(driver);
			LOGGER.info(START_TESTCASE_EXECUTION);
			LOGGER.info("Inside applyCouponScenarios method");
			testPage.tapSkipButton();
			testPage.tapNewDelhiCity();
			testPage.tapHotel();
			testPage.tapSelectDatesButton();
			testPage.tapDoneButtonOnDateSelector();
			testPage.checkIfHotelSoldOut();
			testPage.scrollToEnterCouponCode();
			testPage.enterCouponCode(CouponCode);
			testPage.tapApplyButton();
			testPage.checkCouponAppliedAndClickContinue();
			testPage.enterDetails(FullName, Email, Mobile);
			testPage.tapProceedToPayButton();
			testPage.scrollToRemoveCouponAndClick();
			testPage.tapIWantToPayNowButton();
			testPage.checkCouponRemoved();
		} catch (Exception exception) {
			LOGGER.error(SUCCESSFUL_USER_FLOW_ERROR, exception);
			Assert.fail(EXCEPTION_ASSERT_ERROR, exception);
		}
	}

	/**
	 * Location and hotel Counts on SearchFormScreen.
	 */
	@Test(enabled = true, priority = 3)
	public void locationAndHotelCounts() {
		try {
			testPage = new TestPage(driver);
			LOGGER.info(START_TESTCASE_EXECUTION);
			LOGGER.info("Inside locationAndHotelCounts method");
			testPage.tapSkipButton();
			testPage.tapSearchIcon();
			testPage.getLocationsAndHotels();
			testPage.printLocationsAndHotels();
		} catch (Exception exception) {
			LOGGER.error(SUCCESSFUL_USER_FLOW_ERROR, exception);
			Assert.fail(EXCEPTION_ASSERT_ERROR, exception);
		}
	}

	/**
	 * Validate that deeplink for https://www.fabhotels.com/hote ls-in-new-delhi
	 * takes user for New Delhi search result screen and hotels are present on
	 * the result screen.
	 */
	@Test(enabled = true, priority = 4)
	public void checkDeepLink() {
		try {
			testPage = new TestPage(driver);
			LOGGER.info(START_TESTCASE_EXECUTION);
			LOGGER.info("Inside checkDeepLink method");
			testPage.getURL(URL);
			testPage.checkDelhiSearchOpens();
			testPage.checkHotelsPresent();
		} catch (Exception exception) {
			LOGGER.error(SUCCESSFUL_USER_FLOW_ERROR, exception);
			Assert.fail(EXCEPTION_ASSERT_ERROR, exception);
		}

	}

}
