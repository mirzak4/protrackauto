export interface CompanyDTO {
  id: number;
  name: string;
  address: string;
  phoneNumber: string;
  email?: string;
  companyType: CompanyType;
  contactPerson: string;
}

export enum CompanyType {
  INSURANCE_COMPANY = 1,
  SERVICE_PROVIDER = 2,
  GAS_STATION = 3
}
