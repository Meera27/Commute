import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeekerHeaderComponent } from './seeker-header.component';

describe('SeekerHeaderComponent', () => {
  let component: SeekerHeaderComponent;
  let fixture: ComponentFixture<SeekerHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SeekerHeaderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SeekerHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
