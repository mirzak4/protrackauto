import { PaymentStatus, ViolationType } from "./enums/traffic-fine.enum";

export interface TrafficFine {
  id?: number;
  issueDate: string;        // ISO date string
  paymentDueDate: string;   // ISO date string
  violationDescription: string;
  violationType: ViolationType;
  location: string;
  paymentStatus: PaymentStatus;
  amount: number;
  vehicleId: number;
  driverId: number;
}
