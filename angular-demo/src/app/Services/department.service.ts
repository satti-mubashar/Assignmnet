import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  baseUrl: string = "";

  constructor(private http: HttpClient) {
    this.baseUrl = "http://localhost:8080/api/v1/";
  
  }

  getAll():Observable<any> {
    return this.http.get(this.baseUrl + "departments");
  }  

  delete(id: any): Observable<any> {
    return this.http.delete(this.baseUrl+"departments/"+id);
  }

  create(data:any): Observable<any> {
    console.log("request data is "+ data);
    return this.http.post(this.baseUrl+"departments", data);
  }
  update(id:any, data:any): Observable<any> {
    return this.http.put(this.baseUrl+"departments/"+id, data);
  }
}
