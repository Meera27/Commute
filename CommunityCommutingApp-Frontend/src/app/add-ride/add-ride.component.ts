import { Component } from '@angular/core';
import { RideInfo } from '../ride-info';
import { RideProviderService } from '../ride-provider.service';
import { ActivatedRoute, Router } from '@angular/router';
import { RideProvider } from '../ride-provider';

@Component({
  selector: 'app-add-ride',
  templateUrl: './add-ride.component.html',
  styleUrls: ['./add-ride.component.css']
})
export class AddRideComponent {

  rideProviderInter : RideProvider;
  minDate: string;
  minTime: string;
  source : string;
  destination : string;


  constructor(private rideProviderService:RideProviderService, private router:Router, private route : ActivatedRoute){
    const today = new Date();
    this.minDate = today.toISOString().slice(0, 10);

    // const now = new Date();
    // const hours = now.getHours().toString().padStart(2, '0');
    // const minutes = now.getMinutes().toString().padStart(2, '0');
    // this.minTime = `${hours}:${minutes}`;
  }
 
  ngOnInit(): void {
    let id=this.route.snapshot.params['id'];
    console.log("id = "+ id);  
    this.rideProviderService.getById(id).subscribe(data=>{
      this.rideProviderInter=data;
    })
  }
  onSubmit(rideInfo : RideInfo){
    //  console.log(this.selectedValue);
      console.log("Id before "+ rideInfo.rpId);
      rideInfo.rpId = this.rideProviderInter.rpId;
      let id =  rideInfo.rpId;
      console.log("Id is "+ id);
      console.log(rideInfo);
      this.rideProviderService.addNewRide(id, rideInfo).subscribe(data=>{
      console.log(data);
      this.router.navigate(["/dashboard"]);
    });

    }
    // checkInputs(){
    //   if (this.source == this.destination){
    //     this.error = true;
    //   }
    //   else{
    //     this.error = false;
    //   }
    // }
}

