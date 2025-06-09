import { RoleEnum } from '../enums/role.enum';

export interface UserInfo {
    firstName: string;
    lastName: string;
    email: string;
    companyId: number;
    role: RoleEnum;
}