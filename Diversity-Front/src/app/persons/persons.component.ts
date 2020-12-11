import { Component, OnInit } from '@angular/core';
import {Person} from '../person';
import {InfoService} from '../info.service';

@Component({
  selector: 'app-persons',
  templateUrl: './persons.component.html',
  styleUrls: ['./persons.component.css']
})
export class PersonsComponent implements OnInit {

  people: Person[];

  constructor(private service: InfoService) { }

  ngOnInit(): void {
    this.getPeople();
  }

  getPeople() : void {

    this.service.getHeroes().subscribe(all => this.people = all);
  }
  

}
