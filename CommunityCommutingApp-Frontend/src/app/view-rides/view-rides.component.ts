import { RideProvider } from './../ride-provider';
import { Component, OnInit } from '@angular/core';
import { RideProviderService } from '../ride-provider.service';
import { ActivatedRoute, Router } from '@angular/router';
import { RideInfo } from '../ride-info';
import { ReturnResponse } from '../return-response';

@Component({
  selector: 'app-view-rides',
  templateUrl: './view-rides.component.html',
  styleUrls: ['./view-rides.component.css']
})
export class ViewRidesComponent implements OnInit{

  rideInfo : RideInfo[];
  response :ReturnResponse;

  constructor(private  rideProviderService : RideProviderService, private router : Router, private route : ActivatedRoute){}
  ngOnInit(): void {
    let id=this.route.snapshot.params['id'];
    console.log("id = "+ id); 
    this.rideProviderService.getRidesById(id).subscribe(data =>{
      this.rideInfo = data;
      console.log(this.rideInfo);
    })
  }

  navigateToOtherPage(tripId : String){
    this.router.navigate(["/view-ride-details/",tripId]);
  }

  delete(rider : RideInfo){
      this.rideProviderService.deleteByTripId(rider.tripId).subscribe(values =>{
         this.response = values;
         console.log("Res ",this.response.returnMsg)
        const confirmed = confirm(this.response.returnMsg);
        if(confirmed){
          this.router.navigate(["/dashboard"]);
        }
      })
      
    }
  }
//}
