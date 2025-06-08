export enum VehicleBodyType {
  Sedan = 1,
  Hatchback,
  SUV,
  Coupe,
  Convertible,
  Wagon,
  Pickup,
  Van,
  Minivan,
  Roadster,
  Jeep,
  Limousine,
  Truck,
  Bus,
  Motorcycle
}

export enum VehicleCategory {
  TravelCar = 1,
  Truck,
  Motorcycle,
  Bus,
  Van,
  SUV,
  Pickup,
  Tractor,
  Scooter,
  Bicycle
}

export interface VehicleDTO {
  id?: number;
  licensePlate?: string;
  firstRegistrationDate?: string;
  firstRegistrationPlace?: string;
  firstLicensePlate?: string;
  registrationIssueDate?: string;
  registrationIssuePlace?: string;
  fuelId?: number;
  vehicleCategory?:  VehicleCategory | null;
  vehicleBodyType?:  VehicleBodyType | null;
  color?: string;
  vehicleBrandType?: string;
  registrationNumber?: string;
  commercialDescription?: string;
  chassisNumber?: string;
  productionYear?: number;
  maxWeight?: number;
  payload?: number;
  vehicleWeight?: number;
  powerWeightRatio?: number;
  seatCount?: number;
  engineDisplacement?: number;
  maxPower?: number;
  ecoCharacteristics?: string;
  catalyst?: string;
  engineNumber?: string;
}
