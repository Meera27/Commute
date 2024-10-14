import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateRideseekerComponent } from './update-rideseeker.component';

describe('UpdateRideseekerComponent', () => {
  let component: UpdateRideseekerComponent;
  let fixture: ComponentFixture<UpdateRideseekerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateRideseekerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateRideseekerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
