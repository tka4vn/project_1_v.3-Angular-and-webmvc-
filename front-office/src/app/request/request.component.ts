import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AppService} from "../app.service";
import {Use} from "../services/services.component";

export interface Request {
  id?: number
  dateReq: string
  idReqUse?: { id: number, name: string }
  idReqSer?: {idServices: number, name: string }
  idReqRol?: {idRole: number, name: string }
  idReqSta?: {idStatus: number, name: string }
}
export interface Services {
  idServices?: number
  name: string
  idSerRol?: {idRole: number, name: string} []
}
export interface Roles {
  idRole?: number
  name: string
  idRolSer?: {idServices: number, name: string} []
}
export interface Status {
  idStatus?: number
  name: string
}
export interface Users {
  id?: number
  name: string
}
export interface Ingress {
  idIngress?: number
  idIngUse: { id: number, name: string }
  idIngSer: {idServices: number, name: string }
  idIngRol: {idRole: number, name: string }
}
@Component({
  selector: 'app-request',
  templateUrl: './request.component.html',
  styleUrls: ['../app.component.css']
})
export class RequestComponent implements OnInit {

  request: Request[] = []
  services: Services[] = []
  roles: Roles[] = []
  status: Status[] = []
  users: Users[] = []
  ingress: Ingress[] = []
  newDateReq = ''
  newIdReqUse = 0
  newIdReqSer = 0
  newIdReqRol = 0
  newIdReqSta = 1
  idUser = ''
  idUserId = 0
  linkReq = ''
  i = 0
  owner = 23
  admin = 24
  reqStaNew = 1
  reqStaDeniedOwner = 23
  idIng: number | undefined


  constructor(private http: HttpClient,
              private appService: AppService,
  ) {}

  private requestUrl = 'http://localhost:8080/rest/request';
  private servicesUrl = 'http://localhost:8080/rest/services';
  private rolesUrl = 'http://localhost:8080/rest/roles';
  private statusUrl = 'http://localhost:8080/rest/status';
  private usersUrl = `http://localhost:8080/rest/users`;
  private ingressUrl = `http://localhost:8080/rest/ingress`;

  ngOnInit(): void {
    this.http.get<Request[]>(this.requestUrl)
      .subscribe(request => {
        this.request = request
      })
    this.http.get<Services[]>(this.servicesUrl)
      .subscribe(services => {
        this.services = services
      })
    this.http.get<Roles[]>(this.rolesUrl)
      .subscribe(roles => {
        this.roles = roles
      })
    this.http.get<Status[]>(this.statusUrl)
      .subscribe(status => {
        this.status = status
      })
    this.http.get<Use[]>(this.usersUrl)
      .subscribe(users => {
        this.users = users
      })
    this.http.get<Ingress[]>(this.ingressUrl)
      .subscribe(ingress => {
        this.ingress = ingress
      })
    this.appService.methodLogin$.subscribe(idUser => {
      this.idUser = idUser
      this.linkReq = '/' + this.idUser + '/request'
    })
  }
  addRequest() {
    if (!this.newDateReq.trim()) {
      return
    }
    for (this.i = 0; this.i < this.users.length; this.i++) {
      if (this.users[this.i].name == this.idUser) {
        // @ts-ignore
        this.idUserId = this.users[this.i].id
      }
    }
    this.http.post<Request>(
      this.requestUrl, {
        dateReq: this.newDateReq,
        idReqUse: {id: this.idUserId},
        idReqSer: {idServices: this.newIdReqSer},
        idReqRol: {idRole: this.newIdReqRol},
        idReqSta: {idStatus: this.newIdReqSta}
      }
    )
      .subscribe(request => {
        this.http.get<Request[]>(this.requestUrl) // new get
          .subscribe(request => {
            this.request = request
          })
        this.request.push(request)
        this.newDateReq = ''
        this.newIdReqSer = 0
        this.newIdReqRol = 0
      })
  }
  removeRequest(idReq: number | undefined) {
    this.http.delete<Request>(this.requestUrl + '/delete/' + idReq)
      .subscribe(req => {
        this.http.get<Request[]>(this.requestUrl) // new get
          .subscribe(request => {
            this.request = request
          })
        this.request.push(req)
      })
  }
  editRequest(
    id: number | undefined,
    dateReq: string,
    idReqUse: number | undefined,
    idReqSer: number | undefined,
    idReqRol: number | undefined,
    idReqSta: number | undefined,) {
    this.http.put<Request>(this.requestUrl + '/edit/' + id, {
      id: id,
      dateReq: dateReq,
      idReqUse: {id: idReqUse},
      idReqSer: {idServices: idReqSer},
      idReqRol: {idRole: idReqRol},
      idReqSta: {idStatus: idReqSta},
    })
      .subscribe(req => {
        this.http.get<Request[]>(this.requestUrl) // new get
          .subscribe(request => {
            this.request = request
          })
        this.request.push(req)
      })
  }
  addIngress(idIngUse: number | undefined, idIngSer: number | undefined, idIngRol: number | undefined) {
    this.http.post<Ingress>(
      this.ingressUrl, {
        idIngUse: {id: idIngUse},
        idIngSer: {idServices: idIngSer},
        idIngRol: {idRole: idIngRol}
      })
      .subscribe(ing => {
        this.ingress.push(ing)
      })
  }
  removeIngress(
    idReqUse: number | undefined,
    idReqSer: number | undefined,
    idReqRol: number | undefined
  ) {
    for (this.i = 0; this.i < this.ingress.length; this.i++) {
      if (this.ingress[this.i].idIngUse.id == idReqUse &&
        this.ingress[this.i].idIngSer.idServices == idReqSer &&
        this.ingress[this.i].idIngRol.idRole == idReqRol
      ) {
        this.idIng = this.ingress[this.i].idIngress
      }
    }
    this.http.delete<Ingress>(this.ingressUrl + '/delete/' + this.idIng)
      .subscribe()
  }

  public ingress25(subj: number | undefined, num: number | undefined) {
    // @ts-ignore
    return subj < num;
  }
}
