export class Comic {
  
  public title: string;
  public synopsis: string;
  public imagePath: string;
  public chapter: number;
  
  constructor(chapter: number, title: string, synopsis: string, imagePath: string) {
    this.chapter = chapter;
    this.title = title;
    this.synopsis = synopsis;
    this.imagePath = imagePath;
    
  }
}
