import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";
import {AppService} from "../app.service";

@Component({
  selector: 'app-home',
  templateUrl: './index.component.html',
  styleUrls: ['../app.component.css']
})
export class IndexComponent implements OnInit {

  private subscription: Subscription;
  public idUser = ''
  linkAbout = ''
  linkApp = ''
  linkDep = ''
  linkRol = ''
  linkSer = ''
  linkEmp = ''
  linkPos = ''
  linkIng = ''
  linkUse = ''
  linkReq  = ''

  constructor(
    private route: ActivatedRoute,
    private appService: AppService
  ) {
    this.subscription = route.params.subscribe(params=>this.idUser=params['id']);
    this.linkAbout = '/' + this.idUser + '/about'
    this.linkApp = '/' + this.idUser + '/appointment'
    this.linkEmp = '/' + this.idUser + '/employee'
    this.linkPos = '/' + this.idUser + '/position'
    this.linkDep = '/' + this.idUser + '/department'
    this.linkRol = '/' + this.idUser + '/roles'
    this.linkSer = '/' + this.idUser + '/services'
    this.linkIng = '/' + this.idUser + '/ingress'
    this.linkUse = '/' + this.idUser + '/users'
    this.linkReq = '/' + this.idUser + '/request'
    this.appService.methodLogin(this.idUser);
  }

  ngOnInit(): void {
  }

}
