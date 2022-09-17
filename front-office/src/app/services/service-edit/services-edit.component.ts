import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {AppService} from "../../app.service";
import {Services} from "../services.component";

export interface Ser {
  idServices?: number
  name: string
  idSerRol?: {idRole: number, name: string} []
}
export interface SerEdit {
  idServices?: number
  name: string
}
export interface Roles {
  idRole?: number
  name: string
}
export interface Ingress {
  idIngress?: number
  idIngUse: { id: number, name: string }
  idIngSer: {idServices: number, name: string }
  idIngRol: {idRole: number, name: string }
}
export interface Users {
  id?: number
  name: string
}
@Component({
  selector: 'app-services-edit',
  templateUrl: './services-edit.component.html',
  styleUrls: ['./services-edit.component.css']
})
export class ServicesEditComponent implements OnInit {
  services: Services[] = []
  // rhs: RHS[] = []
  roles: Roles[] = []
  ingress: Ingress[] = []
  users: Users[] = []
  public ser: Ser | undefined;
  private idSer: | undefined;
  private subscription: Subscription;
  editName = ''
  idUser = ''
  linkSer = ''
  linkRol = ''
  owner = 23
  i = 0
  y = [{idrole: 23}]

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient,
    private appService: AppService
  ) {
    this.subscription = route.params.subscribe(params=>this.idSer=params['id'])
  }

  private servicesUrl = `http://localhost:8080/rest/services`;
  private rolesUrl = `http://localhost:8080/rest/roles`;
  private ingressUrl = `http://localhost:8080/rest/ingress`;
  private usersUrl = `http://localhost:8080/rest/users`;

  ngOnInit() {
    this.http.get<Ser>(this.servicesUrl + '/' + this.idSer)
      .subscribe(ser => {
        console.log('ser', ser)
        this.ser = ser
      })
    this.http.get<Roles[]>(this.rolesUrl)
      .subscribe(roles => {
        console.log('roles', roles)
        this.roles = roles
      })
    this.http.get<Users[]>(this.usersUrl)
      .subscribe(users => {
        this.users = users
      })
    this.http.get<Ingress[]>(this.ingressUrl)
      .subscribe(ingress => {
        this.ingress = ingress
      })
    this.appService.methodLogin$.subscribe(idUser => {
      console.log('idUser:', idUser);
      this.idUser = idUser
      this.linkRol = '/' + this.idUser + '/roles'
      this.linkSer = '/' + this.idUser + '/services'
    })
  }
  remove() {
    this.http.delete<Ser>(this.servicesUrl + '/delete/' + this.idSer)
      .subscribe(ser => {
        this.http.get<Services[]>(this.servicesUrl) // new get
          .subscribe(services => {
            console.log('services', services)
            this.services = services
          })
        this.router.navigate([this.linkSer]);
      })
  }
  edit(editId: number | undefined) {
    this.http.put<Ser>(this.servicesUrl + '/edit/' + this.idSer, {
      idServices: editId,
      name: this.editName,
      idSerRol: this.ser?.idSerRol
    })
      .subscribe(ser => {
        this.http.get<Services[]>(this.servicesUrl) // new get
          .subscribe(services => {
            console.log('services', services)
            this.services = services
          })
        this.router.navigate([this.linkSer]);
      })
  }
  addRole(newRoleIdHas: number | undefined) {
    this.http.put<Ser>(this.servicesUrl + '/addRoles/' + this.idSer + '/' + newRoleIdHas,{})
      .subscribe(ser => {
        console.log('ser', ser)
        this.http.get<Ser>(this.servicesUrl + '/' + this.idSer)  //new get
          .subscribe(ser => {
            console.log('ser', ser)
            this.ser = ser
          })
      })
  }
  removeRole(newRoleIdHas: number | undefined) {
    this.http.put<Ser>(this.servicesUrl + '/removeRoles/' + this.idSer + '/' + newRoleIdHas,{})
      .subscribe(ser => {
        this.http.get<Ser>(this.servicesUrl + '/' + this.idSer)  //new get
          .subscribe(ser => {
            this.ser = ser
          })
      })
  }

}
