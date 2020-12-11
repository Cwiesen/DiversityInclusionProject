import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { PersonsComponent } from './persons/persons.component';
import { DemographicsComponent } from './demographics/demographics.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    PersonsComponent,
    DemographicsComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
