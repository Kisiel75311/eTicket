import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Account} from "../model/account";

@Injectable({providedIn: "root"})
export class GlobalService {
  private readonly _accountSource = new BehaviorSubject<Account | undefined>(undefined);

  // Exposed observable (read-only).
  readonly account = this._accountSource.asObservable();

  constructor() {
  }

  // Get last value without subscribing to the puppies$ observable (synchronously).
  getAccount(): Account | undefined {
    return this._accountSource.getValue();
  }

  setAccount(account: Account): void {
    this._accountSource.next(account);
  }
}
