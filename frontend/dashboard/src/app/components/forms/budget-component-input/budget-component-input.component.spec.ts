import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BudgetComponentInputComponent } from './budget-component-input.component';


describe('BudgetComponentInputComponent', () => {
  let component: BudgetComponentInputComponent;
  let fixture: ComponentFixture<BudgetComponentInputComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BudgetComponentInputComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BudgetComponentInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
