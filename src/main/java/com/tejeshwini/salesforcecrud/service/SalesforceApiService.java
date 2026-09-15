package com.tejeshwini.salesforcecrud.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class SalesforceApiService {

    private final HttpClient httpClient;

    public SalesforceApiService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public String getAccounts(HttpSession session)
            throws IOException, InterruptedException {

        String accessToken =
                (String) session.getAttribute("access_token");

        String instanceUrl =
                (String) session.getAttribute("instance_url");

        if (accessToken == null || instanceUrl == null) {
            throw new IllegalStateException(
                    "Salesforce authentication required. Please login first.");
        }

        String soql =
                "SELECT Id, Name, Type, Industry FROM Account LIMIT 20";

        String url =
                instanceUrl
                + "/services/data/v67.0/query/?q="
                + URLEncoder.encode(soql, StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + accessToken)
                .GET()
                .build();

        HttpResponse<String> response =
                httpClient.send(
                        request,
                        HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IllegalStateException(
                    "Salesforce API request failed. Status: "
                    + response.statusCode()
                    + ", Response: "
                    + response.body());
        }

        return response.body();
    }
    
    public String createAccount(
            String name,
            String type,
            String industry,
            HttpSession session)
            throws IOException, InterruptedException {

        String accessToken =
                (String) session.getAttribute("access_token");

        String instanceUrl =
                (String) session.getAttribute("instance_url");

        if (accessToken == null || instanceUrl == null) {
            throw new IllegalStateException(
                    "Salesforce authentication required. Please login first.");
        }

        String jsonBody =
                "{"
                + "\"Name\":\"" + name + "\","
                + "\"Type\":\"" + type + "\","
                + "\"Industry\":\"" + industry + "\""
                + "}";

        String url =
                instanceUrl
                + "/services/data/v67.0/sobjects/Account/";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response =
                httpClient.send(
                        request,
                        HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 201) {
            throw new IllegalStateException(
                    "Account creation failed. Status: "
                    + response.statusCode()
                    + ", Response: "
                    + response.body());
        }

        return response.body();
    }
    
    public String updateAccount(
            String accountId,
            String name,
            String type,
            String industry,
            HttpSession session)
            throws IOException, InterruptedException {

        String accessToken =
                (String) session.getAttribute("access_token");

        String instanceUrl =
                (String) session.getAttribute("instance_url");

        if (accessToken == null || instanceUrl == null) {
            throw new IllegalStateException(
                    "Salesforce authentication required. Please login first.");
        }

        String jsonBody =
                "{"
                + "\"Name\":\"" + name + "\","
                + "\"Type\":\"" + type + "\","
                + "\"Industry\":\"" + industry + "\""
                + "}";

        String url =
                instanceUrl
                + "/services/data/v67.0/sobjects/Account/"
                + accountId;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json")
                .method(
                        "PATCH",
                        HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response =
                httpClient.send(
                        request,
                        HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 204) {
            throw new IllegalStateException(
                    "Account update failed. Status: "
                    + response.statusCode()
                    + ", Response: "
                    + response.body());
        }

        return "{\"success\":true,\"message\":\"Account updated successfully\"}";
    }
    
    public String deleteAccount(
            String accountId,
            HttpSession session)
            throws IOException, InterruptedException {

        String accessToken =
                (String) session.getAttribute("access_token");

        String instanceUrl =
                (String) session.getAttribute("instance_url");

        if (accessToken == null || instanceUrl == null) {
            throw new IllegalStateException(
                    "Salesforce authentication required. Please login first.");
        }

        String url =
                instanceUrl
                + "/services/data/v67.0/sobjects/Account/"
                + accountId;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + accessToken)
                .DELETE()
                .build();

        HttpResponse<String> response =
                httpClient.send(
                        request,
                        HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 204) {
            throw new IllegalStateException(
                    "Account deletion failed. Status: "
                    + response.statusCode()
                    + ", Response: "
                    + response.body());
        }

        return "{\"success\":true,\"message\":\"Account deleted successfully\"}";
    }
}