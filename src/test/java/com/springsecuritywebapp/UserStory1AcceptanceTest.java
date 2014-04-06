package com.springsecuritywebapp;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * I contain end-to-end acceptance/functional tests for the simple web
 * application with spring security.
 *
 * I specifically contain tests that verify the behavior of UserStory1.
 */
public class UserStory1AcceptanceTest {

    private final WebDriver driver = new FirefoxDriver();

    @After
    public void aTearDownMethodForEachTest() {
        // close firefox browser that was launch after each test
        driver.close();
    }

    @Test
    public void shouldBeAskedToAuthenticateWhenTryingToVisitASecurePageAndNotLoggedIn() {

        driver.get("http://localhost:8085/Spring4WebApp/user");

        assertThat(driver.getTitle(),
                is(not("Home: Spring Security Web Application")));
        assertThat(driver.getTitle(),
                is("Login"));
    }
}