package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementActions {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor to initialize WebDriver and WebDriverWait
    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public void waitElementToBeVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            System.out.println("Failed to wait for element to be visible: " + e.getMessage());
        }
    }

    public void waitElementToBeVisibleAndClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(element),
                    ExpectedConditions.elementToBeClickable(element)
            ));
        } catch (Exception e) {
            System.out.println("Failed to wait for element to be visible and clickable: " + e.getMessage());
        }
    }

}
