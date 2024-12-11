import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ItemLocation, Items} from './items';
import { Observable } from 'rxjs/Observable';
import {environment} from '../environments/environment';

@Injectable()
export class LocationService {

  private locationsURL: string;
  private appURL: string;

  constructor(private http: HttpClient) {
    this.appURL = location.origin;
    this.locationsURL = window.location.protocol + '//' + window.location.hostname + ':' + environment.backendPort + '/locations';
    console.log(this.locationsURL);
  }

  public findAll(): Observable<ItemLocation[]> {
    return this.http.get<ItemLocation[]>(this.locationsURL);
  }

  public delete(itemLocation: ItemLocation) {
    return this.http.post<ItemLocation>(this.locationsURL + '/delete', itemLocation);
  }

  public getLabel(itemLocation: ItemLocation) {
    let queryParams = new HttpParams();
    queryParams = queryParams.append('name', String(itemLocation.name)).append('id', String(itemLocation.locationId)).append('description', String(itemLocation.description)).append('appURL', String(this.appURL));
    return this.http.get(this.locationsURL + '/labels', {params: queryParams, responseType: 'text'});
  }

  save(itemLocation: ItemLocation) {
    return this.http.post<ItemLocation>(this.locationsURL + '/update', itemLocation);
  }
}
