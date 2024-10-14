import { Component, OnInit } from '@angular/core';
import { Rideseeker } from '../rideseeker';
import { RideseekerService } from '../rideseeker.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-rideseeker-list',
  templateUrl: './rideseeker-list.component.html',
  styleUrls: ['./rideseeker-list.component.css']
})
export class RideseekerListComponent implements OnInit{

  rideseekers:Rideseeker[];

  constructor(private rideseekerService:RideseekerService,private router:Router){}
  ngOnInit(): void {

    this.rideseekerService.getAllRideseekers().subscribe(data=>{
      this.rideseekers=data;
      console.log(this.rideseekers)
    })

  }

   onDelete(rsId:string){
     console.log(rsId)
     this.rideseekerService.deleteRideseeker(rsId).subscribe(data=>{
       console.log(data);
       
       this.rideseekerService.getAllRideseekers().subscribe(data=>{
       this.rideseekers=data;
          
     })
    })

}

onBook(rsId:string){

  this.router.navigate(["/viewrides",rsId]);

}

viewBookings(rsId:string){
  this.router.navigate(["/bookedrides",rsId]); 
}

onUpdate(rsId:string){

  this.router.navigate(["/update",rsId]);
  // this.router.navigate(["/bookedrides",rsId]);

}

}
