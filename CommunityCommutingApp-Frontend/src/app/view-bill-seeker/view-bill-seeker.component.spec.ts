import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewBillSeekerComponent } from './view-bill-seeker.component';

describe('ViewBillSeekerComponent', () => {
  let component: ViewBillSeekerComponent;
  let fixture: ComponentFixture<ViewBillSeekerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewBillSeekerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewBillSeekerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
