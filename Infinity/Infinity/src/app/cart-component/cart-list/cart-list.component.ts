import { environment } from '../../../environments/environment';
import { Comic } from '../../comics-component/comic.model';
import { OrderService } from '../../orders-component/order.service';
import { DataStorageService } from '../../shared/data-storage.service';
import { Cart } from '../cart.model';
import { CartService } from '../cart.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cart-list',
  templateUrl: './cart-list.component.html',
  styleUrls: ['./cart-list.component.css']
})
export class CartListComponent implements OnInit {
  
      description = 'SCIENCE FICTION COMIC BOOKS';
      currency = 'usd';
      amount = 2000;

cart: Cart = 
    new Cart('username', 
    [ new Comic(1, 'A Test Comic', 'this is simply a test', 'http://www.innomag.no/wp-content/uploads/ai-image.jpg'),
    new Comic(2, 'A Test Comic', 'this is simply a test', 'http://www.innomag.no/wp-content/uploads/ai-image.jpg'),
    new Comic(3, 'A Test Comic', 'this is simply a test', 'http://www.innomag.no/wp-content/uploads/ai-image.jpg'),
    new Comic(4, 'A Test Comic', 'this is simply a test', 'http://www.innomag.no/wp-content/uploads/ai-image.jpg'),
    new Comic(5, 'A Test Comic', 'this is simply a test', 'http://www.innomag.no/wp-content/uploads/ai-image.jpg')]);
 
  constructor(private cartService: CartService, private dataStorageService: DataStorageService, private orderService: OrderService) { }

  ngOnInit() {
    
    this.cart.comics = this.cartService.getComics();
    this.cartService.comicsChanged.subscribe((comics: Comic[]) => {
      this.cart.comics = comics;
      });
    
   // this.dataStorageService.chargeCard(token, 2000, 'PREPARING FOR CHECKOUT', 'usd');
    
  }
  
  openCheckout() {
    
      var handler = (<any>window).StripeCheckout.configure({
      key: environment.publicKey,
      locale: 'auto',

      token: token => {
        
        console.log('charging credit card');
        console.log(this.amount);
        console.log(this.currency);
        console.log(this.description);
        console.log(token.id);
        this.dataStorageService.chargeCard(token.id, this.amount, this.description, this.currency);
        
      }
      
      
      
    });

    handler.open({
      name: 'HUMANITY',
      description: 'PREPARING FOR CHECKOUT',
      amount: 2000,
      image: 'https://stripe.com/img/documentation/checkout/marketplace.png'
    });
    
    
    
  }
  
  onProceedingtoCheckout() {
    
    this.orderService.addComics(this.cart.comics);
    this.cartService.clearComics(this.cart); 
    
  }

}
