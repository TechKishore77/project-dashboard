import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportsIntermediateComponent } from './reports-intermediate.component';

describe('ReportsIntermediateComponent', () => {
  let component: ReportsIntermediateComponent;
  let fixture: ComponentFixture<ReportsIntermediateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReportsIntermediateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportsIntermediateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
