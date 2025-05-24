package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementActions;
import utils.PageBase;

public class LoginPage extends PageBase {
    public ElementActions Ele_Wait;

    public LoginPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);
        Ele_Wait = new ElementActions(driver);
    }

    @FindBy(name = "username")
    public WebElement userNameTxtBox;


    @FindBy(name = "password")
    public WebElement passwordTxtBox;

    @FindBy(xpath = "//button[text()=' Login ']")
    //  @FindBy(css = "button.orangehrm-login-button")
    public WebElement loginBtn;

    public void login(String userName, String password) {

        Ele_Wait.waitElementToBeVisibleAndClickable(userNameTxtBox);
        setTextElementText(userNameTxtBox, userName);
        Ele_Wait.waitElementToBeVisibleAndClickable(passwordTxtBox);
        setTextElementText(passwordTxtBox, password);
        Ele_Wait.waitElementToBeVisibleAndClickable(loginBtn);
        clickButton(loginBtn);

    }


}