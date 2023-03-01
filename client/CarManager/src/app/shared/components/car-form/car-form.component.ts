import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Car } from '../../models/Car';

@Component({
  selector: 'app-car-form',
  templateUrl: './car-form.component.html',
  styleUrls: ['./car-form.component.scss']
})
export class CarFormComponent implements OnInit {
  carForm!: FormGroup<CarForm>;
  brands!: Array<string>;
  @Output() cancel: EventEmitter<void> = new EventEmitter();
  @Output() saveCar: EventEmitter<any> = new EventEmitter();

  constructor(private activatedRoute: ActivatedRoute){
    this.carForm = new FormGroup({
      id: new FormControl('0'),
      brand: new FormControl("", Validators.required),
      model: new FormControl("", [Validators.required, Validators.minLength(2)]),
      driverName: new FormControl("", [Validators.required, Validators.minLength(2)]),
      registration: new FormControl("", [Validators.required, Validators.minLength(5)]),
      circulationDate: new FormControl("", [Validators.required]),
      estimatedPrice: new FormControl(0, [Validators.required]),
    })
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({brands}) => {
      this.brands = brands;
    })
  }

  onCancel(){
    this.cancel.emit();
  }
  onSave(){
    this.saveCar.emit(this.carForm.getRawValue());
  }
}

interface CarForm{
  id: FormControl<string | null>
  brand: FormControl<string | null>
  model: FormControl<string | null>
  driverName: FormControl<string | null>
  registration: FormControl<string | null>
  circulationDate: FormControl<string | null>
  estimatedPrice: FormControl<number | null>
}
