import { Comic } from '../comics-component/comic.model';
import { DataStorageService } from '../shared/data-storage.service';
import { Cart } from './cart.model';
import { CartService } from './cart.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cart-component',
  templateUrl: './cart-component.component.html',
  styleUrls: ['./cart-component.component.css']
})
export class CartComponentComponent implements OnInit {

  comics: Comic[];
   
  constructor(private dataStorageService: DataStorageService, private cartService: CartService) { }

  ngOnInit() {
    
    this.comics = this.cartService.getComics();
    this.cartService.comicsChanged
      .subscribe(
        (comics: Comic[]) => {
          this.comics = comics;
        }
      );
    
  }
  
  
  onComicAdded(comic: Comic) {
    this.comics.push(comic);
  }
 

}
