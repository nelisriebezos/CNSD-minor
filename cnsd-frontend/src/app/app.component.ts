import { Component } from '@angular/core';
import { SharedService } from 'src/service/SharedService';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'cnsd-frontend';
  header: String = 'cnsd-frontend;'

  constructor(private sharedService: SharedService) {}


  ngOnInit() {
    this.setHeader();
    this.sharedService.loggedInUser$.subscribe((user) => {
      if (user) {
        this.setHeader(user);
      }
    });
  }

  setHeader(user?: string) {
    if (user) {
      this.header = `${this.title} - ${user}`;
    } else {
      this.header = this.title;
    }
  }
}
