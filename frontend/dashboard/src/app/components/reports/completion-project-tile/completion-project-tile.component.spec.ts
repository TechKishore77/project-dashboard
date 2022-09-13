import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompletionProjectTileComponent } from './completion-project-tile.component';

describe('CompletionProjectTileComponent', () => {
  let component: CompletionProjectTileComponent;
  let fixture: ComponentFixture<CompletionProjectTileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CompletionProjectTileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CompletionProjectTileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
