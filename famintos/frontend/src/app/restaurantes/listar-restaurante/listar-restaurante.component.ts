import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { Observable, empty } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { MessageService } from 'primeng/api';
import { ConfirmationService } from 'primeng/api';

import { RestauranteService } from '../../services/restaurante.service';
import { Restaurante } from '../../models/restaurante.model';
import { FormGroup } from '@angular/forms';
import { PessoaService } from 'src/app/services/pessoa.service';

@Component({
  selector: 'app-listar-restaurante',
  templateUrl: './listar-restaurante.component.html',
  styleUrls: ['./listar-restaurante.component.css'],
  providers: [MessageService, ConfirmationService]
})
export class ListarRestauranteComponent implements OnInit {

  restaurantes$: Observable<Restaurante[]>;
  mostrarDialogoConfirmacao = false;

  constructor(
    private restauranteService: RestauranteService,
    private pessoaService: PessoaService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.recarregar();
  }

  editar(id) {
    this.router.navigate(['editar', id], { relativeTo: this.route });
  }

  criarNovo() {
    this.router.navigate(['criar'], { relativeTo: this.route });
  }

  excluir(restaurante: Restaurante) {
    this.restauranteService.excluir(restaurante.id).subscribe(
      success => {
        this.messageService.add({ severity: 'info', summary: 'Successo', detail: 'Restaurante criado com sucesso.' });
        this.recarregar();
      },
      error => this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Erro ao criar restaurante, tente novamente!' })
    );
  }

  confirmarExclusao(restaurante: Restaurante) {
    this.confirmationService.confirm({
      header: 'Confirmação',
      message: `Tem certeza que deseja excluir o restaurante ${restaurante.nome} ?`,
      accept: () => {
        this.excluir(restaurante);
      }
    });
  }

  enviarConfirmacaoPessoa(formulario: FormGroup) {
    let dados = formulario.value;
    this.pessoaService.validar(dados).subscribe(
      success => {
      this.votar()
      },
      error => this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Erro ao criar restaurante, tente novamente!' })
    );


  }

  votar(restaurante: Restaurante) {
    this.mostrarDialogoConfirmacao = true;
  }

  recarregar() {
    this.restaurantes$ = this.restauranteService.buscarTodosRestaurantes().pipe(
      catchError(error => {
        console.error(error);
        this.mostrarErro();
        return empty();
      })
    );
  }

  mostrarErro() {
    this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Por favor, corrija os erros abaixo.' });
  }

}
