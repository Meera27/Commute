import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Rideseeker } from './rideseeker';
import { Observable } from  'rxjs'
import { AddResponse } from './create-rideseeker/AddResponse';

@Injectable({
  providedIn: 'root'
})
export class RideseekerService {

  constructor(private http:HttpClient) { }

  getAllRideseekers():Observable<Rideseeker[]>{
    return this.http.get<Rideseeker[]>("http://localhost:8060/api/rideseeker");
  }

  addRideseeker(rideseeker:Rideseeker):Observable<AddResponse>{
   return this.http.post<AddResponse>("http://localhost:8060/api/rideseeker/new",rideseeker);
  }

  //  deleteRideseeker(rsId:string):Observable<Rideseeker>{
  //    return this.http.delete<Rideseeker>("http://localhost:8080/api/rideseeker/"+rsId);

  //  }
  deleteRideseeker(rsId:string):Observable<AddResponse>{
    return this.http.delete<AddResponse>("http://localhost:8060/api/rideseeker/"+rsId);
  }


   getById(rsId:string):Observable<Rideseeker>{
    return this.http.get<Rideseeker>("http://localhost:8060/api/rideseeker/"+rsId);
   }

  updateRideseeker(rsId:string,rideseeker:Rideseeker):Observable<AddResponse>{
    return this.http.put<AddResponse>("http://localhost:8060/api/rideseeker/"+rsId+"/update",rideseeker);

  }

}
