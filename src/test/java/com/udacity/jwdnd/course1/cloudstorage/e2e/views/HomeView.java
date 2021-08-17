package com.udacity.jwdnd.course1.cloudstorage.e2e.views;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomeView {

    @FindBy(xpath = "//button[contains(text(), 'Logout')]")
    public WebElement buttonLogOut;

    @FindBy(xpath = "//a[contains(text(), 'Notes')]")
    public WebElement tabNotes;

    @FindBy(xpath = "//a[contains(text(), 'Credentials')]")
    public WebElement tabCredentials;

    private final WebDriver driver;
    private final WebDriverWait webDriverWait;
    private final String baseUrl;

    public HomeView(WebDriver driver, String baseUrl) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, 10);
        this.baseUrl = baseUrl;

        PageFactory.initElements(driver, this);
    }

    public void navigateToPage() {
        driver.get(baseUrl + "/home");
    }

    public void logOut() {
        webDriverWait.until(ExpectedConditions.visibilityOf(buttonLogOut));
        buttonLogOut.click();
    }
}
