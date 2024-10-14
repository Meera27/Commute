import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';


import { Booking } from '../booking';
import { BookingService } from '../booking.service';
import { TripService } from '../trip.service';
import { Trip } from '../trip';

@Component({
  selector: 'app-book-ride',
  templateUrl: './book-ride.component.html',
  styleUrls: ['./book-ride.component.css']
})
export class BookRideComponent implements OnInit{

  tripId!:string;
  rsId!:string;
  trip:Trip;
  numbers: number[];

  constructor(private tripService:TripService, private bookingService:BookingService, private router : Router, private route : ActivatedRoute){}

  ngOnInit(): void {
    this.rsId = this.route.snapshot.paramMap.get('rsId')!;
    this.tripId = this.route.snapshot.paramMap.get('tripId')!;
    this.tripService.getTripByTripId(this.tripId).subscribe(data=>{
      this.trip=data;

      this.numbers = Array.from({length: (this.trip.noOfSeat-this.trip.seatsFilled)}, (_, i) => i + 1);
    })
  }

  onSubmit(booking : Booking){
      booking.tripId = this.tripId;
      booking.seekerId = this.rsId;
      console.log(this.tripId,this.rsId);
      booking.status = "booked";
      console.log(booking);
      this.bookingService.bookRide(booking).subscribe(data=>{
      console.log(data);
      this.router.navigate(["/trips"]);
    });

    }
}
