/**
 * 
 */
package no.itautomation.website.hooks;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author testcomplete4
 *
 */
public class SessionObject

{
	public static Map<Object, Object> config = new LinkedHashMap<Object, Object>();
	public static Map<Object, Object> data = new LinkedHashMap<Object, Object>();

	private HashMap<String, String> sakId = new HashMap<String, String>();
	private static final String image_chrome_version = "image_chrome_version";
	private static final String image_firefox_version = "image_firefox_version";
	private static final String browserType = "browserType";
	private static final String MAXSYNC = "maxSync";
	private static final String MINSYNC = "minSync";
	private static final String productURL = "productURL";
	private static final String browserUnderTest = "browserUnderTest";
	private static final String productUnderTest = "productUnderTest";
	private static final String webSite = "webSite";
	private static final String JIRAServerURL = "productUnderTest";
	private static final String configPath = "resources/config/config.properties";
	private static final String dataPath = "resources/data/data.properties";
	private static final String testCycleID = "testCycleID";
	private static final String isRemote = "docker";
	private static final Object POLLING_TIMEOUT = "pollingTimeOut";

	private HashMap<String, String> sakTittel = new HashMap<String, String>();
	private HashMap<String, String> journalPostId = new HashMap<String, String>();
	private HashMap<String, String> docId = new HashMap<String, String>();



	public void loadConfig() {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(configPath));
			config.putAll(properties);
			properties.clear();
			properties.load(new FileInputStream(dataPath));
			data.putAll(properties);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getBrowserUnderTest() {
		return browserUnderTest;
	}

	public static String getProductUnderTest() {
		return config.get(productUnderTest).toString();
	}

	public static String getProductURL() {
		return config.get(productURL).toString();
	}

	public static int getMaxSync() {
		return Integer.valueOf(config.get(MAXSYNC).toString());
	}

	public static Long getPollingTimeOut() {
		return Long.valueOf(config.get(POLLING_TIMEOUT).toString())*1000 ;
	}
	
	public static int getMinSync() {
		return Integer.valueOf(config.get(MINSYNC).toString())*1000 ;
	}
	public static String getChromeImageVersion() {
		return config.get(image_chrome_version).toString();
	}

	public static String getFirefoxVersion() {
		return config.get(image_firefox_version).toString();
	}

	public static String getTestCycleID() {
		return config.get(testCycleID).toString();
	}

	public static String getArkivarUserName(String role) {
		return data.get(getArkivarName(role).split(",")[0]).toString();
	}

	public static String getArkivarPassword(String role) {
		return data.get(getArkivarName(role).split(",")[1]).toString();
	}

	public static String getArkivarName(String arkivarName) {
		return data.get(arkivarName).toString();
	}

	
	public static String getArkivarUserName1() {
		return data.get(getArkivarName1().split(",")[0]).toString();
	}

	public static String getArkivarPassword1() {
		return data.get(getArkivarName1().split(",")[1]).toString();
	}

	public static String getArkivarName1() {
		return data.get("Arkivar1Name").toString();
	}
	
}
