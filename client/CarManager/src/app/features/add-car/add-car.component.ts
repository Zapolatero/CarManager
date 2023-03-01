import { Component } from '@angular/core';
import { CarService } from 'src/app/core/providers/car.service';
import { Car } from 'src/app/shared/models/Car';
import { Location } from '@angular/common';

@Component({
  selector: 'app-add-car',
  templateUrl: './add-car.component.html',
  styleUrls: ['./add-car.component.scss']
})
export class AddCarComponent {

  constructor(private readonly carService: CarService, private readonly location: Location){}

  onCancel(){
    this.goBack();
  }

  goBack(){
    this.location.back();
  }

  onSave(car: any){
    this.carService.postCar(car).subscribe( () => this.goBack());
  }

}
