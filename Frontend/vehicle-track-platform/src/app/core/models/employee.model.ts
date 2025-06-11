export interface Employee {
  id?: number;
  companyId: number;
  userId: number;
  company?: {
    id: number;
    name: string;
    address: string;
    phoneNumber: string;
    email: string;
    contactPerson: string;
    companyType?: number;
  };
  user: {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    phoneNumber: string;
    birthDate: string | null;
    roleId: number;
    username?: string;
    password?: string | null;
  };
}
