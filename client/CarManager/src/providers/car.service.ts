import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Car } from 'src/models/Car';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable()
export class CarService {

  constructor(private readonly httpClient: HttpClient) { }

  public getAllCars(): Observable<Array<Car>> {
    return this.httpClient.get<Array<Car>>(`${environment.carApiEndpoint}cars`);
  }
}
