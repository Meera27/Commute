import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RideProvider } from './ride-provider';
import { Observable } from 'rxjs';
import { RideInfo } from './ride-info';
import { ReturnResponse } from './return-response';

@Injectable({
  providedIn: 'root'
})
export class RideProviderService {

  constructor(private http : HttpClient) { }

  getAllRiders():Observable<RideProvider[]>{
    return this.http.get<RideProvider[]>("http://localhost:8080/api/rideProviders");
  }

  addRideProvider(rideProvider : RideProvider):Observable<ReturnResponse>{
    return this.http.post<ReturnResponse>("http://localhost:8080/api/rideProviders/new",rideProvider);
  }

  getById(rpId : string):Observable<RideProvider>{
    return this.http.get<RideProvider>("http://localhost:8080/api/rideProviders/"+rpId);
  }

  getRidesById(rpId : string):Observable<RideInfo[]>{
    return this.http.get<RideInfo[]>("http://localhost:8080/api/rideProviders/rides/"+rpId);
  }

  addNewRide(rpId : string, rideInfo :RideInfo):Observable<void>{
    return this.http.post<void>("http://localhost:8080/api/rideProviders/rides/addnewRide/"+rpId, rideInfo);
  }

  updateRideProvider(rpId:string,rideProvider:RideProvider):Observable<ReturnResponse>{
    return this.http.put<ReturnResponse>("http://localhost:8080/api/rideProviders/"+rpId+"/update",rideProvider);
  }

  // updateRideInfo(tripId:string, rideInfo:RideInfo):Observable<ReturnResponse>{
  //   return this.http.put<ReturnResponse>("http://localhost:8080/api/rideProviders/"+tripId,rideInfo);
  // }

  getRidesBytripId(tripId : string):Observable<RideInfo>{
    return this.http.get<RideInfo>("http://localhost:8080/api/rideProviders/viewRides/"+tripId);
  }
  updateRidesByTripId(tripId:string,rideInfo:RideInfo):Observable<String>{
    return this.http.put<String>("http://localhost:8080/api/rideProviders/"+tripId+"/bookings",rideInfo);
  }
  deleteByTripId(tripId : string) : Observable<ReturnResponse>{
    return this.http.delete<ReturnResponse>("http://localhost:8080/api/rideProviders/bookings/"+tripId);
  }
}
