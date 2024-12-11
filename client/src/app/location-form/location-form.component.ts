import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ItemLocation} from '../items';
import {LocationService} from '../location.service';


@Component({
  selector: 'app-location-form',
  templateUrl: './location-form.component.html',
  styleUrls: ['./location-form.component.css']
})
export class LocationFormComponent implements OnInit {

  @Input() itemLocation: ItemLocation;
  @Input() showSuccess: boolean;
  @Output() dataUpdated = new EventEmitter<boolean>();
  @Output() updateType = new EventEmitter<string>();

  constructor(private locationService: LocationService) {
    this.itemLocation = new ItemLocation();
    this.showSuccess = false;
  }

  ngOnInit() {

  }

  onSubmit() {
    this.locationService.save(this.itemLocation).subscribe(result => this.confirmUpdate());
  }

  confirmUpdate() {
    this.dataUpdated.emit(true);
    this.showSuccess = true;
    if (this.itemLocation.locationId == null) {
      this.updateType.emit('add');
    }
  }

  newItemLocation() {
    this.dataUpdated.emit(false);
    this.updateType.emit('add');
    this.itemLocation = new ItemLocation();
    this.showSuccess = false;
  }
}
