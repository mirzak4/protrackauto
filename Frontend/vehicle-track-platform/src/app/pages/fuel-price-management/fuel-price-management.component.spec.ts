import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FuelPriceManagement } from './fuel-price-management.component';

describe('FuelPriceManagementComponent', () => {
  let component: FuelPriceManagement;
  let fixture: ComponentFixture<FuelPriceManagement>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FuelPriceManagement]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FuelPriceManagement);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
