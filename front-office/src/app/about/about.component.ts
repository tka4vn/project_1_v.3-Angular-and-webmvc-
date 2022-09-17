import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AppService} from "../app.service";
import {Department} from "../department/department.component";
import {Users} from "../users/users.component";
import {Appointment} from "../appointment/appointment.component";
import {Ingress} from "../ingress/ingress.component";
import {Services} from "../services/services.component";
import {Roles} from "../roles/roles.component";
import {Request} from "../request/request.component";
import {Employee} from "../employee/employee.component";
import {Position} from "../position/position.component";

export interface Status {
  id?: number
  name: string
}
@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['../app.component.css']
})
export class AboutComponent implements OnInit {

  department: Department[] = []
  employee: Employee[] = []
  position: Position[] = []
  appointment: Appointment[] = []
  users: Users[] = []
  ingress: Ingress[] = []
  services: Services[] = []
  roles: Roles[] = []
  request: Request[] = []
  status: Status[] = []
  idUser = ''
  owner = 23
  admin = 24
  i = 0
  idIng: number | undefined
  reqStaNew = 1
  reqStaDeniedOwner = 23

  constructor(
    private http: HttpClient,
    private appService: AppService
  ) { }

  private departmentUrl = 'http://localhost:8080/rest/department';
  private employeeUrl = 'http://localhost:8080/rest/employee';
  private positionUrl = 'http://localhost:8080/rest/position';
  private appointmentUrl = 'http://localhost:8080/rest/appointment';
  private usersUrl = `http://localhost:8080/rest/users`;
  private ingressUrl = `http://localhost:8080/rest/ingress`;
  private servicesUrl = 'http://localhost:8080/rest/services';
  private rolesUrl = 'http://localhost:8080/rest/roles';
  private requestUrl = 'http://localhost:8080/rest/request';
  private statusUrl = 'http://localhost:8080/rest/status';

  ngOnInit(): void {

    this.http.get<Department[]>(this.departmentUrl)
      .subscribe(department => {
        this.department = department
      })
    this.http.get<Employee[]>(this.employeeUrl)
      .subscribe(employee => {
        this.employee = employee
      })
    this.http.get<Position[]>(this.positionUrl)
      .subscribe(position => {
        this.position = position
      })
    this.http.get<Appointment[]>(this.appointmentUrl)
      .subscribe(appointment => {
        this.appointment = appointment
      })
    this.http.get<Users[]>(this.usersUrl)
      .subscribe(users => {
        this.users = users
      })
    this.http.get<Ingress[]>(this.ingressUrl)
      .subscribe(ingress => {
        this.ingress = ingress
      })
    this.http.get<Services[]>(this.servicesUrl)
      .subscribe(services => {
        this.services = services
      })
    this.http.get<Roles[]>(this.rolesUrl)
      .subscribe(roles => {
        this.roles = roles
      })
    this.http.get<Request[]>(this.requestUrl)
      .subscribe(request => {
        this.request = request
      })
    this.http.get<Status[]>(this.statusUrl)
      .subscribe(status => {
        this.status = status
      })
    this.appService.methodLogin$.subscribe(idUser => {
      console.log('idUser:', idUser);
      this.idUser = idUser
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
