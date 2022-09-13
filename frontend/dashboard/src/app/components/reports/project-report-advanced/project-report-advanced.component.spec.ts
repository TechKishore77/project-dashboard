import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectReportAdvancedComponent } from './project-report-advanced.component';

describe('ProjectReportAdvancedComponent', () => {
  let component: ProjectReportAdvancedComponent;
  let fixture: ComponentFixture<ProjectReportAdvancedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProjectReportAdvancedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjectReportAdvancedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
