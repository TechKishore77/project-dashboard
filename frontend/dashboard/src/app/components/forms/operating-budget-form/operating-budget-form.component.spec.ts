import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OperatingBudgetFormComponent } from './operating-budget-form.component';

describe('OperatingBudgetFormComponent', () => {
  let component: OperatingBudgetFormComponent;
  let fixture: ComponentFixture<OperatingBudgetFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OperatingBudgetFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OperatingBudgetFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
