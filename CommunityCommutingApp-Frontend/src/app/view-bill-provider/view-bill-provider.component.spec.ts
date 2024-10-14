import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewBillProviderComponent } from './view-bill-provider.component';

describe('ViewBillProviderComponent', () => {
  let component: ViewBillProviderComponent;
  let fixture: ComponentFixture<ViewBillProviderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewBillProviderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewBillProviderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
