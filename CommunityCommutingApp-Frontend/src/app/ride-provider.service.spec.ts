import { TestBed } from '@angular/core/testing';

import { RideProviderService } from './ride-provider.service';

describe('RideProviderService', () => {
  let service: RideProviderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RideProviderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
