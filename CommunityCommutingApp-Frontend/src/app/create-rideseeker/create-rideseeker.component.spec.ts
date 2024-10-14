import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateRideseekerComponent } from './create-rideseeker.component';

describe('CreateRideseekerComponent', () => {
  let component: CreateRideseekerComponent;
  let fixture: ComponentFixture<CreateRideseekerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateRideseekerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateRideseekerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
