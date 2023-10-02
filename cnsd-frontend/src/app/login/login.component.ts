import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Account } from 'src/domain/Account';
import { SharedService } from 'src/service/SharedService';
import { fetchLoginDetails, setLoggedInUser } from 'src/service/authService';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;

  constructor(private fb: FormBuilder, private sharedService: SharedService) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  onSubmit() {
    if (this.loginForm.invalid) {
      return;
    }
    let response: Account | null = fetchLoginDetails(this.loginForm.controls['username'].value, this.loginForm.controls['password'].value)

    if (response != null) {
      setLoggedInUser(response, this.sharedService)
      console.log('Login successful', response);
    } else {
      console.log('Login unsuccesful');
    }
  }
}