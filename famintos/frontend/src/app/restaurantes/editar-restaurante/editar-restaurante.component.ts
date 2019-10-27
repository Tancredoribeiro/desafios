import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Location } from '@angular/common';

import { MessageService } from 'primeng/api';
import { FormUtils } from 'src/app/shared/utils/form-utils';
import { RestauranteCrudBaseComponent } from '../restaurante-crud-base/restaurante-crud-base.component';
import { Restaurante } from 'src/app/models/restaurante.model';
import { ActivatedRoute } from '@angular/router';
import { RestauranteService } from 'src/app/services/restaurante.service';

@Component({
  selector: 'app-editar-restaurante',
  templateUrl: './editar-restaurante.component.html',
  styleUrls: ['./editar-restaurante.component.css'],
  providers: [MessageService]
})
export class EditarRestauranteComponent extends RestauranteCrudBaseComponent implements OnInit {

  restauranteForm: FormGroup;

  constructor(
    public fb: FormBuilder,
    public messageService: MessageService,
    private restauranteService: RestauranteService,
    private location: Location,
    private route: ActivatedRoute) {
    super(fb, messageService);
  }

  ngOnInit() {
    this.iniciarRestauranteForm();

    this.route.params.subscribe(
      (params: any) => {
        const id = params['id'];
        console.log(id);
        const restaurante$ = this.restauranteService.carregarPorId(id);
        restaurante$.subscribe(restaurante => {
          this.popularDadosRestauranteForm(restaurante);
        });
      }
    );
  }


  submit(restauranteForm: FormGroup) {
    console.log('Form Restaurante evento emitido:', this.restauranteForm);
    if (this.restauranteForm.valid) {
      this.restauranteService.atualizar(this.restauranteForm.value).subscribe(
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

  popularDadosRestauranteForm(restaurante: Restaurante) {
    this.restauranteForm.patchValue({
      id: restaurante.id,
      nome: restaurante.nome,
      endereco: restaurante.endereco
    });
  }

}
