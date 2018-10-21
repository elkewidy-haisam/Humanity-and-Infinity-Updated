export class Preview {
  public previewTitle: string;
  public previewSynopsis: string;
  public previewImagePath: string;
  constructor(previewTitle: string, previewSynopsis: string, previewImagePath: string) {
    this.previewTitle = previewTitle;
    this.previewSynopsis = previewSynopsis;
    this.previewImagePath = previewImagePath;
  }
}
