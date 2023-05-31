export interface Account {
  accountName: string
  role: Role
  balance: number
}

enum Role {
  PASSENGER,
  CONTROLLER,
  ADMIN
}
