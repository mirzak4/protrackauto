export interface Company {
  id?: number;
  name: string;
  address: string;
  phoneNumber: string;
  email: string;
  contactPerson: string;
  companyType: 'INSURANCE_COMPANY' | 'SERVICE_PROVIDER' | 'GAS_STATION';
}
