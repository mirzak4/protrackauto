import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrafficFineListComponent } from './traffic-fine-list.component';

describe('TrafficFineListComponent', () => {
  let component: TrafficFineListComponent;
  let fixture: ComponentFixture<TrafficFineListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TrafficFineListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TrafficFineListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
