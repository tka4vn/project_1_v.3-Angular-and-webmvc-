import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Department} from "../department.component";
import {Subscription} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {AppService} from "../../app.service";

export interface Dep {
  id?: number
  name: string
  idDepApp?: []
}
@Component({
  selector: 'app-department-edit',
  templateUrl: './department-edit.component.html',
  styleUrls: ['./department-edit.component.css']
})
export class DepartmentEditComponent implements OnInit {

  department: Department[] = []
  public dep: Dep | undefined;
  private idDep: | undefined;
  private subscription: Subscription;
  editName = ''
  idUser = ''
  linkDep = ''

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient,
    private appService: AppService
  ) {
    this.subscription = route.params.subscribe(params=>this.idDep=params['id']);
  }

  private depUrl = `http://localhost:8080/rest/department`;

  ngOnInit() {

    this.http.get<Dep>(this.depUrl + '/' + this.idDep)
      .subscribe(dep => {
        console.log('Department', dep)
        this.dep = dep
      })
    this.appService.methodLogin$.subscribe(idUser => {
      console.log('idUser:', idUser);
      this.idUser = idUser
      this.linkDep = '/' + this.idUser + '/department'
    })
  }

  remove(id: number | undefined) {
    this.http.delete<Dep>(this.depUrl + '/delete/' + this.idDep)
      .subscribe(rol => {
        this.http.get<Department[]>(this.depUrl) // new get
          .subscribe(department => {
            console.log('Department', department)
            this.department = department
          })
        this.router.navigate([this.linkDep]);
      })
  }

  edit(editId: number | undefined) {
    this.http.put<Dep>(this.depUrl + '/edit/' + this.idDep, {id: editId,name: this.editName})
      .subscribe(rol => {
        this.http.get<Department[]>(this.depUrl) // new get
          .subscribe(department => {
            console.log('Department', department)
            this.department = department
          })
        this.router.navigate([this.linkDep]);
      })
  }

}
