package com.udacity.jwdnd.course1.cloudstorage.e2e;

import com.udacity.jwdnd.course1.cloudstorage.e2e.views.CredentialsTabbedView;
import com.udacity.jwdnd.course1.cloudstorage.e2e.views.HomeView;
import com.udacity.jwdnd.course1.cloudstorage.e2e.views.LogInView;
import com.udacity.jwdnd.course1.cloudstorage.e2e.views.SignUpView;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by Brian Smith on 8/16/21.
 * Description:
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialTests {

    @LocalServerPort
    private int port;

    private WebDriver webDriver;
    private HomeView homeView;
    private CredentialsTabbedView credentialsTabbedView;

    @BeforeEach
    public void setUpBrowser() {
        String url = "http://localhost:" + port;

        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(url);

        LogInView logInView = new LogInView(webDriver, url);
        SignUpView signUpView = new SignUpView(webDriver, url);
        homeView = new HomeView(webDriver, url);
        credentialsTabbedView = new CredentialsTabbedView(webDriver);

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

    private Credential createCredential() {
        homeView.tabCredentials.click();
        credentialsTabbedView.createCredential(
                "https://bandai-hobby.net",
                "collector",
                "B@nD@1");

        homeView.tabCredentials.click();
        return credentialsTabbedView.getCredentialDetails(1);
    }

    @Test
    public void createCredentialAndVerifyContent() {
        Credential credential = createCredential();

        assertAll(
                () -> assertEquals("https://bandai-hobby.net", credential.getUrl()),
                () -> assertEquals("collector", credential.getUsername()),
                () -> assertNotEquals("B@nD@1", credential.getPassword()),
                () -> assertEquals("B@nD@1", credentialsTabbedView.getDecryptedPassword(1))
        );
    }

    @Test
    public void editCredentialAndVerifyContent() {
        createCredential();

        credentialsTabbedView.editCredential(1, "https://bandai-hobby.net/schedule", "admin", "GuNd@M");

        homeView.tabCredentials.click();
        Credential editedCred = credentialsTabbedView.getCredentialDetails(1);

        assertAll(
                () -> assertEquals("https://bandai-hobby.net/schedule", editedCred.getUrl()),
                () -> assertEquals("admin", editedCred.getUsername()),
                () -> assertNotEquals("GuNd@M", editedCred.getPassword()),
                () -> assertEquals("GuNd@M", credentialsTabbedView.getDecryptedPassword(1))
        );
    }

    @Test
    public void deleteCredential() {
        createCredential();

        credentialsTabbedView.deleteCredential(1);
        assertThrows(TimeoutException.class, () -> {
            credentialsTabbedView.getCredentialDetails(1);
        },"Credential should not display");
    }
}
