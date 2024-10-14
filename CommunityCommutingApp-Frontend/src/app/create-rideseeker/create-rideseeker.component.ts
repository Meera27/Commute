import { Component } from '@angular/core';
import { Rideseeker } from '../rideseeker';
import { RideseekerService } from '../rideseeker.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-rideseeker',
  templateUrl: './create-rideseeker.component.html',
  styleUrls: ['./create-rideseeker.component.css']
})
export class CreateRideseekerComponent {

  constructor(private rideseekerService:RideseekerService, private router:Router){}


  onSubmit(rideseeker:Rideseeker){
    console.log(rideseeker);
    this.rideseekerService.addRideseeker(rideseeker).subscribe(data=>{
      console.log(data);
      this.router.navigate(["/list"]);
    });

    
  }

}
