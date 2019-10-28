import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { MessageService } from 'primeng/api';

import { PessoaService } from 'src/app/services/pessoa.service';
import { FormUtils } from 'src/app/shared/utils/form-utils';

@Component({
  selector: 'app-criar-pessoa',
  templateUrl: './criar-pessoa.component.html',
  styleUrls: ['./criar-pessoa.component.css'],
  providers: [MessageService]
})
export class CriarPessoaComponent implements OnInit {

  pessoaForm: FormGroup;

  constructor(private fb: FormBuilder,
              private messageService: MessageService,
              private pessoaService: PessoaService,
              private location: Location, ) {
  }

  ngOnInit() {
    this.pessoaForm = this.fb.group({
      id: [null],
      nome: ['', [Validators.required, Validators.minLength(2)]],
      userName: ['', [Validators.required, Validators.minLength(2)]],
      senha: ['', [Validators.required]]
    });
  }

  submit(pessoaForm: FormGroup) {
    console.log('Form Pessoa evento emitido:', this.pessoaForm);
    if (this.pessoaForm.valid) {
      this.pessoaService.criar(this.pessoaForm.value).subscribe(
        success => {
          this.messageService.add({ severity: 'info', summary: 'Successo', detail: 'Pessoa criado com sucesso.' });
        },
        error => this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Erro ao criar Pessoa, tente novamente!' })
      );
    } else {
      FormUtils.validarCamposDoFormulario(this.pessoaForm);
      this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Por favor, corrija os erros abaixo.' });
    }
  }

  cancelar() {
    this.pessoaForm.reset();
    this.location.back();
  }
}
