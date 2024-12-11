import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'tableFilter'
})
export class ListFilterPipe implements PipeTransform {

  transform(list: any[], showEmpty: boolean, category: string, location: string, searchBy: string, sortExpiryDateBy: string) {
    let sortFunc: Function;
    if (sortExpiryDateBy === 'asc') {
      sortFunc = function(a, b) {  return Date.parse(a.itemExpires) - Date.parse(b.itemExpires); };
    } else if (sortExpiryDateBy === 'desc') {
      sortFunc = function(a, b) {  return Date.parse(b.itemExpires) - Date.parse(a.itemExpires); };
    } else {
      sortFunc = function(a, b) {  return ''; };
    }
    if (showEmpty) {
      return list.filter(item =>
        (category === 'All' || item.itemCategory === category) &&
        (location === 'All' || item.itemLocation.name === location) &&
        item.itemName.toLowerCase().includes(searchBy.toLowerCase())).sort((a, b) => sortFunc(a, b));
    } else {
      return list.filter(item =>
        item.itemAmount > 0 &&
        (category === 'All' || item.itemCategory === category) &&
        (location === 'All' || item.itemLocation.name === location) &&
        item.itemName.toLowerCase().includes(searchBy.toLowerCase())).sort((a, b) => sortFunc(a, b));
    }
  }

}
