import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Booking } from './booking';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  constructor(private http : HttpClient) { }

  getBookings(tripId : string) : Observable<Booking[]> {
    return this.http.get<Booking[]>("http://localhost:8080/api/rideProviders/"+tripId+"/bookings");
  }

  getBookingsBySeekerId(rsId : string) : Observable<Booking[]> {
    return this.http.get<Booking[]>("http://localhost:8060/api/rideseeker/"+rsId+"/bookedrides");
  }

  bookRide(booking: Booking)  : Observable<Booking>{
    return this.http.post<Booking>("http://localhost:8060/api/rideseeker/bookRide", booking);
  }
  
  cancelBookedRide(bookingId: String)  : Observable<Booking>{
    return this.http.delete<Booking>("http://localhost:8060/api/rideseeker/"+bookingId+"/delete");
  }
  
}
