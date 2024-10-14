import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RideseekerListComponent } from './rideseeker-list.component';

describe('RideseekerListComponent', () => {
  let component: RideseekerListComponent;
  let fixture: ComponentFixture<RideseekerListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RideseekerListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RideseekerListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
