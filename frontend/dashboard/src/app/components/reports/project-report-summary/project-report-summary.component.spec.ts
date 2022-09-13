import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectReportSummaryComponent } from './project-report-summary.component';

describe('ProjectReportSummaryComponent', () => {
  let component: ProjectReportSummaryComponent;
  let fixture: ComponentFixture<ProjectReportSummaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProjectReportSummaryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjectReportSummaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
