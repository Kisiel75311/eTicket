import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {UserInfoResponse} from "../core/api/v1";

@Injectable({providedIn: "root"})
export class GlobalService {
  private readonly _accountSource = new BehaviorSubject<UserInfoResponse | undefined>(undefined);

  // Exposed observable (read-only).
  readonly account = this._accountSource.asObservable();

  constructor() {
  }

  getAccount(): UserInfoResponse | undefined {
    return this._accountSource.getValue();
  }

  setAccount(account: UserInfoResponse | undefined): void {
    this._accountSource.next(account);
  }

  isAccountSet(): boolean {
    return !!this._accountSource.getValue()
  }

  isPassenger(): boolean {
    return !!this._accountSource.getValue()?.roles?.includes("ROLE_PASSENGER")
  }

  isAdmin(): boolean {
    return !!this._accountSource.getValue()?.roles?.includes("ROLE_ADMIN")
  }


}
