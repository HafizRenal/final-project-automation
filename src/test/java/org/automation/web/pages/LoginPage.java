package org.automation.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final By SIGN_IN_BUTTON  = By.id("login2");
    private static final By USERNAME_FIELD  = By.id("loginusername");
    private static final By PASSWORD_FIELD  = By.id("loginpassword");
    private static final By LOGIN_BUTTON    = By.xpath("//button[text()='Log in']");
    private static final By NAVBAR_USERNAME = By.id("nameofuser");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(SIGN_IN_BUTTON)).click();
        try { Thread.sleep(1500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_FIELD))
                .sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_FIELD))
                .sendKeys(password);
    }

    public void submitLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON)).click();
        handleAlertIfPresent();
        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    public String getNavbarUsername() {
        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(NAVBAR_USERNAME));
            return driver.findElement(NAVBAR_USERNAME).getText();
        } catch (Exception e) {
            try {

                By welcomeText = By.xpath("//*[contains(text(),'Welcome')]");
                wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeText));
                return driver.findElement(welcomeText).getText();
            } catch (Exception ex) {
                return "";
            }
        }
    }

    public boolean isAlertPresent() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void handleAlertIfPresent() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            shortWait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        } catch (Exception e) {

        }
    }
}