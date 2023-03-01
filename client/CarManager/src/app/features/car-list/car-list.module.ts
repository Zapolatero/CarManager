import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CarListRoutingModule } from './car-list-routing.module';
import { CarListComponent } from './car-list.component';
import { CarService } from 'src/providers/car.service';
import { MaterialModule } from 'src/app/shared/material.module';


@NgModule({
  declarations: [
    CarListComponent
  ],
  imports: [
    CommonModule,
    CarListRoutingModule,
    MaterialModule
  ],
  providers: [
    CarService
  ]
})
export class CarListModule { }
