import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'cars' },
  { path: 'cars',  loadChildren: () => import('./features/car-list/car-list.module').then (m => m.CarListModule)},
  { path: 'cars/:id',  loadChildren: () => import('./features/car-details/car-details.module').then (m => m.CarDetailsModule)},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
