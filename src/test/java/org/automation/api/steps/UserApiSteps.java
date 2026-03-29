package org.automation.api.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.automation.api.pages.UserApiClient;

import static org.junit.Assert.*;

public class UserApiSteps {

    private UserApiClient apiClient;
    private Response response;

    @Given("the API base URL is {string}")
    public void theApiBaseUrlIs(String baseUrl) {
        apiClient = new UserApiClient(baseUrl);
    }

    @When("I send GET request to {string}")
    public void iSendGetRequestTo(String endpoint) {
        response = apiClient.get(endpoint);
    }

    @When("I send POST request to {string} with body:")
    public void iSendPostRequestWithBody(String endpoint, String body) {
        response = apiClient.post(endpoint, body);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatus) {
        assertEquals(
                "Expected status " + expectedStatus + " but got " + response.statusCode(),
                expectedStatus,
                response.statusCode()
        );
    }

    @Then("the response should contain field {string}")
    public void theResponseShouldContainField(String fieldName) {
        assertNotNull(
                "Field '" + fieldName + "' tidak ditemukan di response",
                response.jsonPath().get(fieldName)
        );
    }

    @Then("the response should contain a list of tags")
    public void theResponseShouldContainListOfTags() {
        assertNotNull("Response tidak mengandung data",
                response.jsonPath().getList("data"));
        assertFalse("List tags kosong",
                response.jsonPath().getList("data").isEmpty());
    }

    // BARU: validasi nilai spesifik dari field response
    @Then("the response field {string} should have value {string}")
    public void theResponseFieldShouldHaveValue(String field, String expectedValue) {
        String actualValue = response.jsonPath().getString(field);
        assertEquals(
                "Field '" + field + "' expected '" + expectedValue + "' but got '" + actualValue + "'",
                expectedValue,
                actualValue
        );
    }

    // BARU: validasi response time agar API tidak terlalu lambat
    @Then("the response time should be less than {int} milliseconds")
    public void theResponseTimeShouldBeLessThan(int maxMs) {
        long actualTime = response.getTime();
        assertTrue(
                "Response terlalu lambat: " + actualTime + "ms, batas maksimal: " + maxMs + "ms",
                actualTime < maxMs
        );
    }

    // BARU: validasi jumlah data dalam list
    @Then("the response data list should not be empty")
    public void theResponseDataListShouldNotBeEmpty() {
        assertNotNull("Field 'data' tidak ada", response.jsonPath().getList("data"));
        assertFalse("List data kosong", response.jsonPath().getList("data").isEmpty());
    }
}