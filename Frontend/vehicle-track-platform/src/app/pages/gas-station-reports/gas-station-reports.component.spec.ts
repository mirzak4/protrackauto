import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GasStationReportsComponent } from './gas-station-reports.component';

describe('GasStationReportsComponent', () => {
  let component: GasStationReportsComponent;
  let fixture: ComponentFixture<GasStationReportsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GasStationReportsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GasStationReportsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
