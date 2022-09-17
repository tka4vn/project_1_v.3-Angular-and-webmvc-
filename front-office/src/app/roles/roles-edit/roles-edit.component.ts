import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {AppService} from "../../app.service";
import {Roles} from "../roles.component";

export interface Rol {
  idRole?: number
  name: string
  idRolSer?: {idServices: number, name: string} []
}
export interface Ser {
  idServices?: number
  name: string
  idSerRol?: {idRole: number, name: string} []
}
@Component({
  selector: 'app-roles-edit',
  templateUrl: './roles-edit.component.html',
  styleUrls: ['./roles-edit.component.css']
})
export class RolesEditComponent implements OnInit {
  roles: Roles[] = []
  public rol: Rol | undefined;
  services: Ser[] = []
  private idRol: | undefined;
  private subscription: Subscription;
  editName = ''
  idUser = ''
  linkRol = ''
  linkSer = ''

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient,
    private appService: AppService
  ) {
    this.subscription = route.params.subscribe(params=>this.idRol=params['id'])
  }

  private rolesUrl = `http://localhost:8080/rest/roles`;
  private servicesUrl = `http://localhost:8080/rest/services`;

  ngOnInit() {
    this.http.get<Rol>(this.rolesUrl + '/' + this.idRol)
      .subscribe(rol => {
        console.log('rol', rol)
        this.rol = rol
      })
    this.http.get<Ser[]>(this.servicesUrl)
      .subscribe(services => {
        console.log('services', services)
        this.services = services
      })
    this.appService.methodLogin$.subscribe(idUser => {
      console.log('idUser:', idUser);
      this.idUser = idUser
      this.linkRol = '/' + this.idUser + '/roles'
      this.linkSer = '/' + this.idUser + '/services'
    })
  }
  remove() {
    this.http.delete<Rol>(this.rolesUrl + '/delete/' + this.idRol)
      .subscribe(rol => {
        this.http.get<Roles[]>(this.rolesUrl) // new get
          .subscribe(roles => {
            console.log('roles', roles)
            this.roles = roles
          })
        this.router.navigate([this.linkRol]);
      })
  }
  edit(editId: number | undefined) {
    this.http.put<Rol>(this.rolesUrl + '/edit/' + this.idRol, {
      idRole: editId,
      name: this.editName,
      idRolSer: this.rol?.idRolSer
    })
      .subscribe(rol => {
        this.http.get<Roles[]>(this.rolesUrl) // new get
          .subscribe(roles => {
            console.log('roles', roles)
            this.roles = roles
          })
        this.router.navigate([this.linkRol]);
      })
  }
  addService(newServiceIdHas: number | undefined) {
    this.http.put<Rol>(this.rolesUrl + '/addService/' + this.idRol + '/' + newServiceIdHas,{})
      .subscribe(rol => {
        console.log('rol', rol)
        this.http.get<Rol>(this.rolesUrl + '/' + this.idRol)  //new get
          .subscribe(rol => {
            console.log('rol', rol)
            this.rol = rol
          })
      })
  }
  removeService(newServiceIdHas: number | undefined) {
    this.http.put<Rol>(this.rolesUrl + '/removeService/' + this.idRol + '/' + newServiceIdHas,{})
      .subscribe(rol => {
        this.http.get<Rol>(this.rolesUrl + '/' + this.idRol)  //new get
          .subscribe(rol => {
            this.rol = rol
          })
      })
  }

}
