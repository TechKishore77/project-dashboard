import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentSummaryDashboardComponent } from './payment-summary-dashboard.component';

describe('PaymentSummaryDashboardComponent', () => {
  let component: PaymentSummaryDashboardComponent;
  let fixture: ComponentFixture<PaymentSummaryDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PaymentSummaryDashboardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PaymentSummaryDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
