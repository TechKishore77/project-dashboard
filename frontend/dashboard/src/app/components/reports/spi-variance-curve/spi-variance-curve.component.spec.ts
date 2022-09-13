import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpiVarianceCurveComponent } from './spi-variance-curve.component';

describe('SpiVarianceCurveComponent', () => {
  let component: SpiVarianceCurveComponent;
  let fixture: ComponentFixture<SpiVarianceCurveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SpiVarianceCurveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SpiVarianceCurveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
