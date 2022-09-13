import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnticipatedBudgetFormComponent } from './anticipated-budget-form.component';

describe('AnticipatedBudgetFormComponent', () => {
  let component: AnticipatedBudgetFormComponent;
  let fixture: ComponentFixture<AnticipatedBudgetFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnticipatedBudgetFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnticipatedBudgetFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
