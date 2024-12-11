import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { ItemsListComponent } from './items-list/items-list.component';
import { ItemsFormComponent } from './items-form/items-form.component';
import { ItemsService } from './items-service.service';
import { ItemsEditComponent } from './items-edit/items-edit.component';
import {ListFilterPipe} from './items-list/list-filter.pipe';
import { LocationListComponent } from './location-list/location-list.component';
import {LocationService} from './location.service';
import { LocationFormComponent } from './location-form/location-form.component';

@NgModule({
  declarations: [
    AppComponent,
    ItemsListComponent,
    ItemsFormComponent,
    ItemsEditComponent,
    ListFilterPipe,
    LocationListComponent,
    LocationFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    ItemsService,
    LocationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
