import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PreviewsDetailsComponent } from './previews-details.component';

describe('PreviewsDetailsComponent', () => {
  let component: PreviewsDetailsComponent;
  let fixture: ComponentFixture<PreviewsDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PreviewsDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PreviewsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
