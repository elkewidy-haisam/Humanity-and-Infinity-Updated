import { DataStorageService } from '../../shared/data-storage.service';
import { Preview } from '../preview.model';
import { PreviewService } from '../preview.service';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-previews-list',
  templateUrl: './previews-list.component.html',
  styleUrls: ['./previews-list.component.css']
})
export class PreviewsListComponent implements OnInit {

  @Output() previewWasSelected = new EventEmitter<Preview>();
  
  previews: Preview[] = [];
  subscription: Subscription = new Subscription();
  
  constructor(private previewService: PreviewService, 
    private dataStorageService: DataStorageService,
     private router: Router,
   private route: ActivatedRoute) { }

  ngOnInit() {
    
    this.subscription = this.previewService.previewsChanged
      .subscribe(
        (previews : Preview[]) => {
          this.previews = previews;
        }
      );
    
    console.log("Trying to get the comics and store them in comics-list component.");
    console.log("Actual: " + this.previews);
    this.previews = this.previewService.getPreviews();
    
    
  }
  
  onPreviewSelected(preview: Preview) {
    
    
  }

}
