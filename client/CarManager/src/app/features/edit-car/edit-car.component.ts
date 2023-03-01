import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CarService } from 'src/app/core/providers/car.service';
import { Car } from 'src/app/shared/models/Car';
import { Location } from '@angular/common';

@Component({
  selector: 'app-edit-car',
  templateUrl: './edit-car.component.html',
  styleUrls: ['./edit-car.component.scss']
})
export class EditCarComponent implements OnInit{
  car!: Car;
  
  constructor(private activatedRoute: ActivatedRoute, private readonly carService: CarService, private readonly location: Location) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({car}) => {
      console.info(car)
      this.car = car;
    })
  }

  onCancel(){
    this.goBack();
  }

  goBack(){
    this.location.back();
  }

  onSave(car: any){
    this.carService.putCar(car.id, car).subscribe( () => this.goBack());
  }

}
