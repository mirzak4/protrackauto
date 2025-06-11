import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrafficFineFormComponent } from './traffic-fine-form.component';

describe('TrafficFineFormComponent', () => {
  let component: TrafficFineFormComponent;
  let fixture: ComponentFixture<TrafficFineFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TrafficFineFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TrafficFineFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
