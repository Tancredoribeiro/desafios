import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { Observable, empty } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { MessageService } from 'primeng/api';
import { ConfirmationService } from 'primeng/api';

import { RestauranteService } from '../../services/restaurante.service';
import { Restaurante } from '../../models/restaurante.model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { PessoaService } from 'src/app/services/pessoa.service';
import { VotoService } from 'src/app/services/voto.service';
import { Pessoa } from 'src/app/models/pessoa.model';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-listar-restaurante',
  templateUrl: './listar-restaurante.component.html',
  styleUrls: ['./listar-restaurante.component.css'],
  providers: [MessageService, ConfirmationService, RestauranteService, VotoService]
})
export class ListarRestauranteComponent implements OnInit {

  restaurantes$: Observable<Restaurante[]>;
  mostrarDialogoConfirmacao = false;
  restauranteSelecionado: Restaurante;

  formularioPessoa: FormGroup;

  constructor(
    private restauranteService: RestauranteService,
    private pessoaService: PessoaService,
    private votoService: VotoService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    this.recarregar();
    this.formularioPessoa = this.fb.group({
      userName: ['', Validators.required],
      senha: ['', Validators.required]
    });
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
    const dados = formulario.value;
    this.pessoaService.validar(dados).subscribe(
      (pessoa: Pessoa) => {
        this.votar(pessoa);
      },
      erro => this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Uusuário ou senha inválidos, tente novamente!' })
    );


  }

  pepararVoto(restaurante: Restaurante) {
    this.mostrarDialogoConfirmacao = true;
    this.restauranteSelecionado = restaurante;
  }

  votar(pessoa: Pessoa) {
    this.votoService.votar(pessoa, this.restauranteSelecionado).subscribe(
      sucesso => {
        this.messageService.add({ severity: 'info', summary: 'Successo', detail: 'Voto registrado com sucesso.' });
        this.restauranteSelecionado = undefined;
        this.mostrarDialogoConfirmacao = false;
        this.formularioPessoa.reset()
        this.recarregar();
      },
      (erro: HttpErrorResponse) => {
        console.log('Corpo da resposta:', erro.error);
        this.messageService.add({ severity: 'error', summary: 'Erro', detail: erro.error.message });
      }
    );
  }

  cancelarVoto() {
    this.mostrarDialogoConfirmacao = false;
    this.restauranteSelecionado = undefined;
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
    this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Erro ao buscar restaurantes.' });
  }

}
