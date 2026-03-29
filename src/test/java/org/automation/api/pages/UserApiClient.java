package org.automation.api.pages;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserApiClient {

    private static final String APP_ID = "63a804408eb0cb069b57e43a";
    private String baseUrl;

    public UserApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private RequestSpecification baseRequest() {
        return given()
                .header("app-id", APP_ID)
                .header("Content-Type", "application/json")
                .baseUri(baseUrl);
    }

    public Response get(String endpoint) {
        return baseRequest()
                .when()
                .get(endpoint);
    }

    public Response post(String endpoint, String body) {
        return baseRequest()
                .body(body)
                .when()
                .post(endpoint);
    }

    public Response put(String endpoint, String body) {
        return baseRequest()
                .body(body)
                .when()
                .put(endpoint);
    }

    public Response delete(String endpoint) {
        return baseRequest()
                .when()
                .delete(endpoint);
    }
}