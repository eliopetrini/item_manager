import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemsService } from '../items-service.service';
import {ItemLocation, Items} from '../items';

@Component({
  selector: 'app-user-form',
  templateUrl: './items-form.component.html',
  styleUrls: ['./items-form.component.css']
})
export class ItemsFormComponent {

  items: Items;
  todayDate: Date;
  locations: ItemLocation[];
  categories: string[];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private itemsService: ItemsService) {
    this.items = new Items();
    this.items.itemAmount = 1;
    this.todayDate = new Date();
    this.items.itemBought = this.todayDate; // this.todayDate.getFullYear() + '-' + ('0' + (this.todayDate.getMonth() + 1)).slice(-2) + '-' + this.todayDate.getDate();
    this.getCategories();
    this.getLocations();
  }

  onSubmit() {
    const date = new Date();
    this.items.changeLog = [{
      logMessage : 'Item created',
      currentAmount : this.items.itemAmount,
      changeDate : date.toJSON(),
      changeAmount : 0}];
    console.log(this.items);
    this.itemsService.save(this.items).subscribe(result => this.gotoUserList());
  }

  gotoUserList() {
    this.router.navigate(['/items']);
  }


  getCategories() {
    return this.itemsService.getCategories().subscribe(data => {
      this.categories = data;
    });
  }

  getLocations() {
    return this.itemsService.getLocations().subscribe(data => {
      this.locations = data;
    });
  }
}
