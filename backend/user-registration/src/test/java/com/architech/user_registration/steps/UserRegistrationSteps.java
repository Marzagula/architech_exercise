package com.architech.user_registration.steps;

import com.architech.user_registration.model.UserAccount;
import com.architech.user_registration.model.dtos.UserAccountDTO;
import com.architech.user_registration.model.dtos.UserRegistrationDTO;
import com.architech.user_registration.repository.UserAccountRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@ActiveProfiles("test")
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserRegistrationSteps {

    private static final String BASE_URL = "http://localhost:";
    private static final String HEALTH_ENDPOINT = "/actuator/health";
    private static final String REGISTER_USER = "/api/v1/user/register";
    @Autowired
    Environment environment;
    @Autowired
    UserAccountRepository userAccountRepository;


    @Given("The user registration service is running")
    public void theUserRegistrationServiceIsRunning() {
        RestClient restClient = RestClient.create();
        String url = BASE_URL + environment.getProperty("server.port") + HEALTH_ENDPOINT;
        try {
            ResponseEntity<String> response = restClient.get()
                    .uri(url)
                    .retrieve()
                    .toEntity(String.class);
            assertEquals(200, response.getStatusCode().value());

        } catch (RestClientException e) {
            fail("Service is not running: " + e.getMessage());
        }
    }

    @When("I create a new user with the following credentials:")
    public void iCreateANewUserWithTheFollowingCredentials(DataTable dataTable) {
        Map<String, String> userCredentials = dataTable.asMap(String.class, String.class);
        String url = BASE_URL + environment.getProperty("server.port") + REGISTER_USER;

        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO(userCredentials.get("username"), userCredentials.get("password"));

        RestClient restClient = RestClient.create();
        try {
            ResponseEntity<UserAccountDTO> response = restClient.post()
                    .uri(url)
                    .body(userRegistrationDTO)
                    .retrieve()
                    .toEntity(UserAccountDTO.class);

            assertNotNull(response.getBody().id(), "UserAccount ID should not be null");
            assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        } catch (RestClientException e) {
            fail("Failed to create user account: " + e.getMessage());
        }
    }

    @Then("the user should be added to the database with username {string}")
    public void theUserShouldBeAddedToTheDatabaseWithUsername(String username) {
        try {
            UserAccount userAccount = userAccountRepository.findByUsername(username);
            Assert.assertNotNull("UserAccount was not found in the database", userAccount);
            assertEquals(username, userAccount.getUsername());
        } catch (Exception e) {
            fail("An error occurred while verifying the UserAccount in the database: " + e.getMessage());
        }
    }


    @When("I create a duplicated user with the following credentials:")
    public void iCreateADuplicatedUserWithTheFollowingCredentials(DataTable dataTable) {
        Map<String, String> userCredentials = dataTable.asMap(String.class, String.class);
        String url = BASE_URL + environment.getProperty("server.port") + REGISTER_USER;

        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO(userCredentials.get("username"), userCredentials.get("password"));

        RestClient restClient = RestClient.create();
        try {
            ResponseEntity<UserAccountDTO> response = restClient.post()
                    .uri(url)
                    .body(userRegistrationDTO)
                    .retrieve()
                    .toEntity(UserAccountDTO.class);

            assertNotNull(response.getBody().id(), "UserAccount ID should not be null");
            assertEquals(HttpStatus.CONFLICT.value(), response.getStatusCode().value());
            fail("Failed to create duplicated user account: ");
        } catch (Exception e) {

        }
    }

    @Then("there should be only one user with username {string}")
    public void thereShouldBeOnlyOneUserWithUsername(String username) {
        try {
            List<UserAccount> userAccounts = userAccountRepository.findAll().stream()
                    .filter(userAccount -> userAccount.getUsername().equals(username))
                    .toList();
            assertEquals(1, userAccounts.size());
        } catch (Exception e) {
            fail("An error occurred while verifying the UserAccounts in the database: " + e.getMessage());
        }
    }
}
