import { Component, OnInit } from '@angular/core';
import { RideProvider } from '../ride-provider';
import { RideProviderService } from '../ride-provider.service';
import { Router } from '@angular/router';
import { BillService } from '../bill.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{

  rideProvider : RideProvider[];
  isRegistered : boolean;
  startDisabled: boolean = false;
  endDisabled: boolean = true;
  buttonContent: string = '';

  constructor(private  rideProviderService : RideProviderService,private billService : BillService, private router : Router){}
  ngOnInit(): void {
    this.rideProviderService.getAllRiders().subscribe(data =>{
      this.rideProvider = data;
      console.log(this.rideProvider);
       this.isRegistered = this.rideProvider.some(provider => provider.status === 'Un-Registered');
    })
  }
   addRide(rider:any){
   // localStorage.setItem("rpId", rider.rpId);
    this.router.navigate(["/api/rideProviders/bookings", rider.rpId]);
  }

  updateRide(rider:any){
    this.router.navigate(["/api/rideProviders/",rider.rpId,"update"]);
  }

  viewRides(ride : string){
    this.router.navigate(["/view-rides",ride]);
  }

  viewBillReport(rpId : String){
    this.router.navigate(["/billreport",rpId]);
  }

  startTrip() {
    this.buttonContent = 'You started the trip!';
    this.startDisabled = true;
    this.endDisabled = false;
  }

  endTrip() {
    this.buttonContent = 'Trip Ended!';
   // this.startDisabled = false;
    this.endDisabled = true;
    this.router.navigate(["/dashboard"]);
  }
}
