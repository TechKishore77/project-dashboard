import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExistingProjectUploadComponent } from './existing-project-upload.component';

describe('ExistingProjectUploadComponent', () => {
  let component: ExistingProjectUploadComponent;
  let fixture: ComponentFixture<ExistingProjectUploadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExistingProjectUploadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExistingProjectUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
