package no.itautomation.website.hooks;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;



public class ItAutomationDriver {
	public SelenideDriver driver;
//	private Map<String, Object> data;
	private static final long DEFAULT_POLLING_TIMOUT = SessionObject.getPollingTimeOut();
	private String testId="";

	public ItAutomationDriver() {

	}

	public SelenideConfig getSelenideConfig(Scenario scenario) {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-web-security");
		options.addArguments("--allow-file-access-from-files");
		options.addArguments("--allow-running-insecure-content");
		options.addArguments("--allow-cross-origin-auth-prompt");

		String testcaseId = scenario.getId().split("/", 3)[2].split("\\.")[0];
		SelenideConfig selenideConfig = new SelenideConfig();
		selenideConfig.browserCapabilities().setCapability(ChromeOptions.CAPABILITY, options);
		selenideConfig.browserCapabilities().setAcceptInsecureCerts(true);
		selenideConfig.browserCapabilities().setCapability("ENSURING_CLEAN_SESSION", true);
		selenideConfig.browser(TestSuiteInitialization.browserDetails.get()[0]);
		selenideConfig.browserVersion(TestSuiteInitialization.browserDetails.get()[1]);
		selenideConfig.browser();
		selenideConfig.startMaximized(true);
		selenideConfig.fastSetValue(true);
		selenideConfig.timeout(1000);
		selenideConfig.pollingInterval(DEFAULT_POLLING_TIMOUT);
		selenideConfig.screenshots(false);
		selenideConfig.savePageSource(false);
		selenideConfig.baseUrl(SessionObject.getProductURL());
		if (TestSuiteInitialization.browserDetails.get()[2].equalsIgnoreCase("true")) {
			selenideConfig.browserCapabilities().setCapability("enableVNC", true);
			selenideConfig.browserCapabilities().setCapability("enableVideo", false);
			selenideConfig.browserCapabilities().setCapability("name", "TestCase Id: " + testcaseId);
			selenideConfig.remote("http://localhost:4444/wd/hub");
//			selenideConfig.remote("http://10.0.102.191:4444/wd/hub");

		}
		return selenideConfig;
	}

	@Before
	public void setUpBrowser(Scenario scenario) {		
		this.driver = new SelenideDriver(getSelenideConfig(scenario));
		this.driver.open(SessionObject.getProductURL());
		testId = scenario.getSourceTagNames().toString().replace("[","").replace("]","").split("=")[1];
		TestSuiteInitialization.tcName.set(testId);
		//login();
	}

	@After
	public void tearDown(Scenario scenario) throws IOException {		
		if (scenario.isFailed()) {
			String fileName = TestSuiteInitialization.screenshotPath + "\\" + scenario.getName()+".png";			
			File scrFile = ((TakesScreenshot)driver.getWebDriver()).getScreenshotAs(OutputType.FILE);
			TestSuiteInitialization.screenshotName.set(scrFile);
			FileUtils.copyFile(scrFile, new File(fileName));
			
		//	logOut();
		}
		driver.close();
		
	}
	
	public void sleep(int seconds) {
		try {Thread.sleep(seconds * 1000);} 
		catch (InterruptedException e) {e.printStackTrace();}
	}

	
	public SelenideElement getElement(By by) {
	
		for (int i = 0; i < SessionObject.getMaxSync(); i++) {
			try {
				boolean elementCondition = driver.$(driver.Wait()
						.until(ExpectedConditions.visibilityOfElementLocated(by)))
						.exists()
						&&
						driver.$(driver.Wait()
						.until(ExpectedConditions.elementToBeClickable(by))).exists();
				if (elementCondition)
				return driver.$(by);
				else {sleep(1);/*System.out.println("Retrying Element to locate" + element.toString());*/}
				}
			 catch (Throwable e) {//	System.out.println("Retrying Element " + element.toString());}
		}
	}
		return driver.$(by);
	}
	
	
	public SelenideElement getElement(By by, int seconds) {
		for (int i = 0; i < seconds; i++) {
			try {
				boolean elementCondition = driver.$(driver.Wait()
						.until(ExpectedConditions.visibilityOfElementLocated(by)))
						.exists()
						&&
						driver.$(driver.Wait()
						.until(ExpectedConditions.elementToBeClickable(by))).exists();
				if (elementCondition)
				return driver.$(by);
				else {sleep(1);/*System.out.println("Retrying Element to locate" + element.toString());*/}
				}
			 catch (Throwable e) {//	System.out.println("Retrying Element " + element.toString());}
		}
	}
		return driver.$(by);
	}
	
	

	public boolean isElementExist(By by) {
		for (int i = 0; i < SessionObject.getMaxSync(); i++) {
			if(driver.Wait().until(ExpectedConditions.invisibilityOfElementLocated(by)).booleanValue())
				return true;}
		return false;
	}
	
	public boolean isElementExist(By by, int seconds) {
		for (int i = 0; i < seconds; i++) {
			if(driver.Wait().until(ExpectedConditions.invisibilityOfElementLocated(by)).booleanValue())
				return true;}
		return false;
	}
	
}
