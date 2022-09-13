import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DrivingActivityFormComponent } from './driving-activity-form.component';

describe('DrivingActivityFormComponent', () => {
  let component: DrivingActivityFormComponent;
  let fixture: ComponentFixture<DrivingActivityFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DrivingActivityFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DrivingActivityFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
