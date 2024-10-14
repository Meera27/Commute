import { BillService } from './../bill.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Bill } from '../bill';

@Component({
  selector: 'app-view-bill',
  templateUrl: './view-bill.component.html',
  styleUrls: ['./view-bill.component.css']
})
export class ViewBillComponent implements OnInit {

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
