import { Comic } from '../../comics-component/comic.model';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {

  @Input() comic: Comic;
  @Input() index: number;
  @Output() comicSelected = new EventEmitter<void>();
  
  constructor() { }

  ngOnInit() {
    
  }
  
  onSelected() {
    this.comicSelected.emit();
  }

}
