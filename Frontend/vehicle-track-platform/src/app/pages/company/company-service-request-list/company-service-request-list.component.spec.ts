import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompanyServiceRequestListComponent } from './company-service-request-list.component';

describe('CompanyServiceRequestListComponent', () => {
  let component: CompanyServiceRequestListComponent;
  let fixture: ComponentFixture<CompanyServiceRequestListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CompanyServiceRequestListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CompanyServiceRequestListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
