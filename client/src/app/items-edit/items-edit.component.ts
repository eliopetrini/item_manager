import {Component, NgModule, OnInit} from '@angular/core';
import {ItemLocation, Items} from '../items';
import {ItemsService} from '../items-service.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-items-edit',
  templateUrl: './items-edit.component.html',
  styleUrls: ['./items-edit.component.css']
})
export class ItemsEditComponent implements OnInit {

  item: Items;
  id: string;
  hasBeenSaved: boolean;
  base64Data: any;
  label: string;
  locations: ItemLocation[];
  categories: string[];

  constructor(private itemsService: ItemsService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.hasBeenSaved = false;
    this.route.params.subscribe(params => {
      this.id = params['id'];
    });
    this.refreshData();
    this.getCategories();
    this.getLocations();
  }

  onSave() {
    this.hasBeenSaved = true;
    const date = new Date();
    this.item.changeLog.push({
      logMessage : 'Information has been updated',
      currentAmount : -1,
      changeDate: date.toJSON(),
      changeAmount : 0});
    this.saveChanges();
  }

  saveChanges() {
    this.itemsService.save(this.item).subscribe( data => {
      this.refreshData();
    });
  }

  refreshData() {
    this.itemsService.getByID(this.id).subscribe(data => {
      this.item = data;
      this.item.changeLog.reverse();
      this.getLabel(data);
    });
  }

  getLabel(item: Items) {
    this.itemsService.getLabel(item).subscribe(data => {
      this.base64Data = data;
      this.label = 'data:image/jpeg;base64,' + this.base64Data;
    });
  }

  setAmount(change: number) {

    let message = 'One item added';
    if (change < 0) {
      message = 'One item removed';
    }

    const changeAmount = Number(change);
    this.item.itemAmount += change;
    if (this.item.itemAmount < 0) {
      this.item.itemAmount = 0;
    } else {
      const date = new Date();
      this.item.changeLog.push({
        logMessage : message,
        currentAmount : this.item.itemAmount,
        changeDate: date.toJSON(),
        changeAmount : changeAmount});
    }

    this.saveChanges();
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

  compareLocations(c1: ItemLocation, c2: ItemLocation): boolean {
    return c1 && c2 ? c1.locationId === c2.locationId : c1 === c2;
  }
}
