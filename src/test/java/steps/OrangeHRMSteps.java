package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.AdminPage;
import pages.DashboardPage;
import pages.LoginPage;
import utils.Constants;
import utils.TestBase;

import static utils.TestBase.driver;

public class OrangeHRMSteps extends TestBase {
    public LoginPage loginPage = new LoginPage(driver);
    public DashboardPage dashboardPage = new DashboardPage(driver);
    public AdminPage adminPage = new AdminPage(driver);

    int initialCount, finalCount;
    String newUsername = "testuser11";

    @Given("user is on login page")
    public void user_is_on_login_page() {

        driver.get(Constants.orangeHrmUrl);

    }

    @When("user enters username and password and click on Login button")
    public void userEntersUsernameAndPasswordAndClickOnLoginButton() {

        loginPage.login("Admin", "admin123");
    }

    @Then("user navigates to Admin tab")
    public void user_navigates_to_admin_tab() {

        dashboardPage.clickAdmin();

    }

    @Then("user notes the current number of records")
    public void user_notes_the_current_number_of_records() {

     initialCount = adminPage.getUserCount();

    }

    @Then("user adds a new user")
    public void user_adds_a_new_user() {

         adminPage.addUser("ESS", "Amelia  Brown", newUsername, "Enabled", "Password@123");

    }

    @Then("the number of records should increase by {int}")
    public void the_number_of_records_should_increase_by(Integer int1) {


          finalCount = adminPage.getUserCount();
          Assert.assertEquals(initialCount + 1, finalCount);

    }

    @When("user searches for the new user")
    public void user_searches_for_the_new_user() {

        adminPage.searchUser(newUsername);

    }

    @When("user deletes the new user")
    public void user_deletes_the_new_user() {

        adminPage.deleteNewUser();

    }

    @Then("the number of records should decrease by {int}")
    public void the_number_of_records_should_decrease_by(Integer int1) {


         int afterDeleteCount = adminPage.getUserCount();
         Assert.assertEquals(initialCount, afterDeleteCount);

    }

}
