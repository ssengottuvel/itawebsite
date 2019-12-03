package no.itautomation.website.pages.home;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;

import no.itautomation.website.hooks.ItAutomationDriver;

public class HomePage {
	
    public String HOME_TEXT = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/main[1]/article[1]/div[1]/div[1]/div[1]/div[1]/section[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/p[1]";
    public String CONTACT_MENU = "//a[contains(text(),'Contact')]";
    
	protected ItAutomationDriver driver;

	public HomePage(ItAutomationDriver driver) {
		this.driver = driver;
	}
	
}