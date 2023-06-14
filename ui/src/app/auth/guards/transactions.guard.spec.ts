import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { transactionsGuard } from './transactions.guard';

describe('transactionsGuardGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) =>
      TestBed.runInInjectionContext(() => transactionsGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
