import { User } from "./user.model.";

export interface TweetModel {
  id: number,
  content: string,
  author: User
}
