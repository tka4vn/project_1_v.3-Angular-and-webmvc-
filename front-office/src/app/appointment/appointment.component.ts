import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AppService} from "../app.service";


export interface Appointment {
  id?: number
  dateApp: string
  idAppDep?: {id?: number, name: string}
  idAppPos?: {id?: number, name: string}
  idAppEmp?: {id?: number, fullName: string}
}
export interface Department {
  id?: number
  name: string
  idDepApp?: number
}
export interface Employee {
  id?: number
  fullName: string
  idEmpApp?: number
}
export interface Position {
  id?: number
  name: string
  idPosApp?: number
}
@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['../app.component.css']
})

export class AppointmentComponent implements OnInit {

  appointment: Appointment[] = []
  department: Department[] = []
  employee: Employee[] = []
  position: Position[] = []
  newDateApp = ''
  newIdAppDep = 0
  newIdAppPos = 0
  newIdAppEmp = 0
  idUser = ''
  linkApp = ''

  constructor(private http: HttpClient,
              private appService: AppService
  ) { }

  private appointmentUrl = 'http://localhost:8080/rest/appointment';
  private departmentUrl = 'http://localhost:8080/rest/department';
  private employeeUrl = 'http://localhost:8080/rest/employee';
  private positionUrl = 'http://localhost:8080/rest/position';

  ngOnInit() {

    this.http.get<Appointment[]>(this.appointmentUrl)
      .subscribe(appointment => {
        console.log('app', appointment)
        this.appointment = appointment
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

  addAppointment() {
    if (!this.newDateApp.trim()) {
      return
    }
    this.http.post<Appointment>(
    this.appointmentUrl, {
        dateApp: this.newDateApp,
        idAppDep: {id: this.newIdAppDep},
        idAppPos: {id: this.newIdAppPos},
        idAppEmp: {id: this.newIdAppEmp}
      }
    )
      .subscribe(appointment => {
        console.log('appointment', appointment)
        this.appointment.push(appointment)
        this.newDateApp = ''
        this.newIdAppDep = 0
        this.newIdAppPos = 0
        this.newIdAppEmp = 0
      })
  }

}
