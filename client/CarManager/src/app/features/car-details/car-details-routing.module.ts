import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { carDetailsResolver } from 'src/app/core/providers/car.service';
import { CarDetailsComponent } from './car-details.component';

const routes: Routes = [
  { path: '', component: CarDetailsComponent, resolve: {car: carDetailsResolver} }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CarDetailsRoutingModule { 

}
