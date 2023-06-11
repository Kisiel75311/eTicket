import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {UserInfoResponse, UserInterface} from "../core/api/v1";

@Injectable({providedIn: "root"})
export class GlobalService {
  private readonly _accountSource = new BehaviorSubject<UserInfoResponse<UserInterface> | undefined>(undefined);

  // Exposed observable (read-only).
  readonly account = this._accountSource.asObservable();

  constructor() {
  }

  getAccount(): UserInfoResponse<UserInterface> | undefined {
    return this._accountSource.getValue();
  }

  setAccount(account: UserInfoResponse<UserInterface> | undefined): void {
    this._accountSource.next(account);
  }

  isAccountSet(): boolean {
    return !!this._accountSource.getValue()
  }
}
