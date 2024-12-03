package com.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTest {

    // Temporary solution: Validate only the status code
    @Test
    public void testGetRequest() {
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/1");
        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch!");

        // Log the response body for debugging
        System.out.println("Response Body: " + response.getBody().asString());

        /*
         Temporary Solution:
         Currently validating only the status code.
         Future Enhancements:
         - Validate the response body contents.
         - Add handling for other status codes.
         */
    }

    // Enhanced solution: Validate status code and response body
    @Test
    public void testGetRequestWithValidation() {
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/1");
        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch!");

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("userId"), "Response does not contain 'userId'!");
        Assert.assertTrue(responseBody.contains("title"), "Response does not contain 'title'!");
        Assert.assertTrue(responseBody.contains("body"), "Response does not contain 'body'!");

        // Log the response for debugging
        System.out.println("Enhanced Response Body: " + responseBody);
    }

    // Validate all posts response
    @Test
    public void testAllPosts() {
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts");
        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch!");

        int postCount = response.getBody().jsonPath().getList("$").size();
        Assert.assertTrue(postCount > 0, "No posts retrieved!");

        // Log the number of posts for debugging
        System.out.println("Number of Posts: " + postCount);
    }

    // Temporary solution for POST request
    @Test
    public void testPostRequest() {
        Response response = RestAssured.given()
            .contentType("application/json")
            .body("{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }")
            .post("https://jsonplaceholder.typicode.com/posts");
        Assert.assertEquals(response.getStatusCode(), 201, "Status code mismatch!");

        // Log the response for debugging
        System.out.println("POST Response: " + response.getBody().asString());

        /*
         Technical Debt:
         - No error handling for different status codes.
         - Not validating response fields.
         Future Improvements:
         - Validate response body contents.
         - Handle errors for different HTTP status codes.
         */
    }
}

