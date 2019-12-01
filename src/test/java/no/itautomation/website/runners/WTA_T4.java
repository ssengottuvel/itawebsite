package no.itautomation.website.runners;

import cucumber.api.CucumberOptions;
import no.itautomation.website.hooks.TestSuiteInitialization;

@CucumberOptions(features = "resources/feature/", tags = "@TestCaseKey=WTA-T4", glue = { "classpath:no.itautomation.website"}, monochrome = true, strict = true, plugin = {
		"pretty", "html:target/surefire-reports/html/WTA-T4", "json:target/surefire-reports/json/WTA-T4.json" })
public class WTA_T4 extends TestSuiteInitialization  {

}
