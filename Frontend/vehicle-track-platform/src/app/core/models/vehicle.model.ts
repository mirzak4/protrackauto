export enum VehicleBodyType {
  SEDAN = 'SEDAN',
  SUV = 'SUV',
  TRUCK = 'TRUCK',
  VAN = 'VAN',
  HATCHBACK = 'HATCHBACK',
  COUPE = 'COUPE',
  CONVERTIBLE = 'CONVERTIBLE',
  WAGON = 'WAGON'
}

export enum VehicleCategory {
  PASSENGER = 'PASSENGER',
  COMMERCIAL = 'COMMERCIAL',
  MOTORCYCLE = 'MOTORCYCLE',
  SPECIAL = 'SPECIAL'
}

export interface VehicleDTO {
  id: number;
  licensePlate: string;
  firstRegistrationDate: string;
  firstRegistrationPlace: string;
  firstLicensePlate: string;
  registrationIssueDate: string;
  registrationIssuePlace: string;
  fuelId: number;
  vehicleCategory: VehicleCategory;
  vehicleBodyType: VehicleBodyType;
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