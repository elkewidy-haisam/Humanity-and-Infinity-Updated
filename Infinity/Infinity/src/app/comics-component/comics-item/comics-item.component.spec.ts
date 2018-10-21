import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ComicsItemComponent } from './comics-item.component';

describe('ComicsItemComponent', () => {
  let component: ComicsItemComponent;
  let fixture: ComponentFixture<ComicsItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ComicsItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ComicsItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
