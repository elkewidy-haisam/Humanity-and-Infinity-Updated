import { Comic } from '../comic.model';
import { ComicService } from '../comic.service';
import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';

@Component({
  selector: 'app-comics-details',
  templateUrl: './comics-details.component.html',
  styleUrls: ['./comics-details.component.css']
})
export class ComicsDetailsComponent implements OnInit {

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

