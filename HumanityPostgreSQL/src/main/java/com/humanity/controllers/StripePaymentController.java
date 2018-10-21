package com.humanity.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.humanity.model.ChargeRequest;
import com.humanity.services.StripePaymentService;
import com.stripe.exception.StripeException;

@RestController
@RequestMapping("/humanity")
public class StripePaymentController {
 
    @Autowired
    private StripePaymentService paymentsService;
 
    @RequestMapping(value="/charge", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void charge(ChargeRequest charge)
      throws StripeException {
       
    	paymentsService.charge(charge);
    	
    }
 
    
}
