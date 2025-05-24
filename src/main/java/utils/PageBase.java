package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PageBase {
    private WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void clickButton(WebElement button) {
        try {
            button.click();
        } catch (Exception e) {
            System.out.println("Failed to click button: " + e.getMessage());
        }
    }

    protected void setTextElementText(WebElement textElement, String value) {
        try {
            textElement.sendKeys(value);
        } catch (Exception e) {
            System.out.println("Failed to set text: " + e.getMessage());
        }
    }

    protected void clearTextBox(WebElement webElement){

        webElement.clear();
    }

    protected void submitTextBox(WebElement webElement){

        webElement.submit();
    }

    public void scrollToBottom()

    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,2500)");
    }

    public void scrollToUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,-2500)");
    }

    protected void scrollToElement(WebElement element){

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

}
