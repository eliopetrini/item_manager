import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ItemLocation, Items} from './items';
import { Observable } from 'rxjs/Observable';
import { environment } from '../environments/environment';
import {ItemsListComponent} from './items-list/items-list.component';

@Injectable()
export class ItemsService {

  private itemsURL: string;
  private itemsURLDelete: string;
  private itemsURLLabel: string;
  private itemsEditURL: string;
  private itemsBaseURL: string;
  private appURL: string;

  constructor(private http: HttpClient) {
    this.appURL = location.origin;
    this.itemsBaseURL = window.location.protocol + '//' + window.location.hostname + ':' + environment.backendPort;
    this.itemsURL = this.itemsBaseURL + '/items';
    this.itemsURLDelete = this.itemsBaseURL + '/delete';
    this.itemsURLLabel = this.itemsBaseURL + '/get-label';
    this.itemsEditURL = this.itemsBaseURL + '/items/';
  }

  public findAll(): Observable<Items[]> {
    return this.http.get<Items[]>(this.itemsURL);
  }

  public save(items: Items) {
    return this.http.post<Items>(this.itemsURL, items);
  }

  public delete(items: Items) {
    let queryParams = new HttpParams();
    queryParams = queryParams.append('id', String(items.id));
    return this.http.get<any>(this.itemsURLDelete, {params: queryParams});
  }

  public getLabel(items: Items) {
    let queryParams = new HttpParams();
    queryParams = queryParams.append('name', String(items.itemName)).append('id', String(items.id)).append('description', String(items.itemDescription)).append('appURL', String(this.appURL));
    return this.http.get(this.itemsURLLabel, {params: queryParams, responseType: 'text'});
  }

  public getByID(id: string) {
    let queryParams = new HttpParams();
    queryParams = queryParams.append('id', String(id));
    return this.http.get<Items>(this.itemsEditURL + id);
  }

  public getCategories(): Observable<string[]> {
    return this.http.get<string[]>(this.itemsURL + '/groups/category');
  }

  public getLocations(): Observable<ItemLocation[]> {
    return this.http.get<ItemLocation[]>(this.itemsURL + '/groups/location');
  }
}
