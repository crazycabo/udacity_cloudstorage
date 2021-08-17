package com.udacity.jwdnd.course1.cloudstorage.e2e;

import com.udacity.jwdnd.course1.cloudstorage.e2e.views.HomeView;
import com.udacity.jwdnd.course1.cloudstorage.e2e.views.LogInView;
import com.udacity.jwdnd.course1.cloudstorage.e2e.views.NotesTabbedView;
import com.udacity.jwdnd.course1.cloudstorage.e2e.views.SignUpView;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteTests {

    @LocalServerPort
    private int port;

    private WebDriver webDriver;
    private HomeView homeView;
    private NotesTabbedView notesTabbedView;

    @BeforeEach
    public void setUpBrowser() {
        String url = "http://localhost:" + port;

        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(url);

        LogInView logInView = new LogInView(webDriver, url);
        SignUpView signUpView = new SignUpView(webDriver, url);
        homeView = new HomeView(webDriver, url);
        notesTabbedView = new NotesTabbedView(webDriver);

        signUpView.navigateToPage();

        String randomUsername = RandomStringUtils.randomAlphabetic(16);;
        String randomPassword = RandomStringUtils.randomAlphabetic(8);;
        signUpView.createAccount("Random", "User", randomUsername, randomPassword);

        logInView.logIn(randomUsername, randomPassword);
    }

    @AfterEach
    public void tearDownBrowser() {
        webDriver.quit();
    }

    private Note createNote() {
        homeView.tabNotes.click();
        notesTabbedView.createNote("Alarm", "This could be a problem");

        homeView.tabNotes.click();
        return notesTabbedView.getNoteDetails(1);
    }

    @Test
    public void createNoteAndVerifyContent() {
        Note note = createNote();

        assertAll(
                () -> assertEquals("Alarm", note.getTitle()),
                () -> assertEquals("This could be a problem", note.getDescription())
        );
    }

    @Test
    public void editNoteAndVerifyContent() {
        createNote();

        notesTabbedView.editNote(1, "Cleared", "Everything is fine now");

        homeView.tabNotes.click();
        Note editedNote = notesTabbedView.getNoteDetails(1);

        assertAll(
                () -> assertEquals("Cleared", editedNote.getTitle()),
                () -> assertEquals("Everything is fine now", editedNote.getDescription())
        );
    }

    @Test
    public void deleteNote() {
        createNote();

        notesTabbedView.deleteNote(1);
        assertThrows(TimeoutException.class, () -> {
            notesTabbedView.getNoteDetails(1);
        },"Note should not display");
    }
}
