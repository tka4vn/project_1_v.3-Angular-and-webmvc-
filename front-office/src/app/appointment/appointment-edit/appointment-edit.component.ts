import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Appointment} from "../appointment.component";
import {AppService} from "../../app.service";

export interface App {
  id?: number
  dateApp: string
  idAppDep?: {id?: number}
  idAppPos?: {id?: number}
  idAppEmp?: {id?: number}
}
export interface Department {
  id?: number
  name: string
}
export interface Employee {
  id?: number
  fullName: string
}
export interface Position {
  id?: number
  name: string
}
@Component({
  selector: 'app-appointment-edit',
  templateUrl: './appointment-edit.component.html',
  styleUrls: ['./appointment-edit.component.css']
})
export class AppointmentEditComponent implements OnInit {

  appointment: Appointment[] = []
  public app: App | undefined;
  private idApp: | undefined;
  public idUser = ''
  private subscription: Subscription;
  department: Department[] = []
  employee: Employee[] = []
  position: Position[] = []
  public editDateApp: string | undefined
  public editIdAppDep: number | undefined
  public editIdAppPos: number | undefined
  public editIdAppEmp: number | undefined

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient,
    private appService: AppService
  ) {
    this.subscription = route.params.subscribe(params=>this.idApp=params['id']);

  }

  private appointmentUrl = `http://localhost:8080/rest/appointment`;
  private departmentUrl = 'http://localhost:8080/rest/department';
  private employeeUrl = 'http://localhost:8080/rest/employee';
  private positionUrl = 'http://localhost:8080/rest/position';
  public linkApp = ''

  ngOnInit() {
    this.http.get<App>(this.appointmentUrl + '/' + this.idApp)
      .subscribe(app => {
        this.app = app
        this.editDateApp = app.dateApp
        this.editIdAppDep = app.idAppDep?.id
        this.editIdAppPos = app.idAppPos?.id
        this.editIdAppEmp = app.idAppEmp?.id
      })
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
    this.appService.methodLogin$.subscribe(idUser => {
      console.log('idUser:', idUser);
      this.idUser = idUser
      this.linkApp = '/' + this.idUser + '/appointment'
    })
  }

  remove(id: number | undefined) {
    this.http.delete<App>(this.appointmentUrl + '/delete/' + this.idApp)
      .subscribe(app => {
        this.http.get<Appointment[]>(this.appointmentUrl) //new get
          .subscribe(appointment => {
            console.log('app', appointment)
            this.appointment = appointment
          })
        this.router.navigate([this.linkApp]);
      })
  }

  edit(editId: number | undefined) {
    this.http.put<App>(this.appointmentUrl + '/edit/' + this.idApp, {
      id: editId,
      dateApp: this.editDateApp,
      idAppDep: {id: this.editIdAppDep},
      idAppPos: {id: this.editIdAppPos},
      idAppEmp: {id: this.editIdAppEmp}
    })
      .subscribe(app => {
        this.http.get<Appointment[]>(this.appointmentUrl) //new get
          .subscribe(appointment => {
            console.log('app', appointment)
            this.appointment = appointment
          })
        this.router.navigate([this.linkApp]);
      })
  }

}
