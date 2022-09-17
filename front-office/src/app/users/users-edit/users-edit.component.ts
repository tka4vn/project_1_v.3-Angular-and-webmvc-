import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {AppService} from "../../app.service";
import {Users} from "../users.component";



export interface Use {
  id?: number
  name: string
  password: string
  idUseEmp?: {id?: number, fullName?: string}
}
export interface Employee {
  id?: number
  fullName: string
}
@Component({
  selector: 'app-users-edit',
  templateUrl: './users-edit.component.html',
  styleUrls: ['./users-edit.component.css']
})
export class UsersEditComponent implements OnInit {

  employee: Employee[] = []
  users: Users[] = []
  public use: Use | undefined;
  private idUse: | undefined;
  public editName: string | undefined
  public editPassword: string | undefined
  public editIdUseEmp: number | undefined
  private subscription: Subscription;
  linkUse = ''
  idUser = ''

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient,
    private appService: AppService
  ) {
    this.subscription = route.params.subscribe(params=>this.idUse=params['id']);
  }

  private employeeUrl = 'http://localhost:8080/rest/employee';
  private usersUrl = `http://localhost:8080/rest/users`;

  ngOnInit() {
    this.http.get<Use>(this.usersUrl + '/' + this.idUse)
      .subscribe(use => {
        this.use = use
        this.editName = use.name
        this.editPassword = use.password
        this.editIdUseEmp = use.idUseEmp?.id
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
      this.http.put<Use>(this.usersUrl + '/edit/' + userId, {
        id: userId, name: this.editName, password: this.editPassword, idUseEmp: {id: this.editIdUseEmp}})
        .subscribe(use => {
          this.http.get<Users[]>(this.usersUrl) // new get
            .subscribe(users => {
              this.users = users
            })
          this.router.navigate([this.linkUse]);
        })
    }
    remove(idUse: number | undefined) {
      this.http.delete<Use>(this.usersUrl + '/delete/' + idUse)
        .subscribe(use => {
          this.http.get<Users[]>(this.usersUrl) // new get
            .subscribe(users => {
              this.users = users
            })
          this.router.navigate([this.linkUse]);
        })
    }

}
