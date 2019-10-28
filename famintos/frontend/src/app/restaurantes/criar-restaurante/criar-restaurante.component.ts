import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup} from '@angular/forms';
import { Location } from '@angular/common';

import { MessageService } from 'primeng/api';
import { FormUtils } from 'src/app/shared/utils/form-utils';
import { RestauranteCrudBaseComponent } from '../restaurante-crud-base/restaurante-crud-base.component';
import { RestauranteService } from 'src/app/services/restaurante.service';

@Component({
  selector: 'app-criar-restaurante',
  templateUrl: './criar-restaurante.component.html',
  styleUrls: ['./criar-restaurante.component.css'],
  providers: [MessageService, RestauranteService]
})
export class CriarRestauranteComponent extends RestauranteCrudBaseComponent implements OnInit {
  restauranteForm: FormGroup;

  constructor(public fb: FormBuilder,
              public messageService: MessageService,
              private restauranteService: RestauranteService,
              private location: Location, ) {
    super(fb, messageService);
  }

  ngOnInit() {
    this.iniciarRestauranteForm();
  }

  submit(restauranteForm: FormGroup) {
    console.log('Form Restaurante evento emitido:', this.restauranteForm);
    if (this.restauranteForm.valid) {
      this.restauranteService.criar(this.restauranteForm.value).subscribe(
        success => {
          this.messageService.add({ severity: 'info', summary: 'Successo', detail: 'Restaurante criado com sucesso.' });
          this.location.back();
        },
        error => this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Erro ao criar restaurante, tente novamente!' })
      );
    } else {
      FormUtils.validarCamposDoFormulario(this.restauranteForm);
      this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Por favor, corrija os erros abaixo.' });
    }
  }

  cancelar() {
    this.restauranteForm.reset();
    this.location.back();
  }

}
