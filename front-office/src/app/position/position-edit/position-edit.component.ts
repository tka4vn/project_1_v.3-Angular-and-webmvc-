import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {AppService} from "../../app.service";
import {Position} from "../position.component";

export interface Pos {
  id?: number
  name: string
}

@Component({
  selector: 'app-position-edit',
  templateUrl: './position-edit.component.html',
  styleUrls: ['./position-edit.component.css']
})
export class PositionEditComponent implements OnInit {

  position: Position[] = []
  public pos: Pos | undefined;
  private idPos: | undefined;
  private subscription: Subscription;
  editName = ''
  idUser = ''
  linkPos = ''

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient,
    private appService: AppService
  ) {
    this.subscription = route.params.subscribe(params=>this.idPos=params['id']);
  }

  private posUrl = `http://localhost:8080/rest/position`;

  ngOnInit() {

    this.http.get<Pos>(this.posUrl + '/' + this.idPos)
      .subscribe(pos => {
        this.pos = pos
      })
    this.appService.methodLogin$.subscribe(idUser => {
      console.log('idUser:', idUser);
      this.idUser = idUser
      this.linkPos = '/' + this.idUser + '/position'
    })
  }

  remove(id: number | undefined) {
    this.http.delete<Pos>(this.posUrl + '/delete/' + this.idPos)
      .subscribe(pos => {
        this.http.get<Position[]>(this.posUrl) // new get
          .subscribe(position => {
            this.position = position
          })
        this.router.navigate([this.linkPos]);
      })
  }

  edit(editId: number | undefined) {
    this.http.put<Pos>(this.posUrl + '/edit/' + this.idPos, {id: editId,name: this.editName})
      .subscribe(pos => {
        this.http.get<Position[]>(this.posUrl) // new get
          .subscribe(position => {
            this.position = position
          })
        this.router.navigate([this.linkPos]);
      })
  }

}
