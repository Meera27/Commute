import { TestBed } from '@angular/core/testing';

import { RideseekerService } from './rideseeker.service';

describe('RideseekerService', () => {
  let service: RideseekerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RideseekerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
