import { CartService } from '../../cart-component/cart.service';
import { Comic } from '../comic.model';
import { ComicService } from '../comic.service';
import { Component, OnInit, Input, } from '@angular/core';

@Component({
  selector: 'app-comics-item',
  templateUrl: './comics-item.component.html',
  styleUrls: ['./comics-item.component.css']
})
export class ComicsItemComponent implements OnInit {

  @Input() comic: Comic;
  @Input() index: number;

  
  constructor(private cartService: CartService) {}
  
  ngOnInit() {
  }
  
  onAddComicToCart() {
    
    this.cartService.addComic(this.comic);
    
  }

}
