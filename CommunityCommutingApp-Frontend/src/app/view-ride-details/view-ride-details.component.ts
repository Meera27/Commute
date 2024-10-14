import { TripService } from './../trip.service';
import { Component } from '@angular/core';
import { RideInfo } from '../ride-info';
import { RideProviderService } from '../ride-provider.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { Trip } from '../trip';

@Component({
  selector: 'app-view-ride-details',
  templateUrl: './view-ride-details.component.html',
  styleUrls: ['./view-ride-details.component.css']
})
export class ViewRideDetailsComponent {

  ride:RideInfo;
  enabled :boolean = false;
  tripStatus : boolean = false;
  buttonText : string
  textColor ='black'
  trip : Trip;
  statusColor = 'red'
  constructor(private rideProviderService:RideProviderService,private tripService : TripService,private router:Router,private route:ActivatedRoute){}

  ngOnInit(): void {
    let id=this.route.snapshot.params['id'];
    console.log("id = ",id);
    this.rideProviderService.getRidesBytripId(id).subscribe(data=>{
    this.ride=data;
    console.log(this.ride);
  })
    this.tripService.getTripByTripId(id).subscribe(data=>{
      console.log(data);
      this.trip = data;
    })
  }

  startTrip(rider : any) {
    this.tripStatus = true;
    this.enabled = true;
    this.textColor = 'green';
    this.buttonText = 'Your trip from '+this.ride.source+' to '+this.ride.destination+' has started!';
    this.tripService.startTripById(rider.tripId, rider).subscribe(data=>{
      // this.ride=data;
      console.log(this.ride);
    })
  }

  updateRide(rider:any){
    this.router.navigate(["/update-provider",rider.tripId]);
  }
  endTrip(rider:any) {
    this.tripService.endTripById(rider.tripId, rider).subscribe(data=>{

    })
    this.router.navigate(["/generate-bill/",rider.tripId])
  }
  // onClick(){
  //     this.location.back();
  // }
  showBookings(tripId :String){
  this.router.navigate(["/bookings/",tripId])
  }
}
