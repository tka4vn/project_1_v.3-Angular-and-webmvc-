import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AppService} from "../app.service";

export interface Department {
  id?: number
  name: string
}

@Component({
  selector: 'app-department',
  templateUrl: './department.component.html',
  styleUrls: ['../app.component.css']
})

export class DepartmentComponent implements OnInit {

  department: Department[] = []
  newName = ''
  idUser = ''
  linkDep = ''

  constructor(
    private appService: AppService,
    private http: HttpClient,
  ) {}

  private departmentsUrl = 'http://localhost:8080/rest/department';

  ngOnInit() {

    this.http.get<Department[]>(this.departmentsUrl)
      .subscribe(department => {
        console.log('Department', department)
        this.department = department
      })

    this.appService.methodLogin$.subscribe(idUser => {
      console.log('idUser:', idUser);
      this.idUser = idUser
      this.linkDep = '/' + this.idUser + '/department'
    })

  }

  addDepartment() {

    if (!this.newName.trim()) {
      return
    }
    const newDep: Department = {
      name:this.newName
    }
    this.http.post<Department>(this.departmentsUrl, newDep)
      .subscribe(department => {
        console.log('department', department)
        this.department.push(department)
        this.newName = ''
      })
  }

}
