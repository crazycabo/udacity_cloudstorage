package com.udacity.jwdnd.course1.cloudstorage.e2e.views;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInView {

    @FindBy(id = "inputUsername")
    public WebElement inputUsername;

    @FindBy(id = "inputPassword")
    public WebElement inputPassword;

    @FindBy(xpath = "//button[contains(text(), 'Login')]")
    public WebElement buttonSubmitLogIn;

    private final WebDriver driver;
    private final WebDriverWait webDriverWait;
    private final String baseUrl;

    public LogInView(WebDriver driver, String baseUrl)
    {   this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, 10);
        this.baseUrl = baseUrl;

        PageFactory.initElements(driver, this);
    }

    public void navigateToPage() {
        driver.get(baseUrl + "/login");
    }

    public void logIn(String username, String password) {
        webDriverWait.until(ExpectedConditions.visibilityOf(buttonSubmitLogIn));

        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
        buttonSubmitLogIn.click();
    }
}
