import { Component, OnInit } from '@angular/core';
import {AppService} from "../app.service";
import {HttpClient} from "@angular/common/http";


export interface Ingress {
  idIngress?: number
  idIngUse: { id: number, name: string }
  idIngSer: {idServices: number, name: string }
  idIngRol: {idRole: number, name: string }
}

@Component({
  selector: 'app-ingress',
  templateUrl: './ingress.component.html',
  styleUrls: ['../app.component.css']
})
export class IngressComponent implements OnInit {

  ingress: Ingress[] = []
  idUser = ''

  constructor(
    private appService: AppService,
    private http: HttpClient,
  ) {}

  private ingressUrl = `http://localhost:8080/rest/ingress`;

  ngOnInit() {
    this.http.get<Ingress[]>(this.ingressUrl)
      .subscribe(ingress => {
        this.ingress = ingress
      })
  }
  removeIngress(idIng: number | undefined) {
    this.http.delete<Ingress>(this.ingressUrl + '/delete/' + idIng)
      .subscribe(ing => {
        this.http.get<Ingress[]>(this.ingressUrl) // new get
          .subscribe(ingress => {
            this.ingress = ingress
          })
        this.ingress.push(ing)
      })
  }

}
