import {Component, Injectable} from '@angular/core';
import {BehaviorSubject, Observable, Subject} from 'rxjs';

@Injectable({
  providedIn: 'root',
})

export class AppService {

  public methodLoginSubject = new BehaviorSubject("ошибка idUser :(");
  methodLogin$ = this.methodLoginSubject.asObservable();

  constructor() {}

  methodLogin(idUser: string) {
    console.log('idUser:', idUser);
    this.methodLoginSubject.next(idUser);
  }

}
