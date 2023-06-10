import {inject} from "@angular/core";
import {ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot,} from "@angular/router";
import {TicketControllerService, TicketTypeDto} from "../core/api/v1";
export const ticketsResolver: ResolveFn<Array<TicketTypeDto>> =
  (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
  return inject(TicketControllerService).getAllTicketTypes();
};
