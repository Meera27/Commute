import { RideProviderService } from './../ride-provider.service';
import { RideProvider } from './../ride-provider';
import { Component, OnInit } from '@angular/core';
import { BillService } from '../bill.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Bill } from '../bill';
import { TripService } from '../trip.service';
import { Trip } from '../trip';
import { RideInfo } from '../ride-info';

@Component({
  selector: 'app-generate-bill',
  templateUrl: './generate-bill.component.html',
  styleUrls: ['./generate-bill.component.css']
})
export class GenerateBillComponent implements OnInit{

  tripId : string;
  // carType : string;
  // fuelType : string;
  trip : Trip;
  rideInfo : RideInfo;
  // rpId! : string;

  constructor(private rideProviderService : RideProviderService, private tripService : TripService, private billService : BillService, private router : Router, private route : ActivatedRoute){}

  ngOnInit(): void {
    this.tripId = this.route.snapshot.params['tripId'];
    // this.rpId = this.route.snapshot.paramMap.get('rpId')!;
    console.log("id = "+ this.tripId); 
    this.rideProviderService.getRidesBytripId(this.tripId).subscribe(value=>{
      this.rideInfo = value;
      console.log(value)
    })
    this.tripService.getTripByTripId(this.tripId).subscribe(data=>{
      this.trip=data;
      console.log(data);
    })
  }

  onSubmit(bill : Bill){
    bill.tripId = this.tripId;
    bill.noOfOccupants = this.trip.seatsFilled;
    bill.carType=this.rideInfo.carType;
    bill.fuelType=this.rideInfo.fuelType;
    // bill.rideDate = this.trip.rideDate;

    this.billService.addBill(bill).subscribe(data=>{
    console.log(data);
     this.router.navigate(["/viewbill-provider",this.tripId]);
  });
  
}
}
