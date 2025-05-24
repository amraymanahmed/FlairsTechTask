package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementActions;
import utils.PageBase;
import java.time.Duration;


public class AdminPage extends PageBase {
    WebDriver driver;
    public ElementActions Ele_Wait;

    public AdminPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);
        Ele_Wait = new ElementActions(driver);
        this.driver = driver;
    }


    @FindBy(xpath = "//button[text()=' Add ']")
    public WebElement addButton;

    @FindBy(xpath = "//label[text()='User Role']/following::div[1]")
    public WebElement userRoleDropdown;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    public WebElement employeeName;

    @FindBy(xpath = "//label[text()='Username']/following::input[1]")
    public WebElement username;


    @FindBy(xpath = "//label[text()='Status']/following::div[1]")
    public WebElement statusDropdown;

    @FindBy(xpath = "//label[text()='Password']/following::input[1]")
    public WebElement password;

    @FindBy(xpath = "//label[text()='Confirm Password']/following::input[1]")
    public WebElement confirmPassword;


    @FindBy(xpath = "//button[text()=' Save ']")
    public WebElement saveButton;

    @FindBy(css = "div.orangehrm-horizontal-padding.orangehrm-vertical-padding")
    public WebElement recordsFoundLabel;

    @FindBy(xpath = "//label[text()='Username']/following::input[1]")
    public WebElement searchBox;

    @FindBy(xpath = "//button[text()=' Search ']")
    public WebElement searchButton;

    @FindBy(xpath = "//button[text()=' Reset ']")
    public WebElement resetButton;


    @FindBy(css = "i.oxd-icon.bi-trash")
    public WebElement deleteButton;

    @FindBy(xpath = "//button[text()=' Yes, Delete ']")
    public WebElement confirmDelete;


    public void addUser(String role, String empName,
                        String uname, String status, String pwd) {

        Ele_Wait.waitElementToBeVisibleAndClickable(addButton);
        clickButton(addButton);
        Ele_Wait.waitElementToBeVisible(userRoleDropdown);
        selectFromDropDownList(userRoleDropdown,role);
        Ele_Wait.waitElementToBeVisibleAndClickable(employeeName);
        searchAndSelectFromList(employeeName,empName);
        Ele_Wait.waitElementToBeVisibleAndClickable(statusDropdown);
        selectFromDropDownList(statusDropdown,status);
        Ele_Wait.waitElementToBeVisibleAndClickable(username);
        setTextElementText(username, uname);
        Ele_Wait.waitElementToBeVisibleAndClickable(password);
        setTextElementText(password, pwd);
        Ele_Wait.waitElementToBeVisibleAndClickable(confirmPassword);
        setTextElementText(confirmPassword, pwd);
        Ele_Wait.waitElementToBeVisibleAndClickable(saveButton);
        clickButton(saveButton);

    }

    public int getUserCount() {

        Ele_Wait.waitElementToBeVisible(recordsFoundLabel);
        String text = recordsFoundLabel.getText();
        String numberOnly = text.replaceAll("[^0-9]", "");
        return Integer.parseInt(numberOnly);

    }

    public void searchUser(String uname) {
        Ele_Wait.waitElementToBeVisibleAndClickable(searchBox);
        clearTextBox(searchBox);
        setTextElementText(searchBox, uname);
        clickButton(searchButton);

    }

    public void deleteNewUser() {

       // searchUser(uname);
        scrollToBottom();
        Ele_Wait.waitElementToBeVisibleAndClickable(deleteButton);
        clickButton(deleteButton);
        Ele_Wait.waitElementToBeVisibleAndClickable(confirmDelete);
        clickButton(confirmDelete);
        scrollToUp();
        Ele_Wait.waitElementToBeVisibleAndClickable(resetButton);
        clickButton(resetButton);
    }

    public void selectFromDropDownList(WebElement element, String value) {
        clickButton(element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='listbox']//span[text()='" + value + "']")));
        option.click();
    }

    public void searchAndSelectFromList(WebElement element, String value) {
        clickButton(element);
        setTextElementText(element,value);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='listbox']//span[text()='" + value + "']")));
        option.click();
    }
}
