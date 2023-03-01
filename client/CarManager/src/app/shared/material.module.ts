import { NgModule } from '@angular/core';
import { MatListModule } from '@angular/material/list';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';



@NgModule({
  declarations: [],
  exports: [
    MatListModule,
    MatCardModule,
    MatDividerModule,
    MatButtonModule,
    MatIconModule
  ]
})
export class MaterialModule { }
