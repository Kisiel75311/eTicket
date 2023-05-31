export interface TicketType {
  id: number,
  name: string,
  price: number
}

export interface Ticket {
  ticketId: number,
  tickerType: TicketType,
  purchaseDate: Date,
  validityDate: Date,
  expirationDate: Date,
  checked: boolean,
  isActivated: boolean,
  vehicleId: number,
  remainingValidityTime: string
}
