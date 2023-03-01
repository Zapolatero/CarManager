import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from './material.module';
import { CarFormComponent } from './components/car-form/car-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatNativeDateModule } from '@angular/material/core';
import { CarService } from '../core/providers/car.service';



@NgModule({
  declarations: [
    CarFormComponent
  ],
  imports: [
    ReactiveFormsModule,
    MaterialModule,
    CommonModule,
    MatNativeDateModule
  ],
  exports: [
    CommonModule,
    MaterialModule,
    CarFormComponent
  ],
  providers: [
    CarService
  ]
})
export class SharedModule { }
