package com.tejeshwini.salesforcecrud.controller;

import com.tejeshwini.salesforcecrud.service.SalesforceApiService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}