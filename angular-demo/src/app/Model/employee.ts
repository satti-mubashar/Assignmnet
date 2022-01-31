import { Department } from "./department";

export class Employee {
    id: number;
    salary:number;
    firstName: string;
    lastName: string;
    email: string;
    phoneNumber: string;
    department : Department;
    constructor(){
        this.department = new Department();
    }
}
