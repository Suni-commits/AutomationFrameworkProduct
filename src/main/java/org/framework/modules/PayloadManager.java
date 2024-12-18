package org.framework.modules;

import com.google.gson.Gson;
import java.util.*;
import org.framework.pojos.ProductDetails;
import org.framework.pojos.ProductResponse;

public class PayloadManager {
    private Gson gson = new Gson();  // Gson instance to handle JSON serialization/deserialization

    // Method to create a product payload for creating a new product
    public String createAddproduct() {
        ProductDetails productdetails = new ProductDetails();
        productdetails.setTitle("Metal");
        productdetails.setPrice(300);
        productdetails.setDescription("Metal will be very useful for daily usage.");
        productdetails.setCategoryId(1);  // Example category ID
        productdetails.setImages(Arrays.asList("https://placeimg.com/640/480/any"));

        // Convert product details to JSON string using Gson
        String prodDetails = gson.toJson(productdetails);
        System.out.println(prodDetails);  // Optional: for logging the payload

        return prodDetails;
    }

    // Method to parse the response into ProductResponse object
    public ProductResponse productresponse(String responseData) {
        return gson.fromJson(responseData, ProductResponse.class);
    }
    public String UpdatedProduct() {
        ProductDetails productdetails = new ProductDetails();
        productdetails.setTitle("Wooden");
        productdetails.setPrice(200);
        productdetails.setDescription("Wood will be very useful for furniture and house construcitons usage.");
        productdetails.setCategoryId(1);  // Example category ID
        productdetails.setImages(Arrays.asList("https://placeimg.com/640/480/any"));

        // Convert product details to JSON string using Gson
        String prodDetails = gson.toJson(productdetails);
        System.out.println(prodDetails);  // Optional: for logging the payload

        return prodDetails;
    }
}
