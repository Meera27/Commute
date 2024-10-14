import { BillService } from './../bill.service';
import { Component } from '@angular/core';
import { Bill } from '../bill';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-view-bill-seeker',
  templateUrl: './view-bill-seeker.component.html',
  styleUrls: ['./view-bill-seeker.component.css']
})
export class ViewBillSeekerComponent {

  bill:Bill;

  constructor(private billService:BillService,private router:Router, private route:ActivatedRoute){}
  ngOnInit(): void {
    let tripId = this.route.snapshot.params['tripId'];

     console.log("id = "+ tripId);
     this.billService.getBillByTripId(tripId).subscribe(data=>{
      this.bill=data;
     })
  }
}