export class ChargeRequest {
  
  public token: string;
  public paymentCurrency: string;
  public amount: number;
  public description: string;
  
  
 constructor(token: string, amount: number, paymentCurrency: string, description: string) {
      
    this.token = token;
    this.paymentCurrency = paymentCurrency;
    this.amount = amount;
    this.description = description;
      
  }
  
}