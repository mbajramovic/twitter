import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Detail } from '../../models/detail.model';
import { User } from '../../models/user.model.';

const DETAIL_ENDPOINT  = '/api/details';
const REGISTER_ENDPOINT = '/api/user';


@Injectable()
export class UserService {

  constructor(private http : HttpClient) { }

  getUserDetails(username : string) {
    return this.http.get<Detail>(DETAIL_ENDPOINT + '/' + username);
  }

  create(user : User) {
    return this.http.post<User>(REGISTER_ENDPOINT, user);
  }
}
