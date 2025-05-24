package runners;
import io.cucumber.testng.CucumberOptions;
import utils.TestBase;

@CucumberOptions(
        features = "src//test//java//features",
        glue = {"steps"},
        plugin = {"pretty", "html:target//cucumber-report.html"},
        monochrome = true
)

public class TestRunner extends TestBase {

}
