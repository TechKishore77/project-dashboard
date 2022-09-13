import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IssuesGridComponent } from './issues-grid.component';

describe('IssuesGridComponent', () => {
  let component: IssuesGridComponent;
  let fixture: ComponentFixture<IssuesGridComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IssuesGridComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IssuesGridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
