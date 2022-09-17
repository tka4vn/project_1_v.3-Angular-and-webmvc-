import {Component, OnInit} from '@angular/core';
import {AppService} from "../app.service";
import {HttpClient} from "@angular/common/http";

export interface Roles {
  idRole?: number
  name: string
  idRolSer?: {idServices: number, name: string} []
}
@Component({
  selector: 'app-roles',
  templateUrl: './roles.component.html',
  styleUrls: ['../app.component.css']
})
export class RolesComponent implements OnInit {

  roles: Roles[] = []
  newName = ''
  idUser = ''
  linkRol = ''

  constructor(
    private appService: AppService,
    private http: HttpClient,
  ) {}

  private rolesUrl = 'http://localhost:8080/rest/roles';

  ngOnInit() {

    this.http.get<Roles[]>(this.rolesUrl)
      .subscribe(roles => {
        console.log('roles', roles)
        this.roles = roles
      })

    this.appService.methodLogin$.subscribe(idUser => {
      console.log('idUser:', idUser);
      this.idUser = idUser
      this.linkRol = '/' + this.idUser + '/roles'
    })

  }

  addRole() {
    if (!this.newName.trim()) {
      return
    }
    const newRol: Roles = {
      name:this.newName
    }
    this.http.post<Roles>(this.rolesUrl, newRol)
      .subscribe(role => {
        console.log('role', role)
        this.roles.push(role)
        this.newName = ''
      })
  }

}
