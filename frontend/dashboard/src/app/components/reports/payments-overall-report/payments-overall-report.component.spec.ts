import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentsOverallReportComponent } from './payments-overall-report.component';

describe('PaymentsOverallReportComponent', () => {
  let component: PaymentsOverallReportComponent;
  let fixture: ComponentFixture<PaymentsOverallReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PaymentsOverallReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PaymentsOverallReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
