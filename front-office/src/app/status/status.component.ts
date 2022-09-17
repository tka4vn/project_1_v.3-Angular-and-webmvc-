import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

export interface Status {
  name: string
  idStatus?: number
}

@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.css']
})
export class StatusComponent implements OnInit {

  status: Status[] = []

  constructor(private statushttp: HttpClient) { }

  ngOnInit() {
    this.statushttp.get<Status[]>('http://localhost:8080/testj')
      .subscribe(status => {
        console.log('Response', status)
        this.status = status

      })
  }

}
