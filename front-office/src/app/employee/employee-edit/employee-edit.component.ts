import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {AppService} from "../../app.service";
import {Employee} from "../employee.component";


export interface Emp {
  id?: number
  fullName: string
}

@Component({
  selector: 'app-employee-edit',
  templateUrl: './employee-edit.component.html',
  styleUrls: ['./employee-edit.component.css']
})
export class EmployeeEditComponent implements OnInit {

  employee: Employee[] = []
  public emp: Emp | undefined;
  private idEmp: | undefined;
  private subscription: Subscription;
  editName = ''
  idUser = ''
  linkEmp = ''

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient,
    private appService: AppService
  ) {
    this.subscription = route.params.subscribe(params=>this.idEmp=params['id']);
  }

  private empUrl = `http://localhost:8080/rest/employee`;

  ngOnInit() {

    this.http.get<Emp>(this.empUrl + '/' + this.idEmp)
      .subscribe(emp => {
        this.emp = emp
      })
    this.appService.methodLogin$.subscribe(idUser => {
      console.log('idUser:', idUser);
      this.idUser = idUser
      this.linkEmp = '/' + this.idUser + '/employee'
    })
  }

  remove(id: number | undefined) {
    this.http.delete<Emp>(this.empUrl + '/delete/' + this.idEmp)
      .subscribe(emp => {
        this.http.get<Employee[]>(this.empUrl) // new get
          .subscribe(employee => {
            this.employee = employee
          })
        this.router.navigate([this.linkEmp]);
      })
  }

  edit(editId: number | undefined) {
    this.http.put<Emp>(this.empUrl + '/edit/' + this.idEmp, {id: editId,fullName: this.editName})
      .subscribe(rol => {
        this.http.get<Employee[]>(this.empUrl) // new get
          .subscribe(employee => {
            this.employee = employee
          })
        this.router.navigate([this.linkEmp]);
      })
  }

}
