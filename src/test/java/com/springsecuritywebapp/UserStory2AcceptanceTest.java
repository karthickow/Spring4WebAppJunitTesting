package com.springsecuritywebapp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsNot;
import org.hamcrest.core.StringContains;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * I contain end-to-end acceptance/functional tests for the simple web
 * application with spring security.
 *
 * I specifically contain tests that verify the behaviour of UserStory 2.
 */
public class UserStory2AcceptanceTest {

    private final WebDriver driver = new FirefoxDriver();

    @After
    public void aTearDownMethodForEachTest() {
        // close firefox browser that was launched after each test
        driver.close();
    }

    //@Test
    public void shouldBeAbleToViewHomePageWhenSuccessfullyAuthenticated() {

        // try to get to home.htm
        driver.get("http://localhost:8085/Spring4WebApp/user");

        // check we are on springs login page
        assertThat(driver.getTitle(), is("Login"));

        final WebElement usernameField = driver.findElement(By.name("j_username"));
        usernameField.sendKeys("kaviya");

        final WebElement passwordField = driver.findElement(By.name("j_password"));
        passwordField.sendKeys("user@123");

        passwordField.submit();

        // state verification
        assertThat(driver.getTitle(), is("User"));
    }
    
   // @Test
    public void shouldRemainOnLoginPageWithInformativeMessageWhenAuthenticationFailsDueToIncorrectPassword() {

        // try to get to home.htm
        driver.get("http://localhost:8085/Spring4WebApp/login-form");

        final WebElement usernameField = driver.findElement(By.name("j_username"));
        usernameField.sendKeys("karthick");

        final WebElement passwordField = driver.findElement(By.name("j_password"));
        passwordField.sendKeys("user@123");

        passwordField.submit();

        // state verification
        assertThat(driver.getTitle(), is("Login"));

        final WebElement informationMessageSection = driver.findElement(By.id("infomessage"));
        assertThat(informationMessageSection.getText(), StringContains.containsString("Your login attempt was not successful, try again."));
    }

   // @Test
    public void shouldRemainOnLoginPageWithInformativeMessageWhenAuthenticationFailsDueToIncorrectUsername() {

        // try to get to home.htm
        driver.get("http://localhost:8085/Spring4WebApp/login-form");

        final WebElement usernameField = driver.findElement(By.name("j_username"));
        usernameField.sendKeys("kavi");

        final WebElement passwordField = driver.findElement(By.name("j_password"));
        passwordField.sendKeys("admin@123");

        passwordField.submit();

        // state verification
        assertThat(driver.getTitle(), is("Login"));

        final WebElement informationMessageSection = driver.findElement(By.id("infomessage"));
        assertThat(informationMessageSection.getText(), StringContains.containsString("Your login attempt was not successful, try again."));
    }

    //@Test
    public void shouldRemainOnLoginPageWithUsernameStillPopulatedWhenAuthenticationFails() {

        // try to get to home.htm
        driver.get("http://localhost:8085/Spring4WebApp/login-form");

        final WebElement usernameField = driver.findElement(By.name("j_username"));
        usernameField.sendKeys("karthick");
        
        String userName = usernameField.getAttribute("value");

        final WebElement passwordField = driver.findElement(By.name("j_password"));
        passwordField.sendKeys("user@123");

        passwordField.submit();

        usernameField.sendKeys(userName);
        
        // state verification
        assertThat(driver.getTitle(), is("Login"));

        assertThat(userName, StringContains.containsString("karthick"));       
    }
    
    @Test
    public void shouldForwardUserToPageTheyAttemptedToReachPreviouslyAfterSuccessfulLogin() {

        // try to access admin
        driver.get("http://localhost:8085/Spring4WebApp/admin");

        // login on login page
        final WebElement usernameField = driver.findElement(By.name("j_username"));
        usernameField.sendKeys("karthick");

        final WebElement passwordField = driver.findElement(By.name("j_password"));
        passwordField.sendKeys("admin@123");

        passwordField.submit();

        // verify we are not at admin and not (home)
        assertThat(driver.getTitle(), is(IsNot.not("Home")));
        assertThat(driver.getTitle(), StringContains.containsString(("Admin")));

    }
}
