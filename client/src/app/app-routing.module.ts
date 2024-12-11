import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ItemsListComponent } from './items-list/items-list.component';
import { ItemsFormComponent } from './items-form/items-form.component';
import {ItemsEditComponent} from './items-edit/items-edit.component';
import {LocationListComponent} from './location-list/location-list.component';

const routes: Routes = [
  { path: 'items' , component: ItemsListComponent },
  { path: 'items/:locationId' , component: ItemsListComponent },
  { path: '' , component: ItemsListComponent },
  { path: 'additem', component: ItemsFormComponent },
  { path: 'locations', component: LocationListComponent },
  { path: 'item/:id', component: ItemsEditComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
