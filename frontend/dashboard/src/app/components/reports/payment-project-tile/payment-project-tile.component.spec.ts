import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentProjectTileComponent } from './payment-project-tile.component';

describe('PaymentProjectTileComponent', () => {
  let component: PaymentProjectTileComponent;
  let fixture: ComponentFixture<PaymentProjectTileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PaymentProjectTileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PaymentProjectTileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
