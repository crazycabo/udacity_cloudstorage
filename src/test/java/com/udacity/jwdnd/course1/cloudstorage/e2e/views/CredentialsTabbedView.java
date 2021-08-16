package com.udacity.jwdnd.course1.cloudstorage.e2e.views;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialsTabbedView {

    @FindBy(xpath = "//button[contains(text(), '+ Add a New Credential')]")
    public WebElement buttonAddNewCredential;

    @FindBy(id = "credential-url")
    public WebElement inputCredentialUrl;

    @FindBy(id = "credential-username")
    public WebElement inputCredentialUsername;

    @FindBy(id = "credential-password")
    public WebElement inputCredentialPassword;

    @FindBy(xpath = "//button[contains(text(), 'Save changes')]")
    public WebElement buttonSaveChanges;

    private final WebDriver driver;
    private final WebDriverWait webDriverWait;

    public CredentialsTabbedView(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, 3);

        PageFactory.initElements(driver, this);
    }

    public Credential getCredentialDetails(int rowNum) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(buttonAddNewCredential));

        WebElement url = driver.findElement(By.xpath("//tr[" + rowNum + "]/td[2]"));
        WebElement username = driver.findElement(By.xpath("//tr[" + rowNum + "]/td[3]"));
        WebElement password = driver.findElement(By.xpath("//tr[" + rowNum + "]/td[4]"));

        return Credential.builder()
                .url(url.getText())
                .username(username.getText())
                .password(password.getText())
                .build();
    }

    public void createCredential(String url, String username, String password) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(buttonAddNewCredential));
        buttonAddNewCredential.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(inputCredentialUrl));

        inputCredentialUrl.sendKeys(url);
        inputCredentialUsername.sendKeys(username);
        inputCredentialPassword.sendKeys(password);

        buttonSaveChanges.click();

        acknowledgeSuccessMessage();
    }

    public void editCredential(int rowNum, String url, String username, String password) {
        WebElement editButton = driver.findElement(By.xpath("//tr[" + rowNum + "]/td/button[contains(text(), 'Edit')]"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(editButton));

        editButton.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(inputCredentialUrl));

        inputCredentialUrl.clear();
        inputCredentialUrl.sendKeys(url);
        inputCredentialUsername.clear();
        inputCredentialUsername.sendKeys(username);
        inputCredentialPassword.clear();
        inputCredentialPassword.sendKeys(password);

        buttonSaveChanges.click();

        acknowledgeSuccessMessage();
    }

    public void deleteCredential(int rowNum) {
        WebElement deleteButton = driver.findElement(By.xpath("//tr[" + rowNum + "]/td/a[contains(text(), 'Delete')]"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(deleteButton));

        deleteButton.click();

        acknowledgeSuccessMessage();
    }

    private void acknowledgeSuccessMessage() {
        WebElement successMessageHomeLink = driver.findElement(By.xpath("//a[contains(text(), 'here')]"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(successMessageHomeLink));

        successMessageHomeLink.click();
    }
}
