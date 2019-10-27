import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { take } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { Restaurante } from '../models/restaurante.model';

@Injectable()
export class RestauranteService {

  constructor(private http: HttpClient) { }

  private URI = `${environment.API}/restaurantes`;




  buscarTodosRestaurantes() {
    return this.http.get<Restaurante[]>(this.URI).pipe(take(1));
  }

  carregarPorId(id: any) {
    return this.http.get<Restaurante>(`${this.URI}/${id}`).pipe(take(1));
  }

  criar(restaurante: Restaurante) {
    return this.http.post(this.URI, restaurante).pipe(take(1));
  }

  atualizar(restaurante: Restaurante) {
    return this.http.put(`${this.URI}/${restaurante.id}`, restaurante).pipe(take(1));
  }
  excluir(id) {
    return this.http.delete(`${this.URI}/${id}`).pipe(take(1));
  }
}
