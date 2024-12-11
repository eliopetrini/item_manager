import { Component, OnInit } from '@angular/core';
import {ItemLocation, Items} from '../items';
import { ItemsService } from '../items-service.service';
import { ActivatedRoute } from '@angular/router';
import { ListFilterPipe } from './list-filter.pipe';

@Component({
  selector: 'app-user-list',
  templateUrl: './items-list.component.html',
  styleUrls: ['./items-list.component.css']
})
export class ItemsListComponent implements OnInit {

  items: Items[];
  base64Data: any;
  labels: any;
  showEmpty: boolean;
  searchBy: string;
  locations: ItemLocation[];
  categories: string[];
  selectedCategory: string;
  selectedLocation: string;
  defaultLocationId: number;
  sortExpiryDateBy: string;

  constructor(private itemsService: ItemsService, private route: ActivatedRoute) {
    this.showEmpty = false;
  }

  ngOnInit() {
    this.getCategories();
    this.getLocations();
    this.selectedCategory = 'All';
    this.sortExpiryDateBy = '';
    this.searchBy = '';
    this.labels = new Map<string, any>();
    this.itemsService.findAll().subscribe(data => {
      this.items = data;
      this.items.forEach(function (value) {
        this.getLabels(value);
      }.bind(this));
    });
  }

  delete(items: Items) {
    this.itemsService.delete(items).subscribe(data => {
      this.ngOnInit();
    });
  }

  getLabels(items: Items) {
    this.itemsService.getLabel(items).subscribe(data => {
      this.base64Data = data;
      this.labels.set(items.id, 'data:image/jpeg;base64,' + this.base64Data);
      return null;
    });
  }


  getCategories() {
    return this.itemsService.getCategories().subscribe(data => {
      this.categories = data;
    });
  }

  getLocations() {
    return this.itemsService.getLocations().subscribe(data => {
      this.locations = data;
      this.route.params.subscribe(params => {
        this.defaultLocationId = params['locationId'];
        if (this.defaultLocationId === undefined) {
          this.selectedLocation = 'All';
        } else {
          const result = this.locations.find(i => i.locationId == this.defaultLocationId);
          this.selectedLocation = result.name;
        }
      });
    });
  }

  calculateDiff(dateSent){
    let currentDate = new Date();
    dateSent = new Date(dateSent);
    return Math.floor((Date.UTC(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate()) - Date.UTC(dateSent.getFullYear(), dateSent.getMonth(), dateSent.getDate()) ) / (1000 * 60 * 60 * 24));
  }


}
