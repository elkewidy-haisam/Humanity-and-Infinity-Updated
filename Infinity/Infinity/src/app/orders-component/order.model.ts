import { Comic } from '../comics-component/comic.model';
export class Order {
  
  public username: string;
  public comics: Comic[];
  
    constructor(username: string, comics: Comic[]) {
      
    this.username = username;
    this.comics = comics;
      
    }
  
}
