import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewProjectUploadComponent } from './new-project-upload.component';

describe('NewProjectUploadComponent', () => {
  let component: NewProjectUploadComponent;
  let fixture: ComponentFixture<NewProjectUploadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewProjectUploadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewProjectUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
