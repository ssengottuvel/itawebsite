package no.itautomation.website.hooks;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.codeborne.selenide.WebDriverRunner;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import no.itautomation.website.util.TestResultManager;


public class TestSuiteInitialization extends AbstractTestNGCucumberTests {

	public SessionObject ssObj;
	public static ThreadLocal<String[]> browserDetails;
	public static ThreadLocal<List<String>> jp = new ThreadLocal<>();
	public static Path screenshotPath;
	public TestResultManager testResultManager;
	public static ThreadLocal<String> tcName;
	public static ThreadLocal<File> screenshotName;
	

	@BeforeSuite
	public void setUp() {
		//Arrays.stream(LogManager.getLogManager().getLogger("").getHandlers()).forEach(h -> h.setLevel(Level.SEVERE));
		initializeObject();
		tcName = new ThreadLocal<String>();
		screenshotName = new ThreadLocal<File>();
	}
	
	@Parameters({ "browserName", "browserVersion", "isRemoteEnabled" })
	@BeforeClass
	public void setUp(String browserName, String browserVersion, String isRemoteEnabled) {
		browserDetails.set(new String[] { browserName, browserVersion, isRemoteEnabled });
	}
	
	@BeforeMethod
	public void beforemethod() {
		testResultManager = new TestResultManager(SessionObject.getTestCycleID());
	}

	@AfterMethod
	public void tearDown(ITestResult testResult) throws UnsupportedOperationException, ClientProtocolException, IOException {
		
		String resultStatus = (testResult.isSuccess()) ? "Pass" : "Fail";
		// Check test status, if test failed, close the webdriver
		if (resultStatus.equals("Fail")) {
			// Close the webdriver in case of failure
			//WebDriverRunner.getWebDriver().close();
		}
		String comment = (resultStatus.equals("Pass")) ? TestResultManager.testResultCommonMessage : "Testcase Failed";
		String testComment = "";
		if (testResult.getThrowable() != null) {
			testComment = testResult.getThrowable().getLocalizedMessage();
			// testComment = testComment.replaceAll("\\r\\n|\\r|\\n", " ").toString();
			// testComment = testComment.replaceAll("[{}:.]", " ").toString();
			testComment = testComment.replaceAll("[æ]", "aa").toString();
			testComment = testComment.replaceAll("[ø]", "o").toString();
			testComment = testComment.replaceAll("[å]", "a").toString();
		}
		
		testResultManager.setTestcaseID(tcName.get());
		testResultManager.setStatus(resultStatus);
		testResultManager.setActualResultComment(testComment, testResult.getEndMillis() - testResult.getStartMillis());
		testResultManager.setComment(comment);
		testResultManager.updateTestResult();
	}

	public void initializeObject() {
		ssObj = new SessionObject();
		ssObj.loadConfig();
		browserDetails = new ThreadLocal<>();
		String fileDirName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-hh-mm-ss"));
		try {
			screenshotPath = Files.createDirectories(Paths.get("screenshot")).resolve(fileDirName).toAbsolutePath();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
