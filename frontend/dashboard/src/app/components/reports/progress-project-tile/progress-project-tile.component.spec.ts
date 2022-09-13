import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProgressProjectTileComponent } from './progress-project-tile.component';

describe('ProgressProjectTileComponent', () => {
  let component: ProgressProjectTileComponent;
  let fixture: ComponentFixture<ProgressProjectTileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProgressProjectTileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProgressProjectTileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
