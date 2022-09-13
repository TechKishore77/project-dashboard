import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OverallReportsPageComponent } from './overall-reports-page.component';

describe('OverallReportsPageComponent', () => {
  let component: OverallReportsPageComponent;
  let fixture: ComponentFixture<OverallReportsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OverallReportsPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OverallReportsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
