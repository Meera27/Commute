import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Trip } from './trip';
import { ReturnResponse } from './return-response';

@Injectable({
  providedIn: 'root'
})
export class TripService {

  constructor(private http : HttpClient) { }
  
  getAllTrip() : Observable<Trip[]> {
    return this.http.get<Trip[]>("http://localhost:8090/api/tripmanager/trips");
  }
  getAllTrips() : Observable<Trip[]> {
    return this.http.get<Trip[]>("http://localhost:8060/api/rideseeker/viewrides/list");
  }

  getTripByTripId(tripId: string) : Observable<Trip>{
      return this.http.get<Trip>("http://localhost:8090/api/tripmanager/" + tripId);
  }
  startTripById(tripId : string, trip  : Trip) :Observable<ReturnResponse>{
    return this.http.put<ReturnResponse>("http://localhost:8090/api/tripmanager/startride/"+tripId, trip);
  }
  endTripById(tripId : string, trip  : Trip) : Observable<ReturnResponse>{
      return this.http.put<ReturnResponse>("http://localhost:8090/api/tripmanager/endride/"+tripId, trip);
  }
}



// addRide(trip : Trip) : Observable<Trip> {
//   return this.http.post<Trip>("http://localhost:8080/api/tripmanager/new", trip);
// }
// 
// updateRide(tripId : string, trip : Trip) : Observable<Trip> {
//   return this.http.put<Trip>("http://localhost:8080/api/tripmanager/" + tripId + "/update", trip);
// }

// getRide(tripId : string) : Observable<Trip> {
//   return this.http.get<Trip>("http://localhost:8080/api/tripmanager/" + tripId);
// }