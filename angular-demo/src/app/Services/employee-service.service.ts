import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class EmployeeServiceService {

  baseUrl: string = "";

  constructor(private http: HttpClient) {
    this.baseUrl = "http://localhost:8080/api/v1/";
  
  }

  getAll():Observable<any> {
    return this.http.get(this.baseUrl + "employees");
  }  

  delete(id: any): Observable<any> {
    return this.http.delete(this.baseUrl+"employees/"+id);
  }

  create(data:any): Observable<any> {
    console.log("request data is "+ data);
    return this.http.post(this.baseUrl+"employees", data);
  }
  update(id:any, data:any): Observable<any> {
    return this.http.put(this.baseUrl+"employees/"+id, data);
  }


}
  /* get(id): Observable<any> {
    return this.http.get(`${baseUrl}/${id}`);
  }
  

 
  delete(id): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }

  findByTitle(title): Observable<any> {
    return this.http.get(`${baseUrl}?title=${title}`);
  } */

