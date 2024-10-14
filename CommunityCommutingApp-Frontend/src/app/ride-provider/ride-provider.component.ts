import { Component } from '@angular/core';
import { RideProvider } from '../ride-provider';
import { RideProviderService } from '../ride-provider.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ride-provider',
  templateUrl: './ride-provider.component.html',
  styleUrls: ['./ride-provider.component.css']
})
export class RideProviderComponent{
  minDate: string;
  

  constructor(private rideProviderService:RideProviderService, private router:Router){
    const today = new Date();
    this.minDate = today.toISOString().slice(0, 10);
  }


  onSubmit(rideProvider : RideProvider){
    console.log(rideProvider);
    this.rideProviderService.addRideProvider(rideProvider).subscribe(data=>{
      console.log(data);
      this.router.navigate(["/dashboard"]);
    });
    }
 
  
}
