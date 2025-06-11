import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DriverFinesComponent } from './driver-fines.component';

describe('DriverFinesComponent', () => {
  let component: DriverFinesComponent;
  let fixture: ComponentFixture<DriverFinesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DriverFinesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DriverFinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
