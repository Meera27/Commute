import { Component } from '@angular/core';
import { RideProvider } from '../ride-provider';
import { RideProviderService } from '../ride-provider.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-rideprovider',
  templateUrl: './update-rideprovider.component.html',
  styleUrls: ['./update-rideprovider.component.css']
})
export class UpdateRideproviderComponent {

  ride:RideProvider;
  minDate: string;

  constructor(private rideProviderService:RideProviderService,private router:Router,private route:ActivatedRoute){
    const today = new Date();
    this.minDate = today.toISOString().slice(0, 10);
  }

  ngOnInit(): void {
    let id=this.route.snapshot.params['id'];
    console.log("id = ",id);
    this.rideProviderService.getById(id).subscribe(data=>{
      this.ride=data;
      console.log(this.ride);
    })
  }

  onSubmit(rideProvider : RideProvider){
     this.rideProviderService.updateRideProvider(this.ride.rpId,rideProvider)
     .subscribe(data=>{
      console.log(data);
      this.router.navigate(["/dashboard"]);
     })
  }
}