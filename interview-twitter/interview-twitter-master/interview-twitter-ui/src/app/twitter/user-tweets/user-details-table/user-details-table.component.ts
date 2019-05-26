import { Component, OnInit, Input } from '@angular/core';
import { Detail } from '../../../models/detail.model';

@Component({
  selector: 'app-user-details-table',
  templateUrl: './user-details-table.component.html',
  styleUrls: ['./user-details-table.component.css']
})
export class UserDetailsTableComponent {

  @Input() userDetails : Detail;

}
