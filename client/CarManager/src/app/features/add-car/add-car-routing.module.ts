import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { carBrandsResolver } from 'src/app/core/providers/car.service';
import { AddCarComponent } from './add-car.component';

const routes: Routes = [
  { path: '', component: AddCarComponent, resolve: {brands: carBrandsResolver}}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AddCarRoutingModule { }
