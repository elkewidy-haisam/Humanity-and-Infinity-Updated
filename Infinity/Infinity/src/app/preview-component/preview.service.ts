import { Preview } from './preview.model';
import { Injectable, EventEmitter } from '@angular/core';
import { Subject } from 'rxjs';


@Injectable()
export class PreviewService {
  
  observePreviews = new EventEmitter<Preview[]>();
  previewsChanged = new Subject<Preview[]>();
  previews: Preview[] = [];
  
  
    fireBasePreviews: Preview[] = [
      new Preview('Firebase Title 1', 'Firebase Synopsis 1', 'http://www.innomag.no/wp-content/uploads/ai-image.jpg' ),
      new Preview('Firebase Title 2', 'Firebase Synopsis 2', 'http://www.innomag.no/wp-content/uploads/ai-image.jpg' ),
      new Preview('Firebase Title 3', 'Firebase Synopsis 3', 'http://www.innomag.no/wp-content/uploads/ai-image.jpg' ),
      new Preview('Firebase Title 4', 'Firebase Synopsis 4', 'http://www.innomag.no/wp-content/uploads/ai-image.jpg' ),
      new Preview('Firebase Title 5', 'Firebase Synopsis 5', 'http://www.innomag.no/wp-content/uploads/ai-image.jpg' ),
      
   ];
  
    setPreviews(previews: Preview[]) {
      
      this.previews = previews;
       console.log("Checking that the previews were actually placed there welp: " + this.previews.slice());
      // console.log("Comparing it to firebase comics: " + this.fireBaseComics.slice());
      console.log("Checking that if you fetch the comics now; you actually get them: " + this.getPreviews());
      this.previewsChanged.next(this.previews.slice());
      
    }
    
    getPreviews() {
        
        console.log("Trying to fetch the previews using the getPreviews() method: " + this.previews.slice());
        return this.previews.slice();
        
    }

    getFirebasePreviews() {
      
      return this.fireBasePreviews.slice();
      
    }
  
  
}