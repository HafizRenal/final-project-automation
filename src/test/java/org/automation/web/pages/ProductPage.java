package org.automation.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locator diperkuat — lebih spesifik dan stabil
    private static final By PRODUCT_LIST    = By.cssSelector(".card-block .card-title a");
    private static final By ADD_TO_CART_BTN = By.cssSelector(".btn-success");
    private static final By PRODUCT_TITLE   = By.cssSelector(".name");
    private static final By PRODUCT_PRICE   = By.cssSelector(".price-container");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Thread.sleep dipindah ke dalam page class — bukan di WebSteps lagi
    public void clickCategory(String categoryName) {
        By category = By.xpath("//a[normalize-space()='" + categoryName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(category)).click();
        waitForProductsToLoad();
    }

    private void waitForProductsToLoad() {
        try { Thread.sleep(2500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    public List<WebElement> getProductList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_LIST));
        return driver.findElements(PRODUCT_LIST);
    }

    public void clickFirstProduct() {
        List<WebElement> products = getProductList();
        assertNotEmpty(products);
        products.get(0).click();
        // Tunggu halaman detail produk load
        try { Thread.sleep(1500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    public boolean isProductDetailVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
            return driver.findElement(PRODUCT_TITLE).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAddToCartVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_TO_CART_BTN));
            return driver.findElement(ADD_TO_CART_BTN).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPriceVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_PRICE));
            return driver.findElement(PRODUCT_PRICE).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(ADD_TO_CART_BTN)).click();
        // Tunggu alert muncul
        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    public String getFirstProductName() {
        List<WebElement> products = getProductList();
        assertNotEmpty(products);
        return products.get(0).getText();
    }

    private void assertNotEmpty(List<WebElement> list) {
        if (list.isEmpty()) throw new RuntimeException("Tidak ada produk yang tampil");
    }
}