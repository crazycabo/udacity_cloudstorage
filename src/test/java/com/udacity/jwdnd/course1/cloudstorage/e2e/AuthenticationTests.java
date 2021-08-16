package com.udacity.jwdnd.course1.cloudstorage.e2e;

import com.udacity.jwdnd.course1.cloudstorage.e2e.views.HomeView;
import com.udacity.jwdnd.course1.cloudstorage.e2e.views.LogInView;
import com.udacity.jwdnd.course1.cloudstorage.e2e.views.SignUpView;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationTests {

    @LocalServerPort
    private int port;

    private WebDriver webDriver;
    private LogInView logInView;
    private SignUpView signUpView;
    private HomeView homeView;

    @BeforeEach
    public void setUpBrowser() {
        String url = "http://localhost:" + port;

        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(url);

        logInView = new LogInView(webDriver, url);
        signUpView = new SignUpView(webDriver, url);
        homeView = new HomeView(webDriver, url);
    }

    @AfterEach
    public void tearDownBrowser() {
        webDriver.quit();
    }

    @Test
    public void verifyAccountCreationLogInAndLogOut() {
        signUpView.navigateToPage();
        signUpView.createAccount("Brian", "Smith", "bsmith", "1234");

        logInView.logIn("bsmith", "1234");

        homeView.buttonLogOut.click();
        assertTrue(webDriver.findElement(By.id("logout-msg")).isDisplayed());
    }
}
