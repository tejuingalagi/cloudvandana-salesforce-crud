package com.tejeshwini.salesforcecrud.controller;

import com.tejeshwini.salesforcecrud.service.SalesforceApiService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class SalesforceApiController {

    private final SalesforceApiService salesforceApiService;

    public SalesforceApiController(
            SalesforceApiService salesforceApiService) {
        this.salesforceApiService = salesforceApiService;
    }

    @GetMapping("/api/salesforce/accounts")
    public String getAccounts(HttpSession session)
            throws Exception {

        return salesforceApiService.getAccounts(session);
    }
    
    @PostMapping("/api/salesforce/accounts")
    public String createAccount(
            @RequestParam String name,
            @RequestParam String type,
            @RequestParam String industry,
            HttpSession session)
            throws Exception {

        return salesforceApiService.createAccount(
                name,
                type,
                industry,
                session);
    }
}