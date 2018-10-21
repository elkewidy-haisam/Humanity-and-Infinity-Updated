package com.humanity.model;

import java.io.Serializable;

public class ChargeRequest implements Serializable{
 

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2039118784970115828L;
	
	public enum Currency {
        EUR, USD;
    }
    
    private String description;
    private int amount;
    private String paymentCurrency;
    private String stripeEmail;
    private String token;
    
    public ChargeRequest() {
    	super();
    	
    	
    }
    
	public ChargeRequest(String description, int amount, String paymentCurrency, String stripeEmail, String token) {
		super();
		this.description = description;
		this.amount = amount;
		this.paymentCurrency = paymentCurrency;
		this.stripeEmail = stripeEmail;
		this.token = token;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((paymentCurrency == null) ? 0 : paymentCurrency.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((stripeEmail == null) ? 0 : stripeEmail.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChargeRequest other = (ChargeRequest) obj;
		if (amount != other.amount)
			return false;
		if (paymentCurrency != other.paymentCurrency)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (stripeEmail == null) {
			if (other.stripeEmail != null)
				return false;
		} else if (!stripeEmail.equals(other.stripeEmail))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getPaymentCurrency() {
		return paymentCurrency;
	}

	public void setPaymentCurrency(String paymentCurrency) {
		this.paymentCurrency = paymentCurrency;
	}

	public String getStripeEmail() {
		return stripeEmail;
	}

	public void setStripeEmail(String stripeEmail) {
		this.stripeEmail = stripeEmail;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
    
    
    
}
