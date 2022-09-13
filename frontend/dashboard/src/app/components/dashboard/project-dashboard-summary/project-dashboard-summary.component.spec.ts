import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectDashboardSummaryComponent } from './project-dashboard-summary.component';

describe('ProjectDashboardSummaryComponent', () => {
  let component: ProjectDashboardSummaryComponent;
  let fixture: ComponentFixture<ProjectDashboardSummaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProjectDashboardSummaryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjectDashboardSummaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
