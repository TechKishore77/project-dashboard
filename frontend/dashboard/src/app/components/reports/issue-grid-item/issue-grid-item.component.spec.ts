import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IssueGridItemComponent } from './issue-grid-item.component';

describe('IssueGridItemComponent', () => {
  let component: IssueGridItemComponent;
  let fixture: ComponentFixture<IssueGridItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IssueGridItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IssueGridItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
