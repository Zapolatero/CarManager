import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EditCarRoutingModule } from './edit-car-routing.module';
import { EditCarComponent } from './edit-car.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [
    EditCarComponent
  ],
  imports: [
    SharedModule,
    EditCarRoutingModule
  ]
})
export class EditCarModule { }
