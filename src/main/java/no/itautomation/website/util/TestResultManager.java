package no.itautomation.website.util;

import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.auth.BasicScheme;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
//import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import com.codeborne.selenide.impl.ScreenShotLaboratory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import no.itautomation.website.hooks.TestSuiteInitialization;


public class TestResultManager {

	public static final String ENDPOINT = "http://localhost:1010/"; //your jira server URL
	public static final String TM4J_RESULT_ENDPOINT = "/rest/atm/1.0/testrun/";
	public static final String Template = "resources/tm4j_template/tm4j_create_execution.json";
	private String testCycleID;
	private String testCaseID;
	private JsonObject reqBody;
	public static final String testResultCommonMessage = "Test was success";

	public TestResultManager(String testCycleID) {
		
		this.testCycleID = testCycleID;
		try {
			this.reqBody = new Gson().fromJson(IOUtils.toString(new FileReader((Template))), JsonObject.class);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setTestcaseID(String testcaseID) {
		this.testCaseID = testcaseID;
	}
	
	public String getTestCaseID() {
		return testCaseID;
	}
	
	public String getTestCycleID() {
		return testCycleID;
	}
	
	public JsonObject getReqPayload() {
		return reqBody;
	}
	
	public TestResultManager setStatus(String status) {
		getReqPayload().addProperty("status", status);
		getReqPayload().get("scriptResults").getAsJsonArray().get(0).getAsJsonObject().addProperty("status", status);
		return this;
	}
	
	public TestResultManager setComment(String comment) {
		getReqPayload().addProperty("comment", comment);
		return this;
	}

	public TestResultManager setActualResultComment(String arc, long exectime) {
		getReqPayload().get("scriptResults").getAsJsonArray().get(0).getAsJsonObject().addProperty("comment", arc);
		getReqPayload().getAsJsonObject().addProperty("executionTime", exectime);
		return this;
	}

	
	@SuppressWarnings("deprecation")
	public TestResultManager updateTestResult() throws UnsupportedOperationException, ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		String req = new Gson().toJson(getReqPayload());
		StringEntity se = new StringEntity(req);
		String constructEndPOint = ENDPOINT + TM4J_RESULT_ENDPOINT + getTestCycleID() +"/testcase/"+ getTestCaseID()+"/testresult";
//		System.out.println("THread ID => " +  Thread.currentThread().getId() + "\n ==> "+constructEndPOint + "\n intance ID" + this.hashCode()  );
		HttpPost post = new HttpPost(constructEndPOint);
		post.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials("senthil", "ITA123456"),"UTF-8", false));
		post.addHeader("Content-Type", "application/json");
		post.setEntity(se);
		try {
			String testresultID = new Gson().fromJson(IOUtils.toString(httpclient.execute(post).getEntity().getContent(), Charsets.UTF_8), JsonObject.class)
					.get("id").getAsString();
			if(getReqPayload().get("status").getAsString().equalsIgnoreCase("fail")) {
			screenshotAttachment(testresultID);
			}
		} catch (NullPointerException e) {
			e.printStackTrace(); 
		}
		return this;
		
	}
	
	@SuppressWarnings("deprecation")
	public void screenshotAttachment(String testresultID) throws UnsupportedOperationException, ClientProtocolException, IOException {
//		System.out.println("THread ID => " +  Thread.currentThread().getId() + "\n intance ID" + this.hashCode() +"\n => Screenshot"+ ScreenShotLaboratory.getInstance().getLastScreenshot().getAbsolutePath() );
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		String endPoint = ENDPOINT + "rest/atm/1.0/testresult/"+testresultID+"/attachments";
		HttpEntity entity = MultipartEntityBuilder.create().addPart("file", new FileBody(TestSuiteInitialization.screenshotName.get()))
				.setContentType(ContentType.MULTIPART_FORM_DATA).build();
		HttpPost post = new HttpPost(endPoint); 
		post.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials("senthil", "ITA123456"),"UTF-8", false));
		post.setEntity(entity);
		httpclient.execute(post);
	}
	
	
}