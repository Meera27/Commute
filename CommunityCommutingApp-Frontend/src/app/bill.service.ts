import { Injectable } from '@angular/core';
import { Bill } from './bill';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Billreport } from './billreport';

@Injectable({
  providedIn: 'root'
})
export class BillService {

  constructor(private http : HttpClient) { }

  addBill(bill: Bill) : Observable<Bill> {
    return this.http.post<Bill>("http://localhost:8070/api/bill/new", bill);
  }

  getBillByTripId(tripId: String) : Observable<Bill>{
    return this.http.get<Bill>("http://localhost:8070/api/bill/" + tripId);
  }
    
  getBillByRpId(rpId: String) : Observable<Billreport[]>{
    return this.http.get<Billreport[]>("http://localhost:8070/api/bill/report/" + rpId);
    }

}
