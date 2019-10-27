import { Component, OnInit } from '@angular/core';
import { Observable, empty } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ClassificacaoHoje } from 'src/app/models/classificacao-hoje.model';
import { Restaurante } from 'src/app/models/restaurante.model';
import { RestauranteService } from 'src/app/services/restaurante.service';
import { PessoaService } from 'src/app/services/pessoa.service';
import { VotoService } from 'src/app/services/voto.service';
import { MessageService, ConfirmationService } from 'primeng/api';
import { Pessoa } from 'src/app/models/pessoa.model';
import { faTrophy } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-classificacao',
  templateUrl: './classificacao.component.html',
  styleUrls: ['./classificacao.component.css'],
  providers: [MessageService, ConfirmationService]
})
export class ClassificacaoComponent implements OnInit {

  classificacoes$: Observable<ClassificacaoHoje[]>;
  votantes$: Observable<Pessoa[]>;
  mostrarDialogoVotantes = false;
  restauranteSelecionado: Restaurante;
  faTrophy = faTrophy;
  constructor(
    private restauranteService: RestauranteService,
    private pessoaService: PessoaService,
    private votoService: VotoService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) { }

  ngOnInit() {

    this.classificacoes$ = this.votoService.buscarClassificacaoDeHoje().pipe(
      catchError(error => {
        console.error(error);
        this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Erro ao buscar classificação.' });
        return empty();
      })
    );
    console.log('Classificaçoes' + this.classificacoes$);
  }

  verVotos(restaurante: Restaurante) {
    this.restauranteSelecionado = restaurante;
    this.votantes$ = this.votoService.buascarPessoasVotantesPorRestaurante(restaurante).pipe(
      catchError(error => {
        console.error(error);
        this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Erro ao buscar classificação.' });
        return empty();
      })
    );
    this.votantes$.subscribe(
      values => this.mostrarDialogoVotantes = true
  );
  }
}
