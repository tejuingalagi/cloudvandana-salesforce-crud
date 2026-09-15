package com.tejeshwini.salesforcecrud.controller;

import com.tejeshwini.salesforcecrud.service.SalesforceApiService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PatchMapping;

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
    @PatchMapping("/api/salesforce/accounts/{accountId}")
    public String updateAccount(
            @PathVariable  String accountId,
            @RequestParam String name,
            @RequestParam String type,
            @RequestParam String industry,
            HttpSession session)
            throws Exception {

        return salesforceApiService.updateAccount(
                accountId,
                name,
                type,
                industry,
                session);
    }
    
    @DeleteMapping("/api/salesforce/accounts/{accountId}")
    public String deleteAccount(
            @PathVariable String accountId,
            HttpSession session)
            throws Exception {

        return salesforceApiService.deleteAccount(
                accountId,
                session);
    }
    
    @GetMapping("/api/salesforce/opportunities")
    public String getOpportunities(HttpSession session)
            throws Exception {

        return salesforceApiService.getOpportunities(session);
    }
    
    @PostMapping("/api/salesforce/opportunities")
    public String createOpportunity(
            @RequestParam String name,
            @RequestParam String stageName,
            @RequestParam String closeDate,
            @RequestParam String amount,
            HttpSession session)
            throws Exception {

        return salesforceApiService.createOpportunity(
                name,
                stageName,
                closeDate,
                amount,
                session);
    }
    @PatchMapping("/api/salesforce/opportunities/{opportunityId}")
    public String updateOpportunity(

            @PathVariable String opportunityId,

            @RequestParam String name,

            @RequestParam String stageName,

            @RequestParam String closeDate,

            @RequestParam String amount,

            HttpSession session)

            throws Exception {

        return salesforceApiService.updateOpportunity(
                opportunityId,
                name,
                stageName,
                closeDate,
                amount,
                session);
    }
    
}