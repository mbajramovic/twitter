import {Component, OnInit} from '@angular/core';
import {TweetModel} from "../../../models/tweet.model";
import {TweetService} from "../../../services/tweet/tweet.service";
import {Observable} from "rxjs/Observable";
import {AuthService} from "../../../services/auth.service";
import {ActivatedRoute, Params} from "@angular/router";
import { Detail } from '../../../models/detail.model';
import { UserService } from '../../../services/user/user.service';

@Component({
  selector: 'app-user-tweets-view',
  templateUrl: './user-tweets-view.component.html',
  styleUrls: ['./user-tweets-view.component.css']
})
export class UserTweetsViewComponent implements OnInit {

  $tweets: Observable<TweetModel[]>;
  $details : Observable<Detail>;
  userName: string;

  constructor(private tweetService: TweetService, private activatedRoute: ActivatedRoute, private userService: UserService) {
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.userName = params['username'];
      this.$tweets = this.tweetService.fetchForUser(this.userName);
      this.$details = this.userService.getUserDetails(this.userName);
    });
  }

}
