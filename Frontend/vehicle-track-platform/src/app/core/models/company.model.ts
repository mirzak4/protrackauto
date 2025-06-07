export interface CompanyDTO {
  id: number;
  name: string;
  address: string;
  phone: string;
  email?: string;
  companyType: number;
}