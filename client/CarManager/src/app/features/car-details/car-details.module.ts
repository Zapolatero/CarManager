import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CarDetailsRoutingModule } from './car-details-routing.module';
import { CarDetailsComponent } from './car-details.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [CarDetailsComponent],
  imports: [
    SharedModule,
    CarDetailsRoutingModule
  ]
})
export class CarDetailsModule { }
