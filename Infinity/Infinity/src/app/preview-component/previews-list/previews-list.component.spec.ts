import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PreviewsListComponent } from './previews-list.component';

describe('PreviewsListComponent', () => {
  let component: PreviewsListComponent;
  let fixture: ComponentFixture<PreviewsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PreviewsListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PreviewsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
