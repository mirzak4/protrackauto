import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleFinesComponent } from './vehicle-fines.component';

describe('VehicleFinesComponent', () => {
  let component: VehicleFinesComponent;
  let fixture: ComponentFixture<VehicleFinesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VehicleFinesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VehicleFinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
