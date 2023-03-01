import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { carBrandsResolver, carDetailsResolver } from 'src/app/core/providers/car.service';
import { EditCarComponent } from './edit-car.component';

const routes: Routes = [
  {path: '', component: EditCarComponent, resolve:{car: carDetailsResolver, brands: carBrandsResolver}}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EditCarRoutingModule { }
