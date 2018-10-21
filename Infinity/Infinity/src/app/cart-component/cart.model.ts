import { Comic } from '../comics-component/comic.model';
export class Cart {
  
  public username: string;
  public comics: Comic[];
  
    constructor(user: string, comics: Comic[]) {
      
    this.username = user;
    this.comics = comics;
      
    }
  
}
