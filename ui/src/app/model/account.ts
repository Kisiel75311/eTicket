export interface Account {
  id: number,
  accountName: string
  role: [Role]
  balance: number
}

enum Role {
  ROLE_PASSENGER,
  ROLE_CONTROLLER,
  ROLE_ADMIN
}
