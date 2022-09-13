import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfitProjectTileComponent } from './profit-project-tile.component';

describe('ProfitProjectTileComponent', () => {
  let component: ProfitProjectTileComponent;
  let fixture: ComponentFixture<ProfitProjectTileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfitProjectTileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfitProjectTileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
