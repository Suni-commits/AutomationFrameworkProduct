package org.framework.base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.framework.asserts.AssertActions;
import org.framework.endpoints.APIConstants;
import org.framework.modules.PayloadManager;
import org.testng.annotations.BeforeTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BaseTest {

    // Request and response specifications
    public RequestSpecification requestSpecification;
    public Response response;
    public ValidatableResponse validatableresponse;

    // Helper classes for assertions and payload management
    public AssertActions assertActions;
    public PayloadManager payload_manager;

    // Initialize everything before the tests run
    @BeforeTest
    public void setUp() {
        // Initialize the PayloadManager and other helper classes
        payload_manager = new PayloadManager();
        assertActions = new AssertActions();

        // Set up the RequestSpecification with base URI and logging
        requestSpecification = RestAssured.given()
                .baseUri(APIConstants.Base_url)  // Base URL from APIConstants
                .contentType(ContentType.JSON)   // Content type JSON for API requests
                .log().all();                    // Log all request/response details

        // Assert that the request specification is not null
        assertThat(requestSpecification).isNotNull();
    }

    // Utility method to create a product and retrieve its ID
    public Integer getProductId() {
        // Create a product payload using PayloadManager
        String payload = payload_manager.createAddproduct();

        // Send POST request to create the product
        response = RestAssured.given(requestSpecification)
                .body(payload)
                .post(APIConstants.Create_Update_Url);  // Using the "products" endpoint

        // Extract the product ID from the response
        Integer productId = response.jsonPath().getInt("id");
        return productId;
    }
}
