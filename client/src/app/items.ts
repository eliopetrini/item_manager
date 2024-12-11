export class Items {
  id: string;
  itemName: string;
  itemDescription: string;
  itemBought: Date;
  itemExpires: Date;
  itemAmount: number;
  itemCategory: string;
  itemLocation: ItemLocation;
  itemComment: string;
  changeLog: ChangeLog[];
}

export interface ChangeLog {
  currentAmount: number;
  logMessage: string;
  changeDate: string;
  changeAmount: number;
}

export class ItemLocation {
  locationId: number;
  createdDate: string;
  description: string;
  name: string;
  itemAmount: number;
  totalItemAmount: number;
}
