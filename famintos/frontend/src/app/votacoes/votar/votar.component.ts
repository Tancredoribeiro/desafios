import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';


import { Observable, empty } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { MessageService, ConfirmationService } from 'primeng/api';

import { Restaurante } from 'src/app/models/restaurante.model';
import { RestauranteService } from 'src/app/services/restaurante.service';


@Component({
  selector: 'app-votar',
  templateUrl: './votar.component.html',
  styleUrls: ['./votar.component.css'],
  providers: [MessageService, ConfirmationService]
})
export class VotarComponent implements OnInit {

  restaurantes$: Observable<Restaurante[]>;

  constructor(
    private restauranteService: RestauranteService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.carregarRestaurantes();
  }

  carregarRestaurantes() {
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
