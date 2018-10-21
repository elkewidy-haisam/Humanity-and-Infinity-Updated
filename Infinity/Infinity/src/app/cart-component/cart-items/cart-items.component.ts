import { Comic } from '../../comics-component/comic.model';
import { CartService } from '../cart.service';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-cart-items',
  templateUrl: './cart-items.component.html',
  styleUrls: ['./cart-items.component.css']
})
export class CartItemsComponent implements OnInit {

  @Input() comic: Comic;
  @Input() index: number;
  
  @Output() comicSelected = new EventEmitter<void>();
  
  constructor(private cartService: CartService) { }

  ngOnInit() {
  }
  
  onSelected() {
    this.comicSelected.emit();
  }
  
  onDelete() {
    
    this.cartService.deleteComic(this.index);
    
  }

}
