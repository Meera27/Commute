export interface Bill {
    billId:string,
    tripId:string, 
    // rpId : string,
    noOfKm:number, 
    totalBill:number,
    noOfOccupants:number, 
    feeId:string, 
    costPerOccupant:number,
    carType:string,
    fuelType:string
    // rideDate:Date
}
