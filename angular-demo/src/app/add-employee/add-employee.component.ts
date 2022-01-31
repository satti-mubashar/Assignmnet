import { Component, OnInit } from '@angular/core';
import { Employee } from '../Model/employee';
import { DepartmentService } from '../Services/department.service';
import { EmployeeServiceService } from '../Services/employee-service.service';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {

  employee: Employee;
  employees : any;
  departments :any;
  constructor(private employeeService: EmployeeServiceService,
    private departmentService: DepartmentService) {
    this.employee = new Employee();
    //this.employees = new Array<Employee>();
  }

  ngOnInit(): void {
    this.getAllEmployees();
    this.getAllDepartments();
  }

  onSubmit() {
    console.log("request data is =---> " + this.employee);
    this.employeeService.create(this.employee).subscribe((data: any) => {
      console.log("Data response is " + data);

    });
  }

  getAllEmployees() {
    this.employeeService.getAll().subscribe((data: any) => {
      this.employees = data.data.content;
    });
  }

  getAllDepartments() {
    this.departmentService.getAll().subscribe((data: any) => {
      this.departments = data.data.content;
    });
  }

  mangerChange(value:any){
    alert(JSON.stringify(value));
  }

}
