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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotesTests {

    @LocalServerPort
    private int port;

    private WebDriver webDriver;
    private LogInView logInView;
    private SignUpView signUpView;
    private HomeView homeView;
    private NotesTabbedView notesTabbedView;

    @BeforeEach
    public void setUpBrowser() {
        String url = "http://localhost:" + port;

        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(url);

        logInView = new LogInView(webDriver, url);
        signUpView = new SignUpView(webDriver, url);
        homeView = new HomeView(webDriver, url);
        notesTabbedView = new NotesTabbedView(webDriver, url);

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

    @Test
    public void createEditDeleteNoteAndVerifyContent() {
        homeView.tabNotes.click();
        notesTabbedView.createNote("Alarm", "This could be a problem");

        homeView.tabNotes.click();
        Note note = notesTabbedView.getNoteDetails(1);

        assertEquals("Alarm | This could be a problem", note.getTitle() + " | " + note.getDescription(),
                "New note details are not correct");

        notesTabbedView.editNote(1, "Cleared", "Everything is fine now");

        homeView.tabNotes.click();
        Note editedNote = notesTabbedView.getNoteDetails(1);

        assertEquals("Cleared | Everything is fine now", editedNote.getTitle() + " | " + editedNote.getDescription(),
                "Edited note details are not correct");

        notesTabbedView.deleteNote(1);
        assertThrows(TimeoutException.class, () -> {
            notesTabbedView.getNoteDetails(1);
        }, "Note should not display");
    }
}
