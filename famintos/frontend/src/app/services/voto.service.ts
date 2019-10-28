import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Pessoa } from '../models/pessoa.model';
import { Restaurante } from '../models/restaurante.model';
import { take } from 'rxjs/operators';
import { ClassificacaoHoje } from '../models/classificacao-hoje.model';
import { ClassificacaoGeral } from '../models/classificacao-geral.model';

@Injectable()
export class VotoService {

  private URI = `${environment.API}/votacoes`;

  constructor(private http: HttpClient) { }

  votar(pessoa: Pessoa, restaurante: Restaurante) {
    return this.http.post(this.URI, {pessoa, restaurante}).pipe(take(1));
  }

  buscarClassificacaoDeHoje() {
    return this.http.get<ClassificacaoHoje[]>(`${this.URI}/classificacao/hoje`).pipe(take(1));
  }

  buascarPessoasVotantesPorRestaurante(restaurante: Restaurante) {
    return this.http.get<Pessoa[]>(`${this.URI}/votantes/${restaurante.id}`).pipe(take(1));
  }

  buscarClassificacaoGeral() {
    return this.http.get<ClassificacaoGeral[]>(`${this.URI}/classificacao/geral`).pipe(take(1));
  }

}
