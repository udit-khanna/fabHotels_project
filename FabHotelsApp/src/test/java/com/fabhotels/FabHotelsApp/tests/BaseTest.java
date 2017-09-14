package com.fabhotels.FabHotelsApp.tests;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.android.AndroidDriver;

/**
 * This class includes all the initial setup required for Appium
 * 
 * @author UDIT KHANNA
 *
 */
public class BaseTest {
	public static final Logger LOGGER = Logger.getLogger(BaseTest.class);
	private static final String JSON_FILE_NAME = "BaseTest.json";
	private static final String INITIALIZE_INFO = "Initializing test data from BaseTest.json file";
	private static final String CONSTRUCTOR_INFO = "In BaseTest() constructor";
	private static final String CONSTRUCTOR_ERROR = "Thrown by constructor: BaseTest()";
	private static final String INSTANTIATE_ANDROID_DRIVER = "Instantiating android driver";
	private static final String TEAR_DOWN_INFO = "Quitting driver";
	private static final String INSTANTIATE_JSON_OBJ = "Instantiating JSON Object for .json files";
	private static final String APPLICATION_PACKAGE = "com.fabhotels.guests";
	private static final String APP_ACTIVITY = "app.fabhotels.MainActivity";
	public static String appiumHost;
	public static String appiumPort;
	public static AndroidDriver<WebElement> driver;
	private JSONObject appConfig;
	private JSONObject appiumConfiguration;
	private JSONObject fabHotelsConfiguration;

	static {
		PropertyConfigurator.configure(System.getProperty("user.dir").concat("\\log4j.properties"));
	}

	/**
	 * Constructor
	 *
	 * @throws JSONException
	 */
	public BaseTest() throws Exception {
		LOGGER.info(CONSTRUCTOR_INFO);
		try {
			appConfig = this.getConfig(JSON_FILE_NAME);
			fabHotelsConfiguration = appConfig.getJSONObject("FabHotelsConfiguration");
			appiumConfiguration = appConfig.getJSONObject("appiumConfiguration");
			initialize();
		} catch (JSONException exception) {
			LOGGER.error(CONSTRUCTOR_ERROR, exception);
			throw exception;
		} catch (Exception exception) {
			LOGGER.error(CONSTRUCTOR_ERROR, exception);
			throw exception;
		}
	}

	/**
	 * This method reads test data from BaseTest.json
	 *
	 * @throws JSONException
	 */
	public void initialize() throws JSONException {
		LOGGER.info(INITIALIZE_INFO);
		appiumPort = appiumConfiguration.getString("appiumPort");
		appiumHost = appiumConfiguration.getString("appiumHost");
	}

	/**
	 * Instantiate AndroidDriver
	 *
	 * @throws MalformedURLException
	 * @throws JSONException
	 * @throws InterruptedException
	 */
	@BeforeMethod
	public void loadLoginPage() throws MalformedURLException, JSONException, InterruptedException {
		LOGGER.info(INSTANTIATE_ANDROID_DRIVER);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", "Moto G5 Plus");
		capabilities.setCapability("platformVersion", "7.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("sessionOverride", "true");
		capabilities.setCapability("app", System.getProperty("user.dir").concat("\\Apps\\FabHotels\\FabHotels.apk"));
		capabilities.setCapability("appActivity", APP_ACTIVITY);
		capabilities.setCapability("autoGrantPermissions", true);

		driver = new AndroidDriver<WebElement>(
				new URL("http://".concat(appiumHost).concat(":").concat(appiumPort).concat("/wd/hub")), capabilities);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(10000);
	}

	/**
	 * Quit AndroidDriver
	 */
	@AfterMethod
	public void tearDown() {
		LOGGER.info(TEAR_DOWN_INFO);
		// driver.removeApp(APPLICATION_PACKAGE);
		driver.quit();
	}

	/**
	 * This method return a reference to the json file whose name is passed as
	 * an argument to the method
	 *
	 * @param jsonFile
	 *            Name of the json file
	 * @return object Reference to the json file
	 * @throws JSONException
	 */
	protected JSONObject getConfig(String jsonFile) throws JSONException {
		LOGGER.info(INSTANTIATE_JSON_OBJ);
		JSONObject object;
		InputStream input = this.getClass().getClassLoader().getResourceAsStream((jsonFile));
		// The jsonFile path cannot be externalized in BaseTest.json as this
		// method is called in the constructor to create a JSONObject of file.
		object = new JSONObject(new JSONTokener(new InputStreamReader(input)));
		return object;
	}

}
