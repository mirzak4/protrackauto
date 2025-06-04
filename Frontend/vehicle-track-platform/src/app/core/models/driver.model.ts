export interface UserDTO {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
}

export interface DriverDTO {
  id: number;
  userId: number;
  licenseNumber: string;
  licenseExpiry: string;
  employmentDate: string;
  user: UserDTO;
}
