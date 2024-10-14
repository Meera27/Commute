import { GenerateBillComponent } from './generate-bill/generate-bill.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddRideComponent } from './add-ride/add-ride.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HeaderComponent } from './header/header.component';
import { RideProviderComponent } from './ride-provider/ride-provider.component';
import { UpdateRideComponent } from './update-ride/update-ride.component';
import { UpdateRideproviderComponent } from './update-rideprovider/update-rideprovider.component';
import { FormsModule, FormBuilder } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { ViewRidesComponent } from './view-rides/view-rides.component';
import { ViewRideDetailsComponent } from './view-ride-details/view-ride-details.component';
import { RideHeaderComponent } from './ride-header/ride-header.component';
import { BookingStatusComponent } from './booking-status/booking-status.component';
import { RideseekerListComponent } from './rideseeker-list/rideseeker-list.component';
import { CreateRideseekerComponent } from './create-rideseeker/create-rideseeker.component';
import { TripListComponent } from './trip-list/trip-list.component';
import { BookRideComponent } from './book-ride/book-ride.component';
import { BookingListComponent } from './booking-list/booking-list.component';
import { UpdateRideseekerComponent } from './update-rideseeker/update-rideseeker.component';
import { HomeComponent } from './home/home.component';
import { SeekerHeaderComponent } from './seeker-header/seeker-header.component';
import { CommonModule } from '@angular/common';
import { BillReportComponent } from './bill-report/bill-report.component';
import { ViewBillSeekerComponent } from './view-bill-seeker/view-bill-seeker.component';
import { ViewBillProviderComponent } from './view-bill-provider/view-bill-provider.component';

@NgModule({
  declarations: [
    AppComponent,
    AddRideComponent,
    DashboardComponent,
    HeaderComponent,
    RideProviderComponent,
    UpdateRideComponent,
    UpdateRideproviderComponent,
    ViewRidesComponent,
    ViewRideDetailsComponent,
    RideHeaderComponent,
    BookingStatusComponent,
    GenerateBillComponent,
    RideseekerListComponent,
    CreateRideseekerComponent,
    TripListComponent,
    BookRideComponent,
    BookingListComponent,
    UpdateRideseekerComponent,
    HomeComponent,
    SeekerHeaderComponent,
    BillReportComponent,
    ViewBillSeekerComponent,
    ViewBillProviderComponent
],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    CommonModule,
    HttpClientModule,
    RouterModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
