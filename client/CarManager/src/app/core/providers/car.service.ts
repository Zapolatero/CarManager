import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Car } from 'src/app/shared/models/Car';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class CarService {

  constructor(private readonly httpClient: HttpClient) { }

  public getAllCars(): Observable<Array<Car>> {
    return this.httpClient.get<Array<Car>>(`${environment.carApiEndpoint}cars`);
  }

  getCar(id: string): Observable<Car>{
    return this.httpClient.get<Car>(`${environment.carApiEndpoint}cars/${id}`);
  }
}

export const carDetailsResolver: ResolveFn<Car> = 
  (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    return inject(CarService).getCar(route.paramMap.get('id')!);
  }
