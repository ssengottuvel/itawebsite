/**
 * 
 */
package no.itautomation.website.runners;

import cucumber.api.CucumberOptions;
import no.itautomation.website.hooks.TestSuiteInitialization;

/**
 * @author senth
 *
 */
@CucumberOptions(features = "resources/feature/", tags = "@TestCaseKey=WTA-T1", glue = { "classpath:no.itautomation.website"}, monochrome = true, strict = true, plugin = {
		"pretty", "html:target/surefire-reports/html/WTA-T1", "json:target/surefire-reports/json/WTA-T1.json" })
public class WTA_T1 extends TestSuiteInitialization {

}
