import { Comic } from '../comics-component/comic.model';
import { Cart } from '../cart-component/cart.model';
import { OrderService } from '../orders-component/order.service';
import { EventEmitter, Injectable, Input } from '@angular/core';

@Injectable()
export class CartService {
  
  @Input() private cart: Cart;
  private comic: Comic;

  comicsChanged = new EventEmitter<Comic[]>();
  
  private  comics: Comic[] = [
    new Comic(1, 'A Test Comic', 'this is simply a test', 'http://www.innomag.no/wp-content/uploads/ai-image.jpg'),
    new Comic(2, 'A Test Comic', 'this is simply a test', 'http://www.innomag.no/wp-content/uploads/ai-image.jpg'),
    new Comic(3, 'A Test Comic', 'this is simply a test', 'http://www.innomag.no/wp-content/uploads/ai-image.jpg'),
    new Comic(4, 'A Test Comic', 'this is simply a test', 'http://www.innomag.no/wp-content/uploads/ai-image.jpg'),
    new Comic(5, 'A Test Comic', 'this is simply a test', 'http://www.innomag.no/wp-content/uploads/ai-image.jpg')
  ];
  
  constructor (private orderService: OrderService) {}
  
  getComics() {
    return this.comics.slice();
  }
  
  addComic(comic: Comic) {
    this.comics.push(comic);
    this.comicsChanged.emit(this.cart.comics.slice());
  }
  
  addComicsToOrderHistory(comics: Comic[]) {
        
     this.orderService.addComics(comics);
        
  }
  
  setComics(comics: Comic[]) {
    
    for (let comic of comics) {
      this.addComic(comic);
    }
  }
  
  addComics(comics: Comic[]) {
    
    this.comics.push(...comics);
    this.comicsChanged.emit(this.comics.slice());
    
  }
  
  deleteComic(index: number) {
    
    this.comics.splice(index, 1);
    this.comicsChanged.next(this.comics.slice());
    
  }
  
  clearComics(cart) {
    
    this.comics.splice(0,this.comics.length);
    
  }
  
  getComicByIndex() {
    
    return this.comics.find;
    
  }
  
}


