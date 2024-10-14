import { Component, OnInit } from '@angular/core';
import { Trip } from '../trip';
import { TripService } from '../trip.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-trip-list',
  templateUrl: './trip-list.component.html',
  styleUrls: ['./trip-list.component.css']
})
export class TripListComponent implements OnInit{
  trips:Trip[];
  
  constructor(private tripService:TripService, private router : Router, private route : ActivatedRoute){}

  ngOnInit(): void {
    //let rsId=this.route.snapshot.params['id'];
    this.tripService.getAllTrips().subscribe(data=>{
      this.trips=data;
      console.log(data);
    })
  }

  BookRide(trip:Trip){
     let rsId=this.route.snapshot.params['rsId'];
    
    console.log(rsId);
    this.router.navigate(["/bookride", rsId,trip.tripId]);
    
  }
}
