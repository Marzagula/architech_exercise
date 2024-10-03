export class UserAccountDTO {
  constructor(
    public id: number,
    public username: string,
    public registerDate: Date,
    public lastLoginDate: Date
  ) {
  }
}
