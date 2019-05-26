import { Author } from "./author.model";

export interface TweetModel {
  id: number,
  content: string,
  author: Author
}
