import { Component, OnInit } from '@angular/core';
import { Rideseeker } from '../rideseeker';
import { RideseekerService } from '../rideseeker.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-rideseeker',
  templateUrl: './update-rideseeker.component.html',
  styleUrls: ['./update-rideseeker.component.css']
})
export class UpdateRideseekerComponent implements OnInit {

  rideseeker:Rideseeker;
  constructor(private rideseekerService:RideseekerService,private router:Router, private route:ActivatedRoute){

  }
  ngOnInit(): void {
    let rsId=this.route.snapshot.params['rsId'];
    console.log("rsId = ",rsId);
    this.rideseekerService.getById(rsId).subscribe(data=>{
      this.rideseeker=data;
      console.log(this.rideseeker);
    })
  }

  onSubmit(rideseeker:Rideseeker){
    this.rideseekerService.updateRideseeker(rideseeker.rsId,rideseeker)
     .subscribe(data=>{
      console.log(data);
      this.router.navigate(["/list"]);
    })

  }

}
