import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

export interface Users {
  id: number
  name: string
}
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['../app.component.css']
})
export class LoginComponent implements OnInit {

  users: Users[] = []
  userId = 0

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  private usersUrl = 'http://localhost:8080/rest/users';

  ngOnInit(): void {
    this.http.get<Users[]>(this.usersUrl)
      .subscribe(users => {
        console.log('users', users)
        this.users = users
      })
  }
  loginUser() {
    if (this.userId == 0) {
      return
    }
    this.router.navigate([this.userId + '/about']);
  }

}
