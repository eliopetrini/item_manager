import { Component, OnInit } from '@angular/core';
import {ItemLocation, Items} from '../items';
import {LocationService} from '../location.service';
import {ItemsService} from '../items-service.service';

@Component({
  selector: 'app-location-list',
  templateUrl: './location-list.component.html',
  styleUrls: ['./location-list.component.css']
})
export class LocationListComponent implements OnInit {

  locations: ItemLocation[];
  base64Data: any;
  labels: any;
  locationEdited: ItemLocation;
  dataUpdated: boolean;
  updateType = 'add';
  locationToBeDeleted: ItemLocation;

  constructor(private locationService: LocationService) {
  }

  ngOnInit() {
    this.locationEdited = new ItemLocation();
    this.labels = new Map<string, any>();
    this.locationService.findAll().subscribe(data => {
      this.locations = data;
       this.locations.forEach(function (value) {
        this.getLabels(value);
       }.bind(this));
    });
  }

  delete(itemLocation: ItemLocation) {
    this.locationService.delete(itemLocation).subscribe(data => {
      this.ngOnInit();
      this.updateType = 'deleted';
      this.locationToBeDeleted = null;
    });
  }

  getLabels(itemLocation: ItemLocation) {
    this.locationService.getLabel(itemLocation).subscribe(data => {
      this.base64Data = data;
      this.labels.set(itemLocation.locationId, 'data:image/jpeg;base64,' + this.base64Data);
      return null;
    });
  }

  getOutput(dataUpdated: boolean) {
    this.dataUpdated = dataUpdated;
    if (dataUpdated === true) {
      this.ngOnInit();
    }
  }

  promptDelete(itemLocation: ItemLocation) {
    this.updateType = 'delete';
    this.locationToBeDeleted = itemLocation;
  }

  getUpdateType(updateType: string) {
    this.updateType = updateType;
  }
}
