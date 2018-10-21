
import { DataStorageService } from '../../shared/data-storage.service';
import { Comic } from '../comic.model';
import { Component, OnInit, EventEmitter, Output, Input, OnDestroy } from '@angular/core';
import { ComicService } from '../comic.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-comics-list',
  templateUrl: './comics-list.component.html',
  styleUrls: ['./comics-list.component.css']
})
export class ComicsListComponent implements OnInit, OnDestroy {
  
  comics: Comic[];
  subscription: Subscription = new Subscription();
  
  
  constructor(private comicService: ComicService, 
    private dataStorageService: DataStorageService,
     private router: Router,
   private route: ActivatedRoute) {
  
    
    
      
   }

  ngOnInit() {
   
    this.subscription = this.comicService.comicsChanged
      .subscribe(
        (comics : Comic[]) => {
          this.comics = comics;
        }
      );
    
    console.log("Trying to get the comics and store them in comics-list component.");
    console.log("Actual: " + this.comics);
    this.comics = this.comicService.getComics();
    
  }
  
  onNewComic() {
    this.router.navigate(['new'], {relativeTo: this.route});
  }
  
  onComicSelected(comic: Comic) {
    
    
  }
  
  ngOnDestroy() {
    
    this.subscription.unsubscribe();
  } 

}
