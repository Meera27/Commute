import { Component, OnInit } from '@angular/core';
import { BookingService } from '../booking.service';
import { Booking } from '../booking';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-booking-status',
  templateUrl: './booking-status.component.html',
  styleUrls: ['./booking-status.component.css']
})
export class BookingStatusComponent {
  
  bookings:Booking[];

  constructor(private bookingService:BookingService, private router : Router, private route : ActivatedRoute){}

  ngOnInit(): void {
    let tripId = this.route.snapshot.params['tripId'];
    console.log("id = "+ tripId); 
    this.bookingService.getBookings(tripId).subscribe(data=>{
      this.bookings=data;
      console.log("Bookings", this.bookings);


    })
  }
//   onClick(){
//     this.location.back();
// }
}
