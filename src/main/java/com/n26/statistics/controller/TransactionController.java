package com.n26.statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.n26.statistics.model.Transaction;
import com.n26.statistics.service.TransactionService;

/**
 * API for transaction requests
 *
 */
@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/transactions", method = RequestMethod.POST)
    public ResponseEntity<?> addTransaction(@RequestBody Transaction transaction){
        transactionService.addTransaction(transaction);
        return new ResponseEntity<>(HttpStatus.CREATED);
        
    }
}
