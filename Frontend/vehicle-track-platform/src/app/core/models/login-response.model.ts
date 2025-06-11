import { RoleEnum } from '../enums/role.enum';

export interface LoginResponse {
    email: string;
    firstName: string;
    lastName: string;
    companyId: number;
    role: RoleEnum;
    token: string;
}