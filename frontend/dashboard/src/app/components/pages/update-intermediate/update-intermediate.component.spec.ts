import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateIntermediateComponent } from './update-intermediate.component';

describe('UpdateIntermediateComponent', () => {
  let component: UpdateIntermediateComponent;
  let fixture: ComponentFixture<UpdateIntermediateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateIntermediateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateIntermediateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
