export class Account {
    constructor(
        private _id: number,
        private _firstName: string,
        private _lastName: string,
        private _email: string,
        private _password: string,
        private _accounts: number[]
    ) {}

    getFullName(): string {
    return this._firstName + " " + this._lastName;    
    }
}