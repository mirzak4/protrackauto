import { ServiceRequestStatus, ServiceType } from "./service-request.enums";

export interface ServiceRequest {
  id?: number;
  serviceType: ServiceType;
  fiscalReceiptNumber: number;
  cost: number;
  status: ServiceRequestStatus;
  requestDate: string;
  requestedBy: string;
  vehicleId: number;
  servicerId: number;
}
