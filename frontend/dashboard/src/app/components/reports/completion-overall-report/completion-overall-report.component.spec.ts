import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompletionOverallReportComponent } from './completion-overall-report.component';

describe('CompletionOverallReportComponent', () => {
  let component: CompletionOverallReportComponent;
  let fixture: ComponentFixture<CompletionOverallReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CompletionOverallReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CompletionOverallReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
