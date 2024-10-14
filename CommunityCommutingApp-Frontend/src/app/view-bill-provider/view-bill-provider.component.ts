import { Component } from '@angular/core';
import { Bill } from '../bill';
import { BillService } from '../bill.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-view-bill-provider',
  templateUrl: './view-bill-provider.component.html',
  styleUrls: ['./view-bill-provider.component.css']
})
export class ViewBillProviderComponent {
  
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