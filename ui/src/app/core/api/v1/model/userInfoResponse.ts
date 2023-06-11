export interface UserInterface {
  id?: number;
  username?: string;
  email?: string;
  roles?: Array<string>;
  balance?: number;
}

export interface UserInfoResponse<T> {
  data: T;
  timestamp: Date;
  error: string;
}
