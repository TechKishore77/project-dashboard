import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ForgotPasswordComponent } from './components/auth/forgot-password/forgot-password.component';
import { LoginComponent } from './components/auth/login/login.component';
import { ProfileComponent } from './components/auth/profile/profile.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { ProjectItemListComponent } from './components/common/project-item-list/project-item-list.component';
import { DashboardComponent } from './components/dashboard/dashboard/dashboard.component';
import { AnticipatedBudgetFormComponent } from './components/forms/anticipated-budget-form/anticipated-budget-form.component';
import { DrivingActivityFormComponent } from './components/forms/driving-activity-form/driving-activity-form.component';
import { ExistingProjectUploadComponent } from './components/forms/existing-project-upload/existing-project-upload.component';
import { IssueFormComponent } from './components/forms/issue-form/issue-form.component';
import { MonthlyPaymentSummaryComponent } from './components/forms/monthly-payment-summary/monthly-payment-summary.component';
import { NewProjectUploadComponent } from './components/forms/new-project-upload/new-project-upload.component';
import { OperatingBudgetFormComponent } from './components/forms/operating-budget-form/operating-budget-form.component';
import { ProjectDetailFormComponent } from './components/forms/project-detail-form/project-detail-form.component';
import { ScheduleUpdateComponent } from './components/forms/schedule-update/schedule-update.component';
import { HomePageComponent } from './components/pages/home-page/home-page.component';
import { UpdateIntermediateComponent } from './components/pages/update-intermediate/update-intermediate.component';
import { UpdatePageComponent } from './components/pages/update-page/update-page.component';
import { UploadPageComponent } from './components/pages/upload-page/upload-page.component';
import { CompletionOverallReportComponent } from './components/reports/completion-overall-report/completion-overall-report.component';
import { DrivingActivityGridComponent } from './components/reports/driving-activity-grid/driving-activity-grid.component';
import { IssuesGridComponent } from './components/reports/issues-grid/issues-grid.component';
import { OverallReportsPageComponent } from './components/reports/overall-reports-page/overall-reports-page.component';
import { PaymentsOverallReportComponent } from './components/reports/payments-overall-report/payments-overall-report.component';
import { ProfitOverallReportComponent } from './components/reports/profit-overall-report/profit-overall-report.component';
import { ProgressOverallReportComponent } from './components/reports/progress-overall-report/progress-overall-report.component';
import { ProjectReportAdvancedComponent } from './components/reports/project-report-advanced/project-report-advanced.component';
import { ProjectReportSummaryComponent } from './components/reports/project-report-summary/project-report-summary.component';
import { ReportsHomeComponent } from './components/reports/reports-home/reports-home.component';
import { ReportsIntermediateComponent } from './components/reports/reports-intermediate/reports-intermediate.component';
import { AuthGuard } from './core/providers/auth.guard';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'forgot-password', component: ForgotPasswordComponent},
  {path: 'project-detail-form', component: ProjectDetailFormComponent, canActivate: [AuthGuard]},
  {path: 'projects', component: ProjectItemListComponent, canActivate: [AuthGuard]},
  {path: 'operating-budget', component: OperatingBudgetFormComponent, canActivate: [AuthGuard]},
  {path: 'project/:projectId/anticipated-budget-form', component: AnticipatedBudgetFormComponent, canActivate: [AuthGuard]},
  {path: 'project/:projectId/issue-form', component: IssueFormComponent, canActivate: [AuthGuard]},
  {path: 'driving-activity-form', component: DrivingActivityFormComponent, canActivate: [AuthGuard]},
  {path: '', component: HomePageComponent, canActivate: [AuthGuard]},
  {path: 'project/upload', component: UploadPageComponent, canActivate: [AuthGuard]},
  {path: 'update', component: UpdateIntermediateComponent, canActivate: [AuthGuard]},
  {path: 'project/:projectId/update', component: UpdatePageComponent, canActivate: [AuthGuard]},
  {path: 'project/:projectId/schedule-update', component: ScheduleUpdateComponent, canActivate: [AuthGuard]},
  {path: 'new-project-upload', component: NewProjectUploadComponent, canActivate: [AuthGuard]},
  {path: 'existing-project-upload', component: ExistingProjectUploadComponent, canActivate: [AuthGuard]},
  {path: 'issues-grid', component: IssuesGridComponent, canActivate: [AuthGuard]},
  {path: 'driving-activities-grid', component: DrivingActivityGridComponent, canActivate: [AuthGuard]},
  {path: 'project/:projectId/dashboard', component: DashboardComponent, canActivate: [AuthGuard]},
  {path: 'project/:projectId/monthly-payments', component: MonthlyPaymentSummaryComponent, canActivate: [AuthGuard] },
  {path: 'completion-report', component: CompletionOverallReportComponent, canActivate: [AuthGuard]},
  {path: 'profit-report', component: ProfitOverallReportComponent, canActivate: [AuthGuard]},
  {path: 'progress-report', component: ProgressOverallReportComponent, canActivate: [AuthGuard]},
  {path: 'payment-report', component: PaymentsOverallReportComponent, canActivate: [AuthGuard]},
  {path: 'project/:projectId/project-summary-report', component: ProjectReportSummaryComponent, canActivate: [AuthGuard]},
  {path: 'reports-home', component: ReportsHomeComponent, canActivate: [AuthGuard]},
  {path: 'reports-intermediate', component: ReportsIntermediateComponent, canActivate: [AuthGuard]},
  {path: 'reports-overall', component: OverallReportsPageComponent, canActivate: [AuthGuard]},
  {path: 'project/:projectId/project-report-advanced', component: ProjectReportAdvancedComponent, canActivate: [AuthGuard]},
  {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]},
  {path: '**', pathMatch: 'full', redirectTo: 'login'}
];

@NgModule({ imports: [RouterModule.forRoot(routes, { onSameUrlNavigation: 'reload' })], exports: [RouterModule] })
export class AppRoutingModule {
}
