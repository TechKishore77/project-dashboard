import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProgressOverallReportComponent } from './progress-overall-report.component';

describe('ProgressOverallReportComponent', () => {
  let component: ProgressOverallReportComponent;
  let fixture: ComponentFixture<ProgressOverallReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProgressOverallReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProgressOverallReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
