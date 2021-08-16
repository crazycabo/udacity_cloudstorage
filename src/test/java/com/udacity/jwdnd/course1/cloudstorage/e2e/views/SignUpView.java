package com.udacity.jwdnd.course1.cloudstorage.e2e.views;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpView {

    @FindBy(id = "inputUsername")
    public WebElement inputUsername;

    @FindBy(id = "inputPassword")
    public WebElement inputPassword;

    @FindBy(id = "inputFirstName")
    public WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    public WebElement inputLastName;

    @FindBy(xpath = "//button[contains(text(), 'Sign Up')]")
    public WebElement buttonSubmitSignUp;

    private final WebDriver driver;
    private final WebDriverWait webDriverWait;
    private final String baseUrl;

    public SignUpView(WebDriver driver, String baseUrl)
    {   this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, 10);
        this.baseUrl = baseUrl;

        PageFactory.initElements(driver, this);
    }

    public void navigateToPage() {
        driver.get(baseUrl + "/signup");
    }

    public void createAccount(String firstName, String lastName, String username, String password) {
        webDriverWait.until(ExpectedConditions.visibilityOf(buttonSubmitSignUp));

        inputFirstName.sendKeys(firstName);
        inputLastName.sendKeys(lastName);
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);

        buttonSubmitSignUp.click();
    }
}
