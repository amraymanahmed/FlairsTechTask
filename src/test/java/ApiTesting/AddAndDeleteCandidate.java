package ApiTesting;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class AddAndDeleteCandidate {


        // Base URI for mock or assumed OrangeHRM API
        static final String BASE_URL = "https://opensource-demo.orangehrmlive.com/web/index.php"; // hypothetical
        static String orangehrmcookie;
        public String candidateId;
        public static String token;

        @Test
        public void ExtractTokenWithRestAssured() {

            // Send GET request to the login page
            Response response = RestAssured.get("https://opensource-demo.orangehrmlive.com/");

            // Get full HTML content
            String html = response.getBody().asString();

            // Use regex to find the token in :token="&quot;...&quot;"
            Pattern pattern = Pattern.compile(":token=\\\"&quot;(.*?)&quot;\\\"");
            Matcher matcher = pattern.matcher(html);

            if (matcher.find()) {
                token = matcher.group(1); // Extract the token
                System.out.println("Extracted token: " + token);
                System.out.println(html);
            } else {
                System.out.println("Token not found in HTML.");
            }
        }


    @Test(priority = 1 ,dependsOnMethods = "ExtractTokenWithRestAssured")
    public static void authenticateUser() {
        RequestSpecification request = given();
        request.header("Content-Type", "application/x-www-form-urlencoded");
        request.formParam("_token", token);
        request.formParam("username", "Admin");
        request.formParam("Password", "admin123");

        Response response = request.when().post(BASE_URL + "/auth/validate");

        // Extract cookie if login succeeded
        orangehrmcookie = response.getCookie("orangehrm");
        Assert.assertNotNull(orangehrmcookie, "Session cookie 'orangehrm' must not be null");
        System.out.println("Authenticated with cookie: " + orangehrmcookie);
        System.out.println("Authenticated with cookie: " + response.asString());

    }

    @Test(priority = 1 ,dependsOnMethods = "authenticateUser")
    public void addCandidate() {
            RestAssured.baseURI = BASE_URL;

            String requestBody = "{\n" +
                    "    \"firstName\": \"amr\",\n" +
                    "    \"middleName\": \"ayman\",\n" +
                    "    \"lastName\": \"ayman\",\n" +
                    "    \"email\": \"test@gmail.com\",\n" +
                    "    \"contactNumber\": \"01298474737\",\n" +
                    "    \"keywords\": \"I am software test\",\n" +
                    "    \"comment\": \"I can do different type of testing \",\n" +
                    "    \"dateOfApplication\": \"2025-05-24\",\n" +
                    "    \"consentToKeepData\": true,\n" +
                    "    \"vacancyId\": 1\n" +
                    "}";


        Response response = given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .cookie("orangehrm", orangehrmcookie)
                .body(requestBody)
                .when()
                .post("/api/v2/recruitment/candidates")
                .then()
                .extract().response();

        candidateId = response.jsonPath().getString("id");
        System.out.println("✅ Candidate created with ID: " + candidateId);

            candidateId = response.jsonPath().getString("id");
            System.out.println("Candidate created with ID: " + response.asString());
        }

        @Test(priority = 2, dependsOnMethods = "addCandidate")
        public void deleteCandidate() {
            RestAssured.baseURI = BASE_URL;

            given()
                    .when()
                    .delete("/api/v2/recruitment/candidates" + candidateId)
                    .then();

            System.out.println("Candidate deleted with ID: " + candidateId);
        }
    }

