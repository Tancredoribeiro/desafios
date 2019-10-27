import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-restaurante-crud-base',
  template: '',
  styleUrls: ['./restaurante-crud-base.component.css']
})
export class RestauranteCrudBaseComponent implements OnInit {

  restauranteForm: FormGroup;
  constructor(public fb: FormBuilder, public messageService: MessageService) { }

  ngOnInit() {
  }

  public iniciarRestauranteForm() {
    this.restauranteForm = this.fb.group({
      id: [null],
      nome: ['', [Validators.required, Validators.minLength(2)]],
      endereco: ['', Validators.required]
    });
  }

}
