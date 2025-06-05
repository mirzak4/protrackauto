export interface UserDTO {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  username?: string;
  phoneNumber: string;
  birthDate?: string;
}

export interface DriverDTO {
  id?: number;
  licenseNumber: string;
  licenseExpiry: string;    
  employmentDate: string;  
  user: UserDTO;
}
