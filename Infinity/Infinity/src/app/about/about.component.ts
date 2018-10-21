import { Comic } from '../comics-component/comic.model';
import { ComicService } from '../comics-component/comic.service';
import { PreviewService } from '../preview-component/preview.service';
import { Preview } from '../preview-component/preview.model';
import { DataStorageService } from '../shared/data-storage.service';
import { JQUERY_TOKEN } from '../shared/jquery.service';
import { Component, OnInit, Inject } from '@angular/core';
import { HttpEvent } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {
  
  comics: Comic[];
  previews: Preview[];
  
  comic: Comic = new Comic(6, 'Testing the Post Method', 'Testing the Post Method', 'https://www.barnesandnoble.com/blog/sci-fi-fantasy/wp-content/uploads/sites/4/2015/03/asherdark.jpg');
  

  constructor(private dataStorageService: DataStorageService, private comicService: ComicService, private httpClient: HttpClient, private previewService: PreviewService) {
  
   
  
   }

  ngOnInit() {
    
  }
  
  addComicToDatabase() {
    
    this.httpClient.post('humanity/comics/addComicToStore', this.comic, {
      
      observe: 'response',
      responseType: 'json'
      
    }).subscribe(
        res => {
          console.log(res);
        },
        err => {
          console.log("Error occured");
        }
      );

  }
  
 onSaveFirebaseComics() {
    
    this.dataStorageService.storeFirebaseComics()
      .subscribe(
      (response: HttpEvent<Object>) => {
        console.log(response);
      }
      );
    
  }
  
  onFetchFirebaseComics() {
    
    /*this.dataStorageService.getFirebaseComics();
    console.log("Fetching Firebase comics..."); */
    
    this.httpClient.get<Comic[]>('/humanity/comics/getAllComics', {
      observe: 'body',
      responseType: 'json' 
    })
    .map(
      (comics) => {
      
      console.log(comics);
      return comics;
        
      }
    )
    .subscribe(
       (comics: Comic[]) => {
         
         console.log("checking that the comics have been fetched: " + comics)
         this.comics = comics;
           
         }
     );
    
    
  }
  
   onSaveFirebasePreviews() {
    
    this.dataStorageService.storeFirebasePreviews()
      .subscribe(
      (response: HttpEvent<Object>) => {
        console.log(response);
      }
      );
    
  }
  
  onFetchFirebasePreviews() {
    
   /*  this.dataStorageService.getFirebasePreviews();
    console.log("Fetching Firebase previews..."); */
    
      this.httpClient.get<Preview[]>('/humanity/previews/allPreviews', {
      
      observe: 'body',
      responseType: 'json'
      
    })
    .map(
      (previews) => {
       
       console.log(previews);
       return previews;
        
      }
    )
    .subscribe(
       (previews: Preview[]) => {
         console.log("checking that the previews have been fetched: " + previews)
        this.previews = previews;
         
       } 
     );
    
    
    }

}
