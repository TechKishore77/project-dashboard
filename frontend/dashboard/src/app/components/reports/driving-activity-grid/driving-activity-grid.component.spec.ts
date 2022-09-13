import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DrivingActivityGridComponent } from './driving-activity-grid.component';

describe('DrivingActivityGridComponent', () => {
  let component: DrivingActivityGridComponent;
  let fixture: ComponentFixture<DrivingActivityGridComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DrivingActivityGridComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DrivingActivityGridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
