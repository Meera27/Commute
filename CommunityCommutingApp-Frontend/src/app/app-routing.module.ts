import { ViewRidesComponent } from './view-rides/view-rides.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RideProviderComponent } from './ride-provider/ride-provider.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AddRideComponent } from './add-ride/add-ride.component';
import { UpdateRideproviderComponent } from './update-rideprovider/update-rideprovider.component';
import { UpdateRideComponent } from './update-ride/update-ride.component';
import { ViewRideDetailsComponent } from './view-ride-details/view-ride-details.component';
import { BookingStatusComponent } from './booking-status/booking-status.component';
import { GenerateBillComponent } from './generate-bill/generate-bill.component';
import { ViewBillComponent } from './view-bill/view-bill.component';
import { RideseekerListComponent } from './rideseeker-list/rideseeker-list.component';
import { CreateRideseekerComponent } from './create-rideseeker/create-rideseeker.component';
import { UpdateRideseekerComponent } from './update-rideseeker/update-rideseeker.component';
import { TripListComponent } from './trip-list/trip-list.component';
import { BookRideComponent } from './book-ride/book-ride.component';
import { BookingListComponent } from './booking-list/booking-list.component';
import { HomeComponent } from './home/home.component';
import { BillReportComponent } from './bill-report/bill-report.component';
import { ViewBillProviderComponent } from './view-bill-provider/view-bill-provider.component';
import { ViewBillSeekerComponent } from './view-bill-seeker/view-bill-seeker.component';

const routes: Routes = [
{path:"api/rideProviders/new" , component : RideProviderComponent},
{path:"dashboard" , component : DashboardComponent},
{path :"bookings/:tripId", component : BookingStatusComponent},
{path :"view-ride-details/:id", component : ViewRideDetailsComponent},
{path:"api/rideProviders/bookings/:id" , component : AddRideComponent},
{path :"update-provider/:id", component : UpdateRideComponent},
{path : "generate-bill/:tripId", component : GenerateBillComponent},
{path :"view-rides/:id", component : ViewRidesComponent},
{path :"api/rideProviders/:id/update", component : UpdateRideproviderComponent},
{path : "generatebill/:tripId", component : GenerateBillComponent},
// {path : "viewbill/:tripId", component : ViewBillComponent},
{path : "viewbill-provider/:tripId", component : ViewBillProviderComponent},
{path : "viewbill-seeker/:tripId", component : ViewBillSeekerComponent},
{path : "billreport/:rpId", component : BillReportComponent},
{path : "list",component:RideseekerListComponent},
{path : "add-seeker",component:CreateRideseekerComponent},
{path : "update/:rsId",component:UpdateRideseekerComponent},
{path : "viewrides/:rsId",component:TripListComponent},
{path : "bookride/:rsId/:tripId", component : BookRideComponent},
{path : "bookedrides/:rsId", component : BookingListComponent},
{path : "", component : HomeComponent},
// {path : "", redirectTo:"/dashboard", pathMatch:"full"},
// {path:"",redirectTo:"/home",pathMatch:"full"},
{path:"**",redirectTo:"/list",pathMatch:"full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
