import { Component, OnInit } from '@angular/core';
import { EMPTY, map, Observable } from 'rxjs';
import { Car } from 'src/app/shared/models/Car';
import { CarService } from 'src/app/core/providers/car.service';

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.scss']
})
export class CarListComponent implements OnInit{
  cars$: Observable<Array<Car>> = EMPTY;

  constructor(private readonly carService: CarService){}

  ngOnInit(): void {
    this.refreshList();
  }
  

  private refreshList() {
    this.cars$ = this.carService.getAllCars().pipe(
      //order by brand
      map(res => res.sort((a, b) => a.brand < b.brand ? -1 : 1))
    );
  }

  deleteCar(id: string): void {
    this.carService.deleteCar(id).subscribe(() => this.refreshList());
  }

}
