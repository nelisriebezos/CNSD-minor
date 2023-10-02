// shared.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SharedService {
  private loggedInUserSubject = new BehaviorSubject<string | null>(null);

  loggedInUser$ = this.loggedInUserSubject.asObservable();

  setLoggedInUser(user: string | null) {
    this.loggedInUserSubject.next(user);
  }
}
