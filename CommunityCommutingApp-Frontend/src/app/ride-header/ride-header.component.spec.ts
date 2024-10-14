import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RideHeaderComponent } from './ride-header.component';

describe('RideHeaderComponent', () => {
  let component: RideHeaderComponent;
  let fixture: ComponentFixture<RideHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RideHeaderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RideHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
