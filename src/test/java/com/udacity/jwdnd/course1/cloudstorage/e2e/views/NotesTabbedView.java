package com.udacity.jwdnd.course1.cloudstorage.e2e.views;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotesTabbedView {

    @FindBy(xpath = "//button[contains(text(), '+ Add a New Note')]")
    public WebElement buttonAddNewNote;

    @FindBy(id = "note-title")
    public WebElement inputNoteTitle;

    @FindBy(id = "note-description")
    public WebElement inputNoteDescription;

    @FindBy(xpath = "//button[contains(text(), 'Save changes')]")
    public WebElement buttonSaveChanges;

    private final WebDriver driver;
    private final WebDriverWait webDriverWait;
    private final String baseUrl;

    public NotesTabbedView(WebDriver driver, String baseUrl) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, 10);
        this.baseUrl = baseUrl;

        PageFactory.initElements(driver, this);
    }

    public Note getNoteDetails(int rowNum) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(buttonAddNewNote));

        WebElement title = driver.findElement(By.xpath("//tr[" + rowNum + "]/td[2]"));
        WebElement description = driver.findElement(By.xpath("//tr[" + rowNum + "]/td[3]"));

        return Note.builder()
                .title(title.getText())
                .description(description.getText())
                .build();
    }

    public void createNote(String title, String description) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(buttonAddNewNote));
        buttonAddNewNote.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(inputNoteTitle));
        inputNoteTitle.sendKeys(title);
        inputNoteDescription.sendKeys(description);

        buttonSaveChanges.click();

        acknowledgeSuccessMessage();
    }

    public void editNote(int rowNum, String title, String description) {
        WebElement editButton = driver.findElement(By.xpath("//tr[" + rowNum + "]/td/button[contains(text(), ' Edit ')]"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(editButton));

        editButton.click();

        inputNoteTitle.sendKeys(title);
        inputNoteDescription.sendKeys(description);

        buttonSaveChanges.click();

        acknowledgeSuccessMessage();
    }

    public void deleteNote(int rowNum) {
        WebElement deleteButton = driver.findElement(By.xpath("//tr[" + rowNum + "]/td/button[contains(text(), ' Delete ')]"));
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
