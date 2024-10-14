import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateRideproviderComponent } from './update-rideprovider.component';

describe('UpdateRideproviderComponent', () => {
  let component: UpdateRideproviderComponent;
  let fixture: ComponentFixture<UpdateRideproviderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateRideproviderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateRideproviderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
