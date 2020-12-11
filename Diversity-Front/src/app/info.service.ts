import { Injectable } from '@angular/core';
import {Person} from './person';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InfoService {

  private infoUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  getHeroes(): Observable<Person[]> {
    return this.http.get<Person[]>("http://localhost:8080/api/people");
  }

}
