import { CartService } from '../../cart-component/cart.service';
import { Comic } from '../../comics-component/comic.model';
import { DataStorageService } from '../../shared/data-storage.service';
import { Order } from '../order.model';
import { OrderService } from '../order.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-orders-list',
  templateUrl: './orders-list.component.html',
  styleUrls: ['./orders-list.component.css']
})
export class OrdersListComponent implements OnInit {

  order: Order = 
    new Order('username',
  [ new Comic(1, 'A Test Comic', 'this is simply a test', 'http://www.innomag.no/wp-content/uploads/ai-image.jpg'),
    new Comic(2, 'A Test Comic', 'this is simply a test', 'http://www.innomag.no/wp-content/uploads/ai-image.jpg'),
    new Comic(3, 'A Test Comic', 'this is simply a test', 'http://www.innomag.no/wp-content/uploads/ai-image.jpg'),
    new Comic(4, 'A Test Comic', 'this is simply a test', 'http://www.innomag.no/wp-content/uploads/ai-image.jpg'),
    new Comic(5, 'A Test Comic', 'this is simply a test', 'http://www.innomag.no/wp-content/uploads/ai-image.jpg')]
  
  );
  
  constructor(private cartService: CartService, private dataStorageService: DataStorageService, private orderService: OrderService) { }

  ngOnInit() {
    
    this.order.comics = this.orderService.getComics();
    this.cartService.comicsChanged.subscribe((comics: Comic[]) => {
      this.order.comics = comics;
      });
    
  }

}
