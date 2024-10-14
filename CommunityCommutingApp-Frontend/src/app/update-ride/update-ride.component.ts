import { RideInfo } from './../ride-info';
import { RideProviderService } from './../ride-provider.service';
import { RideProvider } from './../ride-provider';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-ride',
  templateUrl: './update-ride.component.html',
  styleUrls: ['./update-ride.component.css']
})
export class UpdateRideComponent{

  ride:RideInfo;

  constructor(private rideProviderService:RideProviderService,private router:Router,private route:ActivatedRoute){}

  ngOnInit(): void {
    let id=this.route.snapshot.params['id'];
    this.rideProviderService.getRidesBytripId(id).subscribe(data=>{
      this.ride=data;
      console.log(this.ride.tripId);
    })
  }

  onSubmit(rideInfo:RideInfo){
    rideInfo.tripId = this.ride.tripId;
    this.rideProviderService.updateRidesByTripId(this.ride.tripId,rideInfo)
    .subscribe(data=>{
     console.log(data);
     this.router.navigate(["/dashboard"]);
    })
 }
 
}