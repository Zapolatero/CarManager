import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from './material.module';
import { CarService } from '../core/providers/car.service';



@NgModule({
  declarations: [],
  exports: [
    CommonModule,
    MaterialModule
  ],
  providers: [
    CarService
  ]
})
export class SharedModule { }
