package com.tejeshwini.salesforcecrud.controller;

import com.tejeshwini.salesforcecrud.SalesforceProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthController {

    private final SalesforceProperties salesforceProperties;

    public OAuthController(SalesforceProperties salesforceProperties) {
        this.salesforceProperties = salesforceProperties;
    }

    @GetMapping("/oauth/login")
    public String login() {

        String authorizationUrl = salesforceProperties.getAuthorizationUrl();

        return "Salesforce authorization URL: " + authorizationUrl;
    }
}