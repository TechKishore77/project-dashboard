import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DrivingActivityGridItemComponent } from './driving-activity-grid-item.component';

describe('DrivingActivityGridItemComponent', () => {
  let component: DrivingActivityGridItemComponent;
  let fixture: ComponentFixture<DrivingActivityGridItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DrivingActivityGridItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DrivingActivityGridItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
