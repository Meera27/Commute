import { Component, OnInit } from '@angular/core';
import { BookingService } from '../booking.service';
import { Booking } from '../booking';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-booking-list',
  templateUrl: './booking-list.component.html',
  styleUrls: ['./booking-list.component.css']
})
export class BookingListComponent implements OnInit{
  rsId : string;
  bookings:Booking[];

  constructor(private bookingService:BookingService, private router : Router, private route : ActivatedRoute){}

  ngOnInit(): void {
    this.rsId = this.route.snapshot.params['rsId'];
    console.log("id = "+ this.rsId); 
    this.bookingService.getBookingsBySeekerId(this.rsId).subscribe(data=>{
      this.bookings=data;
    })
  }

  CancelBooking(bookingId : String){
    this.bookingService.cancelBookedRide(bookingId).subscribe(data=>{
      console.log(data);
      this.bookingService.getBookingsBySeekerId(this.rsId).subscribe(data=>{
        this.bookings=data;
      })
    })
  }
  ViewBill(tripId : any){
    this.router.navigate(["/viewbill-seeker/",tripId])
  }
}
