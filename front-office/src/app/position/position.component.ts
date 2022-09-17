import { Component, OnInit } from '@angular/core';
import {AppService} from "../app.service";
import {HttpClient} from "@angular/common/http";

export interface Position {
  id?: number
  name: string
}
@Component({
  selector: 'app-position',
  templateUrl: './position.component.html',
  styleUrls: ['../app.component.css']
})
export class PositionComponent implements OnInit {

  position: Position[] = []
  newName = ''
  idUser = ''
  linkPos = ''

  constructor(
    private appService: AppService,
    private http: HttpClient,
  ) {}

  private positionUrl = 'http://localhost:8080/rest/position';

  ngOnInit() {

    this.http.get<Position[]>(this.positionUrl)
      .subscribe(position => {
        this.position = position
      })

    this.appService.methodLogin$.subscribe(idUser => {
      this.idUser = idUser
      this.linkPos = '/' + this.idUser + '/position'
    })

  }

  addPosition() {
    if (!this.newName.trim()) {
      return
    }
    const newPos: Position = {
      name:this.newName
    }
    this.http.post<Position>(this.positionUrl, newPos)
      .subscribe(position => {
        this.position.push(position)
        this.newName = ''
      })
  }
}
