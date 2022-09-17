import { Component, OnInit } from '@angular/core';
import {AppService} from "../app.service";
import {HttpClient} from "@angular/common/http";

export interface Employee {
  id?: number
  fullName: string
}
@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['../app.component.css']
})
export class EmployeeComponent implements OnInit {

  employee: Employee[] = []
  newName = ''
  idUser = ''
  linkEmp = ''

  constructor(
    private appService: AppService,
    private http: HttpClient,
  ) {}

  private employeeUrl = 'http://localhost:8080/rest/employee';

  ngOnInit() {

    this.http.get<Employee[]>(this.employeeUrl)
      .subscribe(employee => {
        this.employee = employee
      })

    this.appService.methodLogin$.subscribe(idUser => {
      console.log('idUser:', idUser);
      this.idUser = idUser
      this.linkEmp = '/' + this.idUser + '/employee'
    })

  }

  addEmployee() {
    if (!this.newName.trim()) {
      return
    }
    const newEmp: Employee = {
      fullName:this.newName
    }
    this.http.post<Employee>(this.employeeUrl, newEmp)
      .subscribe(employee => {
        this.employee.push(employee)
        this.newName = ''
      })
  }
}
