package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementActions;
import utils.PageBase;

public class DashboardPage extends PageBase {

    WebDriver driver;
    public ElementActions Ele_Wait;

    public DashboardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver=driver;
        Ele_Wait=new ElementActions(driver);
    }

    @FindBy(xpath = "//span[text()='Admin']")
    public WebElement adminTab;


    public void clickAdmin() {
        Ele_Wait.waitElementToBeVisibleAndClickable(adminTab);
        clickButton(adminTab);

    }





}
