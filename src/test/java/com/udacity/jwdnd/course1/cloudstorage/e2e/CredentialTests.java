package com.udacity.jwdnd.course1.cloudstorage.e2e;

import com.udacity.jwdnd.course1.cloudstorage.e2e.views.CredentialsTabbedView;
import com.udacity.jwdnd.course1.cloudstorage.e2e.views.HomeView;
import com.udacity.jwdnd.course1.cloudstorage.e2e.views.LogInView;
import com.udacity.jwdnd.course1.cloudstorage.e2e.views.SignUpView;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

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

    @Test
    public void createEditDeleteCredentialAndVerifyContent() {

    }
}
