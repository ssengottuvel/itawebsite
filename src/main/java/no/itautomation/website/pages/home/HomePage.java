package no.itautomation.website.pages.home;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;

import no.itautomation.website.hooks.ItAutomationDriver;

public class HomePage {
	
    public String HOME_TEXT = "//*[@id='post-10']/div/div/div/div/section[1]/div[3]/div/div[1]/div/div/div[1]/div/div";
    public String CONTACT_MENU = "//a[contains(text(),'Contact')]";
    
	protected ItAutomationDriver driver;

	public HomePage(ItAutomationDriver driver) {
		this.driver = driver;
	}
	
}