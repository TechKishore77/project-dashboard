import { TestBed } from '@angular/core/testing';

import { DrivingActivityService } from './driving-activity.service';

describe('DrivingActivityService', () => {
  let service: DrivingActivityService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DrivingActivityService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
