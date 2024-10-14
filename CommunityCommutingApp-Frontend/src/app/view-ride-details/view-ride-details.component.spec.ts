import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewRideDetailsComponent } from './view-ride-details.component';

describe('ViewRideDetailsComponent', () => {
  let component: ViewRideDetailsComponent;
  let fixture: ComponentFixture<ViewRideDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewRideDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewRideDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
