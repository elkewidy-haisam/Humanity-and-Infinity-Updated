import { DataStorageService } from '../shared/data-storage.service';
import { Comic } from './comic.model';
import { ComicService } from './comic.service';
import { Component, OnInit } from '@angular/core';
import { Response } from '@angular/http';

@Component({
  selector: 'app-comics-component',
  templateUrl: './comics-component.component.html',
  styleUrls: ['./comics-component.component.css'],
})
export class ComicsComponentComponent implements OnInit {

  comics: Comic[];
  selectedComic: Comic;
  
  constructor(private comicService: ComicService, private dataStorageService: DataStorageService) {}

  ngOnInit() {
    
    this.comicService.comicSelected
      .subscribe((comic: Comic) => {
      this.selectedComic = comic;
    });
    
    console.log("Trying to get the comics and storing them into the main component: " + this.comicService.getComics());
    console.log("What is actually being stored in the comics here: " + this.comics);
    this.comics = this.comicService.getComics();
    
  }
  


}
