import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnticipatedBudgetSummaryComponent } from './anticipated-budget-summary.component';

describe('AnticipatedBudgetSummaryComponent', () => {
  let component: AnticipatedBudgetSummaryComponent;
  let fixture: ComponentFixture<AnticipatedBudgetSummaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnticipatedBudgetSummaryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnticipatedBudgetSummaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
