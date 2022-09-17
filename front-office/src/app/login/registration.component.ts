import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

export interface Users {
  id?: number
  name: string
  password?: string
  active?: boolean
  idUseEmp?: {id?: number, fullName?: string}
}
export interface Employee {
  id?: number
  fullName: string
}
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['../app.component.css']
})
export class RegistrationComponent implements OnInit {

  users: Users[] = []
  employee: Employee[] = []
  active = true
  userId = 0
  newName = ''
  newPassword = ''
  newEmployee = ''

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  private usersUrl = 'http://localhost:8080/rest/users';
  private employeeUrl = 'http://localhost:8080/rest/employee';

  ngOnInit(): void {
  }
  addUser() {
    if (!this.newEmployee.trim() && !this.newName.trim() && !this.newPassword.trim()) {
      return
    }
    this.http.post<Employee>(this.employeeUrl, {fullName: this.newEmployee})
      .subscribe(employee => {
        this.http.post<Users>(this.usersUrl,{
          name: this.newName, password: this.newPassword, active: this.active, idUseEmp: {id: employee.id}})
          .subscribe(newUser => {
            location.reload()
            console.log('newUser', newUser)})
      })
  }

}
