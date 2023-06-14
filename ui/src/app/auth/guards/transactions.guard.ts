import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {GlobalService} from "../../services/global.service";

export const transactionsGuard: CanActivateFn = (route, state) => {
  const global = inject(GlobalService)
  const acc = global.getAccount()
  const router = inject(Router)
  if (acc === undefined) {
    router.navigate(["/notauthorized"])
    return false;
  } else if (acc.roles?.includes("ROLE_PASSENGER")) {
    return true;
  }
  router.navigate(["/notauthorized"])
  return false;
};
