import {Component, OnInit} from '@angular/core';
import {AppService} from "../app.service";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

export interface Services {
  idServices?: number
  name: string
  idSerRol?: {idRole: number, name: string} []
}
export interface Rol {
  idRole?: number
  name: string
}
export interface Ing {
  idIngress?: number
  idIngUse: { id: number, name: string }
  idIngSer: {idServices: number, name: string }
  idIngRol: {idRole: number, name: string }
}
export interface Use {
  id?: number
  name: string
}
@Component({
  selector: 'app-services',
  templateUrl: './services.component.html',
  styleUrls: ['../app.component.css']
})
export class ServicesComponent implements OnInit {

  services: Services[] = []
  roles: Rol[] = []
  ingress: Ing[] = []
  users: Use[] = []
  newName = ''
  idUser = ''
  idUserId = 0
  linkSer = ''
  owner = 23
  i = 0

  constructor(
    private appService: AppService,
    private router: Router,
    private http: HttpClient,
  ) {}

  private servicesUrl = 'http://localhost:8080/rest/services';
  private rolesUrl = `http://localhost:8080/rest/roles`;
  private ingressUrl = `http://localhost:8080/rest/ingress`;
  private usersUrl = `http://localhost:8080/rest/users`;

  ngOnInit() {
    this.http.get<Services[]>(this.servicesUrl)
      .subscribe(services => {
        console.log('services', services)
        this.services = services
      })
    this.http.get<Rol[]>(this.rolesUrl)
      .subscribe(roles => {
        this.roles = roles
      })
    this.http.get<Use[]>(this.usersUrl)
      .subscribe(users => {
        console.log('users', users)
        this.users = users
      })
    this.http.get<Ing[]>(this.ingressUrl)
      .subscribe(ingress => {
        console.log('ingress', ingress)
        this.ingress = ingress
      })
    this.appService.methodLogin$.subscribe(idUser => {
      this.idUser = idUser
      this.linkSer = '/' + this.idUser + '/services'
    })
  }

  addService() {
    if (!this.newName.trim()) {
      return
    }

    const newSer: Services = {
      name:this.newName,
      // @ts-ignore
      idSerRol: [{idRole: this.owner}]
    }
    this.http.post<Services>(this.servicesUrl, newSer)
      .subscribe(service => {
        console.log('newService', service.idServices)
        this.newName = ''
        // -------- добавляем владельца ---------
        for (this.i = 0; this.i < this.users.length; this.i++) {
          if (this.users[this.i].name == this.idUser) {
            // @ts-ignore
            this.idUserId = this.users[this.i].id
          }
        }
        this.http.post<Ing>(
          this.ingressUrl,
          {
            idIngUse: { id: this.idUserId },
            idIngSer: {idServices: service.idServices },
            idIngRol: {idRole: this.owner }
          })
          .subscribe(rhs => {
            this.http.get<Ing[]>(this.ingressUrl) // new get ing
              .subscribe(ingress => {
                this.ingress = ingress
              })
            this.http.get<Services[]>(this.servicesUrl) // new get ser
              .subscribe(services => {
                this.services = services
              })
          })
        this.roles.push(service)
      })
  }

}
