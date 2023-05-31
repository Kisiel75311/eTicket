import {Ticket} from "./ticket"

export interface Transaction {
  transactionDate: Date,
  ticket: Ticket
}
