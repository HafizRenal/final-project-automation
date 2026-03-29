package org.automation.web.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.automation.web.pages.LoginPage;
import org.automation.web.pages.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.*;

public class WebSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductPage productPage;

    private static final String BASE_URL = "https://www.demoblaze.com/";

    @Before("@web")
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        driver      = new ChromeDriver(options);
        loginPage   = new LoginPage(driver);
        productPage = new ProductPage(driver);
    }

    @After("@web")
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Given("I open the Demoblaze homepage")
    public void iOpenTheDemoblazeHomepage() {
        driver.get(BASE_URL);
    }

    @Given("I am logged in with username {string} and password {string}")
    public void iAmLoggedIn(String username, String password) {
        driver.get(BASE_URL);
        loginPage.clickSignIn();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.submitLogin();
    }

    @When("I click on Sign in button")
    public void iClickOnSignInButton() {
        loginPage.clickSignIn();
    }

    @When("I enter login username {string} and password {string}")
    public void iEnterLoginCredentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("I submit the login form")
    public void iSubmitTheLoginForm() {
        loginPage.submitLogin();
    }

    @Then("I should see the username {string} in the navbar")
    public void iShouldSeeUsernameInNavbar(String username) {
        String navText = loginPage.getNavbarUsername();
        assertTrue(
                "Username tidak muncul di navbar. Teks navbar: '" + navText + "'",
                navText.toLowerCase().contains(username.toLowerCase())
        );
    }

    @Then("I should see an alert message")
    public void iShouldSeeAnAlertMessage() {
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        String navText = loginPage.getNavbarUsername();
        boolean loginFailed = navText == null || navText.isEmpty() || navText.isBlank();
        assertTrue(
                "Login seharusnya gagal, tapi navbar: '" + navText + "'",
                loginFailed
        );
    }

    @When("I click on {string} category")
    public void iClickOnCategory(String categoryName) {
        productPage.clickCategory(categoryName);
    }

    @Then("I should see phone products listed")
    public void iShouldSeePhoneProductsListed() {
        assertFalse("Tidak ada produk yang tampil",
                productPage.getProductList().isEmpty());
    }

    @When("I click on the first product")
    public void iClickOnTheFirstProduct() {
        productPage.clickFirstProduct();
    }

    @Then("I should see the product detail page")
    public void iShouldSeeTheProductDetailPage() {
        assertTrue("Halaman detail produk tidak tampil",
                productPage.isProductDetailVisible());
    }

    @Then("I should see an {string} button")
    public void iShouldSeeButton(String buttonName) {
        if (buttonName.equals("Add to cart")) {
            assertTrue("Tombol Add to cart tidak tampil",
                    productPage.isAddToCartVisible());
        }
    }

    @When("I click Add to cart button")
    public void iClickAddToCartButton() {
        productPage.clickAddToCart();
    }

    @Then("I should see cart confirmation")
    public void iShouldSeeCartConfirmation() {
        assertTrue("Konfirmasi cart tidak muncul",
                loginPage.isAlertPresent());
    }
}