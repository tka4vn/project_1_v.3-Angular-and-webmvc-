import {Component, OnInit} from '@angular/core';
import {AppService} from "../app.service";
import {HttpClient} from "@angular/common/http";

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
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['../app.component.css']
})
export class UsersComponent implements OnInit {

  users: Users[] = []
  employee: Employee[] = []
  active = true
  editName = ''
  editPassword = ''
  editIdUseEmp = ''
  idUser = ''
  linkUse = ''

  constructor(
    private appService: AppService,
    private http: HttpClient,
  ) { }

  private employeeUrl = 'http://localhost:8080/rest/employee';
  private usersUrl = `http://localhost:8080/rest/users`;

  ngOnInit(): void {
    this.http.get<Users[]>(this.usersUrl)
      .subscribe(users => {
        this.users = users
      })
    this.http.get<Employee[]>(this.employeeUrl)
      .subscribe(employee => {
        this.employee = employee
      })
    this.appService.methodLogin$.subscribe(idUser => {
      console.log('idUser:', idUser);
      this.idUser = idUser
      this.linkUse = '/' + this.idUser + '/users'
    })
  }
  edit(userId: number | undefined) {
    this.http.put<Users>(this.usersUrl + '/edit/' + userId, {
      id: userId, name: this.editName, password: this.editPassword, active: this.active, idUseEmp: {id: this.editIdUseEmp}})
      .subscribe(use => {
        this.http.get<Users[]>(this.usersUrl) // new get
          .subscribe(users => {
            this.users = users
          })
        this.users.push(use)
      })
  }
  remove(idUse: number | undefined) {
    this.http.delete<Users>(this.usersUrl + '/delete/' + idUse)
      .subscribe(use => {
        this.http.get<Users[]>(this.usersUrl) // new get
          .subscribe(users => {
            this.users = users
          })
        this.users.push(use)
      })
  }

}
