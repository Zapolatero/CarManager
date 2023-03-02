import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Car } from '../../models/Car';

@Component({
  selector: 'app-car-form',
  templateUrl: './car-form.component.html',
  styleUrls: ['./car-form.component.scss']
})
export class CarFormComponent implements OnInit, OnChanges {
  carForm!: FormGroup<CarForm>;
  brands!: Array<string>;
  car!: Car;
  @Output() cancel: EventEmitter<void> = new EventEmitter();
  @Output() saveCar: EventEmitter<any> = new EventEmitter();

  constructor(private activatedRoute: ActivatedRoute){
    this.carForm = new FormGroup({
      id: new FormControl(),
      brand: new FormControl("", Validators.required),
      model: new FormControl("", [Validators.required, Validators.minLength(2)]),
      driverName: new FormControl("", [Validators.required, Validators.minLength(2)]),
      registration: new FormControl("", [Validators.required, Validators.minLength(5)]),
      circulationDate: new FormControl("", [Validators.required]),
      estimatedPrice: new FormControl(0, [Validators.required]),
    })
  }
  ngOnChanges(changes: SimpleChanges): void {
    const { car } = changes;
    if(car.currentValue !== car.previousValue){
      this.carForm.patchValue(this.car);
    }
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({brands}) => {
      this.brands = brands;
    });
    this.activatedRoute.data.subscribe(({car}) => {
      this.car = car;
      if (this.car){
        this.carForm.patchValue(car);
      }
    });
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
