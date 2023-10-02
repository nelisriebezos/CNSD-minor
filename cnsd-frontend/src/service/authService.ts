import { Account } from "src/domain/Account";
import { SharedService } from "./SharedService";

const accounts = [
    1, {
        "id": 1,
        "firstName": "Niels",
        "lastName": "Riezebos",
        "email": "niels@mail.com",
        "password": "admin",
        "accounts": [1, 2]
    },
    2, {
        "id": 2,
        "firstName": "Bram",
        "lastName": "de Man",
        "email": "bram@mail.com",
        "password": "admin",
        "accounts": [1, 2]
    }
]

export function fetchLoginDetails(email: String, password: String): Account | null {
    let loggedInAccount = null;

    accounts.forEach((item) => {
        if (typeof item === 'object' && item.email === email && item.password === password) {
            loggedInAccount = new Account(item.id, item.firstName, item.lastName, item.email, item.password, item.accounts);
        }
    });

    return loggedInAccount;
}

export function setLoggedInUser(user: Account, sharedService: SharedService) {
    sessionStorage.setItem("loggedInUser", user.getFullName())
    sharedService.setLoggedInUser(user.getFullName());
}

export function logOutUser(sharedService: SharedService) {
    sessionStorage.setItem("loggedInUser", "")
    sharedService.setLoggedInUser("");
}