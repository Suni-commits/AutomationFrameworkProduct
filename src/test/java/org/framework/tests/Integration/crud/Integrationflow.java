package org.framework.tests.Integration.crud;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.framework.base.BaseTest;
import org.framework.endpoints.APIConstants;
import org.framework.pojos.ProductResponse;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class Integrationflow extends BaseTest {
@Owner("Suneetha")
    @Description("TC#01 : verifying the creation of product details")
    @Test(groups="Integration", priority=1)
    public void test_verifyCreateProduct(ITestContext context) {
        // Ensure the payload_manager is properly initialized
        if (payload_manager == null) {
            throw new IllegalStateException("PayloadManager not initialized.");
        }

        // Send the POST request to create the product
        response = RestAssured.given(requestSpecification)
                .when()
                .body(payload_manager.createAddproduct())
                .post(APIConstants.Create_Update_Url);  // POST to the 'products' endpoint

        // Log the response and validate the status code
        validatableresponse = response.then().log().all();
        validatableresponse.statusCode(201);

        // Parse the response into ProductResponse object
        ProductResponse productResponse = payload_manager.productresponse(response.asString());

        // Store the product ID in the ITestContext for further use in other tests
        context.setAttribute("productid", productResponse.getId());
    }
    @Owner("Suneetha")
    @Description("TC#03 : verifying the product details are updating properly")
    @Test(groups="Integration",priority=3)
    public void test_verifyUpdateProduct(ITestContext context) {
        // Retrieve the product ID from the context
        Integer productId = (Integer) context.getAttribute("productid");


            // Prepare the updated product payload using UpdatedProduct method
            String updatedProductPayload = payload_manager.UpdatedProduct(); // Use your updated product method

            // Send PUT request to update the product
            response = RestAssured.given(requestSpecification)
                    .basePath(APIConstants.Create_Update_Url + "/" + productId)
                    .body(updatedProductPayload) // Updated payload
                    .put();

            // Validate the response for successful update
            validatableresponse = response.then().log().all();
            validatableresponse.statusCode(200);  // Ensure update is successful
        }
    @Owner("Suneetha")
    @Description("TC#02 : verifying the product details are fecthing")
        @Test(groups="Integration",priority=2)
        public void test_verifySingleProductDetails(ITestContext context){
        Integer productId=(Integer)context.getAttribute("productid");
            response = RestAssured.given(requestSpecification)
                    .basePath(APIConstants.Create_Update_Url + "/" + productId)
                    .get();

            validatableresponse = response.then().log().all();
            validatableresponse.statusCode(200);

        }
    @Owner("Suneetha")
    @Description("TC#04 : verifying the product details are deleting properly")
        @Test(groups="Integration",priority=4)
        public void test_DeleteProduct(ITestContext context){
            Integer productId=(Integer)context.getAttribute("productid");
            response = RestAssured.given(requestSpecification)
                    .basePath(APIConstants.Create_Update_Url + "/" + productId)
                    .delete();

            validatableresponse = response.then().log().all();
            validatableresponse.statusCode(200);
    }

    }

