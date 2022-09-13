import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { NgbDateParserFormatter, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgApexchartsModule } from 'ng-apexcharts';
import { FileUploadModule } from 'ng2-file-upload';
import { AccordionModule } from 'ngx-bootstrap/accordion';
import { AlertModule } from 'ngx-bootstrap/alert';
import { CarouselModule } from 'ngx-bootstrap/carousel';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ModalModule } from 'ngx-bootstrap/modal';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { SelectDropDownModule } from 'ngx-select-dropdown';
import { NgxSpinnerModule } from 'ngx-spinner';
import { HTTPListener, HTTPStatus } from 'src/app/shared/interceptors/loader.interceptor';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ForgotPasswordComponent } from './components/auth/forgot-password/forgot-password.component';
import { LoginComponent } from './components/auth/login/login.component';
import { ProfileComponent } from './components/auth/profile/profile.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { AppLoaderComponent } from './components/common/app-loader/app-loader.component';
import { ErrorPageComponent } from './components/common/error-page/error-page.component';
import { ErrorPage2Component } from './components/common/error-page2/error-page2.component';
import { FooterComponent } from './components/common/footer/footer.component';
import { HeaderComponent } from './components/common/header/header.component';
import { NotificationComponent } from './components/common/notification/notification.component';
import { OptionButtonComponent } from './components/common/option-button/option-button.component';
import { ProjectItemListComponent } from './components/common/project-item-list/project-item-list.component';
import { ProjectItemComponent } from './components/common/project-item/project-item.component';
import { DashboardComponent } from './components/dashboard/dashboard/dashboard.component';
import { FinancialSummaryComponent } from './components/dashboard/financial-summary/financial-summary.component';
import { PaymentSummaryDashboardComponent } from './components/dashboard/payment-summary-dashboard/payment-summary-dashboard.component';
import { ProjectCostsComponent } from './components/dashboard/project-costs/project-costs.component';
import { ProjectDashboardSummaryComponent } from './components/dashboard/project-dashboard-summary/project-dashboard-summary.component';
import { ProjectProgressComponent } from './components/dashboard/project-progress/project-progress.component';
import { ProjectTimelineComponent } from './components/dashboard/project-timeline/project-timeline.component';
import { AnticipatedBudgetFormComponent } from './components/forms/anticipated-budget-form/anticipated-budget-form.component';
import { BudgetComponentInputComponent } from './components/forms/budget-component-input/budget-component-input.component';
import { DrivingActivityFormComponent } from './components/forms/driving-activity-form/driving-activity-form.component';
import { EditProfileComponent } from './components/forms/edit-profile/edit-profile.component';
import { ExcelUploadComponent } from './components/forms/excel-upload/excel-upload.component';
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
import { CompletionProjectTileComponent } from './components/reports/completion-project-tile/completion-project-tile.component';
import { DrivingActivityGridItemComponent } from './components/reports/driving-activity-grid-item/driving-activity-grid-item.component';
import { DrivingActivityGridComponent } from './components/reports/driving-activity-grid/driving-activity-grid.component';
import { IssueGridItemComponent } from './components/reports/issue-grid-item/issue-grid-item.component';
import { IssuesGridComponent } from './components/reports/issues-grid/issues-grid.component';
import { OverallReportsPageComponent } from './components/reports/overall-reports-page/overall-reports-page.component';
import { PaymentProjectTileComponent } from './components/reports/payment-project-tile/payment-project-tile.component';
import { PaymentsOverallReportComponent } from './components/reports/payments-overall-report/payments-overall-report.component';
import { ProfitOverallReportComponent } from './components/reports/profit-overall-report/profit-overall-report.component';
import { ProfitProjectTileComponent } from './components/reports/profit-project-tile/profit-project-tile.component';
import { ProgressOverallReportComponent } from './components/reports/progress-overall-report/progress-overall-report.component';
import { ProgressProjectTileComponent } from './components/reports/progress-project-tile/progress-project-tile.component';
import { ProjectReportAdvancedComponent } from './components/reports/project-report-advanced/project-report-advanced.component';
import { ProjectReportSummaryComponent } from './components/reports/project-report-summary/project-report-summary.component';
import { ReportsHomeComponent } from './components/reports/reports-home/reports-home.component';
import { ReportsIntermediateComponent } from './components/reports/reports-intermediate/reports-intermediate.component';
import { SCurveComponent } from './components/reports/s-curve/s-curve.component';
import { SpiVarianceCurveComponent } from './components/reports/spi-variance-curve/spi-variance-curve.component';
import { AnticipatedBudgetSummaryComponent } from './components/summary/anticipated-budget-summary/anticipated-budget-summary.component';
import { DrivingActivityTableComponent } from './components/summary/driving-activity-table/driving-activity-table.component';
import { ProjectDetailComponent } from './components/summary/project-detail/project-detail.component';
import { CoreModule } from './core/core.module';
import { AuthGuard } from './core/providers/auth.guard';
import { EllipsisPipe } from './pipes/ellipsis.pipe';
import { NgbDatepickerFormat } from './services/ngbDatePickerFormat';
import { AuthInterceptor } from './shared/interceptors/auth.interceptor';
import { SharedModule } from './shared/shared.module';

const RxJS_Services = [HTTPListener, HTTPStatus];

@NgModule({
  declarations: [
    AppComponent,
    ProjectDetailComponent,
    ProjectItemComponent,
    ProjectItemListComponent,
    ProjectDetailFormComponent,
    HomePageComponent,
    OptionButtonComponent,
    LoginComponent,
    RegisterComponent,
    ErrorPageComponent,
    ErrorPage2Component,
    ForgotPasswordComponent,
    IssueFormComponent,
    AnticipatedBudgetFormComponent,
    OperatingBudgetFormComponent,
    ExcelUploadComponent,
    BudgetComponentInputComponent,
    DrivingActivityFormComponent,
    UploadPageComponent,
    ScheduleUpdateComponent,
    UpdatePageComponent,
    DrivingActivityTableComponent,
    AnticipatedBudgetSummaryComponent,
    NewProjectUploadComponent,
    ExistingProjectUploadComponent,
    SCurveComponent,
    IssuesGridComponent,
    DrivingActivityGridComponent,
    IssueGridItemComponent,
    EllipsisPipe,
    DrivingActivityGridItemComponent,
    DashboardComponent,
    HeaderComponent,
    FooterComponent,
    PaymentSummaryDashboardComponent,
    ProjectProgressComponent,
    ProjectCostsComponent,
    ProjectTimelineComponent,
    ProjectDashboardSummaryComponent,
    FinancialSummaryComponent,
    SpiVarianceCurveComponent,
    MonthlyPaymentSummaryComponent,
    NotificationComponent,
    DashboardComponent,
    AppLoaderComponent,
    ReportsHomeComponent,
    ProjectReportSummaryComponent,
    ProgressOverallReportComponent,
    ProfitOverallReportComponent,
    CompletionOverallReportComponent,
    PaymentsOverallReportComponent,
    ProfitProjectTileComponent,
    CompletionProjectTileComponent,
    ProgressProjectTileComponent,
    PaymentProjectTileComponent,
    UpdateIntermediateComponent,
    ReportsIntermediateComponent,
    OverallReportsPageComponent,
    ProjectReportAdvancedComponent,
    ProfileComponent,
    EditProfileComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    CarouselModule.forRoot(),
    BsDatepickerModule.forRoot(),
    AccordionModule.forRoot(),
    HttpClientModule,
    FileUploadModule,
    FontAwesomeModule,
    ReactiveFormsModule,
    AlertModule.forRoot(),
    BsDropdownModule.forRoot(),
    TabsModule.forRoot(),
    ModalModule.forRoot(),
    NgxSpinnerModule,
    SelectDropDownModule,
    NgApexchartsModule,
    NgbModule,
    SharedModule,
    CoreModule
  ],
  exports: [NgbModule],
  providers: [NgbDatepickerFormat, { provide: NgbDateParserFormatter, useClass: NgbDatepickerFormat },
    RxJS_Services,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HTTPListener,
      multi: true
    },
    AuthGuard
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
