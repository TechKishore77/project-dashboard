import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MonthlyPaymentSummaryComponent } from './monthly-payment-summary.component';

describe('MonthlyPaymentSummaryComponent', () => {
  let component: MonthlyPaymentSummaryComponent;
  let fixture: ComponentFixture<MonthlyPaymentSummaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MonthlyPaymentSummaryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MonthlyPaymentSummaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
