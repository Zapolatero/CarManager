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

  getCarBrands(): Observable<Array<string>>{
    return this.httpClient.get<Array<string>>(`${environment.carApiEndpoint}cars/brands`);
  }

  postCar(car: Car): Observable<void>{
    return this.httpClient.post<void>(`${environment.carApiEndpoint}cars`, car);
  }

  putCar(id: string, car: Car): Observable<void>{
    return this.httpClient.put<void>(`${environment.carApiEndpoint}cars/${id}`, car);
  }

  deleteCar(id: string): Observable<void> {
    return this.httpClient.delete<void>(`${environment.carApiEndpoint}cars/${id}`);
  }
}

export const carDetailsResolver: ResolveFn<Car> = 
  (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    return inject(CarService).getCar(route.paramMap.get('id')!);
}

export const carBrandsResolver: ResolveFn<Array<string>> = 
  (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    return inject(CarService).getCarBrands();
}
