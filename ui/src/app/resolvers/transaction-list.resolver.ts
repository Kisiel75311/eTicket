import {inject} from "@angular/core";
import {ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot,} from "@angular/router";
import {PassengerControllerService, TransactionDto} from "../core/api/v1";
export const transactionsResolver: ResolveFn<Array<TransactionDto>> =
  (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    return inject(PassengerControllerService).getPasengerTransactions();
  };
