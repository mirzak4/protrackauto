import { ServiceRequestStatus, ServiceType } from "./enums/service-request.enums";

export interface ServiceRequest {
  companyName?: string;
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
