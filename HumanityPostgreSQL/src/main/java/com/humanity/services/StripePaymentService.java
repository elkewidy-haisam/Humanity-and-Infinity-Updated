package com.humanity.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.humanity.model.ChargeRequest;

@Service
public class StripePaymentService {
 
    
    private String secretKey = System.getenv("STRIPE_SECRET_KEY");
     
    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
        
    }
    
    public Charge charge(ChargeRequest charge) 
    	      throws AuthenticationException, InvalidRequestException,
    	        APIConnectionException, CardException, APIException {
    	
    			System.out.println(charge);
    			System.out.println(charge.getAmount());
    			System.out.println(charge.getPaymentCurrency());
    			System.out.println(charge.getDescription());
    			System.out.println(charge.getStripeEmail());
    			System.out.println(charge.getToken());
    			System.out.println(charge.getStripeEmail());
    	
    	        Map<String, Object> chargeParams = new HashMap<>();
    	        chargeParams.put("amount", charge.getAmount());
    	        chargeParams.put("currency", charge.getPaymentCurrency());
    	        chargeParams.put("description", charge.getDescription());
    	        chargeParams.put("source", charge.getToken());
    	        chargeParams.put("email", charge.getStripeEmail());
    	        
    	        return Charge.create(chargeParams);
    }
}