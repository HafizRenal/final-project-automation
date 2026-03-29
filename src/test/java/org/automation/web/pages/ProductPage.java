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

    private static final By CATEGORY_PHONES  = By.xpath("//a[text()='Phones']");
    private static final By PRODUCT_LIST     = By.className("card-title");
    private static final By ADD_TO_CART_BTN  = By.xpath("//a[text()='Add to cart']");
    private static final By PRODUCT_TITLE    = By.className("name");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickCategory(String categoryName) {
        By category = By.xpath("//a[text()='" + categoryName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(category)).click();
        // Tunggu produk dimuat
        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    public List<WebElement> getProductList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_LIST));
        return driver.findElements(PRODUCT_LIST);
    }

    public void clickFirstProduct() {
        List<WebElement> products = getProductList();
        assertNotEmpty(products);
        products.get(0).click();
    }

    public boolean isProductDetailVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAddToCartVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_TO_CART_BTN));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(ADD_TO_CART_BTN)).click();
    }

    private void assertNotEmpty(List<WebElement> list) {
        if (list.isEmpty()) throw new RuntimeException("Tidak ada produk yang tampil");
    }
}