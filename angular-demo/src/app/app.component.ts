import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Employee } from '../app/Model/employee';
import { EmployeeServiceService } from './Services/employee-service.service';
import { Router } from '@angular/router';
import { Department } from './Model/department';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular-demo';
  isShown: boolean = true;
  employee: any;

  employees: Array<Employee>;
  displayedColumns: string[] = ['ID', 'Name', 'Email', 'Phone', 'Salary','Department','Action'];
  articles: any;
  isLoaded : boolean = false;
  showAddEmployee : boolean =false;
  dataSource : any;

  constructor(private employeeService: EmployeeServiceService,private router: Router) {
    this.employee = new Employee();
    this.employees = new Array<Employee>();
    this.dataSource = new MatTableDataSource<Employee>(this.employees);
  

  }

  ngOnInit(): void {
    this.getAllEmployees();
  }

  getAllEmployees() {
    this.employeeService.getAll().subscribe((data: any) => {
     // this.employees = this.articles.data.content;
     this.articles = data.data.content;
      this.dataSource.data = this.articles;
    
  });
  }
  onSubmit() {
    console.log(this.employee);
  }

  
  deleteEmplpoyee(em: Employee, index : number) {
    console.log(index);
    this.employeeService.delete(em.id).subscribe((data: any) => {
      console.log("Data response is "+data);
      this.employees.splice(index, 1);
      this.dataSource = new MatTableDataSource(this.employees);
    });
  }

  
  updateEmplpoyee(em: Employee) {
    console.log(em);
  }

  openAddEmployee(){
    this.showAddEmployee =true;
  }

  openListEmployee(){
    this.showAddEmployee =false;
  }

}
