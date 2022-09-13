import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DrivingActivityTableComponent } from './driving-activity-table.component';

describe('DrivingActivityTableComponent', () => {
  let component: DrivingActivityTableComponent;
  let fixture: ComponentFixture<DrivingActivityTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DrivingActivityTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DrivingActivityTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
