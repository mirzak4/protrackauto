export enum VehicleBodyType {
  SEDAN = 1,
  HATCHBACK = 2,
  SUV = 3,
  COUPE = 4,
  CONVERTIBLE = 5,
  WAGON = 6,
  PICKUP = 7,
  VAN = 8,
  MINIVAN = 9,
  ROADSTER = 10,
  JEEP = 11,
  LIMOUSINE = 12,
  TRUCK = 13,
  BUS = 14,
  MOTORCYCLE = 15
}

export enum VehicleCategory {
  TRAVEL_CAR = 1,
  TRUCK = 2,
  MOTORCYCLE = 3,
  BUS = 4,
  VAN = 5,
  SUV = 6,
  PICKUP = 7,
  TRACTOR = 8,
  SCOOTER = 9,
  BICYCLE = 10
}

export interface Vehicle {
  id: number;
  licensePlate: string;
  firstRegistrationDate: string; 
  firstRegistrationPlace: string;
  firstLicensePlate: string;
  registrationIssueDate: string;
  registrationIssuePlace: string;
  fuelId: number;
  vehicleCategory: VehicleCategory | null;
  vehicleBodyType: VehicleBodyType | null;
  color: string;
  vehicleBrandType: string;
  registrationNumber: string;
  commercialDescription: string;
  chassisNumber: string;
  productionYear: number;
  maxWeight: number;
  payload: number;
  vehicleWeight: number;
  powerWeightRatio: number;
  seatCount: number;
  engineDisplacement: number;
  maxPower: number;
  ecoCharacteristics: string;
  catalyst: string;
  engineNumber: string;
}
