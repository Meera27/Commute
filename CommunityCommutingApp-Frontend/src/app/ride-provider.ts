import { RideInfo } from "./ride-info"

export interface RideProvider {
    rpId : string,
    aadharcard : string,
    dlNo :string,
    emailId : string,
    firstName : string,
    lastName : string,
    phone :string,
    status : string
    validUpto : Date,
    rideInfo : RideInfo[]
}
