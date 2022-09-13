import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfitOverallReportComponent } from './profit-overall-report.component';

describe('ProfitOverallReportComponent', () => {
  let component: ProfitOverallReportComponent;
  let fixture: ComponentFixture<ProfitOverallReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfitOverallReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfitOverallReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
